/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xbase.tests.typing;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XFeatureCall;
import org.eclipse.xtext.xbase.XbaseFactory;
import org.eclipse.xtext.xbase.lib.Functions;
import org.eclipse.xtext.xbase.tests.AbstractXbaseTestCase;
import org.eclipse.xtext.xbase.typing.ITypeProvider;

import com.google.inject.Inject;

/**
 * @author Sven Efftinge
 */
public class XbaseTypeProviderTest extends AbstractXbaseTestCase {
	
	public void testFeatureCallWithArrayToIterableConversion() throws Exception {
		assertResolvedType("java.util.Iterator<java.lang.Character>", "'foo'.toCharArray.iterator");
	}
	
	public void testReturnType() throws Exception {
		assertResolvedType("void", "return 'foo'");
	}
	
	public void testClosure_00() throws Exception {
		assertResolvedType("java.lang.String", "[|'literal'].apply()");
	}
	
	public void testClosure_01() throws Exception {
		assertResolvedType("java.lang.String", "{" +
				"  var closure = [|'literal']" +
				"  new testdata.ClosureClient().invoke0(closure)" +
		"}");
	}
	
	public void testTypeArgs() throws Exception {
		assertResolvedType("boolean", "new java.util.ArrayList<String>() += 'foo'");
	}

	public void testIfExpression() throws Exception {
		assertResolvedType("java.lang.String", "if (true) 'foo' else null");
		assertResolvedType("java.lang.String", "if (true) 'foo' else 'bar'");
		assertResolvedType("java.lang.String", "if (true) 'foo'");
	}
	
	public void testIfExpression_02() throws Exception {
		assertResolvedType("java.lang.AbstractStringBuilder & java.io.Serializable", "if (true) new StringBuilder() else new StringBuffer()");
	}

	public void testSwitchExpression() throws Exception {
		assertResolvedType(String.class.getName(),
				"switch true { case true : 's' case false : 'foo' default: 'bar'}");
		assertResolvedType(Object.class.getName(),
				"switch true { case true : 's' case false : new java.lang.Object() default: 'bar'}");
	}
	
	public void testSwitchExpression_Bug343100() throws Exception {
		assertResolvedType("java.lang.Class<? extends java.lang.Object>",
			"switch 'a' {\n" + 
			"  case null: typeof(java.lang.String)\n" + 
			"  case 'a': typeof(java.lang.Void)\n" + 
			"}");
	}

	public void testBlockExpression() throws Exception {
		assertResolvedType(Integer.TYPE.getName(), "{''.toString;4;}");
		assertResolvedType(Boolean.TYPE.getName(), "{''.toString;true;}");
		assertResolvedType("null", "{null;}");
	}

	public void testNullLiteral() throws Exception {
		assertResolvedType("null", "null");
	}

	public void testBooleanLiteral() throws Exception {
		assertResolvedType(Boolean.TYPE.getName(), "true");
		assertResolvedType(Boolean.TYPE.getName(), "false");
	}

	public void testStringLiteral() throws Exception {
		assertResolvedType(String.class.getName(), "'foo'");
		assertResolvedType(String.class.getName(), "\"foo\"");
	}

	public void testIntLiteral() throws Exception {
		assertResolvedType(Integer.TYPE.getName(), "3");
	}

	public void testCastExpression() throws Exception {
		assertResolvedType(String.class.getName(), "null as String");
		assertResolvedType(Boolean.class.getName(), "null as Boolean");
	}

	public void testConstructorCall() throws Exception {
		assertResolvedType("java.util.ArrayList<E>", "new java.util.ArrayList()");
		assertResolvedType("java.util.ArrayList<java.lang.String>", "new java.util.ArrayList<java.lang.String>()");
		assertResolvedType("java.util.HashMap<java.lang.String,java.lang.Boolean>",
				"new java.util.HashMap<java.lang.String, java.lang.Boolean>()");
	}
	
	public void testConstructorTypeInference_01() throws Exception {
		assertResolvedType("testdata.GenericType1<java.lang.String>", "new testdata.GenericType1('')");
	}
	
	public void testConstructorTypeInference_02() throws Exception {
		assertResolvedType("boolean", "<java.util.List<String>>newArrayList().add(new java.util.ArrayList())");
	}

	public void testClosure() throws Exception {
		assertResolvedType(Functions.class.getCanonicalName()+"$Function1<java.lang.String,java.lang.Boolean>", "[java.lang.String x| true]");
	}

	public void testFeatureCall_01() throws Exception {
		assertResolvedType("int", "'foo'.length");
	}

	public void testFeatureCall_02() throws Exception {
		assertResolvedType("byte[]", "'foo'.getBytes()");
		assertResolvedType("java.lang.String", "new java.util.ArrayList<java.lang.String>().get(23)");
	}
	
	public void testFeatureCall_03() throws Exception {
		assertResolvedType("java.util.List<java.lang.Object>", "new testdata.ClassWithVarArgs().toList()");
		assertResolvedType("java.util.List<java.lang.String>", "new testdata.ClassWithVarArgs().toList('')");
		assertResolvedType("java.util.List<java.lang.String>", "new testdata.ClassWithVarArgs().toList('', '')");
	}
	
	public void testFeatureCall_04() throws Exception {
		// TODO: to be discussed: jdt expects java.util.List<? extends Object> 
		assertResolvedType("java.util.List<java.lang.Comparable<? extends java.lang.Object>>", "new testdata.ClassWithVarArgs().toList('', 1)");
	}
	
	public void testFeatureCall_05() throws Exception {
		assertResolvedType("java.util.List<java.lang.Number>", "new testdata.ClassWithVarArgs().toNumberList()");
		assertResolvedType("java.util.List<java.lang.Integer>", "new testdata.ClassWithVarArgs().toNumberList(0)");
		assertResolvedType("java.util.List<java.lang.Integer>", "new testdata.ClassWithVarArgs().toNumberList(0, 1)");
		assertResolvedType("java.util.List<java.lang.Number & java.lang.Comparable<? extends java.lang.Object>>", "new testdata.ClassWithVarArgs().toNumberList(new Integer(0), new Integer(0).doubleValue)");
	}
	
	public void testFeatureCall_06() throws Exception {
		assertResolvedType("java.util.List<java.lang.String>", "newArrayList('').map(s|s)");
	}
	
	public void testFeatureCall_06_1() throws Exception {
		assertResolvedType("java.util.List<java.lang.Integer>", "newArrayList('').map(s|1)");
	}
	
	public void testFeatureCall_07() throws Exception {
		assertResolvedType("java.util.List<java.lang.Integer>", "newArrayList('').map(s|s.length)");
	}
	
	public void testFeatureCall_08() throws Exception {
		assertResolvedType("java.util.List<java.lang.Boolean>", "newArrayList('').map(s|s != null)");
	}
	
	public void testFeatureCall_09() throws Exception {
		assertResolvedType("java.util.List<java.lang.Integer>", "newArrayList('').map(s|s.length+1)");
	}
	
	public void testFeatureCall_10() throws Exception {
		assertResolvedType("java.util.List<java.lang.Integer>", "newArrayList('').map(s|1).map(i|i+1)");
	}
	
	public void testFeatureCall_11() throws Exception {
		assertResolvedType("java.util.List<java.lang.Integer>", "newArrayList('').map(s|1).toList()");
	}
	
	public void testFeatureCall_12() throws Exception {
		assertResolvedType("java.util.List<java.lang.Integer>", "newArrayList('').map(s|1).toList().map(i|i)");
	}
	
	public void testFeatureCall_13() throws Exception {
		assertResolvedType("java.util.List<java.lang.Integer>", "newArrayList('').map(s|1).toList().map(i|i+1)");
	}
	
	public void testFeatureCall_13_2() throws Exception {
		assertResolvedType("java.util.List<java.lang.Integer>", "{ var this = newArrayList('').map(s|1).toList() this.map(i|i+1) }");
	}
	
	public void testFeatureCall_13_3() throws Exception {
		assertResolvedType("java.util.List<java.lang.Integer>", "{ var this = newArrayList('').map(s|1).toList() map(i|i+1) }");
	}
	
	public void testFeatureCall_14() throws Exception {
		assertResolvedType("java.util.ArrayList<java.util.List<java.lang.Integer>>", "newArrayList(newArrayList('').map(s|1))");
	}
	
	public void testFeatureCall_15() throws Exception {
		assertResolvedType("java.util.List<java.lang.Integer>", "newArrayList(newArrayList('').map(s|1)).map(iterable|iterable.size())");
	}
	
	public void testFeatureCall_16() throws Exception {
		assertResolvedType("java.util.List<java.lang.Integer>", "newArrayList('').map(s|1).map(i|1)");
	}
	
	public void testFeatureCall_17() throws Exception {
		assertResolvedType("java.util.List<java.lang.Integer>", "newArrayList('').map(s|s.length).map(i|i)");
	}
	
	public void testFeatureCall_18() throws Exception {
		assertResolvedType("java.util.List<java.lang.Boolean>", "newArrayList('').map(s|s.length + 1 == 5).map(b|b)");
	}
	
	public void testFeatureCall_19() throws Exception {
		assertResolvedType("java.util.List<java.lang.Boolean>", "newArrayList('').map(s|s.length + 1 == 5).map(b| { 'length'.length b })");
	}
	
	public void testFeatureCall_20() throws Exception {
		assertResolvedType("java.util.List<java.lang.Boolean>", "newArrayList('').map(s|s.length + 1 == 5).map(Boolean b|!b)");
	}
	
	public void testFeatureCall_21() throws Exception {
		assertResolvedType("java.util.List<java.lang.Boolean>", "newArrayList('').map(s|s.length + 1 == 5).map(b| ! b )");
	}
	
	public void testFeatureCall_22() throws Exception {
		assertResolvedType("java.util.List<java.lang.Boolean>", "newArrayList('').map(s|s.length + 1 == 5).map(b| { !b } )");
	}
	
	public void testFeatureCall_Bug342134_01() throws Exception {
		assertResolvedType("java.lang.Iterable<java.lang.String>", "newArrayList('').map(e|newArrayList(e)).flatten");
	}
	
	public void testFeatureCall_Bug342134_02() throws Exception {
		assertResolvedType("java.util.List<java.util.ArrayList<java.lang.String>>", "newArrayList('').map(e|newArrayList(e))");
	}
	
	public void testFeatureCall_Bug342134_03() throws Exception {
		assertResolvedType("java.lang.Iterable<java.lang.String>", "<String>newArrayList.map(e|newArrayList(e)).flatten");
	}
	
	public void testFeatureCall_Bug342134_04() throws Exception {
		assertResolvedType("java.lang.Iterable<java.lang.String>", "newArrayList('').map(e|<String>newArrayList(e)).flatten");
	}
	
	public void testFeatureCall_Bug342134_05() throws Exception {
		assertResolvedType("java.lang.Iterable<java.lang.String>", "newArrayList.map(String e|<String>newArrayList(e)).flatten");
	}
	
	public void testFeatureCall_Bug342134_06() throws Exception {
		assertResolvedType("java.lang.Iterable<java.lang.String>", "newArrayList(newArrayList('')).flatten");
	}
	
	public void testFeatureCall_Bug342134_07() throws Exception {
		assertResolvedType("java.lang.Iterable<java.lang.String>", "<java.util.List<java.lang.String>>newArrayList().flatten");
	}

	public void testFeatureCallWithOperatorOverloading_2() throws Exception {
		assertResolvedType("boolean", "new java.util.ArrayList<Byte>() += 'x'.getBytes().iterator.next");
	}
	
	public void testFeatureCallWithOperatorOverloading_3() throws Exception {
		assertResolvedType("boolean", "new java.util.ArrayList<Byte>() += null");
	}
	
	public void testFeatureCallWithOperatorOverloading_4() throws Exception {
		assertResolvedType("boolean", "new java.util.ArrayList<Byte>() += newArrayList('x'.getBytes().iterator.next)");
	}
	
	public void testFeatureCallWithOperatorOverloading_5() throws Exception {
		assertResolvedType("boolean", "new java.util.ArrayList<Byte>() += 'x'.getBytes()");
	}
	
	public void testFeatureCallOnThis() throws Exception {
		assertResolvedType("boolean", "{ val this = 'foo'; length == 3;}");
	}
	
	public void testStaticMethods_01() throws Exception {
		assertResolvedType("java.util.ArrayList<java.lang.String>", "newArrayList('')");
	}
	
	public void testStaticMethods_02() throws Exception {
		assertResolvedType("java.util.ArrayList<java.lang.String>", "newArrayList('', '')");
	}
	
	public void testStaticMethods_03() throws Exception {
		assertResolvedType("java.util.ArrayList<java.util.HashSet<java.lang.String>>", "newArrayList(newHashSet(''))");
	}
	
	public void testStaticMethods_04() throws Exception {
		assertResolvedType("java.util.ArrayList<java.lang.Object>", "newArrayList()");
	}
	
	public void testStaticMethods_05() throws Exception {
		assertResolvedType("java.util.HashMap<java.lang.Object,java.lang.Object>", "newHashMap()");
	}

	public void testThrowExpression() throws Exception {
		assertResolvedType("void", "throw new java.lang.Exception()");
	}

	public void testTryCatchFinallyExpression_0() throws Exception {
		assertResolvedType("java.lang.String", "try 'foo' catch (java.lang.Exception e) 'bar'"); 
	}
	
	public void testTryCatchFinallyExpression_1() throws Exception {
		assertResolvedType("java.lang.String", "try 'foo' catch (java.lang.Exception e) 'bar' catch(java.lang.RuntimeException e) 'baz'");	
	}
	
	public void testTryCatchFinallyExpression_2() throws Exception {
		assertResolvedType("java.lang.String", "try 'foo' catch (java.lang.Exception e) 'bar' catch(java.lang.RuntimeException e) 'baz' finally true");	
	}

	public void testForExpression() throws Exception {
		assertResolvedType("void", "for(java.lang.String x : new java.util.ArrayList<String>()) 'foo'");
		assertResolvedType("void", "for(java.lang.String x : newArrayList('foo')) x");
		assertResolvedType("void", "for(java.lang.String x : <String>newArrayList()) x");
	}

	public void testNull() throws Exception {
		assertNull(typeProvider.getType(null));
	}

	public void testProxy() throws Exception {
		XFeatureCall proxy = XbaseFactory.eINSTANCE.createXFeatureCall();
		((InternalEObject) proxy).eSetProxyURI(URI.createURI("path#fragment"));
		assertNull(typeProvider.getType(proxy));
	}
	
	public void testMethodTypeParamInference_00() throws Exception {
		assertResolvedType("java.lang.String", "new java.util.ArrayList<String>().findFirst(e | true)");
	}
	
	public void testMethodTypeParamInference_01() throws Exception {
		assertResolvedType("java.lang.String", "new java.util.ArrayList<String>().findFirst(e|e == 'foo')");
	}

	@Inject
	private ITypeProvider typeProvider;

	public void assertResolvedType(String typeName, String expression) throws Exception {
		final XExpression expression2 = expression(expression, true);
		JvmTypeReference typeRef = typeProvider.getType(expression2);
		assertNotNull("type ref was null for " + expression, typeRef);
		assertEquals(typeName, toString(typeRef));
	}

	protected String toString(JvmTypeReference typeref) {
		return typeref.getIdentifier();
	}

}
