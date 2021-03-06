/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.xtext.common.types;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Jvm Type Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.xtext.common.types.TypesPackage#getJvmTypeReference()
 * @model abstract="true"
 * @generated
 */
public interface JvmTypeReference extends EObject
{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	JvmType getType();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * <p>The identifier of a JvmTypeReference is a canonical representation of the referenced type 
	 * including its type arguments.</p>
	 * <p>The types fully qualified name is used ('$' is the delimiter for inner types).</p>
	 * <p>Examples for reference identifiers are:</p>
	 * <ul>
	 * <li>java.lang.String for a reference to an object type</li>
	 * <li>java.util.Map$Entry<java.lang.Object,java.lang.Integer> for a parameterized type</li>
	 * <li>java.util.List<? extends java.lang.String>[] for a generic array type</li>
	 * </ul>
	 * <!-- end-model-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getIdentifier();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getSimpleName();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getQualifiedName();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	String getQualifiedName(char innerClassDelimiter);

} // JvmTypeReference
