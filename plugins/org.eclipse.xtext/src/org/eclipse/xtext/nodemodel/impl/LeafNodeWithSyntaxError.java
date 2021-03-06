/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.nodemodel.impl;

import org.eclipse.xtext.nodemodel.SyntaxErrorMessage;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 * @noextend This class is not intended to be subclassed by clients.
 */
public class LeafNodeWithSyntaxError extends LeafNode {

	private SyntaxErrorMessage syntaxErrorMessage;
	
	@Override
	public SyntaxErrorMessage getSyntaxErrorMessage() {
		return syntaxErrorMessage;
	}

	protected void basicSetSyntaxErrorMessage(SyntaxErrorMessage syntaxErrorMessage) {
		this.syntaxErrorMessage = syntaxErrorMessage;
	}
	
}
