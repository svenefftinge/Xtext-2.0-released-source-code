/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xtext.ui.wizard.ecore2xtext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.util.Strings;

/**
 * @author Jan Koehnlein - Initial contribution and API
 */
public class UniqueNameUtil {

	private static final String IMPL_NAME_SUFFIX = "_Impl";

	private static Map<ENamedElement, String> element2uniqueName = new HashMap<ENamedElement, String>();

	private static Map<ENamedElement, String> element2uniqueImplName = new HashMap<ENamedElement, String>();

	private static final List<String> RESERVED_RULES = Arrays.asList(new String[] { "ml_comment", "id", "ws", "int",
			"string", "any_other", "sl_comment" });

	private static final List<String> RESERVED_KEYWORDS = Arrays.asList(new String[] { "returns", "generate",
			"terminal", "with", "hidden", "enum", "grammar", "import", "as", "current", "fragment", "EOF" });

	public static String uniqueName(ENamedElement element) {
		return uniqueName(element, element.getName(), element2uniqueName);
	}

	public static String uniqueImplName(ENamedElement element) {
		return uniqueName(element, element.getName() + IMPL_NAME_SUFFIX, element2uniqueImplName);
	}

	private static String uniqueName(ENamedElement element, String originalName,
			Map<ENamedElement, String> uniqueNameMap) {
		if (uniqueNameMap.containsKey(element)) {
			return uniqueNameMap.get(element);
		}
		String uniqueName = originalName;
		for (int i = 0; RESERVED_KEYWORDS.contains(uniqueName) || RESERVED_RULES.contains(uniqueName.toLowerCase())
				|| element2uniqueName.containsValue(uniqueName) || element2uniqueImplName.containsValue(uniqueName); ++i) {
			uniqueName = originalName + i;
		}
		uniqueNameMap.put(element, uniqueName);
		return uniqueName;
	}

	public static void clearUniqueNames(EPackageInfo defaultPackageInfo) {
		element2uniqueName.clear();
		element2uniqueImplName.clear();
		if (defaultPackageInfo != null) {
			element2uniqueName.put(defaultPackageInfo.getEPackage(), null);
		}
	}

	public static String importURI(EPackage ePackage) {
		Resource resource = ePackage.eResource();
		if (resource != null) {
			URI uri = resource.getURI();
			if (uri != null) {
				if (!Strings.equal(uri.scheme(), URI.createURI(ePackage.getNsURI()).scheme())) {
					return resource.getURI().toString();
				}
			}
		}
		return ePackage.getNsURI();
	}

	public static EClassifier eString() {
		return EcorePackage.eINSTANCE.getEString();
	}

	public static void debug(String s) {
		System.out.println(s);
	}
}
