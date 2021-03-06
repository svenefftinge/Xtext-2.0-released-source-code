/*******************************************************************************
 * Copyright (c) 2008 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.eclipse.xtext.ui.editor.model;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPositionCategoryException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.model.IXtextDocumentContentObserver.Processor;
import org.eclipse.xtext.ui.editor.model.edit.ITextEditComposer;
import org.eclipse.xtext.ui.editor.model.edit.ReconcilingUnitOfWork;
import org.eclipse.xtext.util.concurrent.AbstractReadWriteAcces;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.google.inject.Inject;

/**
 * @author Sven Efftinge - Initial contribution and API
 * @author Michael Clay
 * @author Jan Koehnlein
 */
public class XtextDocument extends Document implements IXtextDocument {

	private DocumentTokenSource tokenSource;

	private ITextEditComposer composer;

	@Inject
	public XtextDocument(DocumentTokenSource tokenSource, ITextEditComposer composer) {
		this.tokenSource = tokenSource;
		tokenSource.computeDamageRegion(new DocumentEvent(this, 0, getLength(), this.get()));
		this.composer = composer;
	}

	private XtextResource resource = null;
	private final ListenerList modelListeners = new ListenerList(ListenerList.IDENTITY);
	private final ListenerList xtextDocumentObservers = new ListenerList(ListenerList.IDENTITY);

	public void setInput(XtextResource resource) {
		Assert.isNotNull(resource);
		this.resource = resource;
	}

	public void disposeInput() {
		if (resource != null) {
			ResourceSet resourceSet = resource.getResourceSet();
			if (resourceSet != null) {
				resourceSet.getResources().clear();
				resourceSet.eAdapters().clear();
			}
			resource = null;
		}
	}

	private final XtextDocumentLocker stateAccess = createDocumentLocker();

	protected XtextDocumentLocker createDocumentLocker() {
		return new XtextDocumentLocker();
	}

	public <T> T readOnly(IUnitOfWork<T, XtextResource> work) {
		return stateAccess.readOnly(work);
	}

	public <T> T modify(IUnitOfWork<T, XtextResource> work) {
		IUnitOfWork<T, XtextResource> reconcilingUnitOfWork = new ReconcilingUnitOfWork<T>(work, this, composer);
		return internalModify(reconcilingUnitOfWork);
	}

	/**
	 * Modifies the document's semantic model without reconciling the text nor the node model. For internal use only.
	 */
	public <T> T internalModify(IUnitOfWork<T, XtextResource> work) {
		return stateAccess.modify(work);
	}

	protected void ensureThatStateIsNotReturned(Object exec, IUnitOfWork<?, XtextResource> uow) {
		// TODO activate
		// if (exec instanceof EObject) {
		// if (((EObject) exec).eResource() == resource
		// || ((exec instanceof AbstractNode) &&
		// NodeUtil.getNearestSemanticObject((AbstractNode) exec)
		// .eResource() == resource))
		// throw new IllegalStateException(
		// "The unit of work returned state from the resource. This is causing race condition problems and therefore not allowed! "+uow.getClass().getName());
		// }
	}

	public void addModelListener(IXtextModelListener listener) {
		Assert.isNotNull(listener);
		modelListeners.add(listener);
	}

	public void removeModelListener(IXtextModelListener listener) {
		Assert.isNotNull(listener);
		modelListeners.remove(listener);
	}

	protected void notifyModelListeners(XtextResource res) {
		Object[] listeners = modelListeners.getListeners();
		for (int i = 0; i < listeners.length; i++) {
			((IXtextModelListener) listeners[i]).modelChanged(res);
		}
	}

	public void addXtextDocumentContentObserver(IXtextDocumentContentObserver observer) {
		addDocumentListener(observer);
		xtextDocumentObservers.add(observer);
	}

	public void removeXtextDocumentContentObserver(IXtextDocumentContentObserver observer) {
		xtextDocumentObservers.remove(observer);
		removeDocumentListener(observer);
	}

	protected <T> void updateContentBeforeRead() {
		Object[] listeners = xtextDocumentObservers.getListeners();
		for (int i = 0; i < listeners.length; i++) {
			((IXtextDocumentContentObserver) listeners[i]).performNecessaryUpdates(stateAccess);
		}
	}

	/**
	 * @author Sven Efftinge - Initial contribution and API
	 * 
	 */
	protected class XtextDocumentLocker extends AbstractReadWriteAcces<XtextResource> implements Processor {
		@Override
		protected XtextResource getState() {
			return resource;
		}

		@Override
		protected void beforeReadOnly(XtextResource res, IUnitOfWork<?, XtextResource> work) {
			if (log.isDebugEnabled())
				log.debug("read - " + Thread.currentThread().getName());
			// don't updateContent on reentrant read lock request
			if (rwLock.getReadLockCount() == 1)
				updateContentBeforeRead();
		}

		@Override
		protected void beforeModify(XtextResource state, IUnitOfWork<?, XtextResource> work) {
			if (log.isDebugEnabled())
				log.debug("write - " + Thread.currentThread().getName());
		}

		@Override
		protected void afterReadOnly(XtextResource res, Object result, IUnitOfWork<?, XtextResource> work) {
			ensureThatStateIsNotReturned(result, work);
		}

		@Override
		protected void afterModify(XtextResource res, Object result, IUnitOfWork<?, XtextResource> work) {
			ensureThatStateIsNotReturned(result, work);
			notifyModelListeners(resource);
		}

		@Override
		public <T> T modify(IUnitOfWork<T, XtextResource> work) {
			try {
				if (validationJob!=null) {
					validationJob.cancel();
				}
				return super.modify(work);
			} catch (RuntimeException e) {
				try {
					XtextResource state = getState();
					if (state != null)
						state.reparse(get());
				} catch (IOException ioe) {
				}
				throw e;
			} finally {
				checkAndUpdateAnnotations();
			}
		}

		public <T> T process(IUnitOfWork<T, XtextResource> transaction) {
			if (transaction != null) {
				readLock.unlock();
				writeLock.lock();
				try {
					if (log.isDebugEnabled())
						log.debug("process - " + Thread.currentThread().getName());
					return modify(transaction);
				} finally {
					readLock.lock();
					writeLock.unlock();
				}
			}
			return null;
		}
	}

	private static final Logger log = Logger.getLogger(XtextDocument.class);

	private transient Job validationJob;

	public void setValidationJob(Job validationJob) {
		this.validationJob = validationJob;
	}

	public Job getValidationJob() {
		return validationJob;
	}

	public void checkAndUpdateAnnotations() {
		if (validationJob!=null) {
			validationJob.cancel();
			validationJob.schedule();
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T getAdapter(Class<T> adapterType) {
		URI uri = resource.getURI();
		if ((adapterType == IFile.class || adapterType == IResource.class) && uri.isPlatformResource()) {
			return (T) ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(uri.toPlatformString(true)));
		}
		return null;
	}

	/*
	 * fix for https://bugs.eclipse.org/bugs/show_bug.cgi?id=297946
	 */
	private ReadWriteLock positionsLock = new ReentrantReadWriteLock();
	private Lock positionsReadLock = positionsLock.readLock();
	private Lock positionsWriteLock = positionsLock.writeLock();

	@Override
	public Position[] getPositions(String category, int offset, int length, boolean canStartBefore, boolean canEndAfter)
			throws BadPositionCategoryException {
		positionsReadLock.lock();
		try {
			return super.getPositions(category, offset, length, canStartBefore, canEndAfter);
		} finally {
			positionsReadLock.unlock();
		}
	}

	@Override
	public Position[] getPositions(String category) throws BadPositionCategoryException {
		positionsReadLock.lock();
		try {
			return super.getPositions(category);
		} finally {
			positionsReadLock.unlock();
		}
	}

	@Override
	public void addPosition(Position position) throws BadLocationException {
		positionsWriteLock.lock();
		try {
			super.addPosition(position);
		} finally {
			positionsWriteLock.unlock();
		}
	}

	@Override
	public void addPosition(String category, Position position) throws BadLocationException,
			BadPositionCategoryException {
		positionsWriteLock.lock();
		try {
			super.addPosition(category, position);
		} finally {
			positionsWriteLock.unlock();
		}
	}

	@Override
	public void removePosition(Position position) {
		positionsWriteLock.lock();
		try {
			super.removePosition(position);
		} finally {
			positionsWriteLock.unlock();
		}
	}

	@Override
	public void removePosition(String category, Position position) throws BadPositionCategoryException {
		positionsWriteLock.lock();
		try {
			super.removePosition(category, position);
		} finally {
			positionsWriteLock.unlock();
		}
	}

	@Override
	protected void fireDocumentChanged(DocumentEvent event) {
		tokenSource.updateStructure(event);
		super.fireDocumentChanged(event);
	}

	public IRegion getLastDamage() {
		return tokenSource.getLastDamagedRegion();
	}

	public Iterable<ILexerTokenRegion> getTokens() {
		return tokenSource.getTokenInfos();
	}

}
