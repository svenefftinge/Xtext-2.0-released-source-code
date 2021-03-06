/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.parser.antlr;

import org.antlr.runtime.BitSet;
import org.eclipse.xtext.IGrammarAccess;

import junit.framework.TestCase;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class Bug326509Test extends TestCase {

	public static class TestableParser extends AbstractInternalAntlrParser {

		public TestableParser() {
			super(null);
		}
		
		@Override
		protected IGrammarAccess getGrammarAccess() {
			return null;
		}

		@Override
		protected String getFirstRuleName() {
			return null;
		}
		
		public BitSet[] getFollowing() {
			return state.following;
		}
		
		@Override
		public void pushFollow(BitSet bitSet) {
			super.pushFollow(bitSet);
		}
		
	}
	
	private TestableParser testMe;
	
	@Override
	protected void setUp() throws Exception {
		testMe = new TestableParser();
	}
	
	public void testBug326509() {
		for(int i = 0; i <= 200; i++) {
			testMe.pushFollow(new BitSet());
		}
		BitSet[] following = testMe.getFollowing();
		for(int i = 0; i < following.length && i <= 200; i++) {
			assertNotNull(Integer.toString(i), following[i]);
		}
	}
	
}
