/*******************************************************************************
 * Copyright (c) 2008 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.eclipse.xtext.linking.lazy;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.util.Triple;
import org.eclipse.xtext.util.Tuples;

import com.google.inject.Singleton;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
@Singleton
public class LazyURIEncoder {
	
	private static final String XTEXT_LINK = "xtextLink_";
	private static final String SEP = "::";

	/**
	 * encodes the given three parameters into a string, so that they can be
	 * retrieved from a resource using {@link #decode(Resource, String)}
	 * 
	 * @param obj
	 * @param ref
	 * @param node
	 * @return
	 */
	public String encode(EObject obj, EReference ref, INode node) {
		StringBuilder fragment = new StringBuilder(20).append(XTEXT_LINK).append(SEP);
		appendShortFragment(obj, fragment);
		fragment.append(SEP);
		fragment.append(toShortExternalForm(obj.eClass(), ref)).append(SEP);
		getRelativePath(fragment, NodeModelUtils.getNode(obj), node);
		return fragment.toString();
	}
	
	public void appendShortFragment(EObject obj, StringBuilder target) {
		EReference containmentFeature = obj.eContainmentFeature();
		if (containmentFeature == null) {
			target.append(obj.eResource().getContents().indexOf(obj));
		} else {
			EObject container = obj.eContainer();
			appendShortFragment(container, target);
			target.append('.').append(container.eClass().getFeatureID(containmentFeature));
			if (containmentFeature.isMany()) {
				List<?> list = (List<?>) container.eGet(containmentFeature);
				target.append('.').append(list.indexOf(obj));
			}
		}
	}
	
	public String toShortExternalForm(EClass clazz, EReference ref) {
		return Integer.toString(clazz.getFeatureID(ref));
	}

	/**
	 * decodes the uriFragment
	 * 
	 * @param res
	 * @param uriFragment
	 * @return
	 */
	public Triple<EObject, EReference, INode> decode(Resource res, String uriFragment) {
		String[] split = uriFragment.split(SEP);
		EObject source = resolveShortFragment(res, split[1]);
		EReference ref = fromShortExternalForm(source.eClass(), split[2]);
		INode compositeNode = NodeModelUtils.getNode(source);
		if (compositeNode==null)
			throw new IllegalStateException("Couldn't resolve lazy link, because no node model is attached.");
		INode textNode = getNode(compositeNode, split[3]);
		return Tuples.create(source, ref, textNode);
	}
	
	public EObject resolveShortFragment(Resource res, String shortFragment) {
		String[] split = shortFragment.split("\\.");
		int contentsIdx = Integer.parseInt(split[0]);
		EObject result = res.getContents().get(contentsIdx);
		int splitIdx = 1;
		while(splitIdx < split.length) {
			int featureId = Integer.parseInt(split[splitIdx++]);
			EReference reference = (EReference) result.eClass().getEStructuralFeature(featureId);
			if (reference.isMany()) {
				List<?> list = (List<?>) result.eGet(reference);
				int listIdx = Integer.parseInt(split[splitIdx++]);
				result = (EObject) list.get(listIdx);
			} else {
				result = (EObject) result.eGet(reference);
			}
		}
		return result;
	}
	
	public EReference fromShortExternalForm(EClass clazz, String shortForm) {
		int featureId = Integer.parseInt(shortForm);
		return (EReference) clazz.getEStructuralFeature(featureId);
	}

	/**
	 * ONLY public to be testable
	 */
	public void getRelativePath(StringBuilder result, INode parserNode, INode node) {
		if (parserNode == node)
			return;
		if (isAncestor(parserNode, node)) {
			getRelativePath(result, parserNode, node.getParent());
			int idx = 0;
			INode child = node.getParent().getFirstChild();
			while(child != node && child.hasNextSibling()) {
				idx++;
				child = child.getNextSibling();
			}
			result.append("/").append(idx);
		} else {
			result.append("/..");
			getRelativePath(result, parserNode.getParent(), node);
		}
	}
	
	protected boolean isAncestor(INode parent, INode child) {
		if (child.equals(parent))
			return true;
		INode node = child;
		while (node.getParent() != null) {
			if (node.getParent().equals(parent))
				return true;
			node = node.getParent();
		}
		return false;
	}

	/**
	 * ONLY public to be testable
	 */
	public INode getNode(final INode node, String path) {
		final String[] split = path.split("/");
		INode result = node;
		for (String string : split) {
			String trimmed = string.trim();
			if (trimmed.length() > 0) {
				if ("..".equals(trimmed)) {
					if (result.getParent() == null)
						throw new IllegalStateException("node has no parent");
					result = result.getParent();
				} else {
					int index = Integer.parseInt(string);
					if (index >= 0) {
						INode child = ((ICompositeNode) result).getFirstChild();
						while(index > 0) {
							child = child.getNextSibling();
							index--;
						}
						result = child;
					}
				}
			}
		}
		return result;
	}

	public boolean isCrossLinkFragment(Resource res, String s) {
		return s.startsWith(XTEXT_LINK);
	}
}
