/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.xtext.common.types;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Jvm Component Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.xtext.common.types.JvmComponentType#getArrayType <em>Array Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.xtext.common.types.TypesPackage#getJvmComponentType()
 * @model abstract="true"
 * @generated
 */
public interface JvmComponentType extends JvmType
{
	/**
	 * Returns the value of the '<em><b>Array Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Array Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Array Type</em>' containment reference.
	 * @see #setArrayType(JvmArrayType)
	 * @see org.eclipse.xtext.common.types.TypesPackage#getJvmComponentType_ArrayType()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	JvmArrayType getArrayType();

	/**
	 * Sets the value of the '{@link org.eclipse.xtext.common.types.JvmComponentType#getArrayType <em>Array Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Array Type</em>' containment reference.
	 * @see #getArrayType()
	 * @generated
	 */
	void setArrayType(JvmArrayType value);

} // JvmComponentType
