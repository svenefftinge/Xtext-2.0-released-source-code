/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.label;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.jface.viewers.BaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.ui.resource.IResourceUIServiceProvider;

/**
 * Delegates to the {@link IDescriptionLabelProvider} looked up in the {@link IResourceServiceProvider.Registry}
 * 
 * @author Jan Koehnlein - Initial contribution and API
 */
public class GlobalDescriptionLabelProvider extends BaseLabelProvider implements ILabelProvider, IItemLabelProvider, IStyledLabelProvider {

	public Image getImage(Object element) {
		ILabelProvider descriptionLabelProvider = lookupDescriptionLabelProvider(element);
		if (descriptionLabelProvider != null) {
			return descriptionLabelProvider.getImage(element);
		} else {
			return null;
		}
	}

	public String getText(Object element) {
		if (element == null) {
			return Messages.GlobalDescriptionLabelProvider_0;
		} else {
			ILabelProvider descriptionLabelProvider = lookupDescriptionLabelProvider(element);
			if (descriptionLabelProvider != null) {
				return descriptionLabelProvider.getText(element);
			} else {
				return element.toString();
			}
		}
	}
	
	public StyledString getStyledText(Object element) {
		if (element == null) {
			return new StyledString(Messages.GlobalDescriptionLabelProvider_0);
		} else {
			ILabelProvider descriptionLabelProvider = lookupDescriptionLabelProvider(element);
			if (descriptionLabelProvider instanceof IStyledLabelProvider)
				return ((IStyledLabelProvider) descriptionLabelProvider).getStyledText(element);
			else if(descriptionLabelProvider != null) {
				return new StyledString(descriptionLabelProvider.getText(element));
			} else {
				return new StyledString(element.toString());
			}
		}
	}

	private ILabelProvider lookupDescriptionLabelProvider(Object description) {
		URI uri = uri(description);
		if (uri != null) {
			IResourceServiceProvider resourceServiceProvider = IResourceServiceProvider.Registry.INSTANCE
					.getResourceServiceProvider(uri);
			if (resourceServiceProvider instanceof IResourceUIServiceProvider)
				return ((IResourceUIServiceProvider) resourceServiceProvider).getLabelProvider();
		}
		return null;
	}

	private URI uri(Object description) {
		if (description instanceof IEObjectDescription) {
			return ((IEObjectDescription) description).getEObjectURI();
		} else if (description instanceof IResourceDescription) {
			return ((IResourceDescription) description).getURI();
		} else if(description instanceof IReferenceDescription) {
			return ((IReferenceDescription) description).getContainerEObjectURI();
		}
		return null;
	}

}