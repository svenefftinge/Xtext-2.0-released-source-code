/*
* generated by Xtext
*/

package org.eclipse.xtext.ui.tests.quickfix.services;

import com.google.inject.Singleton;
import com.google.inject.Inject;

import org.eclipse.xtext.*;
import org.eclipse.xtext.service.GrammarProvider;
import org.eclipse.xtext.service.AbstractElementFinder.*;

import org.eclipse.xtext.common.services.TerminalsGrammarAccess;

@Singleton
public class QuickfixCrossrefTestLanguageGrammarAccess extends AbstractGrammarElementFinder {
	
	
	public class MainElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Main");
		private final Assignment cElementsAssignment = (Assignment)rule.eContents().get(1);
		private final RuleCall cElementsElementParserRuleCall_0 = (RuleCall)cElementsAssignment.eContents().get(0);
		
		//Main:
		//	elements+=Element*;
		public ParserRule getRule() { return rule; }

		//elements+=Element*
		public Assignment getElementsAssignment() { return cElementsAssignment; }

		//Element
		public RuleCall getElementsElementParserRuleCall_0() { return cElementsElementParserRuleCall_0; }
	}

	public class ElementElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Element");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cNameAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cNameIDTerminalRuleCall_0_0 = (RuleCall)cNameAssignment_0.eContents().get(0);
		private final Keyword cLeftCurlyBracketKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cContainedAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cContainedElementParserRuleCall_2_0 = (RuleCall)cContainedAssignment_2.eContents().get(0);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Keyword cRefKeyword_3_0 = (Keyword)cGroup_3.eContents().get(0);
		private final Assignment cReferencedAssignment_3_1 = (Assignment)cGroup_3.eContents().get(1);
		private final CrossReference cReferencedElementCrossReference_3_1_0 = (CrossReference)cReferencedAssignment_3_1.eContents().get(0);
		private final RuleCall cReferencedElementIDTerminalRuleCall_3_1_0_1 = (RuleCall)cReferencedElementCrossReference_3_1_0.eContents().get(1);
		private final Keyword cRightCurlyBracketKeyword_4 = (Keyword)cGroup.eContents().get(4);
		
		//Element:
		//	name=ID "{" contained+=Element* ("ref" referenced+=[Element])* "}";
		public ParserRule getRule() { return rule; }

		//name=ID "{" contained+=Element* ("ref" referenced+=[Element])* "}"
		public Group getGroup() { return cGroup; }

		//name=ID
		public Assignment getNameAssignment_0() { return cNameAssignment_0; }

		//ID
		public RuleCall getNameIDTerminalRuleCall_0_0() { return cNameIDTerminalRuleCall_0_0; }

		//"{"
		public Keyword getLeftCurlyBracketKeyword_1() { return cLeftCurlyBracketKeyword_1; }

		//contained+=Element*
		public Assignment getContainedAssignment_2() { return cContainedAssignment_2; }

		//Element
		public RuleCall getContainedElementParserRuleCall_2_0() { return cContainedElementParserRuleCall_2_0; }

		//("ref" referenced+=[Element])*
		public Group getGroup_3() { return cGroup_3; }

		//"ref"
		public Keyword getRefKeyword_3_0() { return cRefKeyword_3_0; }

		//referenced+=[Element]
		public Assignment getReferencedAssignment_3_1() { return cReferencedAssignment_3_1; }

		//[Element]
		public CrossReference getReferencedElementCrossReference_3_1_0() { return cReferencedElementCrossReference_3_1_0; }

		//ID
		public RuleCall getReferencedElementIDTerminalRuleCall_3_1_0_1() { return cReferencedElementIDTerminalRuleCall_3_1_0_1; }

		//"}"
		public Keyword getRightCurlyBracketKeyword_4() { return cRightCurlyBracketKeyword_4; }
	}
	
	
	private MainElements pMain;
	private ElementElements pElement;
	
	private final GrammarProvider grammarProvider;

	private TerminalsGrammarAccess gaTerminals;

	@Inject
	public QuickfixCrossrefTestLanguageGrammarAccess(GrammarProvider grammarProvider,
		TerminalsGrammarAccess gaTerminals) {
		this.grammarProvider = grammarProvider;
		this.gaTerminals = gaTerminals;
	}
	
	public Grammar getGrammar() {	
		return grammarProvider.getGrammar(this);
	}
	

	public TerminalsGrammarAccess getTerminalsGrammarAccess() {
		return gaTerminals;
	}

	
	//Main:
	//	elements+=Element*;
	public MainElements getMainAccess() {
		return (pMain != null) ? pMain : (pMain = new MainElements());
	}
	
	public ParserRule getMainRule() {
		return getMainAccess().getRule();
	}

	//Element:
	//	name=ID "{" contained+=Element* ("ref" referenced+=[Element])* "}";
	public ElementElements getElementAccess() {
		return (pElement != null) ? pElement : (pElement = new ElementElements());
	}
	
	public ParserRule getElementRule() {
		return getElementAccess().getRule();
	}

	//terminal ID:
	//	"^"? ("a".."z" | "A".."Z" | "_") ("a".."z" | "A".."Z" | "_" | "0".."9")*;
	public TerminalRule getIDRule() {
		return gaTerminals.getIDRule();
	} 

	//terminal INT returns ecore::EInt:
	//	"0".."9"+;
	public TerminalRule getINTRule() {
		return gaTerminals.getINTRule();
	} 

	//terminal STRING:
	//	"\"" ("\\" ("b" | "t" | "n" | "f" | "r" | "u" | "\"" | "\'" | "\\") | !("\\" | "\""))* "\"" | "\'" ("\\" ("b" | "t" |
	//	"n" | "f" | "r" | "u" | "\"" | "\'" | "\\") | !("\\" | "\'"))* "\'";
	public TerminalRule getSTRINGRule() {
		return gaTerminals.getSTRINGRule();
	} 

	//terminal ML_COMMENT:
	//	"/ *"->"* /";
	public TerminalRule getML_COMMENTRule() {
		return gaTerminals.getML_COMMENTRule();
	} 

	//terminal SL_COMMENT:
	//	"//" !("\n" | "\r")* ("\r"? "\n")?;
	public TerminalRule getSL_COMMENTRule() {
		return gaTerminals.getSL_COMMENTRule();
	} 

	//terminal WS:
	//	(" " | "\t" | "\r" | "\n")+;
	public TerminalRule getWSRule() {
		return gaTerminals.getWSRule();
	} 

	//terminal ANY_OTHER:
	//	.;
	public TerminalRule getANY_OTHERRule() {
		return gaTerminals.getANY_OTHERRule();
	} 
}
