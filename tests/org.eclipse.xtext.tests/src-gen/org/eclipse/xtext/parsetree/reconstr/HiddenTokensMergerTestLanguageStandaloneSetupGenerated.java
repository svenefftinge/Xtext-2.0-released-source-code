
package org.eclipse.xtext.parsetree.reconstr;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.ISetup;
import org.eclipse.emf.ecore.resource.Resource;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Generated from StandaloneSetup.xpt!
 */
@SuppressWarnings("all")
public class HiddenTokensMergerTestLanguageStandaloneSetupGenerated implements ISetup {

	public Injector createInjectorAndDoEMFRegistration() {
		org.eclipse.xtext.common.TerminalsStandaloneSetup.doSetup();

		Injector injector = createInjector();
		register(injector);
		return injector;
	}
	
	public Injector createInjector() {
		return Guice.createInjector(new org.eclipse.xtext.parsetree.reconstr.HiddenTokensMergerTestLanguageRuntimeModule());
	}
	
	public void register(Injector injector) {

		org.eclipse.xtext.resource.IResourceFactory resourceFactory = injector.getInstance(org.eclipse.xtext.resource.IResourceFactory.class);
		org.eclipse.xtext.resource.IResourceServiceProvider serviceProvider = injector.getInstance(org.eclipse.xtext.resource.IResourceServiceProvider.class);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("hiddentokensmergertestlanguage", resourceFactory);
		org.eclipse.xtext.resource.IResourceServiceProvider.Registry.INSTANCE.getExtensionToFactoryMap().put("hiddentokensmergertestlanguage", serviceProvider);
		
	if (!EPackage.Registry.INSTANCE.containsKey("http://www.eclipse.org/2009/tmf/xtext/hiddentokensmerger")) {
		EPackage.Registry.INSTANCE.put("http://www.eclipse.org/2009/tmf/xtext/hiddentokensmerger", org.eclipse.xtext.parsetree.reconstr.hiddentokenmergertest.HiddentokenmergertestPackage.eINSTANCE);
	}

	}
}
