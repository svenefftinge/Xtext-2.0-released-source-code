/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xtend2.ui.highlighting;

import org.eclipse.swt.graphics.RGB;
import org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultHighlightingConfiguration;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightingConfigurationAcceptor;
import org.eclipse.xtext.ui.editor.utils.TextStyle;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class HighlightingConfiguration extends DefaultHighlightingConfiguration {

	public static final String RICH_TEXT_ID = "xtend2.richText";
	public static final String RICH_TEXT_DELIMITER_ID = "xtend2.richText.delimiter";
	public static final String INSIGNIFICANT_TEMPLATE_TEXT = "xtend2.insignificant.template.text";
	public static final String POTENTIAL_LINE_BREAK = "xtend2.potential.line.break";
	public static final String TEMPLATE_LINE_BREAK = "xtend2.template.line.break";
	
	@Override
	public void configure(IHighlightingConfigurationAcceptor acceptor) {
		super.configure(acceptor);
		acceptor.acceptDefaultHighlighting(RICH_TEXT_ID, "Template Text", richTextStyle());
		acceptor.acceptDefaultHighlighting(RICH_TEXT_DELIMITER_ID, "Template Delimiter", richTextDelimiterStyle());
		acceptor.acceptDefaultHighlighting(INSIGNIFICANT_TEMPLATE_TEXT, "Insignificant Template Text", insignificantTemplateTextStyle());
		acceptor.acceptDefaultHighlighting(POTENTIAL_LINE_BREAK, "Potential Line Break (if line is not empty)", insignificantTemplateTextStyle());
		acceptor.acceptDefaultHighlighting(TEMPLATE_LINE_BREAK, "Template Line Break", richTextStyle());
	}
	
	public TextStyle richTextStyle() {
		TextStyle textStyle = stringTextStyle().copy();
		textStyle.setBackgroundColor(new RGB(220, 220, 220));
		return textStyle;
	}
	
	public TextStyle richTextDelimiterStyle() {
		TextStyle textStyle = defaultTextStyle().copy();
		return textStyle;
	}
	
	public TextStyle insignificantTemplateTextStyle() {
		TextStyle textStyle = stringTextStyle().copy();
		return textStyle;
	}
}
