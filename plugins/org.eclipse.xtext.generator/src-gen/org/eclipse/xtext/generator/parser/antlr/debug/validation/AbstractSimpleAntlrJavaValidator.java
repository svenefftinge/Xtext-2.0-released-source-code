package org.eclipse.xtext.generator.parser.antlr.debug.validation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;

public class AbstractSimpleAntlrJavaValidator extends AbstractDeclarativeValidator {

@Override
	protected List<EPackage> getEPackages() {
	    List<EPackage> result = new ArrayList<EPackage>();
	    result.add(org.eclipse.xtext.generator.parser.antlr.debug.simpleAntlr.SimpleAntlrPackage.eINSTANCE);
		return result;
	}

}
