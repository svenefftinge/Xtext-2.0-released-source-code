/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.serializer.diagnostic;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.scoping.IScope;

import com.google.inject.ImplementedBy;

/**
 * @author Moritz Eysholdt - Initial contribution and API
 */
@ImplementedBy(TokenDiagnosticProvider.class)
public interface ITokenDiagnosticProvider {

	ISerializationDiagnostic getNullNotAllowedDiagnostic(EObject semanticObject, AbstractElement ele);

	ISerializationDiagnostic getValueConversionExceptionDiagnostic(EObject semanticObject, AbstractElement element,
			Object value, Throwable exception);

	ISerializationDiagnostic getNoScopeFoundDiagnostic(EObject semanticObject, CrossReference element, EObject target);

	ISerializationDiagnostic getNoEObjectDescriptionFoundDiagnostic(EObject semanticObject, CrossReference element,
			EObject target, IScope scope);
}
