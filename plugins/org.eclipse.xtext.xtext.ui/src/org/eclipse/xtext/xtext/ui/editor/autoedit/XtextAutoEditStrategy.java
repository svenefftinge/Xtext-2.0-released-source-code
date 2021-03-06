/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xtext.ui.editor.autoedit;

import org.eclipse.jface.text.IDocument;
import org.eclipse.xtext.ui.editor.autoedit.DefaultAutoEditStrategyProvider;
import org.eclipse.xtext.ui.editor.autoedit.MultiLineTerminalsEditStrategy;
import org.eclipse.xtext.ui.editor.model.DocumentUtil;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
public class XtextAutoEditStrategy extends DefaultAutoEditStrategyProvider {

	@Override
	protected void configure(IEditStrategyAcceptor acceptor) {
		super.configure(acceptor);
		acceptor.accept(singleLineTerminals.newInstance(":", ";"),IDocument.DEFAULT_CONTENT_TYPE);
		MultiLineTerminalsEditStrategy configure = multiLineTerminals.newInstance(":", null, ";");
		// the following is a cheap but working hack, which replaces any double colons '::' by whitespace '  ' temporarily.
		configure.setDocumentUtil(new DocumentUtil() {
			@Override
			protected String preProcessSearchString(String string) {
				return string.replace("::", "  ");
			}
		});
		acceptor.accept(configure, IDocument.DEFAULT_CONTENT_TYPE);
	}
	
}
