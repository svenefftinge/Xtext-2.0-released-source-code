/*
* generated by Xtext
*/
package org.eclipse.xtext.testlanguages.backtracking.parser.antlr;

import java.io.InputStream;
import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

public class SimpleBeeLangTestLanguageAntlrTokenFileProvider implements IAntlrTokenFileProvider {
	
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
    	return classLoader.getResourceAsStream("org/eclipse/xtext/testlanguages/backtracking/parser/antlr/internal/InternalSimpleBeeLangTestLanguage.tokens");
	}
}
