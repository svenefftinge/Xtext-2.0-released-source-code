/*
 * generated by Xtext
 */
package org.eclipse.xtext.xtend2.ui;

import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.xtext.common.types.xtext.ui.ITypesProposalProvider;
import org.eclipse.xtext.ui.editor.actions.IActionContributor;
import org.eclipse.xtext.ui.editor.autoedit.AbstractEditStrategy;
import org.eclipse.xtext.ui.editor.autoedit.AbstractEditStrategyProvider;
import org.eclipse.xtext.ui.editor.doubleClicking.DoubleClickStrategyProvider;
import org.eclipse.xtext.ui.editor.folding.IFoldingRegionProvider;
import org.eclipse.xtext.ui.editor.hover.IEObjectHoverProvider;
import org.eclipse.xtext.ui.editor.hyperlinking.IHyperlinkHelper;
import org.eclipse.xtext.ui.editor.model.ITokenTypeToPartitionTypeMapper;
import org.eclipse.xtext.ui.editor.outline.impl.OutlineFilterAndSorter.IComparator;
import org.eclipse.xtext.ui.editor.preferences.IPreferenceStoreInitializer;
import org.eclipse.xtext.ui.editor.preferences.LanguageRootPreferencePage;
import org.eclipse.xtext.ui.editor.syntaxcoloring.AbstractAntlrTokenToAttributeIdMapper;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightingConfiguration;
import org.eclipse.xtext.ui.editor.syntaxcoloring.ISemanticHighlightingCalculator;
import org.eclipse.xtext.ui.refactoring.ui.RenameElementHandler;
import org.eclipse.xtext.xbase.ui.jvmmodel.refactoring.jdt.JDTRenamePartcipant;
import org.eclipse.xtext.xtend2.ui.autoedit.AutoEditStrategyProvider;
import org.eclipse.xtext.xtend2.ui.autoedit.TokenTypeToPartitionMapper;
import org.eclipse.xtext.xtend2.ui.contentassist.ImportingTypesProposalProvider;
import org.eclipse.xtext.xtend2.ui.editor.Xtend2DoubleClickStrategyProvider;
import org.eclipse.xtext.xtend2.ui.editor.Xtend2FoldingRegionProvider;
import org.eclipse.xtext.xtend2.ui.highlighting.HighlightingConfiguration;
import org.eclipse.xtext.xtend2.ui.highlighting.RichStringAwareHighlightingCalculator;
import org.eclipse.xtext.xtend2.ui.highlighting.RichStringAwareTokenScanner;
import org.eclipse.xtext.xtend2.ui.highlighting.ShowWhitespaceCharactersActionContributor;
import org.eclipse.xtext.xtend2.ui.highlighting.TokenToAttributeIdMapper;
import org.eclipse.xtext.xtend2.ui.hover.XtendHoverProvider;
import org.eclipse.xtext.xtend2.ui.hyperlinking.XtendHyperlinkHelper;
import org.eclipse.xtext.xtend2.ui.outline.Xtend2OutlineNodeComparator;
import org.eclipse.xtext.xtend2.ui.outline.Xtend2OutlinePage;
import org.eclipse.xtext.xtend2.ui.preferences.Xtend2Preferences;
import org.eclipse.xtext.xtend2.ui.preferences.Xtend2RootPreferencePage;
import org.eclipse.xtext.xtend2.ui.refactoring.Xtend2RenameElementHandler;
import org.eclipse.xtext.xtend2.ui.refactoring.Xtend2RenameParticipant;

import com.google.inject.Binder;
import com.google.inject.name.Names;

/**
 * Use this class to register components to be used within the IDE.
 */
@SuppressWarnings("restriction")
public class Xtend2UiModule extends org.eclipse.xtext.xtend2.ui.AbstractXtend2UiModule {
	public Xtend2UiModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	public void configureDebugMode(Binder binder) {
		if (Boolean.getBoolean("org.eclipse.xtext.xtend.debug")) {
			binder.bindConstant().annotatedWith(Names.named(AbstractEditStrategy.DEBUG)).to(true);
		}
	}

	public Class<? extends IHighlightingConfiguration> bindIHighlightingConfiguration() {
		return HighlightingConfiguration.class;
	}

	@Override
	public Class<? extends AbstractAntlrTokenToAttributeIdMapper> bindAbstractAntlrTokenToAttributeIdMapper() {
		return TokenToAttributeIdMapper.class;
	}

	@Override
	public Class<? extends ITokenScanner> bindITokenScanner() {
		return RichStringAwareTokenScanner.class;
	}

	@Override
	public Class<? extends ISemanticHighlightingCalculator> bindISemanticHighlightingCalculator() {
		return RichStringAwareHighlightingCalculator.class;
	}

	public Class<? extends ITokenTypeToPartitionTypeMapper> bindITokenTypeToPartitionTypeMapper() {
		return TokenTypeToPartitionMapper.class;
	}

	@Override
	public Class<? extends AbstractEditStrategyProvider> bindAbstractEditStrategyProvider() {
		return AutoEditStrategyProvider.class;
	}

	public void configureIShowWhitespaceCharactersActionContributor(Binder binder) {
		binder.bind(IActionContributor.class).annotatedWith(Names.named("Show Whitespace"))
				.to(ShowWhitespaceCharactersActionContributor.class);
	}

	public Class<? extends DoubleClickStrategyProvider> bindDoubleClickStrategyProvider() {
		return Xtend2DoubleClickStrategyProvider.class;
	}

	@Override
	public Class<? extends IComparator> bindOutlineFilterAndSorter$IComparator() {
		return Xtend2OutlineNodeComparator.class;
	}

	public Class<? extends IFoldingRegionProvider> bindIFoldingRegionProvider() {
		return Xtend2FoldingRegionProvider.class;
	}

	@Override
	public Class<? extends ITypesProposalProvider> bindITypesProposalProvider() {
		return ImportingTypesProposalProvider.class;
	}

	@Override
	public Class<? extends IContentOutlinePage> bindIContentOutlinePage() {
		return Xtend2OutlinePage.class;
	}

	@Override
	public Class<? extends IHyperlinkHelper> bindIHyperlinkHelper() {
		return XtendHyperlinkHelper.class;
	}

	@Override
	public Class<? extends IEObjectHoverProvider> bindIEObjectHoverProvider() {
		return XtendHoverProvider.class;
	}
	
	public void configurePreferenceInitializer(Binder binder) {
		binder.bind(IPreferenceStoreInitializer.class).annotatedWith(Names.named("Xtend2RootPreferences")).to(Xtend2Preferences.Initializer.class);
	}
	
	public Class<? extends LanguageRootPreferencePage> bindLanguageRootPreferencePage() {
		return Xtend2RootPreferencePage.class;
	}

	@Override
	public Class<? extends RenameElementHandler> bindRenameElementHandler() {
		return Xtend2RenameElementHandler.class;
	}
	
	public Class<? extends JDTRenamePartcipant> bindJDTRenamePartcipant() {
		return Xtend2RenameParticipant.class;
	}
	
}
