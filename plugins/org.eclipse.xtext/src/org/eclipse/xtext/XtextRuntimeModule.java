/*******************************************************************************
 * Copyright (c) 2008 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.eclipse.xtext;

import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.formatting.IFormatter;
import org.eclipse.xtext.linking.ILinker;
import org.eclipse.xtext.linking.ILinkingDiagnosticMessageProvider;
import org.eclipse.xtext.linking.ILinkingService;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.parser.antlr.IReferableElementsUnloader;
import org.eclipse.xtext.parsetree.reconstr.ITokenSerializer.ICrossReferenceSerializer;
import org.eclipse.xtext.parsetree.reconstr.ITransientValueService;
import org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy;
import org.eclipse.xtext.resource.IFragmentProvider;
import org.eclipse.xtext.resource.ILocationInFileProvider;
import org.eclipse.xtext.scoping.IGlobalScopeProvider;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.scoping.impl.DefaultGlobalScopeProvider;
import org.eclipse.xtext.validation.IDiagnosticConverter;
import org.eclipse.xtext.xtext.XtextCrossReferenceSerializer;
import org.eclipse.xtext.xtext.XtextDiagnosticConverter;
import org.eclipse.xtext.xtext.XtextFormatter;
import org.eclipse.xtext.xtext.XtextFragmentProvider;
import org.eclipse.xtext.xtext.XtextLinkingDiagnosticMessageProvider;
import org.eclipse.xtext.xtext.XtextLinkingService;
import org.eclipse.xtext.xtext.XtextLocationInFileProvider;
import org.eclipse.xtext.xtext.XtextQualifiedNameConverter;
import org.eclipse.xtext.xtext.XtextReferableElementsUnloader;
import org.eclipse.xtext.xtext.XtextResourceDescriptionStrategy;
import org.eclipse.xtext.xtext.XtextScopeProvider;
import org.eclipse.xtext.xtext.XtextTransientValueService;
import org.eclipse.xtext.xtext.XtextTransientValueService2;
import org.eclipse.xtext.xtext.XtextValidator;
import org.eclipse.xtext.xtext.XtextValueConverters;
import org.eclipse.xtext.xtext.ecoreInference.IXtext2EcorePostProcessor;
import org.eclipse.xtext.xtext.ecoreInference.XtendXtext2EcorePostProcessor;

import com.google.inject.Binder;

/**
 * used to register components to be used at runtime.
 */
public class XtextRuntimeModule extends AbstractXtextRuntimeModule {

	@Override
	public void configure(Binder binder) {
		super.configure(binder);
		binder.bind(XtextValidator.class).asEagerSingleton();
	}

	@Override
	public Class<? extends ILinkingService> bindILinkingService() {
		return XtextLinkingService.class;
	}

	@Override
	public Class<? extends IScopeProvider> bindIScopeProvider() {
		return XtextScopeProvider.class;
	}

	public Class<? extends ICrossReferenceSerializer> bindICrossReferenceSerializer() {
		return XtextCrossReferenceSerializer.class;
	}

	@Override
	public Class<? extends ILinker> bindILinker() {
		return org.eclipse.xtext.xtext.XtextLinker.class;
	}

	@Override
	public Class<? extends ITransientValueService> bindITransientValueService() {
		return XtextTransientValueService.class;
	}
	
	public Class<? extends org.eclipse.xtext.serializer.sequencer.ITransientValueService> bindITransientValueService2() {
		return XtextTransientValueService2.class;
	}

	@Override
	public Class<? extends IFormatter> bindIFormatter() {
		return XtextFormatter.class;
	}

	@Override
	public Class<? extends IValueConverterService> bindIValueConverterService() {
		return XtextValueConverters.class;
	}

	public Class<? extends IXtext2EcorePostProcessor> bindIXtext2EcorePostProcessor() {
		return XtendXtext2EcorePostProcessor.class;
	}
	
	@Override
	public Class<? extends IFragmentProvider> bindIFragmentProvider() {
		return XtextFragmentProvider.class;
	}
	
	public Class<? extends IReferableElementsUnloader> bindIReferableElementsUnloader() {
		return XtextReferableElementsUnloader.class;
	}
	
	public Class<? extends IQualifiedNameConverter> bindIQualifiedNameConverter() {
		return XtextQualifiedNameConverter.class;
	}
	
	public Class<? extends IDiagnosticConverter> bindIDiagnosticConverter() {
		return XtextDiagnosticConverter.class;
	}
	
	public Class<? extends IDefaultResourceDescriptionStrategy> bindIDefaultResourceDescriptionStrategy() {
		return XtextResourceDescriptionStrategy.class;
	}
	
	public Class<? extends ILinkingDiagnosticMessageProvider.Extended> bindILinkingDiagnosticMessageProvider() {
		return XtextLinkingDiagnosticMessageProvider.class;
	}
	
	@Override
	public Class<? extends ILocationInFileProvider> bindILocationInFileProvider() {
		return XtextLocationInFileProvider.class;
	}
	
	@Override
	public Class<? extends IGlobalScopeProvider> bindIGlobalScopeProvider() {
		return DefaultGlobalScopeProvider.class;
	}
	
}
