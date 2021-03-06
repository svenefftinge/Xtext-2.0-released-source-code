/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xtend2.tests.annotations;

import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.xtext.junit.util.ParseHelper;
import org.eclipse.xtext.junit.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.XbasePackage;
import org.eclipse.xtext.xbase.compiler.OnTheFlyJavaCompiler.EclipseRuntimeDependentJavaCompiler;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.eclipse.xtext.xtend2.compiler.Xtend2Compiler;
import org.eclipse.xtext.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xtend2.tests.AbstractXtend2TestCase;
import org.eclipse.xtext.xtend2.xtend2.Xtend2Package;
import org.eclipse.xtext.xtend2.xtend2.XtendFile;

import testdata.Properties1;

import com.google.common.base.Function;
import com.google.inject.ImplementedBy;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
public class AnnotationsCompilerTest extends AbstractXtend2TestCase {
	
	public void testSimpleAnnotationOnType() throws Exception {
		final String text = "@com.google.inject.Singleton() class Foo {}";
		assertNotNull(getAnnotationOnClass(text, Singleton.class));
	}
	
	public void testParameterizedAnnotationOnType() throws Exception {
		final String text = "@com.google.inject.ImplementedBy(typeof(String)) class Foo {}";
		ImplementedBy implementedBy = getAnnotationOnClass(text, ImplementedBy.class);
		assertTrue(implementedBy.value() == String.class);
	}
	
	public void testKeyValueParameterizedAnnotationOnType() throws Exception {
		final String text = "@com.google.inject.ImplementedBy( value = typeof(String)) class Foo {}";
		ImplementedBy implementedBy = getAnnotationOnClass(text, ImplementedBy.class);
		assertTrue(implementedBy.value() == String.class);
	}
	
	public void testKeyValueParameterizedAnnotationOnField() throws Exception {
		final String text = "class Foo { @com.google.inject.Inject(optional = true) String string }";
		Inject inject = getAnnotationOnField(text, Inject.class);
		assertTrue(inject.optional());
	}
	
	public void testMarkerAnnotationOnField() throws Exception {
		final String text = "class Foo { @com.google.inject.Inject String string }";
		Inject inject = getAnnotationOnField(text, Inject.class);
		assertNotNull(inject);
	}
	
	public void testAnnotationOnField() throws Exception {
		final String text = "class Foo { @com.google.inject.Inject() String string }";
		Inject inject = getAnnotationOnField(text, Inject.class);
		assertNotNull(inject);
	}

	protected <T extends Annotation> T getAnnotationOnClass(final String text, Class<T> annotation) throws Exception {
		Class<?> class1 = compileToClass(text);
		return class1.getAnnotation(annotation);
	}
	
	protected <T extends Annotation> T getAnnotationOnField(final String text, Class<T> annotation) throws Exception {
		Class<?> class1 = compileToClass(text);
		Field field = class1.getDeclaredFields()[0];
		return field.getAnnotation(annotation);
	}

	protected Class<?> compileToClass(final String text) throws Exception {
		XtendFile file = parseHelper.parse(text);
		validationHelper.assertNoErrors(file);
		StringWriter w = new StringWriter();
		compiler.compile(file, w);
		Class<?> class1 = javaCompiler.compileToClass("Foo", w.toString());
		return class1;
	}
	
	@Inject
	private EclipseRuntimeDependentJavaCompiler javaCompiler;

	@Inject
	private ParseHelper<XtendFile> parseHelper;

	@Inject
	private ValidationTestHelper validationHelper;
	
	@Inject
	private Xtend2Compiler compiler;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		javaCompiler.addClassPathOfClass(getClass());
		javaCompiler.addClassPathOfClass(StringExtensions.class);
		javaCompiler.addClassPathOfClass(Notifier.class);
		javaCompiler.addClassPathOfClass(EcorePackage.class);
		javaCompiler.addClassPathOfClass(XbasePackage.class);
		javaCompiler.addClassPathOfClass(Xtend2Package.class);
		javaCompiler.addClassPathOfClass(Inject.class);
		javaCompiler.addClassPathOfClass(Properties1.class);
		javaCompiler.addClassPathOfClass(Function.class);
		javaCompiler.addClassPathOfClass(StringConcatenation.class);
	}
}
