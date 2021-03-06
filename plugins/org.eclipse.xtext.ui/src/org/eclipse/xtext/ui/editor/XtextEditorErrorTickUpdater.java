/*******************************************************************************
 * Copyright (c) 2010 Michael Clay and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.eclipse.xtext.ui.editor;

import java.util.Iterator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.IAnnotationModelListener;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.progress.UIJob;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.ui.IImageHelper;
import org.eclipse.xtext.ui.internal.XtextPluginImages;
import org.eclipse.xtext.ui.util.IssueUtil;
import org.eclipse.xtext.validation.Issue;

import com.google.inject.Inject;

/**
 * @author Michael Clay - Initial contribution and API
 * @author Sven Efftinge
 */
public class XtextEditorErrorTickUpdater extends IXtextEditorCallback.NullImpl implements IAnnotationModelListener {
	private static final ISchedulingRule SEQUENCE_RULE = SchedulingRuleFactory.INSTANCE.newSequence();
	@Inject
	private IImageHelper imageHelper;
	@Inject
	private IssueUtil issueUtil;
	private Image defaultImage;
	private Severity previousSeverity = null;
	private XtextEditor editor;
	private IAnnotationModel annotationModel;
	private UpdateEditorImageJob updateEditorImageJob;

	@Override
	public void beforeDispose(XtextEditor xtextEditor) {
		unregisterListener();
		if (this.editor != null) {
			if (defaultImage != null && !defaultImage.isDisposed())
				editor.updatedTitleImage(defaultImage); // otherwise we'll leak the defaultImage
			this.editor = null;
		}
	}

	@Override
	public void afterSetInput(XtextEditor xtextEditor) {
		if (this.editor != null) {
			unregisterListener();
			updateImageAndRegisterListener();
		}
	}

	@Override
	public void afterCreatePartControl(XtextEditor xtextEditor) {
		editor = xtextEditor;
		updateImageAndRegisterListener();
	}

	protected void unregisterListener() {
		if (annotationModel != null) {
			annotationModel.removeAnnotationModelListener(this);
			annotationModel = null;
		}
	}

	protected void updateImageAndRegisterListener() {
		defaultImage = editor.getDefaultImage();
		updateEditorImage(editor);
		annotationModel = editor.getInternalSourceViewer().getAnnotationModel();
		if (annotationModel != null)
			annotationModel.addAnnotationModelListener(this);
	}

	protected void updateEditorImage(XtextEditor xtextEditor) {
		Severity severity = getSeverity(xtextEditor);
		if (severity == previousSeverity)
			return;
		previousSeverity = severity;
		if (severity != null && severity != Severity.INFO) {
			ImageDescriptor descriptor = severity == Severity.ERROR ? XtextPluginImages.DESC_OVR_ERROR
					: XtextPluginImages.DESC_OVR_WARNING;
			DecorationOverlayIcon decorationOverlayIcon = new DecorationOverlayIcon(defaultImage, descriptor,
					IDecoration.BOTTOM_LEFT);
			Image decoratedImage = imageHelper.getImage(decorationOverlayIcon);
			if (xtextEditor.getTitleImage() != decoratedImage) {
				scheduleUpdateEditorJob(decoratedImage);
			}
		} else {
			scheduleUpdateEditorJob(defaultImage);
		}
	}

	@SuppressWarnings("unchecked")
	protected Severity getSeverity(XtextEditor xtextEditor) {
		if (xtextEditor == null || xtextEditor.getInternalSourceViewer() == null)
			return null;
		IAnnotationModel model = xtextEditor.getInternalSourceViewer().getAnnotationModel();
		if (model != null) {
			Iterator<Annotation> iterator = model.getAnnotationIterator();
			boolean hasWarnings = false;
			boolean hasInfos = false;
			while (iterator.hasNext()) {
				Annotation annotation = iterator.next();
				if (!annotation.isMarkedDeleted()) {
					Issue issue = issueUtil.getIssueFromAnnotation(annotation);
					if (issue != null) {
						if (issue.getSeverity() == Severity.ERROR) {
							return Severity.ERROR;
						} else if (issue.getSeverity() == Severity.WARNING) {
							hasWarnings = true;
						} else if (issue.getSeverity() == Severity.INFO) {
							hasInfos = true;
						}
					}
				}
			}
			if (hasWarnings)
				return Severity.WARNING;
			if (hasInfos)
				return Severity.INFO;
		}
		return null;
	}

	public void scheduleUpdateEditorJob(final Image image) {
		UpdateEditorImageJob job = updateEditorImageJob;
		if (job == null) {
			job = createUpdateEditorImageJob();
			updateEditorImageJob = job;
		}
		job.scheduleFor(image);
	}

	protected UpdateEditorImageJob createUpdateEditorImageJob() {
		// reason described here https://bugs.eclipse.org/bugs/show_bug.cgi?id=308963
		return new UpdateEditorImageJob(SEQUENCE_RULE);
	}
	
	public void modelChanged(IAnnotationModel model) {
		updateEditorImage(editor);
	}

	protected class UpdateEditorImageJob extends UIJob {
		private volatile Image titleImage;

		public UpdateEditorImageJob(ISchedulingRule schedulingRule) {
			super(Messages.XtextEditorErrorTickUpdater_JobName);
			setRule(schedulingRule);
		}

		@Override
		public IStatus runInUIThread(final IProgressMonitor monitor) {
			IEditorSite site = null != editor ? editor.getEditorSite() : null;
			if (site != null) {
				if (!monitor.isCanceled() && titleImage != null && !titleImage.isDisposed()
					&& editor != null) {
					editor.updatedTitleImage(titleImage);
				}
			}
			return Status.OK_STATUS;
		}

		protected void scheduleFor(Image image) {
			cancel();
			this.titleImage = image;
			schedule();
		}
	}
}
