package org.eclipse.xtext.xtend2.tests.smoke

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.EObject

class Case_6 {

	dispatch transform(EClass model) {
		model.ETypeParameters.map(e|transform(e))
	}
	 
	dispatch transform(EPackage packageDecl) {
		packageDecl.eContents.map(e|transform(e))
	}
	
	dispatch transform(EStructuralFeature entity) {
		val inferredType = null
		newArrayList(inferredType as EObject) 	 
	}

}