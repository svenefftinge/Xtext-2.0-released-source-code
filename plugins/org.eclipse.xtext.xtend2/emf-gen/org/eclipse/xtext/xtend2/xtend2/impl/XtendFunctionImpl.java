/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.xtext.xtend2.xtend2.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.common.types.JvmTypeParameter;
import org.eclipse.xtext.common.types.JvmTypeParameterDeclarator;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.TypesPackage;

import org.eclipse.xtext.xbase.XExpression;

import org.eclipse.xtext.xtend2.xtend2.CreateExtensionInfo;
import org.eclipse.xtext.xtend2.xtend2.Xtend2Package;
import org.eclipse.xtext.xtend2.xtend2.XtendFunction;
import org.eclipse.xtext.xtend2.xtend2.XtendParameter;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Xtend Function</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xtext.xtend2.xtend2.impl.XtendFunctionImpl#getTypeParameters <em>Type Parameters</em>}</li>
 *   <li>{@link org.eclipse.xtext.xtend2.xtend2.impl.XtendFunctionImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.xtext.xtend2.xtend2.impl.XtendFunctionImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.eclipse.xtext.xtend2.xtend2.impl.XtendFunctionImpl#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link org.eclipse.xtext.xtend2.xtend2.impl.XtendFunctionImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.eclipse.xtext.xtend2.xtend2.impl.XtendFunctionImpl#isOverride <em>Override</em>}</li>
 *   <li>{@link org.eclipse.xtext.xtend2.xtend2.impl.XtendFunctionImpl#isDispatch <em>Dispatch</em>}</li>
 *   <li>{@link org.eclipse.xtext.xtend2.xtend2.impl.XtendFunctionImpl#getCreateExtensionInfo <em>Create Extension Info</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XtendFunctionImpl extends XtendMemberImplCustom implements XtendFunction
{
	/**
	 * The cached value of the '{@link #getTypeParameters() <em>Type Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<JvmTypeParameter> typeParameters;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getExpression() <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
	protected XExpression expression;

	/**
	 * The cached value of the '{@link #getReturnType() <em>Return Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReturnType()
	 * @generated
	 * @ordered
	 */
	protected JvmTypeReference returnType;

	/**
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<XtendParameter> parameters;

	/**
	 * The default value of the '{@link #isOverride() <em>Override</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOverride()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OVERRIDE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOverride() <em>Override</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOverride()
	 * @generated
	 * @ordered
	 */
	protected boolean override = OVERRIDE_EDEFAULT;

	/**
	 * The default value of the '{@link #isDispatch() <em>Dispatch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDispatch()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DISPATCH_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDispatch() <em>Dispatch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDispatch()
	 * @generated
	 * @ordered
	 */
	protected boolean dispatch = DISPATCH_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCreateExtensionInfo() <em>Create Extension Info</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreateExtensionInfo()
	 * @generated
	 * @ordered
	 */
	protected CreateExtensionInfo createExtensionInfo;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected XtendFunctionImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return Xtend2Package.Literals.XTEND_FUNCTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<JvmTypeParameter> getTypeParameters()
	{
		if (typeParameters == null)
		{
			typeParameters = new EObjectContainmentWithInverseEList.Resolving<JvmTypeParameter>(JvmTypeParameter.class, this, Xtend2Package.XTEND_FUNCTION__TYPE_PARAMETERS, TypesPackage.JVM_TYPE_PARAMETER__DECLARATOR);
		}
		return typeParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName)
	{
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xtend2Package.XTEND_FUNCTION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XExpression getExpression()
	{
		return expression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExpression(XExpression newExpression, NotificationChain msgs)
	{
		XExpression oldExpression = expression;
		expression = newExpression;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xtend2Package.XTEND_FUNCTION__EXPRESSION, oldExpression, newExpression);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpression(XExpression newExpression)
	{
		if (newExpression != expression)
		{
			NotificationChain msgs = null;
			if (expression != null)
				msgs = ((InternalEObject)expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xtend2Package.XTEND_FUNCTION__EXPRESSION, null, msgs);
			if (newExpression != null)
				msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xtend2Package.XTEND_FUNCTION__EXPRESSION, null, msgs);
			msgs = basicSetExpression(newExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xtend2Package.XTEND_FUNCTION__EXPRESSION, newExpression, newExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JvmTypeReference getReturnType()
	{
		return returnType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReturnType(JvmTypeReference newReturnType, NotificationChain msgs)
	{
		JvmTypeReference oldReturnType = returnType;
		returnType = newReturnType;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xtend2Package.XTEND_FUNCTION__RETURN_TYPE, oldReturnType, newReturnType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReturnType(JvmTypeReference newReturnType)
	{
		if (newReturnType != returnType)
		{
			NotificationChain msgs = null;
			if (returnType != null)
				msgs = ((InternalEObject)returnType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xtend2Package.XTEND_FUNCTION__RETURN_TYPE, null, msgs);
			if (newReturnType != null)
				msgs = ((InternalEObject)newReturnType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xtend2Package.XTEND_FUNCTION__RETURN_TYPE, null, msgs);
			msgs = basicSetReturnType(newReturnType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xtend2Package.XTEND_FUNCTION__RETURN_TYPE, newReturnType, newReturnType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<XtendParameter> getParameters()
	{
		if (parameters == null)
		{
			parameters = new EObjectContainmentEList<XtendParameter>(XtendParameter.class, this, Xtend2Package.XTEND_FUNCTION__PARAMETERS);
		}
		return parameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOverride()
	{
		return override;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOverride(boolean newOverride)
	{
		boolean oldOverride = override;
		override = newOverride;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xtend2Package.XTEND_FUNCTION__OVERRIDE, oldOverride, override));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDispatch()
	{
		return dispatch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDispatch(boolean newDispatch)
	{
		boolean oldDispatch = dispatch;
		dispatch = newDispatch;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xtend2Package.XTEND_FUNCTION__DISPATCH, oldDispatch, dispatch));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CreateExtensionInfo getCreateExtensionInfo()
	{
		return createExtensionInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCreateExtensionInfo(CreateExtensionInfo newCreateExtensionInfo, NotificationChain msgs)
	{
		CreateExtensionInfo oldCreateExtensionInfo = createExtensionInfo;
		createExtensionInfo = newCreateExtensionInfo;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xtend2Package.XTEND_FUNCTION__CREATE_EXTENSION_INFO, oldCreateExtensionInfo, newCreateExtensionInfo);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreateExtensionInfo(CreateExtensionInfo newCreateExtensionInfo)
	{
		if (newCreateExtensionInfo != createExtensionInfo)
		{
			NotificationChain msgs = null;
			if (createExtensionInfo != null)
				msgs = ((InternalEObject)createExtensionInfo).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xtend2Package.XTEND_FUNCTION__CREATE_EXTENSION_INFO, null, msgs);
			if (newCreateExtensionInfo != null)
				msgs = ((InternalEObject)newCreateExtensionInfo).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xtend2Package.XTEND_FUNCTION__CREATE_EXTENSION_INFO, null, msgs);
			msgs = basicSetCreateExtensionInfo(newCreateExtensionInfo, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xtend2Package.XTEND_FUNCTION__CREATE_EXTENSION_INFO, newCreateExtensionInfo, newCreateExtensionInfo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIdentifier()
	{
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSimpleName()
	{
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getQualifiedName()
	{
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getQualifiedName(char innerClassDelimiter)
	{
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case Xtend2Package.XTEND_FUNCTION__TYPE_PARAMETERS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTypeParameters()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case Xtend2Package.XTEND_FUNCTION__TYPE_PARAMETERS:
				return ((InternalEList<?>)getTypeParameters()).basicRemove(otherEnd, msgs);
			case Xtend2Package.XTEND_FUNCTION__EXPRESSION:
				return basicSetExpression(null, msgs);
			case Xtend2Package.XTEND_FUNCTION__RETURN_TYPE:
				return basicSetReturnType(null, msgs);
			case Xtend2Package.XTEND_FUNCTION__PARAMETERS:
				return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
			case Xtend2Package.XTEND_FUNCTION__CREATE_EXTENSION_INFO:
				return basicSetCreateExtensionInfo(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch (featureID)
		{
			case Xtend2Package.XTEND_FUNCTION__TYPE_PARAMETERS:
				return getTypeParameters();
			case Xtend2Package.XTEND_FUNCTION__NAME:
				return getName();
			case Xtend2Package.XTEND_FUNCTION__EXPRESSION:
				return getExpression();
			case Xtend2Package.XTEND_FUNCTION__RETURN_TYPE:
				return getReturnType();
			case Xtend2Package.XTEND_FUNCTION__PARAMETERS:
				return getParameters();
			case Xtend2Package.XTEND_FUNCTION__OVERRIDE:
				return isOverride();
			case Xtend2Package.XTEND_FUNCTION__DISPATCH:
				return isDispatch();
			case Xtend2Package.XTEND_FUNCTION__CREATE_EXTENSION_INFO:
				return getCreateExtensionInfo();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID)
		{
			case Xtend2Package.XTEND_FUNCTION__TYPE_PARAMETERS:
				getTypeParameters().clear();
				getTypeParameters().addAll((Collection<? extends JvmTypeParameter>)newValue);
				return;
			case Xtend2Package.XTEND_FUNCTION__NAME:
				setName((String)newValue);
				return;
			case Xtend2Package.XTEND_FUNCTION__EXPRESSION:
				setExpression((XExpression)newValue);
				return;
			case Xtend2Package.XTEND_FUNCTION__RETURN_TYPE:
				setReturnType((JvmTypeReference)newValue);
				return;
			case Xtend2Package.XTEND_FUNCTION__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection<? extends XtendParameter>)newValue);
				return;
			case Xtend2Package.XTEND_FUNCTION__OVERRIDE:
				setOverride((Boolean)newValue);
				return;
			case Xtend2Package.XTEND_FUNCTION__DISPATCH:
				setDispatch((Boolean)newValue);
				return;
			case Xtend2Package.XTEND_FUNCTION__CREATE_EXTENSION_INFO:
				setCreateExtensionInfo((CreateExtensionInfo)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID)
	{
		switch (featureID)
		{
			case Xtend2Package.XTEND_FUNCTION__TYPE_PARAMETERS:
				getTypeParameters().clear();
				return;
			case Xtend2Package.XTEND_FUNCTION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case Xtend2Package.XTEND_FUNCTION__EXPRESSION:
				setExpression((XExpression)null);
				return;
			case Xtend2Package.XTEND_FUNCTION__RETURN_TYPE:
				setReturnType((JvmTypeReference)null);
				return;
			case Xtend2Package.XTEND_FUNCTION__PARAMETERS:
				getParameters().clear();
				return;
			case Xtend2Package.XTEND_FUNCTION__OVERRIDE:
				setOverride(OVERRIDE_EDEFAULT);
				return;
			case Xtend2Package.XTEND_FUNCTION__DISPATCH:
				setDispatch(DISPATCH_EDEFAULT);
				return;
			case Xtend2Package.XTEND_FUNCTION__CREATE_EXTENSION_INFO:
				setCreateExtensionInfo((CreateExtensionInfo)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID)
	{
		switch (featureID)
		{
			case Xtend2Package.XTEND_FUNCTION__TYPE_PARAMETERS:
				return typeParameters != null && !typeParameters.isEmpty();
			case Xtend2Package.XTEND_FUNCTION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case Xtend2Package.XTEND_FUNCTION__EXPRESSION:
				return expression != null;
			case Xtend2Package.XTEND_FUNCTION__RETURN_TYPE:
				return returnType != null;
			case Xtend2Package.XTEND_FUNCTION__PARAMETERS:
				return parameters != null && !parameters.isEmpty();
			case Xtend2Package.XTEND_FUNCTION__OVERRIDE:
				return override != OVERRIDE_EDEFAULT;
			case Xtend2Package.XTEND_FUNCTION__DISPATCH:
				return dispatch != DISPATCH_EDEFAULT;
			case Xtend2Package.XTEND_FUNCTION__CREATE_EXTENSION_INFO:
				return createExtensionInfo != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
	{
		if (baseClass == JvmTypeParameterDeclarator.class)
		{
			switch (derivedFeatureID)
			{
				case Xtend2Package.XTEND_FUNCTION__TYPE_PARAMETERS: return TypesPackage.JVM_TYPE_PARAMETER_DECLARATOR__TYPE_PARAMETERS;
				default: return -1;
			}
		}
		if (baseClass == JvmIdentifiableElement.class)
		{
			switch (derivedFeatureID)
			{
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
	{
		if (baseClass == JvmTypeParameterDeclarator.class)
		{
			switch (baseFeatureID)
			{
				case TypesPackage.JVM_TYPE_PARAMETER_DECLARATOR__TYPE_PARAMETERS: return Xtend2Package.XTEND_FUNCTION__TYPE_PARAMETERS;
				default: return -1;
			}
		}
		if (baseClass == JvmIdentifiableElement.class)
		{
			switch (baseFeatureID)
			{
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString()
	{
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", override: ");
		result.append(override);
		result.append(", dispatch: ");
		result.append(dispatch);
		result.append(')');
		return result.toString();
	}

} //XtendFunctionImpl
