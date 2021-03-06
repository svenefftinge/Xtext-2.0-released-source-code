/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.resource.generic;

import static java.util.Collections.*;

import java.util.Collection;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.DescriptionUtils;
import org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.impl.DefaultResourceDescription;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionDelta;
import org.eclipse.xtext.util.IResourceScopeCache;

import com.google.inject.Inject;

/**
 * An {@link IResourceDescription.Manager} for non-Xtext resources. 
 * 
 * @author Jan Koehnlein - Initial contribution and API
 */
public class GenericResourceDescriptionManager implements IResourceDescription.Manager {

	@Inject
	private IDefaultResourceDescriptionStrategy resourceDescriptionStrategy;

	@Inject
	private DescriptionUtils descriptionUtils;

	@Inject
	private IResourceScopeCache cache = new IResourceScopeCache.NullImpl();

	public IResourceDescription getResourceDescription(Resource resource) {
		return new DefaultResourceDescription(resource, resourceDescriptionStrategy, cache);
	}

	public Delta createDelta(IResourceDescription oldDescription, IResourceDescription newDescription) {
		return new DefaultResourceDescriptionDelta(oldDescription, newDescription);
	}

	public boolean isAffected(Delta delta, IResourceDescription candidate) throws IllegalArgumentException {
		return isAffected(singleton(delta), candidate, null);
	}

	public boolean isAffected(Collection<Delta> deltas, IResourceDescription candidate, IResourceDescriptions context)
			throws IllegalArgumentException {
		Set<URI> outgoingReferences = descriptionUtils.collectOutgoingReferences(candidate);
		if (!outgoingReferences.isEmpty()) {
			for (IResourceDescription.Delta delta : deltas)
				if (delta.haveEObjectDescriptionsChanged() && outgoingReferences.contains(delta.getUri()))
					return true;
		}
		return false;
	}

}
