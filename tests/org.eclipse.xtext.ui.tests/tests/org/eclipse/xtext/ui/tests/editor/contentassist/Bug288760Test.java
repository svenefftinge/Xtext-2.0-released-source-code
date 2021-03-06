/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.tests.editor.contentassist;

import org.eclipse.xtext.ISetup;
import org.eclipse.xtext.ui.junit.editor.contentassist.AbstractContentAssistProcessorTest;
import org.eclipse.xtext.ui.shared.SharedStateModule;
import org.eclipse.xtext.ui.tests.Activator;
import org.eclipse.xtext.ui.tests.editor.contentassist.ui.Bug288760TestLanguageUiModule;
import org.eclipse.xtext.util.Modules2;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class Bug288760Test extends AbstractContentAssistProcessorTest {

	public ISetup getBug288760TestLanguageSetup() {
		return new Bug288760TestLanguageStandaloneSetup() {
			@Override
			public Injector createInjector() {
				return Guice.createInjector(Modules2.mixin(new Bug288760TestLanguageRuntimeModule(), new Bug288760TestLanguageUiModule(Activator
						.getInstance()), new SharedStateModule()));
			}
		};
	}

	public void testBug288760_01() throws Exception {
//		newBuilder(getBug288760TestLanguageSetup()).append(
//				"<workflow>\n" + 
//				"    <first class=\"java.lang.StringBuffer\" />\n" + 
//				"    <second class=\"java.lang.StringBuffer\" />\n" + 
//				"    <third class=\"org.eclipse.xtend.typesystem.emf.XmiReader\" />\n" + 
//				"</workflow>").assertTextAtCursorPosition("<first", "<workflow");
//	}
//	
//	public void testBug288760_02() throws Exception {
//		newBuilder(getBug288760TestLanguageSetup()).append(
//				"<workflow>\n" + 
//				"    <first class=\"java.lang.StringBuffer\" />\n" + 
//				"    <second class=\"java.lang.StringBuffer\" />\n" + 
//				"    <third class=\"org.eclipse.xtend.typesystem.emf.XmiReader\" />\n" + 
//				"</workflow>").assertTextAtCursorPosition("<second", "<workflow");
//	}
//	
//	public void testBug288760_03() throws Exception {
//		newBuilder(getBug288760TestLanguageSetup()).append(
//				"<workflow>\n" + 
//				"    <first class=\"java.lang.StringBuffer\" />\n" + 
//				"    <second class=\"java.lang.StringBuffer\" />\n" + 
//				"    <third class=\"org.eclipse.xtend.typesystem.emf.XmiReader\" />\n" + 
//				"</workflow>").assertTextAtCursorPosition("<third", "<workflow");
	}
}