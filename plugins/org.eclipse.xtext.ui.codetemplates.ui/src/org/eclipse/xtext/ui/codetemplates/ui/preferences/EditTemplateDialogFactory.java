/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.codetemplates.ui.preferences;

import org.eclipse.jface.text.templates.ContextTypeRegistry;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.xtext.Constants;
import org.eclipse.xtext.ui.codetemplates.ui.partialEditing.ISyntheticResourceProvider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class EditTemplateDialogFactory {
	
	@Inject
	private Provider<TemplatesLanguageConfiguration> configurationProvider;
	
	@Inject
	private ContextTypeRegistry contextTypeRegistry;
	
	@Inject
	private ISyntheticResourceProvider resourceProvider;
	
	@Inject
	@Named(Constants.LANGUAGE_NAME)
	private String languageName;
	
	public IEditTemplateDialog createDialog(Template template, boolean edit, boolean isNameModifiable, Shell shell) {
		EditTemplateDialog dialog= new EditTemplateDialog(
				shell, template, edit, isNameModifiable, 
				contextTypeRegistry, 
				configurationProvider.get(),
				resourceProvider, languageName);
		return dialog;
	}
	
}