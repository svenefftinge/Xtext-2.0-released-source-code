/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xbase.tests.parser;

import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.xbase.XAbstractFeatureCall;
import org.eclipse.xtext.xbase.XAssignment;
import org.eclipse.xtext.xbase.XBinaryOperation;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.XBooleanLiteral;
import org.eclipse.xtext.xbase.XCasePart;
import org.eclipse.xtext.xbase.XCastedExpression;
import org.eclipse.xtext.xbase.XCatchClause;
import org.eclipse.xtext.xbase.XClosure;
import org.eclipse.xtext.xbase.XConstructorCall;
import org.eclipse.xtext.xbase.XDoWhileExpression;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XFeatureCall;
import org.eclipse.xtext.xbase.XForLoopExpression;
import org.eclipse.xtext.xbase.XIfExpression;
import org.eclipse.xtext.xbase.XInstanceOfExpression;
import org.eclipse.xtext.xbase.XIntLiteral;
import org.eclipse.xtext.xbase.XMemberFeatureCall;
import org.eclipse.xtext.xbase.XReturnExpression;
import org.eclipse.xtext.xbase.XStringLiteral;
import org.eclipse.xtext.xbase.XSwitchExpression;
import org.eclipse.xtext.xbase.XThrowExpression;
import org.eclipse.xtext.xbase.XTryCatchFinallyExpression;
import org.eclipse.xtext.xbase.XTypeLiteral;
import org.eclipse.xtext.xbase.XUnaryOperation;
import org.eclipse.xtext.xbase.XVariableDeclaration;
import org.eclipse.xtext.xbase.XWhileExpression;
import org.eclipse.xtext.xbase.tests.AbstractXbaseTestCase;
import org.eclipse.xtext.xtype.XFunctionTypeRef;

/**
 * @author Sven Efftinge
 */
public class XbaseParserTest extends AbstractXbaseTestCase {
	
	@Override
	protected XExpression expression(String string) throws Exception {
		return super.expression(string, false);
	}
	
	public void testAssignment_RightAssociativity() throws Exception {
		XAssignment ass = (XAssignment) expression("foo = bar += baz");
		assertEquals(1,ass.getExplicitArguments().size());
		assertEquals("foo", ass.getConcreteSyntaxFeatureName());
		assertNull(ass.getAssignable());
		XBinaryOperation op = (XBinaryOperation) ass.getExplicitArguments().get(0);
		assertEquals(2,op.getExplicitArguments().size());
		assertEquals("+=", op.getConcreteSyntaxFeatureName());
		assertEquals("bar", ((XFeatureCall)op.getExplicitArguments().get(0)).getConcreteSyntaxFeatureName());
		assertEquals("baz", ((XFeatureCall)op.getExplicitArguments().get(1)).getConcreteSyntaxFeatureName());
	}
	
	public void testAssignments_00() throws Exception {
		expression("(foo).bar = baz");
	}
	
	public void testOrAndAndPrecedence() throws Exception {
		XBinaryOperation or = (XBinaryOperation) expression("foo && bar || baz");
		assertEquals(2,or.getExplicitArguments().size());
		assertEquals("||", or.getConcreteSyntaxFeatureName());
		assertEquals("baz", ((XFeatureCall)or.getExplicitArguments().get(1)).getConcreteSyntaxFeatureName());
		XBinaryOperation and = (XBinaryOperation) or.getExplicitArguments().get(0);
		assertEquals(2,and.getExplicitArguments().size());
		assertEquals("&&", and.getConcreteSyntaxFeatureName());
		assertEquals("foo", ((XFeatureCall)and.getExplicitArguments().get(0)).getConcreteSyntaxFeatureName());
		assertEquals("bar", ((XFeatureCall)and.getExplicitArguments().get(1)).getConcreteSyntaxFeatureName());
	}
	
	public void testAddition_1() throws Exception {
		XBinaryOperation operation = (XBinaryOperation) expression("3 + 4");
		assertEquals(3, ((XIntLiteral) operation.getExplicitArguments().get(0)).getValue());
		assertEquals(4, ((XIntLiteral) operation.getExplicitArguments().get(1)).getValue());
	}

	public void testAddition_2() throws Exception {
		XBinaryOperation operation = (XBinaryOperation) expression("foo + 'bar'");
		assertEquals("foo", ((XFeatureCall) operation.getExplicitArguments().get(0)).getConcreteSyntaxFeatureName());
		assertEquals("bar", ((XStringLiteral) operation.getExplicitArguments().get(1)).getValue());
	}

	public void testStringLiteral() throws Exception {
		XStringLiteral literal = (XStringLiteral) expression("'bar'");
		assertEquals("bar", literal.getValue());
	}

	public void testBooleanLiteral() throws Exception {
		XBooleanLiteral literal = (XBooleanLiteral) expression("true");
		assertTrue(literal.isIsTrue());
		literal = (XBooleanLiteral) expression("false");
		assertFalse(literal.isIsTrue());
	}

	public void testClosure_1() throws Exception {
		XClosure closure = (XClosure) expression("[|'foo']");
		assertEquals("foo", ((XStringLiteral) closure.getExpression())
				.getValue());
	}

	public void testClosure_2() throws Exception {
		XClosure closure = (XClosure) expression("[bar|'foo']");
		assertEquals("foo", ((XStringLiteral) closure.getExpression())
				.getValue());
		assertEquals("bar", closure.getFormalParameters().get(0).getName());
		assertNull(closure.getFormalParameters().get(0).getParameterType());
	}

	public void testClosure_3() throws Exception {
		XClosure closure = (XClosure) expression("[String bar|'foo']");
		assertEquals("foo", ((XStringLiteral) closure.getExpression())
				.getValue());
		assertEquals("bar", closure.getFormalParameters().get(0).getName());
		assertEquals(1, closure.getFormalParameters().size());
		assertNotNull(closure.getFormalParameters().get(0).getParameterType());
	}

	public void testClosure_4() throws Exception {
		XClosure closure = (XClosure) expression("[foo, String bar|'foo']");
		assertEquals("foo", ((XStringLiteral) closure.getExpression())
				.getValue());
		assertEquals("foo", closure.getFormalParameters().get(0).getName());
		assertEquals("bar", closure.getFormalParameters().get(1).getName());
		assertEquals(2, closure.getFormalParameters().size());
		assertNull(closure.getFormalParameters().get(0).getParameterType());
		assertNotNull(closure.getFormalParameters().get(1).getParameterType());
	}
	
	public void testClosure_5() throws Exception {
		XClosure closure = (XClosure) expression("[(String) => String mapper|mapper('something')]");
		assertTrue(closure.getExpression() instanceof XFeatureCall);
		JvmFormalParameter formalParameter = closure.getFormalParameters().get(0);
		assertEquals("mapper", formalParameter.getName());
		assertTrue(formalParameter.getParameterType() instanceof XFunctionTypeRef);
	}
	
	public void testClosure_6() throws Exception {
		XClosure closure = (XClosure) expression("([(String) => String mapper|mapper('something')])");
		assertTrue(closure.getExpression() instanceof XFeatureCall);
		JvmFormalParameter formalParameter = closure.getFormalParameters().get(0);
		assertEquals("mapper", formalParameter.getName());
		assertTrue(formalParameter.getParameterType() instanceof XFunctionTypeRef);
	}

	public void testShortClosure_1() throws Exception {
		XFeatureCall featureCall = (XFeatureCall) expression("foo(a,b|a+b)");
		assertEquals(1, featureCall.getExplicitArguments().size());
		assertTrue(featureCall.getExplicitArguments().get(0) instanceof XClosure);
		XClosure closure = (XClosure) featureCall.getExplicitArguments().get(0);
		assertEquals(2, closure.getFormalParameters().size());
	}
	
	public void testShortClosure_2() throws Exception {
		XMemberFeatureCall featureCall = (XMemberFeatureCall) expression("foo.bar(a,b|a+b)");
		assertEquals(1, featureCall.getMemberCallArguments().size());
		assertTrue(featureCall.getMemberCallArguments().get(0) instanceof XClosure);
		XClosure closure = (XClosure) featureCall.getMemberCallArguments().get(0);
		assertEquals(2, closure.getFormalParameters().size());
	}
	
	public void testShortClosure_3() throws Exception {
		XConstructorCall featureCall = (XConstructorCall) expression("new Something(a,b|a+b)");
		assertEquals(1, featureCall.getArguments().size());
		assertTrue(featureCall.getArguments().get(0) instanceof XClosure);
		XClosure closure = (XClosure) featureCall.getArguments().get(0);
		assertEquals(2, closure.getFormalParameters().size());
	}
	
	public void testCastedExpression() throws Exception {
		XCastedExpression cast = (XCastedExpression) expression("bar as Foo");
		assertTrue(cast.getTarget() instanceof XFeatureCall);
		assertNotNull(cast.getType());
	}

	public void testCastedExpression_1() throws Exception {
		XFeatureCall call = (XFeatureCall) expression("(Foo)");
		assertNotNull(call);
	}

	public void testCastedExpression_2() throws Exception {
		XBinaryOperation binary = (XBinaryOperation) expression("bar.baz as Foo + zonk");
		assertTrue(binary.getExplicitArguments().get(0) instanceof XCastedExpression);
		assertTrue(binary.getExplicitArguments().get(1) instanceof XFeatureCall);
	}
	
	public void testUnaryOperation() throws Exception {
		XUnaryOperation call = (XUnaryOperation) expression("-(Foo)");
		assertNotNull(call);
	}
	public void testUnaryOperation_2() throws Exception {
		XUnaryOperation call = (XUnaryOperation) expression("-foo");
		assertNotNull(call);
	}
	public void testUnaryOperation_3() throws Exception {
		XBinaryOperation call = (XBinaryOperation) expression("foo+-bar");
		assertEquals("+",call.getConcreteSyntaxFeatureName());
		assertEquals(2,call.getExplicitArguments().size());
		XUnaryOperation unary = (XUnaryOperation) call.getExplicitArguments().get(1);
		assertEquals("-", unary.getConcreteSyntaxFeatureName());
		assertEquals("bar", ((XFeatureCall)unary.getExplicitArguments().get(0)).getConcreteSyntaxFeatureName());
	}

	public void testFeatureCall_0() throws Exception {
		XFeatureCall call = (XFeatureCall) expression("foo");
		assertNotNull(call);
	}

	public void testFeatureCall_1() throws Exception {
		XMemberFeatureCall call = (XMemberFeatureCall) expression("foo.bar");
		assertNotNull(call);
		assertTrue(call.getExplicitArguments().get(0) instanceof XFeatureCall);
	}

	public void testFeatureCall_2() throws Exception {
		XMemberFeatureCall call = (XMemberFeatureCall) expression("'holla'.bar()");
		assertNotNull(call);
		assertEquals(1, call.getExplicitArguments().size());
		assertTrue(call.getExplicitArguments().get(0) instanceof XStringLiteral);
	}

	public void testFeatureCall_3() throws Exception {
		XMemberFeatureCall call = (XMemberFeatureCall) expression("'holla'.bar(4)");
		assertNotNull(call);
		assertEquals(2, call.getExplicitArguments().size());
		assertEquals(4, ((XIntLiteral) call.getExplicitArguments().get(1)).getValue());
		assertTrue(call.getExplicitArguments().get(0) instanceof XStringLiteral);
	}

	public void testFeatureCall_4() throws Exception {
		XFeatureCall call = (XFeatureCall) expression("bar(0,1,2)");
		assertNotNull(call);
		assertEquals("bar",call.getConcreteSyntaxFeatureName());
		assertEquals(3, call.getExplicitArguments().size());
		for (int i = 0; i < 3; i++)
			assertEquals(i, ((XIntLiteral) call.getExplicitArguments().get(i)).getValue());
	}
	
	public void testFeatureCall_5() throws Exception {
		XFeatureCall call = (XFeatureCall) expression("foo(bar(baz(1)))");
		assertEquals("foo",call.getConcreteSyntaxFeatureName());
		call = (XFeatureCall) call.getExplicitArguments().get(0);
		assertEquals("bar",call.getConcreteSyntaxFeatureName());
		call = (XFeatureCall) call.getExplicitArguments().get(0);
		assertEquals("baz",call.getConcreteSyntaxFeatureName());
		assertTrue(call.getExplicitArguments().get(0) instanceof XIntLiteral);
	}
	
	public void testStaticFeatureCall_0() throws Exception {
		XFeatureCall call = (XFeatureCall) expression("String::valueOf('')");
		assertNotNull(call.getDeclaringType());
		assertEquals("java.lang.String", call.getDeclaringType().getQualifiedName());
		assertEquals("java.lang.String.valueOf(java.lang.Object)", call.getFeature().getIdentifier());
	}
	
	public void testStaticFeatureCall_1() throws Exception {
		XFeatureCall call = (XFeatureCall) expression("String::CASE_INSENSITIVE_ORDER");
		assertNotNull(call.getDeclaringType());
		assertEquals("java.lang.String", call.getDeclaringType().getQualifiedName());
		assertEquals("java.lang.String.CASE_INSENSITIVE_ORDER", call.getFeature().getIdentifier());
	}

	public void testStaticFeatureCall_2() throws Exception {
		XFeatureCall call = (XFeatureCall) expression("java::lang::String::valueOf('')");
		assertNotNull(call.getDeclaringType());
		assertEquals("java.lang.String", call.getDeclaringType().getQualifiedName());
		assertEquals("java.lang.String.valueOf(java.lang.Object)", call.getFeature().getIdentifier());
	}
	
	public void testStaticFeatureCall_3() throws Exception {
		XFeatureCall call = (XFeatureCall) expression("java::lang::String::CASE_INSENSITIVE_ORDER");
		assertNotNull(call.getDeclaringType());
		assertEquals("java.lang.String", call.getDeclaringType().getQualifiedName());
		assertEquals("java.lang.String.CASE_INSENSITIVE_ORDER", call.getFeature().getIdentifier());
	}
	
	public void testMemberFeatureCall_00() throws Exception {
		XMemberFeatureCall call = (XMemberFeatureCall) expression("'holla'?.bar(4)");
		assertTrue(call.isNullSafe());
		assertFalse(call.isSpreading());
	}
	public void testMemberFeatureCall_01() throws Exception {
		XMemberFeatureCall call = (XMemberFeatureCall) expression("somList*.bar(4)");
		assertFalse(call.isNullSafe());
		assertTrue(call.isSpreading());
	}

	public void testIf_0() throws Exception {
		XIfExpression ie = (XIfExpression) expression("if (true) false");
		assertTrue(((XBooleanLiteral) ie.getIf()).isIsTrue());
		assertFalse(((XBooleanLiteral) ie.getThen()).isIsTrue());
		assertNull(ie.getElse());
	}

	public void testIf_1() throws Exception {
		XIfExpression ie = (XIfExpression) expression("if (true) false else bar");
		assertTrue(((XBooleanLiteral) ie.getIf()).isIsTrue());
		assertFalse(((XBooleanLiteral) ie.getThen()).isIsTrue());
		assertTrue(ie.getElse() instanceof XFeatureCall);
	}
	
	public void testIf_2() throws Exception {
		XIfExpression ie = (XIfExpression) expression("if (foo) bar else if (baz) apa else bpa");
		XIfExpression nestedIf = (XIfExpression) ie.getElse();
		XFeatureCall bpa = (XFeatureCall) nestedIf.getElse();
		assertEquals("bpa",bpa.getConcreteSyntaxFeatureName());
	}
	
	public void testIf_3() throws Exception {
		XIfExpression ie = (XIfExpression) expression("if (foo) bar else if (baz) if (apa) bpa else cpa");
		XIfExpression nestedIf = (XIfExpression) ie.getElse();
		XIfExpression secondNested = (XIfExpression) nestedIf.getThen();
		XFeatureCall cpa = (XFeatureCall) secondNested.getElse();
		assertEquals("cpa",cpa.getConcreteSyntaxFeatureName());
	}
	
	public void testIfWithClosure() throws Exception {
		XIfExpression ie = (XIfExpression) expression("if (foo) [| if (bar) zonk else bar]");
		assertNull(ie.getElse());
	}
	
	public void testIfWithAdd() throws Exception {
		XBinaryOperation bo = (XBinaryOperation) expression("1 + if (foo) bar + 2");
		assertTrue(bo.getExplicitArguments().get(1) instanceof XIfExpression);
	}
	
	public void testIfWithAdd_2() throws Exception {
		XBinaryOperation bo = (XBinaryOperation) expression("1 + (if (foo) bar) + 2");
		assertTrue(bo.getExplicitArguments().get(0) instanceof XBinaryOperation);
		assertTrue(bo.getExplicitArguments().get(1) instanceof XIntLiteral);
	}

	public void testSwitch_0() throws Exception {
		XSwitchExpression se = (XSwitchExpression) expression("switch true { case 1==0 : '1' }");
		assertNull(se.getDefault());
		assertEquals(1, se.getCases().size());
		assertNotNull(se.getSwitch());
		XCasePart casePart = se.getCases().get(0);
		assertTrue(casePart.getCase() instanceof XBinaryOperation);
		assertTrue(casePart.getThen() instanceof XStringLiteral);
	}

	public void testSwitch_1() throws Exception {
		XSwitchExpression se = (XSwitchExpression) expression("switch number{case 1:'1' case 2:'2' default:'3'}");
		assertTrue(se.getDefault() instanceof XStringLiteral);
		assertEquals(2, se.getCases().size());
		assertTrue(se.getSwitch() instanceof XFeatureCall);

		XCasePart case1 = se.getCases().get(0);
		assertEquals(1, ((XIntLiteral) case1.getCase()).getValue());
		assertTrue(case1.getThen() instanceof XStringLiteral);

		XCasePart case2 = se.getCases().get(1);
		assertEquals(2, ((XIntLiteral) case2.getCase()).getValue());
		assertTrue(case2.getThen() instanceof XStringLiteral);
	}
	
	public void testSwitch_2() throws Exception {
		XSwitchExpression se = (XSwitchExpression) expression("switch foo{ String case foo.length(): bar String : {baz;}}");
		assertEquals(2,se.getCases().size());
		assertNull(se.getDefault());
		XCasePart c1 = se.getCases().get(0);
		assertEquals("java.lang.String",c1.getTypeGuard().getIdentifier());
		assertFeatureCall("length",c1.getCase());
		assertFeatureCall("foo",((XMemberFeatureCall)c1.getCase()).getMemberCallTarget());
		assertFeatureCall("bar",c1.getThen());
		
		XCasePart c2 = se.getCases().get(1);
		assertEquals("java.lang.String",c2.getTypeGuard().getIdentifier());
		assertNull(c2.getCase());
		assertFeatureCall("baz",((XBlockExpression)c2.getThen()).getExpressions().get(0));
	}

	public void testBlockExpression_0() throws Exception {
		XBlockExpression be = (XBlockExpression) expression("{foo;}");
		assertEquals(1, be.getExpressions().size());
		assertTrue(be.getExpressions().get(0) instanceof XFeatureCall);
	}

	public void testBlockExpression_1() throws Exception {
		XBlockExpression be = (XBlockExpression) expression("{foo;bar;}");
		assertEquals(2, be.getExpressions().size());
		assertTrue(be.getExpressions().get(0) instanceof XFeatureCall);
		assertTrue(be.getExpressions().get(1) instanceof XFeatureCall);
	}
	
	public void testBlockExpression_2() throws Exception {
		XBlockExpression be = (XBlockExpression) expression("{}");
		assertTrue(be.getExpressions().isEmpty());
	}

	public void testBlockExpression_3() throws Exception {
		XBlockExpression be = (XBlockExpression) expression("{foo}");
		assertEquals(1, be.getExpressions().size());
		assertTrue(be.getExpressions().get(0) instanceof XFeatureCall);
	}

	public void testBlockExpression_4() throws Exception {
		XBlockExpression be = (XBlockExpression) expression("{foo bar}");
		assertEquals(2, be.getExpressions().size());
		assertTrue(be.getExpressions().get(0) instanceof XFeatureCall);
		assertTrue(be.getExpressions().get(1) instanceof XFeatureCall);
	}
	
	public void testBlockExpression_5() throws Exception {
		XBlockExpression be = (XBlockExpression) expression("{[foo bar|bar]}");
		assertEquals(1, be.getExpressions().size());
		assertTrue(be.getExpressions().get(0) instanceof XClosure);
	}
	
	public void testBlockExpression_withVariableDeclaration_0()
			throws Exception {
		XBlockExpression be = (XBlockExpression) expression("{val foo = bar;bar;}");
		assertEquals(2, be.getExpressions().size());
		XVariableDeclaration vd = (XVariableDeclaration) be.getExpressions().get(
				0);
		assertEquals("foo", vd.getName());
		assertTrue(vd.getRight() instanceof XFeatureCall);
		assertNull(vd.getType());
		assertTrue(be.getExpressions().get(1) instanceof XFeatureCall);
	}

	public void testBlockExpression_withVariableDeclaration_1()
			throws Exception {
		XBlockExpression be = (XBlockExpression) expression("{var MyType foo = bar;bar;}");
		assertEquals(2, be.getExpressions().size());
		XVariableDeclaration vd = (XVariableDeclaration) be.getExpressions().get(
				0);
		assertEquals("foo", vd.getName());
		assertFeatureCall("bar",vd.getRight());
		assertNotNull(vd.getType());
		assertFeatureCall("bar",be.getExpressions().get(1));
	}
	
	public void testBlockExpression_withVariableDeclaration_2() throws Exception {
		XBlockExpression be = (XBlockExpression) expression("{val foo = bar bar}");
		assertEquals(2, be.getExpressions().size());
		XVariableDeclaration vd = (XVariableDeclaration) be.getExpressions().get(0);
		assertEquals("foo", vd.getName());
		assertTrue(vd.getRight() instanceof XFeatureCall);
		assertNull(vd.getType());
		assertTrue(be.getExpressions().get(1) instanceof XFeatureCall);
	}

	public void testBlockExpression_withVariableDeclaration_3() throws Exception {
		XBlockExpression be = (XBlockExpression) expression("{var MyType foo = bar bar}");
		assertEquals(2, be.getExpressions().size());
		XVariableDeclaration vd = (XVariableDeclaration) be.getExpressions().get(0);
		assertEquals("foo", vd.getName());
		assertFeatureCall("bar", vd.getRight());
		assertNotNull(vd.getType());
		assertFeatureCall("bar", be.getExpressions().get(1));
	}
	public void testBlockExpression_withVariableDeclaration_4() throws Exception {
		XBlockExpression be = (XBlockExpression) expression("{ var Boolean x = 'bar' }");
		assertEquals(1, be.getExpressions().size());
		XVariableDeclaration vd = (XVariableDeclaration) be.getExpressions().get(0);
		assertEquals("x", vd.getName());
		assertTrue(vd.getRight() instanceof XStringLiteral);
		assertNotNull(vd.getType());
	}

	public void testConstructorCall_0() throws Exception {
		XConstructorCall cc = (XConstructorCall) expression("new java.util.ArrayList()");
		assertNotNull(cc.getConstructor());
		assertFalse(cc.getConstructor().eIsProxy());
		assertEquals(0, cc.getArguments().size());
	}

	public void testConstructorCall_1() throws Exception {
		XConstructorCall cc = (XConstructorCall) expression("new java.util.ArrayList(1)",true);
		assertNotNull(cc.getConstructor());
		assertFalse(cc.getConstructor().eIsProxy());
		assertEquals(1, cc.getArguments().size());
		assertEquals(1, ((XIntLiteral) cc.getArguments().get(0)).getValue());
	}

	public void testConstructorCall_2() throws Exception {
		XConstructorCall cc = (XConstructorCall) expression("new java.util.ArrayList<String>(13)");
		assertNotNull(cc.getConstructor());
		assertFalse(cc.getConstructor().eIsProxy());
		assertEquals(1, cc.getArguments().size());
		assertEquals(13, ((XIntLiteral) cc.getArguments().get(0)).getValue());
	}
	
	public void testForLoopExpression() throws Exception {
		XForLoopExpression forExp = (XForLoopExpression) expression("for(s : foo) bar");
		assertFeatureCall("foo",forExp.getForExpression());
		assertEquals("s",forExp.getDeclaredParam().getName());
		assertNull(forExp.getDeclaredParam().getParameterType());
		assertFeatureCall("bar",forExp.getEachExpression());
	}
	
	public void testForLoopExpression_1() throws Exception {
		XForLoopExpression forExp = (XForLoopExpression) expression("for(String s : foo) bar");
		assertFeatureCall("foo",forExp.getForExpression());
		assertEquals("s",forExp.getDeclaredParam().getName());
		assertEquals("java.lang.String", forExp.getDeclaredParam().getParameterType().getIdentifier());
		assertFeatureCall("bar",forExp.getEachExpression());
	}

	public void testWhileExpression() throws Exception {
		XWhileExpression expression = (XWhileExpression) expression("while (true) 'foo'");
		assertTrue(expression.getPredicate() instanceof XBooleanLiteral);
		assertTrue(expression.getBody() instanceof XStringLiteral);
	}
	
	public void testDoWhileExpression() throws Exception {
		XDoWhileExpression expression = (XDoWhileExpression) expression("do foo while (true)");
		assertTrue(expression.getPredicate() instanceof XBooleanLiteral);
		assertFeatureCall("foo",expression.getBody());
	}
	
	protected void assertFeatureCall(String string, XExpression body) {
		assertTrue(body instanceof XAbstractFeatureCall);
		assertEquals(string,((XAbstractFeatureCall)body).getConcreteSyntaxFeatureName());
	}

	public void testTypeLiteral() throws Exception {
		XTypeLiteral expression = (XTypeLiteral) expression("typeof(String)");
		assertEquals("java.lang.String",expression.getType().getIdentifier());
	}
	
	public void testInstanceOf() throws Exception {
		XInstanceOfExpression expression = (XInstanceOfExpression) expression("true instanceof Boolean");
		assertEquals("java.lang.Boolean",expression.getType().getIdentifier());
		assertTrue(expression.getExpression() instanceof XBooleanLiteral);
	}
	
	public void testInstanceOf_1() throws Exception {
		XClosure closure = (XClosure) expression("[|true instanceof Boolean]");
		XInstanceOfExpression expression = (XInstanceOfExpression) closure.getExpression();
		assertEquals("java.lang.Boolean",expression.getType().getIdentifier());
		assertTrue(expression.getExpression() instanceof XBooleanLiteral);
	}
	
	public void testThrowExpression() throws Exception {
		XThrowExpression throwEx = (XThrowExpression) expression("throw foo");
		assertFeatureCall("foo", throwEx.getExpression());
	}
	
	public void testTryCatchExpression() throws Exception {
		XTryCatchFinallyExpression tryEx = (XTryCatchFinallyExpression) expression(
				"try throw foo catch (Exception e) bar finally baz");
		assertFeatureCall("foo", ((XThrowExpression)tryEx.getExpression()).getExpression());
		assertFeatureCall("baz", tryEx.getFinallyExpression());
		
		assertEquals(1,tryEx.getCatchClauses().size());
		XCatchClause clause = tryEx.getCatchClauses().get(0);
		assertFeatureCall("bar", clause.getExpression());
		assertEquals("java.lang.Exception", clause.getDeclaredParam().getParameterType().getIdentifier());
		assertEquals("e", clause.getDeclaredParam().getName());
	}
	
	public void testTryCatchExpression_1() throws Exception {
		XTryCatchFinallyExpression tryEx = (XTryCatchFinallyExpression) expression(
		"try foo finally bar");
		assertFeatureCall("foo", tryEx.getExpression());
		assertFeatureCall("bar", tryEx.getFinallyExpression());
	}

	public void testTryCatchExpression_2() throws Exception {
		XTryCatchFinallyExpression tryEx = (XTryCatchFinallyExpression) expression(
		"try foo catch (java.lang.Exception e) bar");
		assertFeatureCall("foo", tryEx.getExpression());
		assertNull(tryEx.getFinallyExpression());
		
		assertEquals(1,tryEx.getCatchClauses().size());
		XCatchClause clause = tryEx.getCatchClauses().get(0);
		assertFeatureCall("bar", clause.getExpression());
		assertEquals("java.lang.Exception", clause.getDeclaredParam().getParameterType().getIdentifier());
		assertEquals("e", clause.getDeclaredParam().getName());
	}
	
	public void testReturnExpressionInBlock_1() throws Exception {
		XBlockExpression block = (XBlockExpression) expression(
			"{ return 1 2 }");
		assertEquals(2, block.getExpressions().size());
		assertTrue(block.getExpressions().get(0) instanceof XReturnExpression);
		XReturnExpression returnExpression = (XReturnExpression) block.getExpressions().get(0);
		assertTrue(returnExpression.getExpression() instanceof XIntLiteral);
	}
	
	public void testReturnExpressionInBlock_2() throws Exception {
		XBlockExpression block = (XBlockExpression) expression(
			"{ return }");
		assertEquals(1, block.getExpressions().size());
		assertTrue(block.getExpressions().get(0) instanceof XReturnExpression);
		XReturnExpression returnExpression = (XReturnExpression) block.getExpressions().get(0);
		assertNull(returnExpression.getExpression());
	}
	
	public void testReturnExpressionInBlock_3() throws Exception {
		XBlockExpression block = (XBlockExpression) expression(
			"{ return; 1 2 }");
		assertEquals(3, block.getExpressions().size());
		assertTrue(block.getExpressions().get(0) instanceof XReturnExpression);
		XReturnExpression returnExpression = (XReturnExpression) block.getExpressions().get(0);
		assertNull(returnExpression.getExpression());
	}
	
}
