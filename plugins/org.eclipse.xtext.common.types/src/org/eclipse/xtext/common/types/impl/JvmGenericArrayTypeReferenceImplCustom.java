/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.common.types.impl;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.xtext.common.types.JvmArrayType;
import org.eclipse.xtext.common.types.JvmTypeReference;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class JvmGenericArrayTypeReferenceImplCustom extends JvmGenericArrayTypeReferenceImpl {
	
	@Override
	public JvmTypeReference getComponentType() {
		JvmArrayType arrayType = getType();
		if (arrayType != null)
			return arrayType.getComponentType();
		return null;
	}
	
	@Override
	public int getDimensions() {
		JvmArrayType arrayType = getType();
		if (arrayType != null)
			return arrayType.getDimensions();
		return -1;
	}
	
	@Override
	public String getIdentifier() {
		JvmTypeReference componentType = getComponentType();
		if (componentType != null)
			return componentType.getIdentifier() + "[]";
		return null;
	}
	
	@Override
	public String getSimpleName() {
		JvmTypeReference componentType = getComponentType();
		if (componentType != null)
			return componentType.getSimpleName() + "[]";
		return null;
	}
	
	@Override
	public String getQualifiedName(char innerClassDelimiter) {
		JvmTypeReference componentType = getComponentType();
		if (componentType != null)
			return componentType.getQualifiedName(innerClassDelimiter) + "[]";
		return null;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(eClass().getName());
		result.append(": ");
		if (type == null) {
			result.append(" type is null");
		} else if (type.eIsProxy()) {
			result.append(" (type uri: ");
			result.append(((InternalEObject) type).eProxyURI());
			result.append(')');
		} else {
			result.append(getIdentifier());
		}
		return result.toString();
	}
	
}
