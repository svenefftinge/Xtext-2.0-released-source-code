/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.resource;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IStorage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.util.Pair;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
public interface IStorage2UriMapper {

	/**
	 * Find the storages that can be mapped to the given URI. It will typically 
	 * be only one {@link IStorage} assiciated with one {@link IProject} but 
	 * in the case that the same external class folder or jar is referenced in 
	 * multiple projects multiple {@link IStorage}s are returned.
	 * @param uri the {@link URI}. May not be <code>null</code>.
	 * @return IStorages corresponding to the given URI. Never <code>null</code>. 
	 */
	Iterable<Pair<IStorage, IProject>> getStorages(URI uri);

	/**
	 * @return returns the URI for the given {@link IStorage}. 
	 */
	URI getUri(IStorage storage);

}
