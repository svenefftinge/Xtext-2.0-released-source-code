/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.resource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionStrategy;
import org.eclipse.xtext.util.IAcceptor;

import com.google.inject.ImplementedBy;

/**
 * Allows easy customization of what is indexed, i.e. put into a
 * {@link org.eclipse.xtext.resource.impl.DefaultReferenceDescription}.
 * 
 * @author Jan Koehnlein - Initial contribution and API
 */
@ImplementedBy(DefaultResourceDescriptionStrategy.class)
public interface IDefaultResourceDescriptionStrategy {

	/**
	 * Calculates the {@link IEObjectDescription}s for <code>eObject</code> and passes them to the acceptor.
	 * 
	 * @return true if the children of <code>eObject</code> should be traversed.
	 */
	boolean createEObjectDescriptions(EObject eObject, IAcceptor<IEObjectDescription> acceptor);

	/**
	 * Calculates the {@link IReferenceDescription}s for external cross references from <code>eObject</code> and passes
	 * them to the acceptor.
	 * 
	 * @return true if the children of <code>eObject</code> should be traversed.
	 */
	boolean createReferenceDescriptions(EObject eObject, URI exportedContainerURI, IAcceptor<IReferenceDescription> acceptor);
}
