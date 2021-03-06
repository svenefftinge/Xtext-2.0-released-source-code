/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xtext.ui.editor.hyperlinking;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.ISetup;
import org.eclipse.xtext.XtextRuntimeModule;
import org.eclipse.xtext.XtextStandaloneSetup;
import org.eclipse.xtext.junit.AbstractXtextTests;
import org.eclipse.xtext.resource.ClasspathUriUtil;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.hyperlinking.IHyperlinkHelper;
import org.eclipse.xtext.ui.editor.hyperlinking.XtextHyperlink;
import org.eclipse.xtext.ui.shared.SharedStateModule;
import org.eclipse.xtext.util.Modules2;
import org.eclipse.xtext.xtext.ui.Activator;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class HyperlinkHelperTest extends AbstractXtextTests {

	private ISetup getSetup() {
		return new XtextStandaloneSetup() {
			@Override
			public Injector createInjector() {
				return Guice.createInjector(Modules2.mixin(new XtextRuntimeModule(), new org.eclipse.xtext.ui.XtextUiModule(Activator.getDefault()), new SharedStateModule()));
			}
		};
	}

	private IHyperlinkHelper helper;
	private XtextResource resource;
	private Grammar grammar;
	private Grammar terminalGrammar;
	private String model;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		with(getSetup());
		helper = get(IHyperlinkHelper.class);
		model = "grammar org.eclipse.xtext.ui.HyperlinkTest with org.eclipse.xtext.common.Terminals\n" +
				"generate hyperlinkTest 'http://www.eclipse.org/Xtext/2008/HyperlinkTest'\n" +
				"import '" + EcorePackage.eINSTANCE.getNsURI() + "' as ecore\n" +
				"Model: name=STRING elementRefs+=[Element]* objectRefs+=[ecore::EObject]* elements+=Element*;" +
				"Element: 'element' name=ID;"+
				"terminal ID: 'foo';" +
				"terminal STRING returns ecore::EString: 'bar';";
		resource = getResourceFromString(model);
		grammar = (Grammar) resource.getContents().get(0);
		terminalGrammar = grammar.getUsedGrammars().get(0);
		terminalGrammar.eResource().unload();
	}

	@Override
	protected void tearDown() throws Exception {
		resource = null;
		helper = null;
		grammar = null;
		terminalGrammar = null;
		model = null;
		super.tearDown();
	}

	public void testSetup() {
		assertTrue(resource.getErrors().isEmpty());
		assertNull(terminalGrammar.eResource());
		assertTrue(terminalGrammar.eIsProxy());
		assertNotNull(helper);
	}

	public void testCreateHyperlinksByOffset_01() {
		IHyperlink[] links = helper.createHyperlinksByOffset(resource, model.indexOf("common.Terminals"), true);
		assertNotNull(links);
		assertEquals(1, links.length);
		assertTrue(links[0] instanceof XtextHyperlink);
		assertEquals(model.indexOf("org.eclipse.xtext.common.Terminals"), links[0].getHyperlinkRegion().getOffset());
		assertEquals("org.eclipse.xtext.common.Terminals".length(), links[0].getHyperlinkRegion().getLength());
		checkHyperlink((XtextHyperlink) links[0]);
	}
	
	public void testCreateHyperlinksByOffset_02() {
		IHyperlink[] links = helper.createHyperlinksByOffset(resource, model.indexOf("Model") + 1, true);
		assertNull(links);
	}
	
	public void testCreateHyperlinksByOffset_03() {
		IHyperlink[] links = helper.createHyperlinksByOffset(resource, model.indexOf("terminal ID") + "terminal I".length(), true);
		assertNotNull(links);
		assertEquals(2, links.length);
		assertTrue(links[0] instanceof XtextHyperlink);
		XtextHyperlink hyperLink = (XtextHyperlink) links[0];
		assertEquals(model.indexOf("ID:"), hyperLink.getHyperlinkRegion().getOffset());
		assertEquals("ID".length(), hyperLink.getHyperlinkRegion().getLength());
		assertEquals(
				grammar.eResource().getResourceSet().getURIConverter().normalize(
				EcoreUtil.getURI(GrammarUtil.findRuleForName(grammar.getUsedGrammars().get(0), "ID"))), hyperLink.getURI());
		assertTrue(links[1] instanceof XtextHyperlink);
		hyperLink = (XtextHyperlink) links[1];
		assertEquals(model.indexOf("ID:"), hyperLink.getHyperlinkRegion().getOffset());
		assertEquals("ID".length(), hyperLink.getHyperlinkRegion().getLength());
		assertEquals(URI.createURI(EcorePackage.eINSTANCE.getNsURI()).appendFragment("//EString"), hyperLink.getURI());
	}
	
	public void testCreateHyperlinksByOffset_04() {
		IHyperlink[] links = helper.createHyperlinksByOffset(resource, model.indexOf("TRING r"), true);
		assertNotNull(links);
		assertEquals(1, links.length);
		assertTrue(links[0] instanceof XtextHyperlink);
		XtextHyperlink hyperLink = (XtextHyperlink) links[0];
		assertEquals(model.indexOf("STRING r"), hyperLink.getHyperlinkRegion().getOffset());
		assertEquals("STRING".length(), hyperLink.getHyperlinkRegion().getLength());
		assertEquals(
				grammar.eResource().getResourceSet().getURIConverter().normalize(
				EcoreUtil.getURI(GrammarUtil.findRuleForName(grammar.getUsedGrammars().get(0), "STRING"))), hyperLink.getURI());
	}

	public void testCreateHyperlinksByOffset_05() {
		assertNull(helper.createHyperlinksByOffset(resource, model.indexOf("[ecore::EObject]"), true));
		assertNull(helper.createHyperlinksByOffset(resource, model.indexOf("[ecore::EObject]") + "[ecore::EObject".length(), true));
		IHyperlink[] hyperlinks = helper.createHyperlinksByOffset(resource, model.indexOf("core::EObject]"), true);
		assertNotNull(hyperlinks);
		assertEquals(1, hyperlinks.length);
		assertTrue(hyperlinks[0] instanceof XtextHyperlink);
		XtextHyperlink hyperLink = (XtextHyperlink) hyperlinks[0];
		assertEquals("ecore".length(), hyperLink.getHyperlinkRegion().getLength());
	}

	public void testCreateHyperlinksByOffset_06() {
		assertNull(helper.createHyperlinksByOffset(resource, model.indexOf("::EObject]"), true));
		assertNull(helper.createHyperlinksByOffset(resource, model.indexOf(":EObject]"), true));
		assertNull(helper.createHyperlinksByOffset(resource, model.indexOf("EObject]") + "EObject".length(), true));
		IHyperlink[] hyperlinks = helper.createHyperlinksByOffset(resource, model.indexOf("Object]"), true);
		assertNotNull(hyperlinks);
		assertEquals(1, hyperlinks.length);
		assertTrue(hyperlinks[0] instanceof XtextHyperlink);
		XtextHyperlink hyperLink = (XtextHyperlink) hyperlinks[0];
		assertEquals("EObject".length(), hyperLink.getHyperlinkRegion().getLength());
		assertEquals(
				grammar.eResource().getResourceSet().getURIConverter().normalize(
				EcoreUtil.getURI(EcorePackage.eINSTANCE.getEObject())), hyperLink.getURI());

	}

	public void testCreateHyperlinksByOffset_07() {
		assertNull(helper.createHyperlinksByOffset(resource, model.indexOf("Element*") - 1, true));
		IHyperlink[] hyperlinks = helper.createHyperlinksByOffset(resource, model.indexOf("lement*"), true);
		assertNotNull(hyperlinks);
		assertEquals(1, hyperlinks.length);
		assertTrue(hyperlinks[0] instanceof XtextHyperlink);
		XtextHyperlink hyperLink = (XtextHyperlink) hyperlinks[0];
		assertEquals(model.indexOf("Element*"), hyperLink.getHyperlinkRegion().getOffset());
		assertEquals("Element".length(), hyperLink.getHyperlinkRegion().getLength());
		assertEquals(
				grammar.eResource().getResourceSet().getURIConverter().normalize(
				EcoreUtil.getURI(GrammarUtil.findRuleForName(grammar, "Element"))), hyperLink.getURI());
	}

	public void test_Bug281652_should_not_create_links_to_genpackage_classes() {
		assertNull(helper.createHyperlinksByOffset(resource, model.indexOf("[Element]"), true));
		assertNull(helper.createHyperlinksByOffset(resource, model.indexOf("Element]"), true));
		assertNull(helper.createHyperlinksByOffset(resource, model.indexOf("lement]"), true));
	}

	private void checkHyperlink(XtextHyperlink action) {
		assertNotNull(action);
		URI uri = action.getURI();
		assertNotNull(uri);
		assertFalse(ClasspathUriUtil.isClasspathUri(uri));
		assertTrue(ClasspathUriUtil.isClasspathUri(EcoreUtil.getURI(terminalGrammar)));
		EObject obj = grammar.eResource().getResourceSet().getEObject(uri, true);
		assertNotNull(obj);
		Grammar terminalGrammar = grammar.getUsedGrammars().get(0);
		assertFalse(terminalGrammar.eIsProxy());
		assertEquals(terminalGrammar, obj);
	}

//	public void testGetOpenDeclarationAction() {
//		OpenDeclarationAction action = helper.getOpenDeclarationAction(resource, model.indexOf("common.Terminals"));
//		checkHyperlink(action);
//	}

}
