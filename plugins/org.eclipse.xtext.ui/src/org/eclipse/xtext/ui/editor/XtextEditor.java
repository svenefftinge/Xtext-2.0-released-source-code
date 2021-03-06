/*******************************************************************************
 * Copyright (c) 2008 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.eclipse.xtext.ui.editor;

import java.text.BreakIterator;
import java.text.CharacterIterator;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.link.LinkedModeModel;
import org.eclipse.jface.text.link.LinkedPosition;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.AnnotationPainter;
import org.eclipse.jface.text.source.IAnnotationAccess;
import org.eclipse.jface.text.source.IAnnotationAccessExtension;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.IAnnotationModelExtension2;
import org.eclipse.jface.text.source.ICharacterPairMatcher;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.jface.text.source.projection.ProjectionSupport;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.IPostSelectionProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ST;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.DefaultMarkerAnnotationAccess;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.IUpdate;
import org.eclipse.ui.texteditor.MarkerAnnotation;
import org.eclipse.ui.texteditor.SelectMarkerRulerAction;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;
import org.eclipse.ui.texteditor.TextNavigationAction;
import org.eclipse.ui.texteditor.TextOperationAction;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.xtext.Constants;
import org.eclipse.xtext.ui.XtextUIMessages;
import org.eclipse.xtext.ui.editor.actions.IActionContributor;
import org.eclipse.xtext.ui.editor.bracketmatching.BracketMatchingPreferencesInitializer;
import org.eclipse.xtext.ui.editor.folding.IFoldingStructureProvider;
import org.eclipse.xtext.ui.editor.model.CommonWordIterator;
import org.eclipse.xtext.ui.editor.model.DocumentCharacterIterator;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.model.TerminalsTokenTypeToPartitionMapper;
import org.eclipse.xtext.ui.editor.model.XtextDocumentProvider;
import org.eclipse.xtext.ui.editor.model.XtextDocumentUtil;
import org.eclipse.xtext.ui.editor.preferences.IPreferenceStoreAccess;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightingHelper;
import org.eclipse.xtext.ui.editor.syntaxcoloring.TextAttributeProvider;
import org.eclipse.xtext.ui.editor.toggleComments.ToggleSLCommentAction;
import org.eclipse.xtext.ui.internal.Activator;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

/**
 * @author Dennis Huebner - Initial contribution and API
 * @author Peter Friese - Initial contribution and API
 * @author Sven Efftinge
 * @author Michael Clay
 * @author Dan Stefanescu - Fix for bug 278279
 */
public class XtextEditor extends TextEditor {
	public static final String ERROR_ANNOTATION_TYPE = "org.eclipse.xtext.ui.editor.error";
	public static final String WARNING_ANNOTATION_TYPE = "org.eclipse.xtext.ui.editor.warning";

	private static final Logger log = Logger.getLogger(XtextEditor.class);

	public static final String ID = "org.eclipse.xtext.baseEditor"; //$NON-NLS-1$

	@Inject
	private IFoldingStructureProvider foldingStructureProvider;

	@Inject(optional = true)
	private AnnotationPainter.IDrawingStrategy projectionAnnotationDrawingStrategy;

	@Inject
	private CompoundXtextEditorCallback callback;

	@Inject
	private XtextSourceViewerConfiguration sourceViewerConfiguration;

	private IContentOutlinePage outlinePage;

	@Inject(optional = true)
	private Provider<IContentOutlinePage> outlinePageProvider;

	@Inject
	private Provider<XtextDocumentProvider> documentProvider;

	@Inject
	private XtextSourceViewer.Factory sourceViewerFactory;

	@Inject
	private IHighlightingHelper highlightingHelper;

	@Inject
	private IPreferenceStoreAccess preferenceStoreAccess;
	
	@Inject
	private TextAttributeProvider textAttributeProvider;

	private ISelectionChangedListener selectionChangedListener;
	
	private IPropertyListener dirtyListener = new IPropertyListener() {
		public void propertyChanged(Object source, int propId) {
			if (propId == PROP_DIRTY && !isDirty()) {
				callback.afterSave(XtextEditor.this);
			}
		}
	};

	private String languageName;

	public XtextEditor() {
		if (log.isDebugEnabled())
			log.debug("Creating Xtext Editor. Instance: [" + this.toString() + "]");
	}

	public IXtextDocument getDocument() {
		return XtextDocumentUtil.get(getSourceViewer());
	}

	@Inject
	public void setLanguageName(@Named(Constants.LANGUAGE_NAME) String name) {
		this.languageName = name;
	}

	public String getLanguageName() {
		return languageName;
	}

	@Override
	protected void doSetInput(IEditorInput input) throws CoreException {
		if (log.isDebugEnabled()) {
			log.debug("doSetInput:" + input);
			log.debug("Editor instance is [" + this.toString() + "]");
		}
		removePropertyListener(dirtyListener);
		callback.beforeSetInput(this);
		super.doSetInput(input);
		callback.afterSetInput(this);
		addPropertyListener(dirtyListener);
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		if (log.isDebugEnabled())
			log.debug("init:" + input);

		// do document provider setup
		setDocumentProvider(documentProvider.get());

		// source viewer setup
		setSourceViewerConfiguration(sourceViewerConfiguration);

		setPreferenceStore(preferenceStoreAccess.getContextPreferenceStore(input));

		// NOTE: Outline CANNOT be initialized here, since we do not have access
		// to the source viewer yet (it will be created later).

		super.init(site, input);
	}

	@Override
	protected void setPreferenceStore(IPreferenceStore preferenceStore) {
		super.setPreferenceStore(preferenceStore);
		if (getSourceViewerConfiguration() instanceof XtextSourceViewerConfiguration) {
			XtextSourceViewerConfiguration xtextSourceViewerConfiguration = (XtextSourceViewerConfiguration) getSourceViewerConfiguration();
			xtextSourceViewerConfiguration.setPreferenceStore(preferenceStore);
		}
	}

	public XtextSourceViewerConfiguration getXtextSourceViewerConfiguration() {
		return sourceViewerConfiguration;
	}

	@Override
	public void doSaveAs() {
		super.doSaveAs();
		callback.afterSave(this);
	}

	@Override
	public void doSave(IProgressMonitor progressMonitor) {
		super.doSave(progressMonitor);
		callback.afterSave(this);
	}

	@Override
	public void doRevertToSaved() {
		super.doRevertToSaved();
		callback.afterSave(this);
	}
	
	/**
	 * Set key binding scope. Needed to make F3 work properly.
	 */
	@Override
	protected void initializeKeyBindingScopes() {
		setKeyBindingScopes(new String[] { "org.eclipse.xtext.ui.XtextEditorScope" }); //$NON-NLS-1$
	}

	public IResource getResource() {
		Object adapter = getEditorInput().getAdapter(IResource.class);
		if (adapter != null) {
			return (IResource) adapter;
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class adapter) {
		if (IContentOutlinePage.class.isAssignableFrom(adapter)) {
			return getContentOutlinePage();
		}
		return super.getAdapter(adapter);
	}

	private IContentOutlinePage getContentOutlinePage() {
		if (outlinePage == null) {
			outlinePage = createOutlinePage();
		}
		return outlinePage;
	}

	private IContentOutlinePage createOutlinePage() {
		IContentOutlinePage page = null;
		if (outlinePageProvider != null) {
			// can be null, optional injection
			page = outlinePageProvider.get();

			if (page != null) {
				if (page instanceof ISourceViewerAware) {
					((ISourceViewerAware) page).setSourceViewer(getSourceViewer());
				}
				if (page instanceof IXtextEditorAware) {
					((IXtextEditorAware) page).setEditor(this);
				}
			}
		}
		return page;
	}

	/**
	 * Informs the editor that its outline has been closed.
	 */
	public void outlinePageClosed() {
		if (outlinePage != null) {
			outlinePage = null;
			resetHighlightRange();
		}
	}

	@Inject
	private IActionContributor.CompositeImpl actioncontributor;

	@Override
	protected void createActions() {
		super.createActions();
		if (getSourceViewerConfiguration().getContentFormatter(getSourceViewer()) != null) {
			Action action = new TextOperationAction(XtextUIMessages.getResourceBundle(),
					"Format.", this, ISourceViewer.FORMAT); //$NON-NLS-1$
			action.setActionDefinitionId(Activator.PLUGIN_ID + ".FormatAction");
			setAction("Format", action); //$NON-NLS-1$
			markAsStateDependentAction("Format", true); //$NON-NLS-1$
			markAsSelectionDependentAction("Format", true); //$NON-NLS-1$
		}

		ToggleSLCommentAction action = new ToggleSLCommentAction(XtextUIMessages.getResourceBundle(),
				"ToggleComment.", this); //$NON-NLS-1$
		action.setActionDefinitionId(Activator.PLUGIN_ID + ".ToggleCommentAction");
		setAction("ToggleComment", action); //$NON-NLS-1$
		markAsStateDependentAction("ToggleComment", true); //$NON-NLS-1$
		markAsSelectionDependentAction("ToggleComment", true);
		configureToggleCommentAction(action);

		// Creates an build-in "click an ruler annotation, marks corresponding
		// text" - action
		SelectMarkerRulerAction markerAction = new XtextMarkerRulerAction(XtextUIMessages.getResourceBundle(),
				"XtextSelectAnnotationRulerAction.", this, getVerticalRuler()); //$NON-NLS-1$
		setAction(ITextEditorActionConstants.RULER_CLICK, markerAction);

		actioncontributor.contributeActions(this);
	}

	protected void configureToggleCommentAction(ToggleSLCommentAction action) {
		ISourceViewer sourceViewer = getSourceViewer();
		SourceViewerConfiguration configuration = getSourceViewerConfiguration();
		action.configure(sourceViewer, configuration);
	}

	/**
	 * @return true if content assist is available
	 * 
	 */
	public boolean isContentAssistAvailable() {
		boolean result = getSourceViewer().getTextOperationTarget().canDoOperation(ISourceViewer.CONTENTASSIST_PROPOSALS);
		return result;
//		return getSourceViewerConfiguration().getContentAssistant(getSourceViewer()) != null;
	}

	@Override
	protected ISourceViewer createSourceViewer(Composite parent, IVerticalRuler ruler, int styles) {
		if (log.isDebugEnabled())
			log.debug("Creating Xtext source viewer.");

		// overwrite superclass implementation to allow folding
		fAnnotationAccess = createAnnotationAccess();
		fOverviewRuler = createOverviewRuler(getSharedColors());
		ISourceViewer projectionViewer = sourceViewerFactory.createSourceViewer(parent, ruler, getOverviewRuler(),
				isOverviewRulerVisible(), styles);
		getSourceViewerDecorationSupport(projectionViewer);
		return projectionViewer;
	}

	@Inject
	private ICharacterPairMatcher characterPairMatcher;

	@Override
	protected void configureSourceViewerDecorationSupport(SourceViewerDecorationSupport support) {
		super.configureSourceViewerDecorationSupport(support);
		if (characterPairMatcher != null) {
			support.setCharacterPairMatcher(characterPairMatcher);
			support.setMatchingCharacterPainterPreferenceKeys(BracketMatchingPreferencesInitializer.IS_ACTIVE_KEY,
					BracketMatchingPreferencesInitializer.COLOR_KEY);
		}
	}

	private ProjectionSupport projectionSupport;

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		ProjectionViewer projectionViewer = (ProjectionViewer) getSourceViewer();
		projectionSupport = installProjectionSupport(projectionViewer);
		installFoldingSupport(projectionViewer);
		installHighlightingHelper();
		installSelectionChangedListener();
		callback.afterCreatePartControl(this);
	}

	protected ProjectionSupport installProjectionSupport(ProjectionViewer projectionViewer) {
		ProjectionSupport projectionSupport = new ProjectionSupport(projectionViewer, getAnnotationAccess(),
				getSharedColors());
		projectionSupport.addSummarizableAnnotationType(WARNING_ANNOTATION_TYPE); //$NON-NLS-1$
		projectionSupport.addSummarizableAnnotationType(ERROR_ANNOTATION_TYPE); //$NON-NLS-1$
		projectionSupport.setAnnotationPainterDrawingStrategy(projectionAnnotationDrawingStrategy);
		projectionSupport.install();
		return projectionSupport;
	}

	protected void installFoldingSupport(ProjectionViewer projectionViewer) {
		foldingStructureProvider.install(this, projectionViewer);
		projectionViewer.doOperation(ProjectionViewer.TOGGLE);
	}

	private void installSelectionChangedListener() {
		selectionChangedListener = new ISelectionChangedListener() {
			public void selectionChanged(final SelectionChangedEvent event) {
				updateStatusLine();
			}
		};
		final ISelectionProvider selectionProvider = getSelectionProvider();
		if (selectionProvider instanceof IPostSelectionProvider) {
			final IPostSelectionProvider postSelectionProvider = (IPostSelectionProvider) selectionProvider;
			postSelectionProvider.addPostSelectionChangedListener(selectionChangedListener);
		} else {
			getSelectionProvider().addSelectionChangedListener(selectionChangedListener);
		}
	}

	private void installHighlightingHelper() {
		if (highlightingHelper != null)
			highlightingHelper.install(this, (XtextSourceViewer) getSourceViewer());
	}

	private void uninstallHighlightingHelper() {
		if (highlightingHelper != null)
			highlightingHelper.uninstall();
	}

	@Override
	public void dispose() {
		callback.beforeDispose(this);
		actioncontributor.editorDisposed(this);
		super.dispose();
		if (projectionSupport != null) {
			projectionSupport.dispose();
		}
		if (outlinePage != null) {
			outlinePage = null;
		}
		uninstallFoldingSupport();
		uninstallHighlightingHelper();
		uninstallSelectionChangedListener();
	}

	protected void uninstallFoldingSupport() {
		if (foldingStructureProvider != null) {
			foldingStructureProvider.uninstall();
			foldingStructureProvider = null;
		}
	}

	private void uninstallSelectionChangedListener() {
		ISelectionProvider selectionProvider = getSelectionProvider();
		if (selectionProvider != null) {
			if (selectionProvider instanceof IPostSelectionProvider) {
				final IPostSelectionProvider postSelectionProvider = (IPostSelectionProvider) selectionProvider;
				postSelectionProvider.removePostSelectionChangedListener(selectionChangedListener);
			} else {
				selectionProvider.removeSelectionChangedListener(selectionChangedListener);
			}
		}
	}

	public ISourceViewer getInternalSourceViewer() {
		return getSourceViewer();
	}

	@Override
	protected void handlePreferenceStoreChanged(PropertyChangeEvent event) {
		super.handlePreferenceStoreChanged(event);
		if (getSourceViewer() == null)
			return;
		// TODO (dennis) move preference store constants to ui.core or create a
		// handlePrefStoreChanged service
		boolean tokenStyleChanged = event.getProperty().contains(".syntaxColorer.tokenStyles");
		if (tokenStyleChanged) {
			textAttributeProvider.propertyChange(event);
			initializeViewerColors(getSourceViewer());
			getSourceViewer().invalidateTextPresentation();
		}
	}

	@Override
	protected String[] collectContextMenuPreferencePages() {
		String[] ids = super.collectContextMenuPreferencePages();
		String[] more = new String[ids.length + 4];
		// NOTE: preference page at index 0 will be opened, see
		// PreferencesUtil.createPreferenceDialogOn
		more[0] = getLanguageName() + ".editor"; //$NON-NLS-1$
		more[1] = getLanguageName();
		more[2] = getLanguageName() + ".templates"; //$NON-NLS-1$
		more[3] = getLanguageName() + ".coloring"; //$NON-NLS-1$
		System.arraycopy(ids, 0, more, 4, ids.length);
		return more;
	}

	@Override
	protected IAnnotationAccess createAnnotationAccess() {
		return new DefaultMarkerAnnotationAccess() {
			@Override
			public int getLayer(Annotation annotation) {
				if (annotation.isMarkedDeleted()) {
					return IAnnotationAccessExtension.DEFAULT_LAYER;
				}
				return super.getLayer(annotation);
			}
		};
	}

	protected void updateStatusLine() {
		final ITextSelection selection = (ITextSelection) getSelectionProvider().getSelection();
		final Annotation annotation = getAnnotation(selection.getOffset(), selection.getLength());
		String message = null;
		if (annotation != null) {
			updateMarkerViews(annotation);
			if (isProblemMarkerAnnotation(annotation)) {
				message = annotation.getText();
			}
		}
		setStatusLineMessage(message);
	}

	@Override
	public boolean validateEditorInputState() {
		return callback.onValidateEditorInputState(this) && super.validateEditorInputState();
	}

	public void updatedTitleImage(Image image) {
		setTitleImage(image);
	}

	@Override
	public Image getDefaultImage() {
		IEditorRegistry editorRegistry = PlatformUI.getWorkbench().getEditorRegistry();
		IEditorDescriptor editorDesc = editorRegistry.findEditor(getSite().getId());
		ImageDescriptor imageDesc = editorDesc != null ? editorDesc.getImageDescriptor() : null;
		return imageDesc != null ? imageDesc.createImage() : super.getDefaultImage();
	}

	/*
	 * @see org.eclipse.ui.texteditor.AbstractTextEditor#rulerContextMenuAboutToShow(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	protected void rulerContextMenuAboutToShow(IMenuManager menu) {
		super.rulerContextMenuAboutToShow(menu);
		IMenuManager foldingMenu = new MenuManager(XtextUIMessages.Editor_FoldingMenu_name, "projection"); //$NON-NLS-1$
		menu.appendToGroup(ITextEditorActionConstants.GROUP_RULERS, foldingMenu);
		IAction action = getAction("FoldingToggle"); //$NON-NLS-1$
		foldingMenu.add(action);
		action = getAction("FoldingExpandAll"); //$NON-NLS-1$
		foldingMenu.add(action);
		action = getAction("FoldingCollapseAll"); //$NON-NLS-1$
		foldingMenu.add(action);
		action = getAction("FoldingRestore"); //$NON-NLS-1$
		foldingMenu.add(action);
	}

	/**
	 * Resets the foldings structure according to the folding preferences.
	 */
	public void resetProjection() {
		if (foldingStructureProvider != null) {
			foldingStructureProvider.initialize();
		}
	}

	@SuppressWarnings("rawtypes")
	private Annotation getAnnotation(final int offset, final int length) {
		final IAnnotationModel model = getDocumentProvider().getAnnotationModel(getEditorInput());
		if (model == null)
			return null;

		Iterator iterator;
		if (model instanceof IAnnotationModelExtension2) {
			iterator = ((IAnnotationModelExtension2) model).getAnnotationIterator(offset, length, true, true);
		} else {
			iterator = model.getAnnotationIterator();
		}

		while (iterator.hasNext()) {
			final Annotation a = (Annotation) iterator.next();
			final Position p = model.getPosition(a);
			if (p != null && p.overlapsWith(offset, length))
				return a;
		}
		return null;
	}

	private boolean isProblemMarkerAnnotation(final Annotation annotation) {
		if (!(annotation instanceof MarkerAnnotation))
			return false;
		try {
			return (((MarkerAnnotation) annotation).getMarker().isSubtypeOf(IMarker.PROBLEM));
		} catch (final CoreException e) {
			return false;
		}
	}

	/**
	 * Externally set the editor callback, e.g. to disable dirty state support for a specific instance.
	 */
	public void setXtextEditorCallback(CompoundXtextEditorCallback callback) {
		this.callback = callback;
	}
	
	/**
	 * Copied from {@link org.eclipse.ui.texteditor.AbstractTextEditor#selectAndReveal(int, int)} 
	 * and removed selection functionality.
	 */
	public void reveal(int offset, int length) {
		if (getSourceViewer() == null)
			return;
		StyledText widget= getSourceViewer().getTextWidget();
		widget.setRedraw(false);
		{
			adjustHighlightRange(offset, length);
			getSourceViewer().revealRange(offset, length);
			markInNavigationHistory();
		}
		widget.setRedraw(true);
	}
	
	protected CommonWordIterator createWordIterator() {
		return new CommonWordIterator(true);
	}
	
	protected DeleteNextSubWordAction createDeleteNextSubWordAction() {
		return new DeleteNextSubWordAction();
	}

	protected DeletePreviousSubWordAction createDeletePreviousSubWordAction() {
		return new DeletePreviousSubWordAction();
	}

	protected SelectNextSubWordAction createSelectNextSubWordAction() {
		return new SelectNextSubWordAction();
	}

	protected SelectPreviousSubWordAction createSelectPreviousSubWordAction() {
		return new SelectPreviousSubWordAction();
	}

	protected NavigateNextSubWordAction createNavigateNextSubWordAction() {
		return new NavigateNextSubWordAction();
	}

	protected NavigatePreviousSubWordAction createNavigatePreviousSubWordAction() {
		return new NavigatePreviousSubWordAction();
	}

	protected SmartLineStartAction createSmartLineStartAction(final StyledText textWidget, boolean doSelect) {
		return new SmartLineStartAction(textWidget, doSelect);
	}
	
	// CODE BELOW WAS INITIALLY COPIED FROM JavaEditor
	
	@Override
	protected void createNavigationActions() {
		super.createNavigationActions();
		
		final StyledText textWidget= getSourceViewer().getTextWidget();

		IAction action = createSmartLineStartAction(textWidget, false);
		action.setActionDefinitionId(ITextEditorActionDefinitionIds.LINE_START);
		setAction(ITextEditorActionDefinitionIds.LINE_START, action);

		action = createSmartLineStartAction(textWidget, true);
		action.setActionDefinitionId(ITextEditorActionDefinitionIds.SELECT_LINE_START);
		setAction(ITextEditorActionDefinitionIds.SELECT_LINE_START, action);

		action = createNavigatePreviousSubWordAction();
		action.setActionDefinitionId(ITextEditorActionDefinitionIds.WORD_PREVIOUS);
		setAction(ITextEditorActionDefinitionIds.WORD_PREVIOUS, action);
		textWidget.setKeyBinding(SWT.CTRL | SWT.ARROW_LEFT, SWT.NULL);

		action = createNavigateNextSubWordAction();
		action.setActionDefinitionId(ITextEditorActionDefinitionIds.WORD_NEXT);
		setAction(ITextEditorActionDefinitionIds.WORD_NEXT, action);
		textWidget.setKeyBinding(SWT.CTRL | SWT.ARROW_RIGHT, SWT.NULL);

		action = createSelectPreviousSubWordAction();
		action.setActionDefinitionId(ITextEditorActionDefinitionIds.SELECT_WORD_PREVIOUS);
		setAction(ITextEditorActionDefinitionIds.SELECT_WORD_PREVIOUS, action);
		textWidget.setKeyBinding(SWT.CTRL | SWT.SHIFT | SWT.ARROW_LEFT, SWT.NULL);

		action = createSelectNextSubWordAction();
		action.setActionDefinitionId(ITextEditorActionDefinitionIds.SELECT_WORD_NEXT);
		setAction(ITextEditorActionDefinitionIds.SELECT_WORD_NEXT, action);
		textWidget.setKeyBinding(SWT.CTRL | SWT.SHIFT | SWT.ARROW_RIGHT, SWT.NULL);

		action = createDeletePreviousSubWordAction();
		action.setActionDefinitionId(ITextEditorActionDefinitionIds.DELETE_PREVIOUS_WORD);
		setAction(ITextEditorActionDefinitionIds.DELETE_PREVIOUS_WORD, action);
		textWidget.setKeyBinding(SWT.CTRL | SWT.BS, SWT.NULL);
		markAsStateDependentAction(ITextEditorActionDefinitionIds.DELETE_PREVIOUS_WORD, true);

		action = createDeleteNextSubWordAction();
		action.setActionDefinitionId(ITextEditorActionDefinitionIds.DELETE_NEXT_WORD);
		setAction(ITextEditorActionDefinitionIds.DELETE_NEXT_WORD, action);
		textWidget.setKeyBinding(SWT.CTRL | SWT.DEL, SWT.NULL);
		markAsStateDependentAction(ITextEditorActionDefinitionIds.DELETE_NEXT_WORD, true);
	}

	/**
	 * Text navigation action to navigate to the next sub-word.
	 */
	protected abstract class NextSubWordAction extends TextNavigationAction {

		protected CommonWordIterator fIterator = createWordIterator();

		/**
		 * Creates a new next sub-word action.
		 * 
		 * @param code
		 *            Action code for the default operation. Must be an action code from @see org.eclipse.swt.custom.ST.
		 */
		protected NextSubWordAction(int code) {
			super(getSourceViewer().getTextWidget(), code);
		}

		/*
		 * @see org.eclipse.jface.action.IAction#run()
		 */
		@Override
		public void run() {
			// TODO preferences
			// Check whether we are in a java code partition and the preference is enabled
			//			final IPreferenceStore store= getPreferenceStore();
			//			if (!store.getBoolean(PreferenceConstants.EDITOR_SUB_WORD_NAVIGATION)) {
			//				super.run();
			//				return;
			//			}

			final ISourceViewer viewer = getSourceViewer();
			final IDocument document = viewer.getDocument();
			try {
				fIterator.setText((CharacterIterator) new DocumentCharacterIterator(document));
				int position = widgetOffset2ModelOffset(viewer, viewer.getTextWidget().getCaretOffset());
				if (position == -1)
					return;

				int next = findNextPosition(position);
				if (isBlockSelectionModeEnabled()
						&& document.getLineOfOffset(next) != document.getLineOfOffset(position)) {
					super.run(); // may navigate into virtual white space
				} else if (next != BreakIterator.DONE) {
					setCaretPosition(next);
					getTextWidget().showSelection();
					fireSelectionChanged();
				}
			} catch (BadLocationException x) {
				// ignore
			}
		}

		/**
		 * Finds the next position after the given position.
		 * 
		 * @param position
		 *            the current position
		 * @return the next position
		 */
		protected int findNextPosition(int position) {
			ISourceViewer viewer = getSourceViewer();
			int widget = -1;
			int next = position;
			while (next != BreakIterator.DONE && widget == -1) {
				next = fIterator.following(next);
				if (next != BreakIterator.DONE)
					widget = modelOffset2WidgetOffset(viewer, next);
			}

			IDocument document = viewer.getDocument();
			LinkedModeModel model = LinkedModeModel.getModel(document, position);
			if (model != null) {
				LinkedPosition linkedPosition = model.findPosition(new LinkedPosition(document, position, 0));
				if (linkedPosition != null) {
					int linkedPositionEnd = linkedPosition.getOffset() + linkedPosition.getLength();
					if (position != linkedPositionEnd && linkedPositionEnd < next)
						next = linkedPositionEnd;
				} else {
					LinkedPosition nextLinkedPosition = model.findPosition(new LinkedPosition(document, next, 0));
					if (nextLinkedPosition != null) {
						int nextLinkedPositionOffset = nextLinkedPosition.getOffset();
						if (position != nextLinkedPositionOffset && nextLinkedPositionOffset < next)
							next = nextLinkedPositionOffset;
					}
				}
			}

			return next;
		}

		/**
		 * Sets the caret position to the sub-word boundary given with <code>position</code>.
		 * 
		 * @param position
		 *            Position where the action should move the caret
		 */
		protected abstract void setCaretPosition(int position);
	}

	/**
	 * Text navigation action to navigate to the next sub-word.
	 * 
	 * @since 3.0
	 */
	protected class NavigateNextSubWordAction extends NextSubWordAction {

		/**
		 * Creates a new navigate next sub-word action.
		 */
		public NavigateNextSubWordAction() {
			super(ST.WORD_NEXT);
		}

		/*
		 * @see org.eclipse.xtext.ui.editor.XtextEditor.NextSubWordAction#setCaretPosition(int)
		 */
		@Override
		protected void setCaretPosition(final int position) {
			getTextWidget().setCaretOffset(modelOffset2WidgetOffset(getSourceViewer(), position));
		}
	}

	/**
	 * Text operation action to delete the next sub-word.
	 * 
	 * @since 3.0
	 */
	protected class DeleteNextSubWordAction extends NextSubWordAction implements IUpdate {

		/**
		 * Creates a new delete next sub-word action.
		 */
		public DeleteNextSubWordAction() {
			super(ST.DELETE_WORD_NEXT);
		}

		/*
		 * @see org.eclipse.xtext.ui.editor.XtextEditor.NextSubWordAction#setCaretPosition(int)
		 */
		@Override
		protected void setCaretPosition(final int position) {
			if (!validateEditorInputState())
				return;

			final ISourceViewer viewer = getSourceViewer();
			StyledText text = viewer.getTextWidget();
			Point widgetSelection = text.getSelection();
			if (isBlockSelectionModeEnabled() && widgetSelection.y != widgetSelection.x) {
				final int caret = text.getCaretOffset();
				final int offset = modelOffset2WidgetOffset(viewer, position);

				if (caret == widgetSelection.x)
					text.setSelectionRange(widgetSelection.y, offset - widgetSelection.y);
				else
					text.setSelectionRange(widgetSelection.x, offset - widgetSelection.x);
				text.invokeAction(ST.DELETE_NEXT);
			} else {
				Point selection = viewer.getSelectedRange();
				final int caret, length;
				if (selection.y != 0) {
					caret = selection.x;
					length = selection.y;
				} else {
					caret = widgetOffset2ModelOffset(viewer, text.getCaretOffset());
					length = position - caret;
				}

				try {
					viewer.getDocument().replace(caret, length, ""); //$NON-NLS-1$
				} catch (BadLocationException exception) {
					// Should not happen
				}
			}
		}

		/*
		 * @see org.eclipse.ui.texteditor.IUpdate#update()
		 */
		public void update() {
			setEnabled(isEditorInputModifiable());
		}
	}

	/**
	 * Text operation action to select the next sub-word.
	 */
	protected class SelectNextSubWordAction extends NextSubWordAction {

		/**
		 * Creates a new select next sub-word action.
		 */
		public SelectNextSubWordAction() {
			super(ST.SELECT_WORD_NEXT);
		}

		/*
		 * @see org.eclipse.xtext.ui.editor.XtextEditor.NextSubWordAction#setCaretPosition(int)
		 */
		@Override
		protected void setCaretPosition(final int position) {
			final ISourceViewer viewer = getSourceViewer();

			final StyledText text = viewer.getTextWidget();
			if (text != null && !text.isDisposed()) {

				final Point selection = text.getSelection();
				final int caret = text.getCaretOffset();
				final int offset = modelOffset2WidgetOffset(viewer, position);

				if (caret == selection.x)
					text.setSelectionRange(selection.y, offset - selection.y);
				else
					text.setSelectionRange(selection.x, offset - selection.x);
			}
		}
	}

	/**
	 * Text navigation action to navigate to the previous sub-word.
	 */
	protected abstract class PreviousSubWordAction extends TextNavigationAction {

		protected CommonWordIterator fIterator = createWordIterator();

		/**
		 * Creates a new previous sub-word action.
		 * 
		 * @param code
		 *            Action code for the default operation. Must be an action code from @see org.eclipse.swt.custom.ST.
		 */
		protected PreviousSubWordAction(final int code) {
			super(getSourceViewer().getTextWidget(), code);
		}

		/*
		 * @see org.eclipse.jface.action.IAction#run()
		 */
		@Override
		public void run() {
			// TODO preferences
			// Check whether we are in a java code partition and the preference is enabled
			//			final IPreferenceStore store= getPreferenceStore();
			//			if (!store.getBoolean(PreferenceConstants.EDITOR_SUB_WORD_NAVIGATION)) {
			//				super.run();
			//				return;
			//			}

			final ISourceViewer viewer = getSourceViewer();
			final IDocument document = viewer.getDocument();
			try {
				fIterator.setText((CharacterIterator) new DocumentCharacterIterator(document));
				int position = widgetOffset2ModelOffset(viewer, viewer.getTextWidget().getCaretOffset());
				if (position == -1)
					return;

				int previous = findPreviousPosition(position);
				if (isBlockSelectionModeEnabled()
						&& document.getLineOfOffset(previous) != document.getLineOfOffset(position)) {
					super.run(); // may navigate into virtual white space
				} else if (previous != BreakIterator.DONE) {
					setCaretPosition(previous);
					getTextWidget().showSelection();
					fireSelectionChanged();
				}
			} catch (BadLocationException x) {
				// ignore - getLineOfOffset failed
			}

		}

		/**
		 * Finds the previous position before the given position.
		 * 
		 * @param position
		 *            the current position
		 * @return the previous position
		 */
		protected int findPreviousPosition(int position) {
			ISourceViewer viewer = getSourceViewer();
			int widget = -1;
			int previous = position;
			while (previous != BreakIterator.DONE && widget == -1) { // XXX: optimize
				previous = fIterator.preceding(previous);
				if (previous != BreakIterator.DONE)
					widget = modelOffset2WidgetOffset(viewer, previous);
			}

			IDocument document = viewer.getDocument();
			LinkedModeModel model = LinkedModeModel.getModel(document, position);
			if (model != null) {
				LinkedPosition linkedPosition = model.findPosition(new LinkedPosition(document, position, 0));
				if (linkedPosition != null) {
					int linkedPositionOffset = linkedPosition.getOffset();
					if (position != linkedPositionOffset && previous < linkedPositionOffset)
						previous = linkedPositionOffset;
				} else {
					LinkedPosition previousLinkedPosition = model
							.findPosition(new LinkedPosition(document, previous, 0));
					if (previousLinkedPosition != null) {
						int previousLinkedPositionEnd = previousLinkedPosition.getOffset()
								+ previousLinkedPosition.getLength();
						if (position != previousLinkedPositionEnd && previous < previousLinkedPositionEnd)
							previous = previousLinkedPositionEnd;
					}
				}
			}

			return previous;
		}

		/**
		 * Sets the caret position to the sub-word boundary given with <code>position</code>.
		 * 
		 * @param position
		 *            Position where the action should move the caret
		 */
		protected abstract void setCaretPosition(int position);
	}

	/**
	 * Text navigation action to navigate to the previous sub-word.
	 * 
	 * @since 3.0
	 */
	protected class NavigatePreviousSubWordAction extends PreviousSubWordAction {

		/**
		 * Creates a new navigate previous sub-word action.
		 */
		public NavigatePreviousSubWordAction() {
			super(ST.WORD_PREVIOUS);
		}

		/*
		 * @see org.eclipse.xtext.ui.editor.XtextEditor.PreviousSubWordAction#setCaretPosition(int)
		 */
		@Override
		protected void setCaretPosition(final int position) {
			getTextWidget().setCaretOffset(modelOffset2WidgetOffset(getSourceViewer(), position));
		}
	}

	/**
	 * Text operation action to delete the previous sub-word.
	 * 
	 * @since 3.0
	 */
	protected class DeletePreviousSubWordAction extends PreviousSubWordAction implements IUpdate {

		/**
		 * Creates a new delete previous sub-word action.
		 */
		public DeletePreviousSubWordAction() {
			super(ST.DELETE_WORD_PREVIOUS);
		}

		/*
		 * @see org.eclipse.xtext.ui.editor.XtextEditor.PreviousSubWordAction#setCaretPosition(int)
		 */
		@Override
		protected void setCaretPosition(int position) {
			if (!validateEditorInputState())
				return;

			final int length;
			final ISourceViewer viewer = getSourceViewer();
			StyledText text = viewer.getTextWidget();
			Point widgetSelection = text.getSelection();
			if (isBlockSelectionModeEnabled() && widgetSelection.y != widgetSelection.x) {
				final int caret = text.getCaretOffset();
				final int offset = modelOffset2WidgetOffset(viewer, position);

				if (caret == widgetSelection.x)
					text.setSelectionRange(widgetSelection.y, offset - widgetSelection.y);
				else
					text.setSelectionRange(widgetSelection.x, offset - widgetSelection.x);
				text.invokeAction(ST.DELETE_PREVIOUS);
			} else {
				Point selection = viewer.getSelectedRange();
				if (selection.y != 0) {
					position = selection.x;
					length = selection.y;
				} else {
					length = widgetOffset2ModelOffset(viewer, text.getCaretOffset()) - position;
				}

				try {
					viewer.getDocument().replace(position, length, ""); //$NON-NLS-1$
				} catch (BadLocationException exception) {
					// Should not happen
				}
			}
		}

		/*
		 * @see org.eclipse.ui.texteditor.IUpdate#update()
		 */
		public void update() {
			setEnabled(isEditorInputModifiable());
		}
	}

	/**
	 * Text operation action to select the previous sub-word.
	 */
	protected class SelectPreviousSubWordAction extends PreviousSubWordAction {

		/**
		 * Creates a new select previous sub-word action.
		 */
		public SelectPreviousSubWordAction() {
			super(ST.SELECT_WORD_PREVIOUS);
		}

		/*
		 * @see org.eclipse.xtext.ui.editor.XtextEditor.PreviousSubWordAction#setCaretPosition(int)
		 */
		@Override
		protected void setCaretPosition(final int position) {
			final ISourceViewer viewer = getSourceViewer();

			final StyledText text = viewer.getTextWidget();
			if (text != null && !text.isDisposed()) {

				final Point selection = text.getSelection();
				final int caret = text.getCaretOffset();
				final int offset = modelOffset2WidgetOffset(viewer, position);

				if (caret == selection.x)
					text.setSelectionRange(selection.y, offset - selection.y);
				else
					text.setSelectionRange(selection.x, offset - selection.x);
			}
		}
	}

	/**
	 * This action implements smart home.
	 * 
	 * Instead of going to the start of a line it does the following:
	 * 
	 * - if smart home/end is enabled and the caret is after the line's first non-whitespace then the caret is moved
	 * directly before it, taking comments into account. 
	 * - if the caret is before the line's first non-whitespace the caret is moved to the beginning of the line 
	 * - if the caret is at the beginning of the line see first case.
	 */
	protected class SmartLineStartAction extends LineStartAction {

		/**
		 * Creates a new smart line start action
		 * 
		 * @param textWidget
		 *            the styled text widget
		 * @param doSelect
		 *            a boolean flag which tells if the text up to the beginning of the line should be selected
		 */
		public SmartLineStartAction(final StyledText textWidget, final boolean doSelect) {
			super(textWidget, doSelect);
		}

		/*
		 * @see org.eclipse.ui.texteditor.AbstractTextEditor.LineStartAction#getLineStartPosition(java.lang.String, int, java.lang.String)
		 */
		@Override
		protected int getLineStartPosition(final IDocument document, final String line, final int length,
				final int offset) {

			String type = IDocument.DEFAULT_CONTENT_TYPE;
			try {
				type = TextUtilities.getContentType(document, IDocumentExtension3.DEFAULT_PARTITIONING, offset, false);
			} catch (BadLocationException exception) {
				// Should not happen
			}

			int index = super.getLineStartPosition(document, line, length, offset);
			if (type.equals(TerminalsTokenTypeToPartitionMapper.COMMENT_PARTITION)
					|| type.equals(TerminalsTokenTypeToPartitionMapper.SL_COMMENT_PARTITION)) {
				if (index < length - 1 && line.charAt(index) == '*' && line.charAt(index + 1) != '/') {
					do {
						++index;
					} while (index < length && Character.isWhitespace(line.charAt(index)));
				} else if (index < length - 1 && line.charAt(index) == '/' && (line.charAt(index + 1) == '/' || line.charAt(index + 1) == '*')) {
					index++;
					do { 
						++index;
					} while (index < length && Character.isWhitespace(line.charAt(index)));
				}
			} else if (type.equals(IDocument.DEFAULT_CONTENT_TYPE)) {
				if (index < length - 1 && line.charAt(index) == '/' && (line.charAt(index + 1) == '/' || line.charAt(index + 1) == '*')) {
					index++;
					do {
						++index;
					} while (index < length && Character.isWhitespace(line.charAt(index)));
				}
			}
			return index;
		}
	}

}
