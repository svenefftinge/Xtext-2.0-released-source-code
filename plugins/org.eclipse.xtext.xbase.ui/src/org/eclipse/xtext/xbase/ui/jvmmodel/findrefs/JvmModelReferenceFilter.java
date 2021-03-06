/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xbase.ui.jvmmodel.findrefs;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.resource.IReferenceDescription;

import com.google.common.base.Predicate;

public class JvmModelReferenceFilter implements Predicate<IReferenceDescription> {
	private URI excludedSourceURI;

	public JvmModelReferenceFilter(URI excludedSourceURI) {
		this.excludedSourceURI = excludedSourceURI;
	}

	public URI getExcludedSourceURI() {
		return excludedSourceURI;
	}
	
	public boolean apply(IReferenceDescription input) {
		return !isInferredJvmElement(input.getSourceEObjectUri()) && (excludedSourceURI == null || !excludedSourceURI.equals(input.getSourceEObjectUri()));
	}

	protected boolean isInferredJvmElement(URI elementURI) {
		String fragment = elementURI.fragment();
		return !fragment.startsWith("/0") && !fragment.startsWith("//") && !fragment.equals("/");
	}
	
}