/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.tests.refactoring;

import static com.google.common.collect.Lists.*;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.text.edits.ReplaceEdit;
import org.eclipse.text.edits.TextEdit;
import org.eclipse.xtext.junit.AbstractXtextTests;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.refactoring.IRefactoringUpdateAcceptor;
import org.eclipse.xtext.ui.refactoring.IRenameStrategy;
import org.eclipse.xtext.ui.refactoring.impl.IRefactoringDocument;
import org.eclipse.xtext.ui.tests.refactoring.refactoring.Element;

import com.google.inject.Inject;

/**
 * @author Jan Koehnlein - Initial contribution and API
 */
@SuppressWarnings("restriction")
public class DefaultRenameElementStrategyTest extends AbstractXtextTests implements IRefactoringUpdateAcceptor {

	@Inject
	private IRenameStrategy.Provider strategyProvider;
	
	private List<TextEdit> textEdits = newArrayList();
	private List<Change> changes = newArrayList();
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		with(RefactoringTestLanguageStandaloneSetup.class);
		getInjector().injectMembers(this);
	}
	
	public void testRenameElementStrategy() throws Exception {
		final XtextResource resource = getResourceFromString("A { B { C } }");
		EObject targetElement = resource.getContents().get(0).eContents().get(0);
		assertNotNull(targetElement);
		assertTrue(targetElement instanceof Element);
		assertEquals("A", ((Element) targetElement).getName());
		IRenameStrategy renameElementStrategy = strategyProvider.get(targetElement, null);
		assertNotNull(renameElementStrategy);
		assertEquals("A", renameElementStrategy.getOriginalName());
		RefactoringStatus validateNewNameStatus = renameElementStrategy.validateNewName("A");
		assertTrue(validateNewNameStatus.hasWarning());
		assertFalse(validateNewNameStatus.hasError());
		validateNewNameStatus = renameElementStrategy.validateNewName("D");
		assertTrue(validateNewNameStatus.isOK());
		renameElementStrategy.applyDeclarationChange("D", resource.getResourceSet());
		assertEquals("D", ((Element) targetElement).getName());
		renameElementStrategy.createDeclarationUpdates("D", resource.getResourceSet(), this);
		assertEquals(0, changes.size());
		assertEquals(1, textEdits.size());
		assertTrue(textEdits.get(0) instanceof ReplaceEdit);
		ReplaceEdit renameEdit = (ReplaceEdit) textEdits.get(0);
		assertEquals(0, renameEdit.getOffset());
		assertEquals(1, renameEdit.getLength());
		assertEquals("D", renameEdit.getText());
	}

	public void accept(URI resourceURI, TextEdit textEdit) {
		textEdits.add(textEdit);		
	}

	public void accept(URI resourceURI, Change change) {
		changes.add(change);
	}

	public RefactoringStatus getRefactoringStatus() {
		return null;
	}

	public IRefactoringDocument getDocument(URI resourceURI) {
		return null;
	}

	public Change createCompositeChange(String name, IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		return null;
	}
}
