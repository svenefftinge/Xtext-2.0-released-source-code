/*
* generated by Xtext
*/

package org.eclipse.xtext.resource.services;

import com.google.inject.Singleton;
import com.google.inject.Inject;

import org.eclipse.xtext.*;
import org.eclipse.xtext.service.GrammarProvider;
import org.eclipse.xtext.service.AbstractElementFinder.*;

import org.eclipse.xtext.common.services.TerminalsGrammarAccess;

@Singleton
public class EObjectAtOffsetTestLanguageGrammarAccess extends AbstractGrammarElementFinder {
	
	
	public class ModelElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Model");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final Assignment cFoosAssignment_0 = (Assignment)cAlternatives.eContents().get(0);
		private final RuleCall cFoosFooParserRuleCall_0_0 = (RuleCall)cFoosAssignment_0.eContents().get(0);
		private final Assignment cBarsAssignment_1 = (Assignment)cAlternatives.eContents().get(1);
		private final RuleCall cBarsAbstractBarParserRuleCall_1_0 = (RuleCall)cBarsAssignment_1.eContents().get(0);
		
		//Model:
		//	(foos+=Foo | bars+=AbstractBar)*;
		public ParserRule getRule() { return rule; }

		//(foos+=Foo | bars+=AbstractBar)*
		public Alternatives getAlternatives() { return cAlternatives; }

		//foos+=Foo
		public Assignment getFoosAssignment_0() { return cFoosAssignment_0; }

		//Foo
		public RuleCall getFoosFooParserRuleCall_0_0() { return cFoosFooParserRuleCall_0_0; }

		//bars+=AbstractBar
		public Assignment getBarsAssignment_1() { return cBarsAssignment_1; }

		//AbstractBar
		public RuleCall getBarsAbstractBarParserRuleCall_1_0() { return cBarsAbstractBarParserRuleCall_1_0; }
	}

	public class AbstractBarElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "AbstractBar");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cBarParserRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Action cFooBarBarAction_1_0 = (Action)cGroup_1.eContents().get(0);
		private final Keyword cFoobarKeyword_1_1 = (Keyword)cGroup_1.eContents().get(1);
		private final Assignment cFooAssignment_1_2 = (Assignment)cGroup_1.eContents().get(2);
		private final CrossReference cFooFooCrossReference_1_2_0 = (CrossReference)cFooAssignment_1_2.eContents().get(0);
		private final RuleCall cFooFooIDTerminalRuleCall_1_2_0_1 = (RuleCall)cFooFooCrossReference_1_2_0.eContents().get(1);
		
		//AbstractBar:
		//	Bar ({FooBar.bar=current} "foobar" foo+=[Foo])?;
		public ParserRule getRule() { return rule; }

		//Bar ({FooBar.bar=current} "foobar" foo+=[Foo])?
		public Group getGroup() { return cGroup; }

		//Bar
		public RuleCall getBarParserRuleCall_0() { return cBarParserRuleCall_0; }

		//({FooBar.bar=current} "foobar" foo+=[Foo])?
		public Group getGroup_1() { return cGroup_1; }

		//{FooBar.bar=current}
		public Action getFooBarBarAction_1_0() { return cFooBarBarAction_1_0; }

		//"foobar"
		public Keyword getFoobarKeyword_1_1() { return cFoobarKeyword_1_1; }

		//foo+=[Foo]
		public Assignment getFooAssignment_1_2() { return cFooAssignment_1_2; }

		//[Foo]
		public CrossReference getFooFooCrossReference_1_2_0() { return cFooFooCrossReference_1_2_0; }

		//ID
		public RuleCall getFooFooIDTerminalRuleCall_1_2_0_1() { return cFooFooIDTerminalRuleCall_1_2_0_1; }
	}

	public class BarElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Bar");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cBarKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameIDTerminalRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Assignment cFooAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final CrossReference cFooFooCrossReference_2_0 = (CrossReference)cFooAssignment_2.eContents().get(0);
		private final RuleCall cFooFooIDTerminalRuleCall_2_0_1 = (RuleCall)cFooFooCrossReference_2_0.eContents().get(1);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Keyword cCommaKeyword_3_0 = (Keyword)cGroup_3.eContents().get(0);
		private final Assignment cFooAssignment_3_1 = (Assignment)cGroup_3.eContents().get(1);
		private final CrossReference cFooFooCrossReference_3_1_0 = (CrossReference)cFooAssignment_3_1.eContents().get(0);
		private final RuleCall cFooFooIDTerminalRuleCall_3_1_0_1 = (RuleCall)cFooFooCrossReference_3_1_0.eContents().get(1);
		
		//Bar:
		//	"bar" name=ID foo+=[Foo] ("," foo+=[Foo])*;
		public ParserRule getRule() { return rule; }

		//"bar" name=ID foo+=[Foo] ("," foo+=[Foo])*
		public Group getGroup() { return cGroup; }

		//"bar"
		public Keyword getBarKeyword_0() { return cBarKeyword_0; }

		//name=ID
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }

		//ID
		public RuleCall getNameIDTerminalRuleCall_1_0() { return cNameIDTerminalRuleCall_1_0; }

		//foo+=[Foo]
		public Assignment getFooAssignment_2() { return cFooAssignment_2; }

		//[Foo]
		public CrossReference getFooFooCrossReference_2_0() { return cFooFooCrossReference_2_0; }

		//ID
		public RuleCall getFooFooIDTerminalRuleCall_2_0_1() { return cFooFooIDTerminalRuleCall_2_0_1; }

		//("," foo+=[Foo])*
		public Group getGroup_3() { return cGroup_3; }

		//","
		public Keyword getCommaKeyword_3_0() { return cCommaKeyword_3_0; }

		//foo+=[Foo]
		public Assignment getFooAssignment_3_1() { return cFooAssignment_3_1; }

		//[Foo]
		public CrossReference getFooFooCrossReference_3_1_0() { return cFooFooCrossReference_3_1_0; }

		//ID
		public RuleCall getFooFooIDTerminalRuleCall_3_1_0_1() { return cFooFooIDTerminalRuleCall_3_1_0_1; }
	}

	public class FooElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Foo");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cFooKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameIDTerminalRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		
		//Foo:
		//	"foo" name=ID;
		public ParserRule getRule() { return rule; }

		//"foo" name=ID
		public Group getGroup() { return cGroup; }

		//"foo"
		public Keyword getFooKeyword_0() { return cFooKeyword_0; }

		//name=ID
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }

		//ID
		public RuleCall getNameIDTerminalRuleCall_1_0() { return cNameIDTerminalRuleCall_1_0; }
	}
	
	
	private ModelElements pModel;
	private AbstractBarElements pAbstractBar;
	private BarElements pBar;
	private FooElements pFoo;
	
	private final GrammarProvider grammarProvider;

	private TerminalsGrammarAccess gaTerminals;

	@Inject
	public EObjectAtOffsetTestLanguageGrammarAccess(GrammarProvider grammarProvider,
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

	
	//Model:
	//	(foos+=Foo | bars+=AbstractBar)*;
	public ModelElements getModelAccess() {
		return (pModel != null) ? pModel : (pModel = new ModelElements());
	}
	
	public ParserRule getModelRule() {
		return getModelAccess().getRule();
	}

	//AbstractBar:
	//	Bar ({FooBar.bar=current} "foobar" foo+=[Foo])?;
	public AbstractBarElements getAbstractBarAccess() {
		return (pAbstractBar != null) ? pAbstractBar : (pAbstractBar = new AbstractBarElements());
	}
	
	public ParserRule getAbstractBarRule() {
		return getAbstractBarAccess().getRule();
	}

	//Bar:
	//	"bar" name=ID foo+=[Foo] ("," foo+=[Foo])*;
	public BarElements getBarAccess() {
		return (pBar != null) ? pBar : (pBar = new BarElements());
	}
	
	public ParserRule getBarRule() {
		return getBarAccess().getRule();
	}

	//Foo:
	//	"foo" name=ID;
	public FooElements getFooAccess() {
		return (pFoo != null) ? pFoo : (pFoo = new FooElements());
	}
	
	public ParserRule getFooRule() {
		return getFooAccess().getRule();
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
