/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xtext.ui.editor.contentassist;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.xtext.ISetup;
import org.eclipse.xtext.XtextRuntimeModule;
import org.eclipse.xtext.XtextStandaloneSetup;
import org.eclipse.xtext.ui.XtextUiModule;
import org.eclipse.xtext.ui.editor.contentassist.ContentProposalLabelProvider;
import org.eclipse.xtext.ui.junit.editor.contentassist.AbstractContentAssistProcessorTest;
import org.eclipse.xtext.ui.junit.editor.contentassist.ContentAssistProcessorTestBuilder;
import org.eclipse.xtext.ui.label.DefaultEObjectLabelProvider;
import org.eclipse.xtext.ui.shared.SharedStateModule;
import org.eclipse.xtext.util.Modules2;
import org.eclipse.xtext.xtext.ui.Activator;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class XtextContentAssistTest extends AbstractContentAssistProcessorTest {
	
	public ISetup getXtextSetup() {
		return new XtextStandaloneSetup() {
			@Override
			public Injector createInjector() {
				return Guice.createInjector(Modules2.mixin(new XtextRuntimeModule(),new XtextUiModule(Activator.getDefault()) {
					@Override
					public void configureContentProposalLabelProvider(com.google.inject.Binder binder) {
						binder.bind(ILabelProvider.class).annotatedWith(ContentProposalLabelProvider.class).to(DefaultEObjectLabelProvider.class);
					}
				}, new SharedStateModule()));
			}
		};
	}
	
	/**
     * https://bugs.eclipse.org/bugs/show_bug.cgi?id=269680
     */
    public void testCompletionOnDatatypeReference_01() throws Exception {
        newBuilder(getXtextSetup())
	        .appendNl("grammar foo with org.eclipse.xtext.common.Terminals")
	        .appendNl("generate meta \"url\"")
	        .appendNl("Rule: name=ID;")
	        .assertTextAtCursorPosition("org.eclipse.xtext", 2, "org.eclipse.xtext.common.Terminals", ",");
    }
    
    /**
     * https://bugs.eclipse.org/bugs/show_bug.cgi?id=269680
     */
    public void testCompletionOnDatatypeReference_02() throws Exception {
        newBuilder(getXtextSetup())
	        .appendNl("grammar foo with org.eclipse.xtext.common.Terminals")
	        .appendNl("generate meta \"url\"")
	        .appendNl("Rule: name=ID;")
	        .assertTextAtCursorPosition("org.eclipse.xtext", 5, "org.eclipse.xtext.common.Terminals", ",");
    }
    
    public void testCompletionOnDatatypeReference_03() throws Exception {
        newBuilder(getXtextSetup())
	        .appendNl("grammar foo with org.eclipse.xtext.common.Terminals")
	        .appendNl("generate meta \"url\"")
	        .appendNl("Rule: name=ID;")
	        .assertTextAtCursorPosition("org.eclipse.xtext", 4, "org.eclipse.xtext.common.Terminals");
    }
    
    public void testCompletionOnSyntaxError_01() throws Exception {
        newBuilder(getXtextSetup())
	        .appendNl("grammar foo with org.eclipse.xtext.common.Terminals")
	        .appendNl("generate foo 'bar'")
	        .appendNl("grammarA: child=Rule;")
	        .appendNl("Rule: call=grammar")
	        .assertTextAtCursorPosition("=grammar", "=grammar".length(), "grammarA");
    }
    
    public void testCompletionOnSyntaxError_02() throws Exception {
        newBuilder(getXtextSetup())
	        .appendNl("grammar foo with org.eclipse.xtext.common.Terminals")
	        .appendNl("generate foo 'bar'")
	        .appendNl("grammarA: child=Rule;")
	        .append("Rule: call=grammar")
	        .assertText("grammarA");
    }
    
    public void testCompletionOnSyntaxError_03() throws Exception {
        newBuilder(getXtextSetup())
	        .appendNl("grammar foo with org.eclipse.xtext.common.Terminals")
	        .appendNl("generate foo 'bar'")
	        .appendNl("grammarA: child=Rule;")
	        .appendNl("Rule: call=grammar;")
	        .assertTextAtCursorPosition("grammar;", "grammar".length(), "grammarA");
    }

    public void testCompleteGrammarName_01() throws Exception {
    	newBuilder(getXtextSetup())
        .append("grammar ")
        .assertCount(0);
    }
    
    public void testCompleteAfterGrammarName_01() throws Exception {
    	newBuilder(getXtextSetup())
        .append("grammar org.foo.bar")
        .assertCount(0);
    }
    
    public void testCompleteAfterGrammarName_02() throws Exception {
    	newBuilder(getXtextSetup())
        .append("grammar org.foo.bar ")
        .assertText("with", "Name", "enum", "terminal", "hidden", "generate", "import");
    }
    
    public void testCompleteAfterGenerateName_01() throws Exception {
    	newBuilder(getXtextSetup())
        .appendNl("grammar org.foo.bar with org.eclipse.xtext.common.Terminals")
        .append("generate name ")
        .assertCount(EPackage.Registry.INSTANCE.size());
    }
    
    public void testCompleteAfterGenerateName_02() throws Exception {
    	newBuilder(getXtextSetup())
        .appendNl("grammar org.foo.bar with org.eclipse.xtext.common.Terminals")
        .append("generate name \"")
        .assertCount(EPackage.Registry.INSTANCE.size());
    }
    
    public void testCompleteCrossReference_01() throws Exception {
    	newBuilder(getXtextSetup())
        .appendNl("grammar org.foo.bar with org.eclipse.xtext.common.Terminals")
        .appendNl("generate metamodelA 'http://foo.bar/A' as alias")
        .appendNl("generate metamodelB 'http://foo.bar/B'")
        .appendNl("RuleA: name=ID;")
        .append("RuleB returns ")
        .assertText("RuleA", "RuleB", "alias");
    }
    
    public void testCompleteCrossReference_02() throws Exception {
    	newBuilder(getXtextSetup())
        .appendNl("grammar org.foo.bar with org.eclipse.xtext.common.Terminals")
        .appendNl("generate metamodelA 'http://foo.bar/A' as alias")
        .appendNl("generate metamodelB 'http://foo.bar/B'")
        .appendNl("RuleA: name=ID;")
        .append("RuleB returns a")
        .assertText(
        		"alias",
        		"::",
        		":",
        		"a" // has already been inferred as type in metamodelA
        );
    }
    
    public void testCompleteCrossReference_03() throws Exception {
    	newBuilder(getXtextSetup())
        .appendNl("grammar org.foo.bar with org.eclipse.xtext.common.Terminals")
        .appendNl("generate metamodelA 'http://foo.bar/A' as alias")
        .appendNl("generate metamodelB 'http://foo.bar/B'")
        .appendNl("RuleA: name=ID;")
        .append("RuleB returns alias")
        .assertText("alias", "::", ":");
    }
    
    public void testCompleteCrossReference_04() throws Exception {
    	newBuilder(getXtextSetup())
        .appendNl("grammar org.foo.bar with org.eclipse.xtext.common.Terminals")
        .appendNl("generate metamodelA 'http://foo.bar/A' as alias")
        .appendNl("generate metamodelB 'http://foo.bar/B'")
        .appendNl("RuleA: name=ID;")
        .append("RuleB returns alias:")
        .assertText(
        		"::",
        		":",
        		"=>",
        		"Feature",
        		"\"Value\"",
        		"RuleA",
        		"RuleB",
        		"ID",
        		"STRING",
        		"INT",
        		"SL_COMMENT",
        		"WS",
        		"ML_COMMENT",
        		"ANY_OTHER",
        		"(",
        		"{");
    }
    
    public void testCompleteCrossReference_05() throws Exception {
    	newBuilder(getXtextSetup())
        .appendNl("grammar org.foo.bar with org.eclipse.xtext.common.Terminals")
        .appendNl("generate metamodelA 'http://foo.bar/A' as alias")
        .appendNl("generate metamodelB 'http://foo.bar/B'")
        .appendNl("RuleA: name=ID;")
        .append("RuleB returns alias:=>")
        .assertText(
        		"=>",
        		"Feature",
        		"\"Value\"",
        		"RuleA",
        		"RuleB",
        		"ID",
        		"STRING",
        		"INT",
        		"SL_COMMENT",
        		"WS",
        		"ML_COMMENT",
        		"ANY_OTHER",
        		"(");
    }
    
    public void testEnumCompletion_01() throws Exception {
    	newBuilder(getXtextSetup())
    		.appendNl("grammar org.xtext.example.MyDsl1 with org.eclipse.xtext.common.Terminals")
	        .appendNl("generate myDsl1 \"http://www.xtext.org/example/MyDsl1\"")
	        .appendNl("import 'classpath:/org/eclipse/xtext/xtext/ui/editor/contentassist/enumsUi.ecore'")
	        .appendNl("enum ExistingEnum:")
	        .assertText("SameName", "DifferentName", "OverriddenLiteral");
    }
    
    /**
     * https://bugs.eclipse.org/bugs/show_bug.cgi?id=270116
     * @throws Exception
     */
    public void testCompleteTypeRefReturnForEnumRule() throws Exception {
        doTestCompleteTypeRefSetup()
                .appendNl("enum NewEnum returns").assertText("NewEnum");
    }
    
    /**
     * https://bugs.eclipse.org/bugs/show_bug.cgi?id=270116
     * @throws Exception
     */
	public void testCompleteTypeRefReturnForParserRule() throws Exception {
        doTestCompleteTypeRefSetup()
                .appendNl("NewType returns").assertText(
                                "Class", "Import","Model","NewType"
                );
    }
	
    /**
     * https://bugs.eclipse.org/bugs/show_bug.cgi?id=270116
     * @throws Exception
     */
    public void testCompleteTypeRefReturnForTerminalRule_01() throws Exception {
        doTestCompleteTypeRefSetup()
                .appendNl("terminal NewType returns").assertText();
    }
    
    public void testCompleteTypeRefReturnForTerminalRule_02() throws Exception {
        doTestCompleteTypeRefSetup()
        		.appendNl("enum NewEnum: Foobar;")
                .appendNl("terminal NewType returns").assertText("NewEnum");
    }
    
    public void testCompleteTypeRefReturnForTerminalRule_03() throws Exception {
        doTestCompleteTypeRefSetup()
                .appendNl("terminal NewType returns X").assertTextAtCursorPosition("X");
    }
    
    public void testCompleteTypeRefReturnForTerminalRule_04() throws Exception {
        doTestCompleteTypeRefSetup()
        		.appendNl("enum NewEnum: Foobar;")
                .appendNl("terminal NewType returns X").assertTextAtCursorPosition("X", "NewEnum");
    }
    
    public void testCompleteTypeRefAction_01() throws Exception {
        doTestCompleteTypeRefSetup()
        		.appendNl("enum NewEnum: Foobar;")
                .appendNl("NewType: {").assertText(
                                "Class", "Import", "Model", "NewType"
                );
    }
    
    public void testCompleteTypeRefAction_02() throws Exception {
    	doTestCompleteTypeRefSetup()
    	.appendNl("enum NewEnum: Foobar;")
    	.appendNl("NewType: { X").assertTextAtCursorPosition("X",
    			"Class", "Import", "Model", "NewType", "X"
    	);
    }

    public void testCompleteTypeRefCrossReference_01() throws Exception {
    	doTestCompleteTypeRefSetup()
    	.appendNl("enum NewEnum: Foobar;")
    	.appendNl("NewType: ref=[").assertText(
    			"Class", "Import", "Model", "NewType"
    	);
    }
    
    public void testCompleteTypeRefCrossReference_02() throws Exception {
        doTestCompleteTypeRefSetup()
        		.appendNl("enum NewEnum: Foobar;")
                .appendNl("NewType: ref=[ X").assertTextAtCursorPosition("X",
                                "Class", "Import", "Model", "NewType"
                );
    }
    
    public void testCompleteFeatureName_01() throws Exception {
    	newBuilder(getXtextSetup())
	        .appendNl("grammar org.foo.bar with org.eclipse.xtext.common.Terminals")
	        .appendNl("generate metamodelA 'http://foo.bar/A'")
	        .appendNl("RuleA: name=ID;")
	        .append("RuleB returns RuleA: ")
	        .assertText(
	        		"name",
	        		"Feature",
	        		"\"Value\"",
	        		"RuleA",
	        		"RuleB",
	        		"ID",
	        		"STRING",
	        		"INT",
	        		"SL_COMMENT",
	        		"WS",
	        		"ML_COMMENT",
	        		"ANY_OTHER",
	        		"(",
	        		"{",
	        		"=>");
    }
    
    public void testCompleteFeatureName_02() throws Exception {
    	newBuilder(getXtextSetup())
	        .appendNl("grammar org.foo.bar with org.eclipse.xtext.common.Terminals")
	        .appendNl("generate metamodelA 'http://foo.bar/A'")
	        .appendNl("RuleA: name=ID ")
	        .assertText(
	        		"name",
	        		"Feature",
	        		"\"Value\"",
	        		"RuleA",
	        		"ID",
	        		"STRING",
	        		"INT",
	        		"SL_COMMENT",
	        		"WS",
	        		"ML_COMMENT",
	        		"ANY_OTHER",
	        		"(",
	        		"{",
	        		"=>",
	        		"*",
	        		"+",
	        		";",
	        		"?",
	        		"|",
	        		"&");
    }
    
    public void testCompleteFeatureName_03() throws Exception {
    	newBuilder(getXtextSetup())
	        .appendNl("grammar org.foo.bar with org.eclipse.xtext.common.Terminals")
	        .appendNl("generate metamodelA 'http://foo.bar/A'")
	        .appendNl("RuleA: name=ID somethingElse=ID;")
	        .append("RuleB returns RuleA: ")
	        .assertText(
	        		"name",
	        		"somethingElse",
	        		"Feature",
	        		"\"Value\"",
	        		"RuleA",
	        		"RuleB",
	        		"ID",
	        		"STRING",
	        		"INT",
	        		"SL_COMMENT",
	        		"WS",
	        		"ML_COMMENT",
	        		"ANY_OTHER",
	        		"(",
	        		"{",
	        		"=>");
    }
    
    public void testCompleteFeatureName_04() throws Exception {
    	newBuilder(getXtextSetup())
	        .appendNl("grammar org.foo.bar with org.eclipse.xtext.common.Terminals")
	        .appendNl("generate metamodelA 'http://foo.bar/A'")
	        .appendNl("RuleA: name=ID;")
	        .append("RuleB returns RuleA: =>")
	        .assertText(
	        		"=>",
	        		"name",
	        		"Feature",
	        		"\"Value\"",
	        		"RuleA",
	        		"RuleB",
	        		"ID",
	        		"STRING",
	        		"INT",
	        		"SL_COMMENT",
	        		"WS",
	        		"ML_COMMENT",
	        		"ANY_OTHER",
	        		"(");
    }
    
    public void testCompleteFeatureName_05() throws Exception {
    	newBuilder(getXtextSetup())
	        .appendNl("grammar org.foo.bar with org.eclipse.xtext.common.Terminals")
	        .appendNl("generate metamodelA 'http://foo.bar/A'")
	        .appendNl("RuleA: name=ID;")
	        .append("RuleB returns RuleA: => ")
	        .assertText(
	        		"name",
	        		"Feature",
	        		"\"Value\"",
	        		"RuleA",
	        		"RuleB",
	        		"ID",
	        		"STRING",
	        		"INT",
	        		"SL_COMMENT",
	        		"WS",
	        		"ML_COMMENT",
	        		"ANY_OTHER",
	        		"(");
    }
    
    public void testCompleteFeatureName_06() throws Exception {
    	newBuilder(getXtextSetup())
	        .appendNl("grammar org.foo.bar with org.eclipse.xtext.common.Terminals")
	        .appendNl("generate metamodelA 'http://foo.bar/A'")
	        .append("RuleA: name=ID =>")
	        .assertText(
	        		"name",
	        		"Feature",
	        		"\"Value\"",
	        		"RuleA",
	        		"ID",
	        		"STRING",
	        		"INT",
	        		"SL_COMMENT",
	        		"WS",
	        		"ML_COMMENT",
	        		"ANY_OTHER",
	        		"(",
	        		"=>");
    }
    
    public void testCompleteFeatureName_07() throws Exception {
    	newBuilder(getXtextSetup())
	        .appendNl("grammar org.foo.bar with org.eclipse.xtext.common.Terminals")
	        .appendNl("generate metamodelA 'http://foo.bar/A'")
	        .appendNl("RuleA: name=ID => ")
	        .assertText(
	        		"name",
	        		"Feature",
	        		"\"Value\"",
	        		"RuleA",
	        		"ID",
	        		"STRING",
	        		"INT",
	        		"SL_COMMENT",
	        		"WS",
	        		"ML_COMMENT",
	        		"ANY_OTHER",
	        		"(");
    }
    
    public void testCompleteFeatureName_08() throws Exception {
    	newBuilder(getXtextSetup())
	        .appendNl("grammar org.foo.bar with org.eclipse.xtext.common.Terminals")
	        .appendNl("generate metamodelA 'http://foo.bar/A'")
	        .appendNl("RuleA: name=ID somethingElse=ID;")
	        .append("RuleB returns RuleA: =>")
	        .assertText(
	        		"name",
	        		"somethingElse",
	        		"Feature",
	        		"\"Value\"",
	        		"RuleA",
	        		"RuleB",
	        		"ID",
	        		"STRING",
	        		"INT",
	        		"SL_COMMENT",
	        		"WS",
	        		"ML_COMMENT",
	        		"ANY_OTHER",
	        		"(",
	        		"=>");
    }
    
    public void testCompleteFeatureName_09() throws Exception {
    	newBuilder(getXtextSetup())
	        .appendNl("grammar org.foo.bar with org.eclipse.xtext.common.Terminals")
	        .appendNl("generate metamodelA 'http://foo.bar/A'")
	        .appendNl("RuleA: name=ID somethingElse=ID;")
	        .append("RuleB returns RuleA: => ")
	        .assertText(
	        		"name",
	        		"somethingElse",
	        		"Feature",
	        		"\"Value\"",
	        		"RuleA",
	        		"RuleB",
	        		"ID",
	        		"STRING",
	        		"INT",
	        		"SL_COMMENT",
	        		"WS",
	        		"ML_COMMENT",
	        		"ANY_OTHER",
	        		"(");
    }
    
    public void testCompleteFeatureName_10() throws Exception {
    	newBuilder(getXtextSetup())
	        .appendNl("grammar org.foo.bar with org.eclipse.xtext.common.Terminals")
	        .appendNl("generate metamodelA 'http://foo.bar/A'")
	        .appendNl("RuleA: name=ID somethingElse=ID;")
	        .append("RuleB returns RuleA: =")
	        .assertText(
	        		"=>");
    }
    
    public void testCompleteFeatureName_11() throws Exception {
    	newBuilder(getXtextSetup())
	        .appendNl("grammar org.foo.bar with org.eclipse.xtext.common.Terminals")
	        .appendNl("generate metamodelA 'http://foo.bar/A'")
	        .appendNl("RuleA: name=ID;")
	        .append("RuleB returns RuleA: =>(name=ID);")
	        .assertTextAtCursorPosition("(",
	        		"=>",
	        		"name",
	        		"Feature",
	        		"\"Value\"",
	        		"RuleA",
	        		"RuleB",
	        		"ID",
	        		"STRING",
	        		"INT",
	        		"SL_COMMENT",
	        		"WS",
	        		"ML_COMMENT",
	        		"ANY_OTHER",
	        		"(");
    }
    
    public void testCompleteFeatureName_12() throws Exception {
    	newBuilder(getXtextSetup())
	        .appendNl("grammar org.foo.bar with org.eclipse.xtext.common.Terminals")
	        .appendNl("generate metamodelA 'http://foo.bar/A'")
	        .appendNl("RuleA: name=ID;")
	        .append("RuleB returns RuleA: =>  name=ID;")
	        .assertTextAtCursorPosition("  name",
	        		"=>",
	        		"name",
	        		"Feature",
	        		"\"Value\"",
	        		"RuleA",
	        		"RuleB",
	        		"ID",
	        		"STRING",
	        		"INT",
	        		"SL_COMMENT",
	        		"WS",
	        		"ML_COMMENT",
	        		"ANY_OTHER",
	        		"(");
    }
    
    public void testAliasCompletion_01() throws Exception {
    	newBuilder(getXtextSetup())
	        .appendNl("grammar org.foo.bar with org.eclipse.xtext.common.Terminals")
	        .appendNl("generate metamodelA 'http://foo.bar/A' as ")
	        .assertText(
	        		"Alias",
	        		"metamodelA");
    }
    
    public void testAliasCompletion_02() throws Exception {
    	newBuilder(getXtextSetup())
    		.appendNl("grammar org.xtext.example.MyDsl1 with org.eclipse.xtext.common.Terminals")
	        .appendNl("generate myDsl1 \"http://www.xtext.org/example/MyDsl1\"")
	        .appendNl("import 'classpath:/org/eclipse/xtext/xtext/ui/editor/contentassist/enumsUi.ecore' as")
	        .assertText("enumsUi", "Alias");
    }
    
    public void testCompleteTypeRef_01() throws Exception {
        doTestCompleteTypeRefWithAliasSetup()
                .append("NewRule returns ").assertText(
                                "myAlias::Class", "myAlias::Import", "myAlias::Model", "myAlias"
                );
    }
    
	public void testCompleteTypeRef_02() throws Exception {
		doTestCompleteTypeRefWithAliasSetup()
	        .append("NewRule returns mYal").assertText(
	                        "myAlias::Class", "myAlias::Import","myAlias::Model", "myAlias", ":", "::"
	        );
    }
	
    public void testCompleteTypeRef_03() throws Exception {
    	doTestCompleteTypeRefWithAliasSetup()
	        .append("NewRule returns cl").assertText(
	                        "myAlias::Class", ":", "::"
	        );
    }
    
    public void testCompleteTypeRef_04() throws Exception {
    	doTestCompleteTypeRefWithAliasSetup()
	        .append("NewRule returns my::Cl").assertText(
	                        "myAlias::Class", ":"
	        );
    }
    
    public void testCompleteTypeRef_05() throws Exception {
        doTestCompleteTypeRefWithAliasSetup()
                .append("NewRule returns myAlias::NewRule: reference=[").assertText(
                                "myAlias::Class", "myAlias::Import", "myAlias::Model", "myAlias", "myAlias::NewRule", "["
                );
    }
    
	public void testCompleteTypeRef_06() throws Exception {
		doTestCompleteTypeRefWithAliasSetup()
			.append("NewRule returns myAlias::NewRule: reference=[mYal").assertText(
	                        "myAlias::Class", "myAlias::Import","myAlias::Model", "myAlias::NewRule", "myAlias", "]", "::", "|"
	        );
    }
	
    public void testCompleteTypeRef_07() throws Exception {
    	doTestCompleteTypeRefWithAliasSetup()
    		.append("NewRule returns myAlias::NewRule: reference=[cl").assertText(
	                        "myAlias::Class", "]", "|", "::"
	        );
    }
    
    public void testCompleteTypeRef_08() throws Exception {
    	doTestCompleteTypeRefWithAliasSetup()
    		.append("NewRule returns myAlias::NewRule: reference=[my::Cl").assertText(
	                        "myAlias::Class", "]", "|"
	        );
    }
    
    public void testCompleteTypeRef_09() throws Exception {
        doTestCompleteTypeRefWithAliasSetup()
                .append("NewRule returns myAlias::NewRule: {").assertText(
                                "myAlias::Class", "myAlias::Import", "myAlias::Model", "myAlias", "myAlias::NewRule", "{"
                );
    }
    
	public void testCompleteTypeRef_10() throws Exception {
		doTestCompleteTypeRefWithAliasSetup()
			.append("NewRule returns myAlias::NewRule: {mYal").assertText(
	                        "myAlias::Class", "myAlias::Import","myAlias::Model", "myAlias::NewRule", "myAlias", "}", ".", "::"
	        );
    }
	
    public void testCompleteTypeRef_11() throws Exception {
    	doTestCompleteTypeRefWithAliasSetup()
    		.append("NewRule returns myAlias::NewRule: {cl").assertText(
	                        "myAlias::Class", "}", "::", "."
	        );
    }
    
    public void testCompleteTypeRef_12() throws Exception {
    	doTestCompleteTypeRefWithAliasSetup()
    		.append("NewRule returns myAlias::NewRule: {my::Cl").assertText(
	                        "myAlias::Class", "}", "."
	        );
    }
    
    private ContentAssistProcessorTestBuilder doTestCompleteTypeRefSetup() throws Exception {
		return newBuilder(getXtextSetup())
			.appendNl("grammar org.xtext.example.MyDsl1 with org.eclipse.xtext.common.Terminals")
	        .appendNl("generate myDsl1 \"http://www.xtext.org/example/MyDsl1\"")
	        .appendNl("Model :")
	        .appendNl("(imports+=Import)*")
	        .appendNl("(elements+=Class)*;")
	        .appendNl("Import :")
	        .appendNl("'import' importURI=STRING;")
	        .appendNl("Class :")
	        .appendNl("'class' name=ID ('extends' references=[Class])?;");
	}
    
    private ContentAssistProcessorTestBuilder doTestCompleteTypeRefWithAliasSetup() throws Exception {
		return newBuilder(getXtextSetup())
	        .appendNl("grammar org.xtext.example.MyDsl1 with org.eclipse.xtext.common.Terminals")
	        .appendNl("generate myDsl1 \"http://www.xtext.org/example/MyDsl1\" as myAlias")
	        .appendNl("Model returns myAlias::Model:")
	        .appendNl("(imports+=Import)*")
	        .appendNl("(elements+=Class)*;")
	        .appendNl("Import returns myAlias::Import:")
	        .appendNl("'import' importURI=STRING;")
	        .appendNl("Class returns myAlias::Class:")
	        .appendNl("'class' name=ID ('extends' references=[Class])?;");
	}
    
    public void testCompleteRuleForReferencedType() throws Exception {
    	newBuilder(getXtextSetup())
			.appendNl("grammar Foo with org.eclipse.xtext.common.Terminals")
			.appendNl("import \"http://www.eclipse.org/emf/2002/Ecore\"") 
			.append("EPac")
			.assertText("EPackage: \n;\n", ":");
    	newBuilder(getXtextSetup())
    		.appendNl("grammar Foo with org.eclipse.xtext.common.Terminals")
    		.appendNl("import \"http://www.eclipse.org/emf/2002/Ecore\"") 
    		.appendNl("FooBar returns EPackage: 'bar';") 
    		.append("EPac")
    		.assertText(":");
    }

    public void testCompleteRuleForReferencedTypeWithAlias() throws Exception {
    	newBuilder(getXtextSetup())
    		.appendNl("grammar Foo with org.eclipse.xtext.common.Terminals")
    		.appendNl("import \"http://www.eclipse.org/emf/2002/Ecore\" as ecore") 
    		.append("EPac")
    		.assertText("EPackage returns ecore::EPackage: \n;\n", ":");
    	newBuilder(getXtextSetup())
    		.appendNl("grammar Foo with org.eclipse.xtext.common.Terminals")
    		.appendNl("import \"http://www.eclipse.org/emf/2002/Ecore\" as ecore") 
    		.appendNl("FooBar returns ecore::EPackage : 'bar';") 
    		.append("EPac")
    		.assertText(":");
    }
    
    public void testBug317280_01() throws Exception {
    	newBuilder(getXtextSetup())
    		.appendNl("grammar org.xtext.example.MyDsl1 with org.eclipse.xtext.common.Terminals")
	        .appendNl("import 'classpath:/org/eclipse/xtext/Xtext.ecore'")
	        .append("En")
	        .assertText("EnumRule: \n;\n", "EnumLiteralDeclaration: \n;\n", 
	        		"enum", 
	        		":");
    }
    
    public void testBug317280_02() throws Exception {
    	newBuilder(getXtextSetup())
    		.appendNl("grammar org.xtext.example.MyDsl1 with org.eclipse.xtext.common.Terminals")
	        .appendNl("import 'classpath:/org/eclipse/xtext/Xtext.ecore' as xtext")
	        .append("En")
	        .assertText("EnumRule returns xtext::EnumRule: \n;\n", 
	        		"EnumLiteralDeclaration returns xtext::EnumLiteralDeclaration: \n;\n", 
	        		"enum",
	        		":");
    }

    public void testOverrideRule_01() throws Exception {
    	newBuilder(getXtextSetup())
    		.appendNl("grammar org.xtext.example.MyDsl1 with org.eclipse.xtext.common.Terminals")
	        .append("ST")
	        .assertText("terminal STRING:\n\t\n;\n", ":");
    }
    
    public void testOverrideRule_02() throws Exception {
    	newBuilder(getXtextSetup())
    		.appendNl("grammar org.xtext.example.MyDsl1 with org.eclipse.xtext.common.Terminals")
	        .append("IN")
	        .assertText("terminal INT returns ecore::EInt:\n\t\n;\n", ":");
    }
    
    public void testOverrideRule_03() throws Exception {
    	newBuilder(getXtextSetup())
    		.appendNl("grammar org.xtext.example.MyDsl1 with org.eclipse.xtext.common.Terminals")
    		.appendNl("import \"http://www.eclipse.org/emf/2002/Ecore\" as ec")
	        .append("IN")
	        .assertText("terminal INT returns ec::EInt:\n\t\n;\n", ":");
    }
    
    public void testOverrideRule_04() throws Exception {
    	newBuilder(getXtextSetup())
    		.appendNl("grammar org.xtext.example.MyDsl1 with org.eclipse.xtext.common.Terminals")
    		.appendNl("import \"http://www.eclipse.org/emf/2002/Ecore\"")
	        .append("IN")
	        .assertText("terminal INT returns EInt:\n\t\n;\n", ":");
    }
    
    public void testOverrideRule_05() throws Exception {
    	newBuilder(getXtextSetup())
	    	.appendNl("grammar org.xtext.example.MyDsl1 with org.eclipse.xtext.xtext.ui.editor.contentassist.GrammarWithTerminalFragment")
	    	.append("ESC")
	    	.assertText("terminal fragment ESCAPED_CHAR:\n\t\n;\n", ":");
    }
    
    public void testCompleteHiddenTokens_01() throws Exception {
    	newBuilder(getXtextSetup())
	    	.appendNl("grammar org.xtext.example.MyDsl1 with org.eclipse.xtext.common.Terminals hidden()")
	    	.appendNl("Model: name=ID;")
	    	.appendNl("DataType: ID;")
	    	.appendNl("terminal fragment Fragment: 'a'..'z';")
	    	.appendNl("terminal Terminal: 'a'..'z';")
	    	.assertTextAtCursorPosition("hidden(", "hidden(".length(), 
	    			"(", 
	    			")", 
	    			"WS", 
	    			"SL_COMMENT", 
	    			"ML_COMMENT",
	    			"ID",
	    			"INT",
	    			"ANY_OTHER",
	    			"STRING",
	    			"Terminal");
    }
    
    public void testCompleteHiddenTokens_02() throws Exception {
    	newBuilder(getXtextSetup())
	    	.appendNl("grammar org.xtext.example.MyDsl1 with org.eclipse.xtext.common.Terminals")
	    	.appendNl("Model hidden(): name=ID;")
	    	.appendNl("DataType: ID;")
	    	.appendNl("terminal fragment Fragment: 'a'..'z';")
	    	.appendNl("terminal Terminal: 'a'..'z';")
	    	.assertTextAtCursorPosition("hidden(", "hidden(".length(), 
	    			"(", 
	    			")", 
	    			"WS", 
	    			"SL_COMMENT", 
	    			"ML_COMMENT",
	    			"ID",
	    			"INT",
	    			"ANY_OTHER",
	    			"STRING",
	    			"Terminal");
    }
    
    public void testCompleteRuleCall_01() throws Exception {
    	newBuilder(getXtextSetup())
	    	.appendNl("grammar org.xtext.example.MyDsl1 with org.eclipse.xtext.common.Terminals")
	    	.appendNl("generate test 'http://test'")
	    	.appendNl("Model: name=ID;")
	    	.appendNl("DataType: ID;")
	    	.appendNl("enum EnumType: A | B;")
	    	.appendNl("terminal fragment Fragment: 'a'..'z';")
	    	.appendNl("terminal Terminal: 'a'..'z';")
	    	.assertTextAtCursorPosition("ID",
	    			"=>",
	    			"=", 
	    			"[",
	    			"(",
	    			"\"Value\"",
	    			"WS", 
	    			"SL_COMMENT", 
	    			"ML_COMMENT",
	    			"ID",
	    			"INT",
	    			"ANY_OTHER",
	    			"STRING",
    				"Terminal",
    				"DataType",
    				"Model",
    				"EnumType");
    }
    
    public void testCompleteRuleCall_02() throws Exception {
    	newBuilder(getXtextSetup())
	    	.appendNl("grammar org.xtext.example.MyDsl1 with org.eclipse.xtext.common.Terminals")
	    	.appendNl("generate test 'http://test'")
	    	.appendNl("Model: name=STRING;")
	    	.appendNl("DataType: STRING;")
	    	.appendNl("enum EnumType: A | B;")
	    	.appendNl("terminal fragment Fragment: 'a'..'z';")
	    	.appendNl("terminal Terminal: ID;")
	    	.assertTextAtCursorPosition("ID", 
	    			"\"Value\"", 
	    			"(",
	    			"->",
	    			".",
	    			"!",
	    			"WS", 
	    			"SL_COMMENT", 
	    			"ML_COMMENT",
	    			"ID",
	    			"INT",
	    			"ANY_OTHER",
	    			"STRING",
    				"Terminal",
    				"Fragment",
    				"EOF");
    }
    
}
