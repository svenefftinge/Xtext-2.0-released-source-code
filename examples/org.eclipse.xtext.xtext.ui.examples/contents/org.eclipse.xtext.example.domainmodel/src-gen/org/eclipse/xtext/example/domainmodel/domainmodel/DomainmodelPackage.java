/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.xtext.example.domainmodel.domainmodel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see org.eclipse.xtext.example.domainmodel.domainmodel.DomainmodelFactory
 * @model kind="package"
 * @generated
 */
public interface DomainmodelPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "domainmodel";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.xtext.org/example/Domainmodel";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "domainmodel";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  DomainmodelPackage eINSTANCE = org.eclipse.xtext.example.domainmodel.domainmodel.impl.DomainmodelPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.xtext.example.domainmodel.domainmodel.impl.DomainModelImpl <em>Domain Model</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.DomainModelImpl
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.DomainmodelPackageImpl#getDomainModel()
   * @generated
   */
  int DOMAIN_MODEL = 0;

  /**
   * The feature id for the '<em><b>Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOMAIN_MODEL__ELEMENTS = 0;

  /**
   * The number of structural features of the '<em>Domain Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOMAIN_MODEL_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.xtext.example.domainmodel.domainmodel.impl.AbstractElementImpl <em>Abstract Element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.AbstractElementImpl
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.DomainmodelPackageImpl#getAbstractElement()
   * @generated
   */
  int ABSTRACT_ELEMENT = 1;

  /**
   * The number of structural features of the '<em>Abstract Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ABSTRACT_ELEMENT_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.eclipse.xtext.example.domainmodel.domainmodel.impl.ImportImpl <em>Import</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.ImportImpl
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.DomainmodelPackageImpl#getImport()
   * @generated
   */
  int IMPORT = 2;

  /**
   * The feature id for the '<em><b>Imported Namespace</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT__IMPORTED_NAMESPACE = ABSTRACT_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Import</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT_FEATURE_COUNT = ABSTRACT_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.xtext.example.domainmodel.domainmodel.impl.PackageDeclarationImpl <em>Package Declaration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.PackageDeclarationImpl
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.DomainmodelPackageImpl#getPackageDeclaration()
   * @generated
   */
  int PACKAGE_DECLARATION = 3;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PACKAGE_DECLARATION__NAME = ABSTRACT_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PACKAGE_DECLARATION__ELEMENTS = ABSTRACT_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Package Declaration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PACKAGE_DECLARATION_FEATURE_COUNT = ABSTRACT_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.xtext.example.domainmodel.domainmodel.impl.EntityImpl <em>Entity</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.EntityImpl
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.DomainmodelPackageImpl#getEntity()
   * @generated
   */
  int ENTITY = 4;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY__NAME = ABSTRACT_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Super Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY__SUPER_TYPE = ABSTRACT_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY__FEATURES = ABSTRACT_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Entity</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_FEATURE_COUNT = ABSTRACT_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.xtext.example.domainmodel.domainmodel.impl.FeatureImpl <em>Feature</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.FeatureImpl
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.DomainmodelPackageImpl#getFeature()
   * @generated
   */
  int FEATURE = 5;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE__NAME = 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE__TYPE = 1;

  /**
   * The number of structural features of the '<em>Feature</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.xtext.example.domainmodel.domainmodel.impl.PropertyImpl <em>Property</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.PropertyImpl
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.DomainmodelPackageImpl#getProperty()
   * @generated
   */
  int PROPERTY = 6;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY__NAME = FEATURE__NAME;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY__TYPE = FEATURE__TYPE;

  /**
   * The number of structural features of the '<em>Property</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_FEATURE_COUNT = FEATURE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.eclipse.xtext.example.domainmodel.domainmodel.impl.OperationImpl <em>Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.OperationImpl
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.DomainmodelPackageImpl#getOperation()
   * @generated
   */
  int OPERATION = 7;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION__NAME = FEATURE__NAME;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION__TYPE = FEATURE__TYPE;

  /**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION__VISIBILITY = FEATURE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Params</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION__PARAMS = FEATURE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION__BODY = FEATURE_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Operation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_FEATURE_COUNT = FEATURE_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.xtext.example.domainmodel.domainmodel.Visibility <em>Visibility</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.Visibility
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.DomainmodelPackageImpl#getVisibility()
   * @generated
   */
  int VISIBILITY = 8;


  /**
   * Returns the meta object for class '{@link org.eclipse.xtext.example.domainmodel.domainmodel.DomainModel <em>Domain Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Domain Model</em>'.
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.DomainModel
   * @generated
   */
  EClass getDomainModel();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.xtext.example.domainmodel.domainmodel.DomainModel#getElements <em>Elements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Elements</em>'.
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.DomainModel#getElements()
   * @see #getDomainModel()
   * @generated
   */
  EReference getDomainModel_Elements();

  /**
   * Returns the meta object for class '{@link org.eclipse.xtext.example.domainmodel.domainmodel.AbstractElement <em>Abstract Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Element</em>'.
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.AbstractElement
   * @generated
   */
  EClass getAbstractElement();

  /**
   * Returns the meta object for class '{@link org.eclipse.xtext.example.domainmodel.domainmodel.Import <em>Import</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Import</em>'.
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.Import
   * @generated
   */
  EClass getImport();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.xtext.example.domainmodel.domainmodel.Import#getImportedNamespace <em>Imported Namespace</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Imported Namespace</em>'.
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.Import#getImportedNamespace()
   * @see #getImport()
   * @generated
   */
  EAttribute getImport_ImportedNamespace();

  /**
   * Returns the meta object for class '{@link org.eclipse.xtext.example.domainmodel.domainmodel.PackageDeclaration <em>Package Declaration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Package Declaration</em>'.
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.PackageDeclaration
   * @generated
   */
  EClass getPackageDeclaration();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.xtext.example.domainmodel.domainmodel.PackageDeclaration#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.PackageDeclaration#getName()
   * @see #getPackageDeclaration()
   * @generated
   */
  EAttribute getPackageDeclaration_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.xtext.example.domainmodel.domainmodel.PackageDeclaration#getElements <em>Elements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Elements</em>'.
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.PackageDeclaration#getElements()
   * @see #getPackageDeclaration()
   * @generated
   */
  EReference getPackageDeclaration_Elements();

  /**
   * Returns the meta object for class '{@link org.eclipse.xtext.example.domainmodel.domainmodel.Entity <em>Entity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity</em>'.
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.Entity
   * @generated
   */
  EClass getEntity();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.xtext.example.domainmodel.domainmodel.Entity#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.Entity#getName()
   * @see #getEntity()
   * @generated
   */
  EAttribute getEntity_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.xtext.example.domainmodel.domainmodel.Entity#getSuperType <em>Super Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Super Type</em>'.
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.Entity#getSuperType()
   * @see #getEntity()
   * @generated
   */
  EReference getEntity_SuperType();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.xtext.example.domainmodel.domainmodel.Entity#getFeatures <em>Features</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Features</em>'.
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.Entity#getFeatures()
   * @see #getEntity()
   * @generated
   */
  EReference getEntity_Features();

  /**
   * Returns the meta object for class '{@link org.eclipse.xtext.example.domainmodel.domainmodel.Feature <em>Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Feature</em>'.
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.Feature
   * @generated
   */
  EClass getFeature();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.xtext.example.domainmodel.domainmodel.Feature#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.Feature#getName()
   * @see #getFeature()
   * @generated
   */
  EAttribute getFeature_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.xtext.example.domainmodel.domainmodel.Feature#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.Feature#getType()
   * @see #getFeature()
   * @generated
   */
  EReference getFeature_Type();

  /**
   * Returns the meta object for class '{@link org.eclipse.xtext.example.domainmodel.domainmodel.Property <em>Property</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Property</em>'.
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.Property
   * @generated
   */
  EClass getProperty();

  /**
   * Returns the meta object for class '{@link org.eclipse.xtext.example.domainmodel.domainmodel.Operation <em>Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation</em>'.
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.Operation
   * @generated
   */
  EClass getOperation();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.xtext.example.domainmodel.domainmodel.Operation#getVisibility <em>Visibility</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Visibility</em>'.
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.Operation#getVisibility()
   * @see #getOperation()
   * @generated
   */
  EAttribute getOperation_Visibility();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.xtext.example.domainmodel.domainmodel.Operation#getParams <em>Params</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Params</em>'.
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.Operation#getParams()
   * @see #getOperation()
   * @generated
   */
  EReference getOperation_Params();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.xtext.example.domainmodel.domainmodel.Operation#getBody <em>Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Body</em>'.
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.Operation#getBody()
   * @see #getOperation()
   * @generated
   */
  EReference getOperation_Body();

  /**
   * Returns the meta object for enum '{@link org.eclipse.xtext.example.domainmodel.domainmodel.Visibility <em>Visibility</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Visibility</em>'.
   * @see org.eclipse.xtext.example.domainmodel.domainmodel.Visibility
   * @generated
   */
  EEnum getVisibility();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  DomainmodelFactory getDomainmodelFactory();

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
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.eclipse.xtext.example.domainmodel.domainmodel.impl.DomainModelImpl <em>Domain Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.DomainModelImpl
     * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.DomainmodelPackageImpl#getDomainModel()
     * @generated
     */
    EClass DOMAIN_MODEL = eINSTANCE.getDomainModel();

    /**
     * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DOMAIN_MODEL__ELEMENTS = eINSTANCE.getDomainModel_Elements();

    /**
     * The meta object literal for the '{@link org.eclipse.xtext.example.domainmodel.domainmodel.impl.AbstractElementImpl <em>Abstract Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.AbstractElementImpl
     * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.DomainmodelPackageImpl#getAbstractElement()
     * @generated
     */
    EClass ABSTRACT_ELEMENT = eINSTANCE.getAbstractElement();

    /**
     * The meta object literal for the '{@link org.eclipse.xtext.example.domainmodel.domainmodel.impl.ImportImpl <em>Import</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.ImportImpl
     * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.DomainmodelPackageImpl#getImport()
     * @generated
     */
    EClass IMPORT = eINSTANCE.getImport();

    /**
     * The meta object literal for the '<em><b>Imported Namespace</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IMPORT__IMPORTED_NAMESPACE = eINSTANCE.getImport_ImportedNamespace();

    /**
     * The meta object literal for the '{@link org.eclipse.xtext.example.domainmodel.domainmodel.impl.PackageDeclarationImpl <em>Package Declaration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.PackageDeclarationImpl
     * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.DomainmodelPackageImpl#getPackageDeclaration()
     * @generated
     */
    EClass PACKAGE_DECLARATION = eINSTANCE.getPackageDeclaration();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PACKAGE_DECLARATION__NAME = eINSTANCE.getPackageDeclaration_Name();

    /**
     * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PACKAGE_DECLARATION__ELEMENTS = eINSTANCE.getPackageDeclaration_Elements();

    /**
     * The meta object literal for the '{@link org.eclipse.xtext.example.domainmodel.domainmodel.impl.EntityImpl <em>Entity</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.EntityImpl
     * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.DomainmodelPackageImpl#getEntity()
     * @generated
     */
    EClass ENTITY = eINSTANCE.getEntity();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ENTITY__NAME = eINSTANCE.getEntity_Name();

    /**
     * The meta object literal for the '<em><b>Super Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENTITY__SUPER_TYPE = eINSTANCE.getEntity_SuperType();

    /**
     * The meta object literal for the '<em><b>Features</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENTITY__FEATURES = eINSTANCE.getEntity_Features();

    /**
     * The meta object literal for the '{@link org.eclipse.xtext.example.domainmodel.domainmodel.impl.FeatureImpl <em>Feature</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.FeatureImpl
     * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.DomainmodelPackageImpl#getFeature()
     * @generated
     */
    EClass FEATURE = eINSTANCE.getFeature();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FEATURE__NAME = eINSTANCE.getFeature_Name();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FEATURE__TYPE = eINSTANCE.getFeature_Type();

    /**
     * The meta object literal for the '{@link org.eclipse.xtext.example.domainmodel.domainmodel.impl.PropertyImpl <em>Property</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.PropertyImpl
     * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.DomainmodelPackageImpl#getProperty()
     * @generated
     */
    EClass PROPERTY = eINSTANCE.getProperty();

    /**
     * The meta object literal for the '{@link org.eclipse.xtext.example.domainmodel.domainmodel.impl.OperationImpl <em>Operation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.OperationImpl
     * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.DomainmodelPackageImpl#getOperation()
     * @generated
     */
    EClass OPERATION = eINSTANCE.getOperation();

    /**
     * The meta object literal for the '<em><b>Visibility</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute OPERATION__VISIBILITY = eINSTANCE.getOperation_Visibility();

    /**
     * The meta object literal for the '<em><b>Params</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OPERATION__PARAMS = eINSTANCE.getOperation_Params();

    /**
     * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OPERATION__BODY = eINSTANCE.getOperation_Body();

    /**
     * The meta object literal for the '{@link org.eclipse.xtext.example.domainmodel.domainmodel.Visibility <em>Visibility</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.xtext.example.domainmodel.domainmodel.Visibility
     * @see org.eclipse.xtext.example.domainmodel.domainmodel.impl.DomainmodelPackageImpl#getVisibility()
     * @generated
     */
    EEnum VISIBILITY = eINSTANCE.getVisibility();

  }

} //DomainmodelPackage
