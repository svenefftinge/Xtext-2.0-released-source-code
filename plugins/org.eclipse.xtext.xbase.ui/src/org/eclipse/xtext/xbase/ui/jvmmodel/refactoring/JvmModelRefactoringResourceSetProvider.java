/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xbase.ui.jvmmodel.refactoring;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.common.types.access.IJvmTypeProvider;
import org.eclipse.xtext.ui.refactoring.impl.RefactoringResourceSetProvider;

import com.google.inject.Inject;

/**
 * @author Jan Koehnlein - Initial contribution and API
 */
@SuppressWarnings("restriction")
public class JvmModelRefactoringResourceSetProvider extends RefactoringResourceSetProvider {
	
	@Inject
	private IJvmTypeProvider.Factory typeProviderFactory;

	@Override
	protected void configure(ResourceSet resourceSet) {
		super.configure(resourceSet);
		typeProviderFactory.findOrCreateTypeProvider(resourceSet);

	}
}
