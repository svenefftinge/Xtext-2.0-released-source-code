package org.eclipse.xtext.parsetree.unassignedtext;

import org.eclipse.xtext.junit.AbstractXtextTests;

/**
 * @author Moritz Eysholdt - Initial contribution and API
 */
public class UnassignedTextTest extends AbstractXtextTests {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		with(UnassignedTextTestLanguageStandaloneSetup.class);
	}

	public void testCaseInsensitiveKeyword() throws Exception {
		final String in = "kEyWoRd 7";
		final String out = "KeyWord 7";
		String s = serialize(getModel(in));
		assertEquals(out, s);
	}

	public void testPlural1() throws Exception {
		final String in = "contents: 0 items";
		final String out = "contents: 0 items";
		String s = serialize(getModel(in)).trim();
		assertEquals(out, s);
	}

	public void testPlural2() throws Exception {
		final String in = "contents: 0 item";
		final String out = "contents: 0 items";
		String s = serialize(getModel(in)).trim();
		assertEquals(out, s);
	}

	public void testPlural3() throws Exception {
		final String in = "contents: 1 items";
		final String out = "contents: 1 item";
		String s = serialize(getModel(in)).trim();
		assertEquals(out, s);
	}

	public void testPlural4() throws Exception {
		final String in = "contents: 1 item";
		final String out = "contents: 1 item";
		String s = serialize(getModel(in)).trim();
		assertEquals(out, s);
	}

	public void testMulti() throws Exception {
		final String in = "multi 1 accegj01 acddegj01 accehj11";
		final String out = "multi 1 accegj01 accegj02 accegj03";
		String s = serialize(getModel(in)).trim();
		assertEquals(out, s);
	}

	public void testDataType() throws Exception {
		final String in = "datatype 3 'mystr'";
		final String out = "datatype 3 str";
		String s = serialize(getModel(in)).trim();
		assertEquals(out, s);
	}

	public void testCommonTerminals() throws Exception {
		final String in = "terminals xyz 789 'yo' X";
		final String out = "terminals abc 123 'abc' X";
		String s = serialize(getModel(in)).trim();
		assertEquals(out, s);
	}
}
