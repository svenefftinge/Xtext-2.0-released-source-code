/**
 * <copyright>
 * </copyright>
 *
 * $Id: BuilderStatePackage.java,v 1.10 2010/04/12 20:19:21 szarnekow Exp $
 */
package org.eclipse.xtext.builder.builderState;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.xtext.builder.builderState.BuilderStateFactory
 * @model kind="package"
 * @generated
 */
public interface BuilderStatePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "builderState";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/xtext/builderstate/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "builderState";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BuilderStatePackage eINSTANCE = org.eclipse.xtext.builder.builderState.impl.BuilderStatePackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.xtext.builder.builderState.impl.ResourceDescriptionImpl <em>Resource Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.xtext.builder.builderState.impl.ResourceDescriptionImpl
	 * @see org.eclipse.xtext.builder.builderState.impl.BuilderStatePackageImpl#getResourceDescription()
	 * @generated
	 */
	int RESOURCE_DESCRIPTION = 0;

	/**
	 * The feature id for the '<em><b>URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_DESCRIPTION__URI = 0;

	/**
	 * The feature id for the '<em><b>Exported Objects</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_DESCRIPTION__EXPORTED_OBJECTS = 1;

	/**
	 * The feature id for the '<em><b>Reference Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_DESCRIPTION__REFERENCE_DESCRIPTIONS = 2;

	/**
	 * The feature id for the '<em><b>Imported Names</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_DESCRIPTION__IMPORTED_NAMES = 3;

	/**
	 * The number of structural features of the '<em>Resource Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_DESCRIPTION_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.xtext.builder.builderState.impl.EObjectDescriptionImpl <em>EObject Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.xtext.builder.builderState.impl.EObjectDescriptionImpl
	 * @see org.eclipse.xtext.builder.builderState.impl.BuilderStatePackageImpl#getEObjectDescription()
	 * @generated
	 */
	int EOBJECT_DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Fragment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_DESCRIPTION__FRAGMENT = 0;

	/**
	 * The feature id for the '<em><b>EClass</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_DESCRIPTION__ECLASS = 1;

	/**
	 * The feature id for the '<em><b>Resource Descriptor</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_DESCRIPTION__RESOURCE_DESCRIPTOR = 2;

	/**
	 * The feature id for the '<em><b>User Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_DESCRIPTION__USER_DATA = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_DESCRIPTION__NAME = 4;

	/**
	 * The number of structural features of the '<em>EObject Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_DESCRIPTION_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.eclipse.xtext.builder.builderState.impl.UserDataEntryImpl <em>User Data Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.xtext.builder.builderState.impl.UserDataEntryImpl
	 * @see org.eclipse.xtext.builder.builderState.impl.BuilderStatePackageImpl#getUserDataEntry()
	 * @generated
	 */
	int USER_DATA_ENTRY = 2;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DATA_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DATA_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>User Data Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DATA_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.xtext.builder.builderState.impl.ReferenceDescriptionImpl <em>Reference Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.xtext.builder.builderState.impl.ReferenceDescriptionImpl
	 * @see org.eclipse.xtext.builder.builderState.impl.BuilderStatePackageImpl#getReferenceDescription()
	 * @generated
	 */
	int REFERENCE_DESCRIPTION = 3;

	/**
	 * The feature id for the '<em><b>Source EObject Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_DESCRIPTION__SOURCE_EOBJECT_URI = 0;

	/**
	 * The feature id for the '<em><b>Target EObject Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_DESCRIPTION__TARGET_EOBJECT_URI = 1;

	/**
	 * The feature id for the '<em><b>Index In List</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_DESCRIPTION__INDEX_IN_LIST = 2;

	/**
	 * The feature id for the '<em><b>External Form Of EReference</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_DESCRIPTION__EXTERNAL_FORM_OF_EREFERENCE = 3;

	/**
	 * The feature id for the '<em><b>Container EObject URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_DESCRIPTION__CONTAINER_EOBJECT_URI = 4;

	/**
	 * The number of structural features of the '<em>Reference Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_DESCRIPTION_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '<em>EURI</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.URI
	 * @see org.eclipse.xtext.builder.builderState.impl.BuilderStatePackageImpl#getEURI()
	 * @generated
	 */
	int EURI = 4;


	/**
	 * The meta object id for the '<em>EString Array</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.xtext.builder.builderState.impl.BuilderStatePackageImpl#getEStringArray()
	 * @generated
	 */
	int ESTRING_ARRAY = 5;


	/**
	 * The meta object id for the '<em>Qualified Name</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.xtext.naming.QualifiedName
	 * @see org.eclipse.xtext.builder.builderState.impl.BuilderStatePackageImpl#getQualifiedName()
	 * @generated
	 */
	int QUALIFIED_NAME = 6;


	/**
	 * Returns the meta object for class '{@link org.eclipse.xtext.resource.IResourceDescription <em>Resource Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Description</em>'.
	 * @see org.eclipse.xtext.resource.IResourceDescription
	 * @model instanceClass="org.eclipse.xtext.resource.IResourceDescription"
	 * @generated
	 */
	EClass getResourceDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.xtext.resource.IResourceDescription#getURI <em>URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>URI</em>'.
	 * @see org.eclipse.xtext.resource.IResourceDescription#getURI()
	 * @see #getResourceDescription()
	 * @generated
	 */
	EAttribute getResourceDescription_URI();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.xtext.resource.IResourceDescription#getExportedObjects <em>Exported Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exported Objects</em>'.
	 * @see org.eclipse.xtext.resource.IResourceDescription#getExportedObjects()
	 * @see #getResourceDescription()
	 * @generated
	 */
	EReference getResourceDescription_ExportedObjects();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.xtext.resource.IResourceDescription#getImportedNames <em>Imported Names</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Imported Names</em>'.
	 * @see org.eclipse.xtext.resource.IResourceDescription#getImportedNames()
	 * @see #getResourceDescription()
	 * @generated
	 */
	EAttribute getResourceDescription_ImportedNames();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.xtext.resource.IResourceDescription#getReferenceDescriptions <em>Reference Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Reference Descriptions</em>'.
	 * @see org.eclipse.xtext.resource.IResourceDescription#getReferenceDescriptions()
	 * @see #getResourceDescription()
	 * @generated
	 */
	EReference getResourceDescription_ReferenceDescriptions();

	/**
	 * Returns the meta object for class '{@link org.eclipse.xtext.resource.IEObjectDescription <em>EObject Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EObject Description</em>'.
	 * @see org.eclipse.xtext.resource.IEObjectDescription
	 * @model instanceClass="org.eclipse.xtext.resource.IEObjectDescription"
	 * @generated
	 */
	EClass getEObjectDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.xtext.resource.IEObjectDescription#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.xtext.resource.IEObjectDescription#getName()
	 * @see #getEObjectDescription()
	 * @generated
	 */
	EAttribute getEObjectDescription_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.xtext.resource.IEObjectDescription#getFragment <em>Fragment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fragment</em>'.
	 * @see org.eclipse.xtext.resource.IEObjectDescription#getFragment()
	 * @see #getEObjectDescription()
	 * @generated
	 */
	EAttribute getEObjectDescription_Fragment();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.xtext.resource.IEObjectDescription#getEClass <em>EClass</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EClass</em>'.
	 * @see org.eclipse.xtext.resource.IEObjectDescription#getEClass()
	 * @see #getEObjectDescription()
	 * @generated
	 */
	EReference getEObjectDescription_EClass();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.xtext.resource.IEObjectDescription#getResourceDescriptor <em>Resource Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Resource Descriptor</em>'.
	 * @see org.eclipse.xtext.resource.IEObjectDescription#getResourceDescriptor()
	 * @see #getEObjectDescription()
	 * @generated
	 */
	EReference getEObjectDescription_ResourceDescriptor();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.xtext.resource.IEObjectDescription#getUserData <em>User Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>User Data</em>'.
	 * @see org.eclipse.xtext.resource.IEObjectDescription#getUserData()
	 * @see #getEObjectDescription()
	 * @generated
	 */
	EReference getEObjectDescription_UserData();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>User Data Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Data Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueDataType="org.eclipse.emf.ecore.EString"
	 * @generated
	 */
	EClass getUserDataEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getUserDataEntry()
	 * @generated
	 */
	EAttribute getUserDataEntry_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getUserDataEntry()
	 * @generated
	 */
	EAttribute getUserDataEntry_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.xtext.resource.IReferenceDescription <em>Reference Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference Description</em>'.
	 * @see org.eclipse.xtext.resource.IReferenceDescription
	 * @model instanceClass="org.eclipse.xtext.resource.IReferenceDescription"
	 * @generated
	 */
	EClass getReferenceDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.xtext.resource.IReferenceDescription#getSourceEObjectUri <em>Source EObject Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source EObject Uri</em>'.
	 * @see org.eclipse.xtext.resource.IReferenceDescription#getSourceEObjectUri()
	 * @see #getReferenceDescription()
	 * @generated
	 */
	EAttribute getReferenceDescription_SourceEObjectUri();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.xtext.resource.IReferenceDescription#getTargetEObjectUri <em>Target EObject Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target EObject Uri</em>'.
	 * @see org.eclipse.xtext.resource.IReferenceDescription#getTargetEObjectUri()
	 * @see #getReferenceDescription()
	 * @generated
	 */
	EAttribute getReferenceDescription_TargetEObjectUri();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.xtext.resource.IReferenceDescription#getIndexInList <em>Index In List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Index In List</em>'.
	 * @see org.eclipse.xtext.resource.IReferenceDescription#getIndexInList()
	 * @see #getReferenceDescription()
	 * @generated
	 */
	EAttribute getReferenceDescription_IndexInList();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.xtext.resource.IReferenceDescription#getExternalFormOfEReference <em>External Form Of EReference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>External Form Of EReference</em>'.
	 * @see org.eclipse.xtext.resource.IReferenceDescription#getExternalFormOfEReference()
	 * @see #getReferenceDescription()
	 * @generated
	 */
	EAttribute getReferenceDescription_ExternalFormOfEReference();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.xtext.resource.IReferenceDescription#getContainerEObjectURI <em>Container EObject URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Container EObject URI</em>'.
	 * @see org.eclipse.xtext.resource.IReferenceDescription#getContainerEObjectURI()
	 * @see #getReferenceDescription()
	 * @generated
	 */
	EAttribute getReferenceDescription_ContainerEObjectURI();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.emf.common.util.URI <em>EURI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>EURI</em>'.
	 * @see org.eclipse.emf.common.util.URI
	 * @model instanceClass="org.eclipse.emf.common.util.URI"
	 * @generated
	 */
	EDataType getEURI();

	/**
	 * Returns the meta object for data type '<em>EString Array</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>EString Array</em>'.
	 * @model instanceClass="java.lang.Object[]"
	 * @generated
	 */
	EDataType getEStringArray();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.xtext.naming.QualifiedName <em>Qualified Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Qualified Name</em>'.
	 * @see org.eclipse.xtext.naming.QualifiedName
	 * @model instanceClass="org.eclipse.xtext.naming.QualifiedName"
	 * @generated
	 */
	EDataType getQualifiedName();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	BuilderStateFactory getBuilderStateFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.xtext.builder.builderState.impl.ResourceDescriptionImpl <em>Resource Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.xtext.builder.builderState.impl.ResourceDescriptionImpl
		 * @see org.eclipse.xtext.builder.builderState.impl.BuilderStatePackageImpl#getResourceDescription()
		 * @generated
		 */
		EClass RESOURCE_DESCRIPTION = eINSTANCE.getResourceDescription();

		/**
		 * The meta object literal for the '<em><b>URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_DESCRIPTION__URI = eINSTANCE.getResourceDescription_URI();

		/**
		 * The meta object literal for the '<em><b>Exported Objects</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_DESCRIPTION__EXPORTED_OBJECTS = eINSTANCE.getResourceDescription_ExportedObjects();

		/**
		 * The meta object literal for the '<em><b>Imported Names</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_DESCRIPTION__IMPORTED_NAMES = eINSTANCE.getResourceDescription_ImportedNames();

		/**
		 * The meta object literal for the '<em><b>Reference Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_DESCRIPTION__REFERENCE_DESCRIPTIONS = eINSTANCE.getResourceDescription_ReferenceDescriptions();

		/**
		 * The meta object literal for the '{@link org.eclipse.xtext.builder.builderState.impl.EObjectDescriptionImpl <em>EObject Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.xtext.builder.builderState.impl.EObjectDescriptionImpl
		 * @see org.eclipse.xtext.builder.builderState.impl.BuilderStatePackageImpl#getEObjectDescription()
		 * @generated
		 */
		EClass EOBJECT_DESCRIPTION = eINSTANCE.getEObjectDescription();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EOBJECT_DESCRIPTION__NAME = eINSTANCE.getEObjectDescription_Name();

		/**
		 * The meta object literal for the '<em><b>Fragment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EOBJECT_DESCRIPTION__FRAGMENT = eINSTANCE.getEObjectDescription_Fragment();

		/**
		 * The meta object literal for the '<em><b>EClass</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EOBJECT_DESCRIPTION__ECLASS = eINSTANCE.getEObjectDescription_EClass();

		/**
		 * The meta object literal for the '<em><b>Resource Descriptor</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EOBJECT_DESCRIPTION__RESOURCE_DESCRIPTOR = eINSTANCE.getEObjectDescription_ResourceDescriptor();

		/**
		 * The meta object literal for the '<em><b>User Data</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EOBJECT_DESCRIPTION__USER_DATA = eINSTANCE.getEObjectDescription_UserData();

		/**
		 * The meta object literal for the '{@link org.eclipse.xtext.builder.builderState.impl.UserDataEntryImpl <em>User Data Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.xtext.builder.builderState.impl.UserDataEntryImpl
		 * @see org.eclipse.xtext.builder.builderState.impl.BuilderStatePackageImpl#getUserDataEntry()
		 * @generated
		 */
		EClass USER_DATA_ENTRY = eINSTANCE.getUserDataEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_DATA_ENTRY__KEY = eINSTANCE.getUserDataEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_DATA_ENTRY__VALUE = eINSTANCE.getUserDataEntry_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.xtext.builder.builderState.impl.ReferenceDescriptionImpl <em>Reference Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.xtext.builder.builderState.impl.ReferenceDescriptionImpl
		 * @see org.eclipse.xtext.builder.builderState.impl.BuilderStatePackageImpl#getReferenceDescription()
		 * @generated
		 */
		EClass REFERENCE_DESCRIPTION = eINSTANCE.getReferenceDescription();

		/**
		 * The meta object literal for the '<em><b>Source EObject Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_DESCRIPTION__SOURCE_EOBJECT_URI = eINSTANCE.getReferenceDescription_SourceEObjectUri();

		/**
		 * The meta object literal for the '<em><b>Target EObject Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_DESCRIPTION__TARGET_EOBJECT_URI = eINSTANCE.getReferenceDescription_TargetEObjectUri();

		/**
		 * The meta object literal for the '<em><b>Index In List</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_DESCRIPTION__INDEX_IN_LIST = eINSTANCE.getReferenceDescription_IndexInList();

		/**
		 * The meta object literal for the '<em><b>External Form Of EReference</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_DESCRIPTION__EXTERNAL_FORM_OF_EREFERENCE = eINSTANCE.getReferenceDescription_ExternalFormOfEReference();

		/**
		 * The meta object literal for the '<em><b>Container EObject URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCE_DESCRIPTION__CONTAINER_EOBJECT_URI = eINSTANCE.getReferenceDescription_ContainerEObjectURI();

		/**
		 * The meta object literal for the '<em>EURI</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.common.util.URI
		 * @see org.eclipse.xtext.builder.builderState.impl.BuilderStatePackageImpl#getEURI()
		 * @generated
		 */
		EDataType EURI = eINSTANCE.getEURI();

		/**
		 * The meta object literal for the '<em>EString Array</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.xtext.builder.builderState.impl.BuilderStatePackageImpl#getEStringArray()
		 * @generated
		 */
		EDataType ESTRING_ARRAY = eINSTANCE.getEStringArray();

		/**
		 * The meta object literal for the '<em>Qualified Name</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.xtext.naming.QualifiedName
		 * @see org.eclipse.xtext.builder.builderState.impl.BuilderStatePackageImpl#getQualifiedName()
		 * @generated
		 */
		EDataType QUALIFIED_NAME = eINSTANCE.getQualifiedName();

	}

} //BuilderStatePackage
