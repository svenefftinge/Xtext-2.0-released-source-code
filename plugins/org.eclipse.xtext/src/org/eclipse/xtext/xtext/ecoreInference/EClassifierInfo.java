/*******************************************************************************
 * Copyright (c) 2008 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.eclipse.xtext.xtext.ecoreInference;

import java.util.Collection;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.util.ReflectionUtil;
import org.eclipse.xtext.util.Strings;

/**
 * @author Jan K�hnlein - Initial contribution and API
 * @author Heiko Behrens
 * 
 */
public abstract class EClassifierInfo {

	private final EClassifier eClassifier;
	private final boolean isGenerated;

	protected EClassifierInfo(EClassifier metaType, boolean isGenerated) {
		this.isGenerated = isGenerated;
		this.eClassifier = metaType;
	}

	public static EClassifierInfo createEClassInfo(EClass eClass, boolean isGenerated, Set<String> generatedEPackageURIs) {
		return new EClassInfo(eClass, isGenerated, generatedEPackageURIs);
	}

	public static EClassifierInfo createEDataTypeInfo(EDataType eDataType, boolean isGenerated) {
		return new EDataTypeInfo(eDataType, isGenerated);
	}

	public EClassifier getEClassifier() {
		return eClassifier;
	}

	public boolean isGenerated() {
		return isGenerated;
	}

	public boolean isAssignableFrom(EClassifierInfo subTypeInfo) {
		return getEClassifier().equals(subTypeInfo.getEClassifier());
	}

	public abstract boolean addSupertype(EClassifierInfo superTypeInfo);

	public abstract boolean addFeature(String featureName, EClassifierInfo featureType, boolean isMultivalue,
			boolean isContainment, AbstractElement parserElement) throws TransformationException;
	
	public static class EClassInfo extends EClassifierInfo {

		private Set<String> generatedEPackageURIs;
		
		public EClassInfo(EClassifier metaType, boolean isGenerated, Set<String> generatedEPackageURIs) {
			super(metaType, isGenerated);
			this.generatedEPackageURIs = generatedEPackageURIs;
		}

		@Override
		public boolean isAssignableFrom(EClassifierInfo subTypeInfo) {
			return super.isAssignableFrom(subTypeInfo) || (subTypeInfo instanceof EClassInfo)
					&& EcoreUtil2.isAssignableFrom(getEClass(),(EClass) subTypeInfo.getEClassifier());
		}

		@Override
		public boolean addSupertype(EClassifierInfo superTypeInfo) {
			EClass eClass = getEClass();
			EClass superEClass = (EClass) superTypeInfo.getEClassifier();

			if (superEClass.isSuperTypeOf(eClass)) {
				return true;
			}

			if (!isGenerated()) {
				throw new IllegalStateException("Type " + this.getEClassifier().getName()
						+ " is not generated and cannot be modified.");
			}
			if (!(superTypeInfo instanceof EClassInfo)) {
				throw new IllegalArgumentException("superTypeInfo must represent EClass");
			}

			if (eClass.equals(superEClass))
				// cannot add class as it's own superclass
				// this usually happens due to a rule call
				return false;

			return eClass.getESuperTypes().add(superEClass);
		}

		@Override
		public boolean addFeature(String featureName, EClassifierInfo featureType, boolean isMultivalue,
				boolean isContainment, AbstractElement parserElement) throws TransformationException {
			EClassifier featureClassifier = featureType.getEClassifier();
			return addFeature(featureName, featureClassifier, isMultivalue, isContainment, parserElement);
		}

		public boolean addFeature(EStructuralFeature prototype) {
			try {
				boolean isContainment = false;
				if (prototype instanceof EReference) {
					EReference reference = (EReference) prototype;
					isContainment = reference.isContainment();
				}
				boolean result = addFeature(prototype.getName(), prototype.getEType(), prototype.isMany(), isContainment, null);
				if (result) {
					EStructuralFeature newFeature = getEClass().getEStructuralFeature(prototype.getName());
					SourceAdapter oldAdapter = SourceAdapter.find(prototype);
					if (oldAdapter != null) {
						for(EObject source: oldAdapter.getSources())
							SourceAdapter.adapt(newFeature, source);	
					}
				}
				return result;
			}
			catch (TransformationException e) {
				throw new IllegalArgumentException(e.getMessage());
			}
		}
		
		public boolean containsCompatibleFeature(
				String name, 
				boolean isMulti, 
				boolean isContainment,
				EClassifier featureType,
				StringBuilder errorMessage) {
			EStructuralFeature existingFeature = getEClass().getEStructuralFeature(name);
			if (existingFeature != null) {
				boolean many = existingFeature.isMany();
				if (many == isMulti) {
					if (featureType instanceof EClass && existingFeature.getEType() instanceof EClass) {
						EClass expected = (EClass) featureType;
						EClass actual = (EClass) existingFeature.getEType();
						boolean result = EcoreUtil2.isAssignableFrom(actual, expected);
						if (!result) {
							errorMessage.append("The existing reference '" + name + "' has an incompatible type '" + actual.getName() + "'.");
							return result;
						}
						result &= isContainment == ((EReference) existingFeature).isContainment();
						if (!result) {
							errorMessage.append("The existing reference '" + name + "' has a different containment status.");
							return result;
						}
						result &= !((EReference) existingFeature).isContainer();
						if (!result) {
							errorMessage.append("The existing reference '" + name + "' is a container reference.");
							return result;
						}
						return result;
					} else if (featureType instanceof EDataType && existingFeature.getEType() instanceof EDataType) {
						EDataType expected = (EDataType) featureType;
						EDataType actual = (EDataType) existingFeature.getEType();
						Class<?> expectedInstanceClass = ReflectionUtil.getObjectType(expected.getInstanceClass());
						Class<?> actualInstanceClass = ReflectionUtil.getObjectType(actual.getInstanceClass());
						boolean result = actual.equals(expected) || expectedInstanceClass != null && actualInstanceClass != null
								&& actualInstanceClass.isAssignableFrom(expectedInstanceClass);
						if (!result) {
							errorMessage.append("The existing attribute '" + name + "' has an incompatible type '" + actual.getName() + "'.");
						}
						return result;
					} else {
						String msgPart = " has no type.";
						if (existingFeature.getEType() != null)
							msgPart = " has an incompatible type '" + existingFeature.getEType().getName()+"'.";
						errorMessage.append("The existing feature '" + name + "'" + msgPart);
					}
				} else {
					errorMessage.append("The existing feature '" + name + "' has a different cardinality.");
				}
			} else {
				errorMessage.append("The type '" + getEClass().getName() + "' does not have a feature '" + name + "'.");
			}
			return false;
		}
		
		public boolean isFeatureSemanticallyEqualApartFromType(EStructuralFeature f1, EStructuralFeature f2) {
			boolean result = f1.getName().equals(f2.getName());
			result &= f1.isMany() == f2.isMany();
			if (f1 instanceof EReference && f2 instanceof EReference)
				result &= ((EReference) f1).isContainment() == ((EReference) f2).isContainment();
			return result;
		}

		public boolean isFeatureSemanticallyEqualTo(EStructuralFeature f1, EStructuralFeature f2) {
			boolean result = isFeatureSemanticallyEqualApartFromType(f1, f2);
			if (f1 instanceof EReference && f2 instanceof EReference) {
				EClass f1Type = (EClass) f1.getEType();
				EClass f2Type = (EClass) f2.getEType();
				result &= f1Type.isSuperTypeOf(f2Type);
				result &= ((EReference) f1).isContainment() == ((EReference) f2).isContainment();
				result &= ((EReference) f1).isContainer() == ((EReference) f2).isContainer();
			} else
				result &= f1.getEType().equals(f2.getEType());
			return result;
		}

		public static enum FindResult {
			FeatureDoesNotExist, FeatureExists, DifferentFeatureWithSameNameExists
		}

		public EStructuralFeature findFeatureByName(Collection<EStructuralFeature> features, String name) {
			if (name == null)
				return null;
			for (EStructuralFeature feature : features)
				if (name.equals(feature.getName()))
					return feature;
			return null;
		}

		public FindResult containsSemanticallyEqualFeature(EStructuralFeature feature) {
			return containsSemanticallyEqualFeature(getEClass().getEAllStructuralFeatures(), feature);
		}

		public FindResult containsSemanticallyEqualFeature(Collection<EStructuralFeature> features,
				EStructuralFeature feature) {
			EStructuralFeature potentiallyEqualFeature = findFeatureByName(features, feature.getName());
			if (potentiallyEqualFeature == null)
				return FindResult.FeatureDoesNotExist;
			else if (isFeatureSemanticallyEqualTo(potentiallyEqualFeature, feature))
				return FindResult.FeatureExists;
			else
				return FindResult.DifferentFeatureWithSameNameExists;
		}

		private boolean addFeature(String featureName, EClassifier featureClassifier, boolean isMultivalue,
				boolean isContainment, AbstractElement grammarElement) throws TransformationException {
			if (!isGenerated()) {
				StringBuilder errorMessage = new StringBuilder();
				if (!containsCompatibleFeature(featureName, isMultivalue, isContainment, featureClassifier, errorMessage)) {
					throw new TransformationException(TransformationErrorCode.CannotCreateTypeInSealedMetamodel, 
							"Cannot find compatible feature "+featureName+" in sealed EClass "+getEClass().getName()+" from imported package "+getEClass().getEPackage().getNsURI()+". " + errorMessage.toString(), 
							grammarElement);
				}
				return true;
			}
			EStructuralFeature newFeature = createFeatureWith(featureName, featureClassifier, isMultivalue,
					isContainment);

			FindResult containsSemanticallyEqualFeature = containsSemanticallyEqualFeature(newFeature);
			switch (containsSemanticallyEqualFeature) {
				case FeatureDoesNotExist:
					if (!isGenerated())
						throw new TransformationException(TransformationErrorCode.CannotCreateTypeInSealedMetamodel, "Cannot create feature in sealed metamodel.", grammarElement);
					if (grammarElement != null)
						SourceAdapter.adapt(newFeature, grammarElement);
					return getEClass().getEStructuralFeatures().add(newFeature);
				case FeatureExists:
					if (isGenerated()) {
						EStructuralFeature existingFeature = findFeatureByName(getEClass().getEAllStructuralFeatures(), featureName);
						if (grammarElement != null)
							SourceAdapter.adapt(existingFeature, grammarElement);
					}
					return false;
				default:
					// do nothing
			}

			// feature with same name exists, but has a different, 
			// potentially incompatible configuration
			EStructuralFeature existingFeature = getEClass().getEStructuralFeature(featureName);

			if (!isFeatureSemanticallyEqualApartFromType(newFeature, existingFeature))
				throw new TransformationException(TransformationErrorCode.FeatureWithDifferentConfigurationAlreadyExists,
						"A feature '" + newFeature.getName() + "' with a different cardinality or containment " +
								"configuration already exists in type '" + getEClass().getName() + "'.",
						grammarElement);
			
			EClassifier compatibleType = EcoreUtil2.getCompatibleType(existingFeature.getEType(), newFeature.getEType());
			if (compatibleType == null)
				throw new TransformationException(TransformationErrorCode.NoCompatibleFeatureTypeAvailable,
						"Cannot find compatible type for features", grammarElement);
			
			if (isGenerated(existingFeature)) {
				existingFeature.setEType(compatibleType);
			} else {
				throw new TransformationException(TransformationErrorCode.FeatureWithDifferentConfigurationAlreadyExists,
						"Incompatible return type to existing feature", grammarElement);
			}
			return true;
		}

		protected boolean isGenerated(EStructuralFeature existingFeature) {
			return generatedEPackageURIs.contains(existingFeature.getEContainingClass().getEPackage().getNsURI());
		}

		private EStructuralFeature createFeatureWith(String featureName, EClassifier featureClassifier,
				boolean isMultivalue, boolean isContainment) {
			EStructuralFeature newFeature;

			if (featureClassifier instanceof EClass) {
				EReference reference = EcoreFactory.eINSTANCE.createEReference();
				reference.setContainment(isContainment);
				newFeature = reference;
			} else {
				newFeature = EcoreFactory.eINSTANCE.createEAttribute();
			}
			newFeature.setName(featureName);
			newFeature.setEType(featureClassifier);
			newFeature.setLowerBound(0);
			newFeature.setUpperBound(isMultivalue ? -1 : 1);
			newFeature.setUnique(!isMultivalue || (isContainment && featureClassifier instanceof EClass));
			if (newFeature.getEType() instanceof EEnum) {
				newFeature.setDefaultValue(null);
			}
			return newFeature;
		}

		public EClass getEClass() {
			return ((EClass) getEClassifier());
		}
	}

	public static class EDataTypeInfo extends EClassifierInfo {

		public EDataTypeInfo(EClassifier metaType, boolean isGenerated) {
			super(metaType, isGenerated);
		}

		@Override
		public boolean addSupertype(EClassifierInfo superTypeInfo) {
			throw new UnsupportedOperationException("Cannot add supertype "
					+ Strings.emptyIfNull(superTypeInfo.getEClassifier().getName()) + " to simple datatype "
					+ Strings.emptyIfNull(this.getEClassifier().getName()));
		}

		@Override
		public boolean addFeature(String featureName, EClassifierInfo featureType, boolean isMultivalue,
				boolean isContainment, AbstractElement parserElement) throws TransformationException {
			throw new UnsupportedOperationException("Cannot add feature " + featureName + " to simple datatype "
					+ Strings.emptyIfNull(this.getEClassifier().getName()));
		}

	}

}
