/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xtend2.richstring;

import java.util.List;

import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xtend2.xtend2.RichString;
import org.eclipse.xtext.xtend2.xtend2.RichStringLiteral;
import org.eclipse.xtext.xtend2.xtend2.util.Xtend2Switch;

/**
 * Computes the initial indentation of a rich string according to
 * the semantics in the Xtend2 language specification. That is, especially
 * the first and the last line have to be ignored if they only consist whitespace.
 * 
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class InitialTemplateIndentationComputer extends Xtend2Switch<String> {
	
	private final String initial;

	/**
	 * @param initial the assumed indentation if the first line contains text. May not be <code>null</code>.
	 */
	public InitialTemplateIndentationComputer(CharSequence initial) {
		if (initial == null)
			throw new IllegalArgumentException("Initial indentation must not be null.");
		this.initial = initial.toString();
	}
	
	@Override
	public String caseRichString(RichString object) {
		String result = null;
		List<XExpression> elements = object.getExpressions();
		for(int i= 0; i< elements.size(); i++) {
			XExpression element = elements.get(i);
			String elementResult = doSwitch(element);
			if (elementResult == null && i == 0)
				return initial;
			result = getBetterString(result, elementResult);
			if (Strings.isEmpty(result))
				return result;
		}
		return result;
	}

	protected String getBetterString(String current, String candidate) {
		if (candidate == null)
			return current;
		if (candidate.length() == 0)
			return candidate;
		if (current == null || current.length() > candidate.length())
			current = candidate;
		return current;
	}
	
	@Override
	public String caseRichStringLiteral(RichStringLiteral object) {
		String value = object.getValue();
		List<TextLine> lines = TextLines.splitString(value);
		// no line breaks or immediately closed string literal => no initial indentation
		if (lines.size() <= 1) {
			return null;
		}
		TextLine firstLine = lines.get(0);
		// first line has content == no initial indentation
		if (!firstLine.containsOnlyWhitespace()) { 
			return null;
		}
		String result = null;
		boolean emptyLineSeen = false;
		for (int i = 1; i < lines.size(); i++) {
			TextLine line = lines.get(i);
			CharSequence leadingWS = line.getLeadingWhiteSpace();
			// line is not empty
			if (leadingWS.length() != line.length()) {
				if (leadingWS.length() == 0)
					return "";
				result = getBetterString(result, leadingWS.toString());
			} else {
				// some tools tend to right trim text files by default (e.g. git)
				// that's why we ignore empty lines
				RichString completeString = (RichString) object.eContainer();
				List<XExpression> siblings = completeString.getExpressions();
				if (siblings.get(siblings.size() - 1) != object) {
					if (leadingWS.length() == 0) { // empty line
						emptyLineSeen = true;
					} else {
						result = getBetterString(result, leadingWS.toString());
					}
				}
			}
		}
		if (emptyLineSeen && result == null)
			return "";
		return result;
	}
	
}