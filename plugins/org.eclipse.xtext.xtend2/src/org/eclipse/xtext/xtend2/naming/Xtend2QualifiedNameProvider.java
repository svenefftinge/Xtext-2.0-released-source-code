/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xtend2.naming;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.common.types.JvmMember;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.xtend2.xtend2.XtendClass;
import org.eclipse.xtext.xtend2.xtend2.XtendField;
import org.eclipse.xtext.xtend2.xtend2.XtendFunction;

import com.google.inject.Inject;

/**
 * @author Jan Koehnlein - Initial contribution and API
 */
public class Xtend2QualifiedNameProvider extends IQualifiedNameProvider.AbstractImpl {

	@Inject
	private IQualifiedNameConverter qualifiedNameConverter;

	public QualifiedName getFullyQualifiedName(EObject obj) {
		if (obj instanceof JvmType 
				|| obj instanceof JvmMember 
				|| obj instanceof XtendClass
				|| obj instanceof XtendFunction 
				|| obj instanceof XtendField) {
			final String qualifiedName = ((JvmIdentifiableElement) obj).getQualifiedName();
			if (qualifiedName == null)
				return null;
			return qualifiedNameConverter.toQualifiedName(qualifiedName);
		}
		return null;
	}

}
