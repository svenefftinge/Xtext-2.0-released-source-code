/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.tests.editor.outline;

import java.util.List;

import org.eclipse.xtext.ISetup;
import org.eclipse.xtext.junit.AbstractXtextTests;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.ui.editor.outline.impl.EObjectNode;
import org.eclipse.xtext.ui.editor.outline.impl.OutlineFilterAndSorter;
import org.eclipse.xtext.ui.editor.outline.impl.OutlineFilterAndSorter.IComparator;
import org.eclipse.xtext.ui.editor.outline.impl.OutlineFilterAndSorter.IFilter;
import org.eclipse.xtext.ui.tests.Activator;
import org.eclipse.xtext.ui.tests.editor.outline.outlineTest.Model;
import org.eclipse.xtext.ui.tests.editor.outline.outlineTest.OutlineTestFactory;

import com.google.common.collect.Lists;
import com.google.inject.Injector;


/**
 * @author Jan Koehnlein - Initial contribution and API
 */
public class OutlineFilterAndSorterTest extends AbstractXtextTests {

	private List<IOutlineNode> nodes;
	private OutlineFilterAndSorter filterAndSorter;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		final Injector injector = Activator.getInstance().getInjector("org.eclipse.xtext.ui.tests.editor.outline.OutlineTestLanguage");
		with(new ISetup() {
			public Injector createInjectorAndDoEMFRegistration() {
				return injector;
			}
		});
		Model model = OutlineTestFactory.eINSTANCE.createModel();
		nodes = Lists.newArrayList();
		nodes.add(new EObjectNode(model, null, null, "one", true));
		nodes.add(new EObjectNode(model, null, null, "two", true));
		nodes.add(new EObjectNode(model, null, null, "three", true));
		filterAndSorter = new OutlineFilterAndSorter();
	}

	public void testFilter() throws Exception{
		assertTextsEqual(filterAndSorter.filterAndSort(nodes), "one", "two", "three");
		TestFilter testFilter = new TestFilter();
		filterAndSorter.addFilter(testFilter);
		assertTextsEqual(filterAndSorter.filterAndSort(nodes), "one", "two", "three");
		testFilter.setEnabled(true);
		assertTextsEqual(filterAndSorter.filterAndSort(nodes), "one", "two");
	}

	public void testSort() throws Exception {
		assertTextsEqual(filterAndSorter.filterAndSort(nodes), "one", "two", "three");
		TestComparator testComparator = new TestComparator();
		filterAndSorter.setComparator(testComparator);
		assertTextsEqual(filterAndSorter.filterAndSort(nodes), "one", "two", "three");
		testComparator.setEnabled(true);
		assertTextsEqual(filterAndSorter.filterAndSort(nodes), "two", "three", "one");
	}

	public void testFilterAndSort() {
		TestComparator testComparator = new TestComparator();
		TestFilter testFilter = new TestFilter();
		filterAndSorter.setComparator(testComparator);
		filterAndSorter.addFilter(testFilter);
		assertTextsEqual(filterAndSorter.filterAndSort(nodes), "one", "two", "three");
		testComparator.setEnabled(true);
		assertTextsEqual(filterAndSorter.filterAndSort(nodes), "two", "three", "one");
		testFilter.setEnabled(true);
		assertTextsEqual(filterAndSorter.filterAndSort(nodes), "two", "one");
		testComparator.setEnabled(false);
		assertTextsEqual(filterAndSorter.filterAndSort(nodes), "one", "two");
	}
	
	protected void assertTextsEqual(IOutlineNode[] nodes, String...texts) {
		assertEquals(nodes.length, texts.length);
		for(int i=0; i<nodes.length; ++i) 
			assertEquals(nodes[i].getText().toString(), texts[i]);
	}
	
	protected static class TestFilter implements IFilter {

		private boolean isEnabled = false;

		public boolean apply(IOutlineNode input) {
			return input.getText().toString().length() == 3;
		}

		public boolean isEnabled() {
			return isEnabled;
		}
		
		public void setEnabled(boolean isEnabled) {
			this.isEnabled = isEnabled;
		}
	}
	
	protected static class TestComparator implements IComparator {

		private boolean isEnabled = false;

		public int compare(IOutlineNode o1, IOutlineNode o2) {
			return o2.getText().toString().compareTo(o1.getText().toString());
		}

		public boolean isEnabled() {
			return isEnabled;
		}
		
		public void setEnabled(boolean isEnabled) {
			this.isEnabled = isEnabled;
		}
		
	}
	
}
