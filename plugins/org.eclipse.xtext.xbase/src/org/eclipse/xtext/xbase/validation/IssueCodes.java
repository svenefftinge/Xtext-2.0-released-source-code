/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xbase.validation;

import java.util.List;

import com.google.common.collect.ImmutableList;

/**
 * @author Jan Koehnlein - Initial contribution and API
 */
public class IssueCodes {
	protected static final String ISSUE_CODE_PREFIX = "org.eclipse.xtext.xbase.validation.IssueCodes.";

	public static final String ABSTRACT_CLASS_INSTANTIATION = ISSUE_CODE_PREFIX + "abstract_class_instantiation";
	public static final String ANNOTATIONS_NO_VALUE_ATTRIBUTE = ISSUE_CODE_PREFIX + "no_attribute_value";
	public static final String ANNOTATIONS_MISSING_ATTRIBUTE_DEFINITION = ISSUE_CODE_PREFIX + "missing_attribute_definition";
	public static final String ASSIGNMENT_TARGET_IS_NOT_WRITEABLE = ISSUE_CODE_PREFIX + "assignment_target_is_not_writeable";
	public static final String ASSIGNMENT_TO_FINAL = ISSUE_CODE_PREFIX + "assignment_to_final";
	public static final String FEATURE_NOT_VISIBLE = ISSUE_CODE_PREFIX + "invisible_feature";
	public static final String FIELD_ACCESS_WITH_PARENTHESES = ISSUE_CODE_PREFIX + "field_access_with_parentheses";
	public static final String INCOMPATIBLE_RETURN_TYPE = ISSUE_CODE_PREFIX + "incomptible_return_type";
	public static final String INCOMPATIBLE_TYPES = ISSUE_CODE_PREFIX + "incompatible_types";
	public static final String INSTANCE_ACCESS_TO_STATIC_MEMBER = ISSUE_CODE_PREFIX + "instance_access_to_static_member";
	public static final String INVALID_ARGUMENT_TYPES = ISSUE_CODE_PREFIX + "invalid_argument_types";
	public static final String INVALID_CAST = ISSUE_CODE_PREFIX + "invalid_cast";
	public static final String INVALID_EARLY_EXIT = ISSUE_CODE_PREFIX + "invalid_early_exit";
	public static final String INVALID_INNER_EXPRESSION = ISSUE_CODE_PREFIX + "invalid_inner_expression";
	public static final String INVALID_INSTANCEOF = ISSUE_CODE_PREFIX + "invalid_instanceof";
	public static final String INVALID_NUMBER_OF_ARGUMENTS = ISSUE_CODE_PREFIX + "invalid_number_of_arguments";
	public static final String INVALID_NUMBER_OF_TYPE_ARGUMENTS = ISSUE_CODE_PREFIX + "invalid_number_of_type_arguments";
	public static final String INVALID_USE_OF_TYPE = ISSUE_CODE_PREFIX + "invalid_use_of_void";
	public static final String INVALID_USE_OF_WILDCARD = ISSUE_CODE_PREFIX + "invalid_use_of_wild_card";
	public static final String LOCAL_VAR_ACCESS_WITH_PARENTHESES = ISSUE_CODE_PREFIX + "local_var_access_with_parentheses";
	public static final String METHOD_ACCESS_WITHOUT_PARENTHESES = ISSUE_CODE_PREFIX + "method_access_without_parentheses";
	public static final String MISSING_INITIALIZATION = ISSUE_CODE_PREFIX + "missing_initialization";
	public static final String MISSING_TYPE = ISSUE_CODE_PREFIX + "missing_type";
	public static final String OBSOLETE_CAST = ISSUE_CODE_PREFIX + "obsolete_cast";
	public static final String OBSOLETE_INSTANCEOF = ISSUE_CODE_PREFIX + "obsolete_instanceof";
	public static final String STATIC_ACCESS_TO_INSTANCE_MEMBER = ISSUE_CODE_PREFIX	+ "static_access_to_instance_member";
	public static final String TOO_LITTLE_TYPE_INFORMATION = ISSUE_CODE_PREFIX + "too_little_type_information";
	public static final String TOO_MANY_PARAMS_IN_CLOSURE = ISSUE_CODE_PREFIX + "too_many_params_in_closure";
	public static final String UNREACHABLE_CODE = ISSUE_CODE_PREFIX + "unreachable_code";
	public static final String UNRESOLVABLE_PROXY = ISSUE_CODE_PREFIX + "unresolvable_proxy";
	public static final String VARIABLE_NAME_SHADOWING = ISSUE_CODE_PREFIX + "variable_name_shadowing";
	public static final String SIDE_EFFECT_FREE_EXPRESSION_IN_BLOCK = ISSUE_CODE_PREFIX + "side_effect_free_expression_in_block";
	
	// list is not necessarily complete
	// list is sorted from least important issue to worst issue
	private static final List<String> sortedIssueCodes = ImmutableList.of(
			FIELD_ACCESS_WITH_PARENTHESES,
			LOCAL_VAR_ACCESS_WITH_PARENTHESES,
			METHOD_ACCESS_WITHOUT_PARENTHESES,
			ASSIGNMENT_TARGET_IS_NOT_WRITEABLE,
			INSTANCE_ACCESS_TO_STATIC_MEMBER,
			INVALID_NUMBER_OF_TYPE_ARGUMENTS,
			INVALID_ARGUMENT_TYPES,
			INVALID_NUMBER_OF_ARGUMENTS
	);

	
	public static int compareIssueCodes(String left, String right) {
		if (left == right || left.equals(right))
			return 0;
		for(int i = 0; i < sortedIssueCodes.size(); i++) {
			String candidate = sortedIssueCodes.get(i);
			if (candidate.equals(left)) {
				for( int j = i + 1; j < sortedIssueCodes.size(); j++) {
					String followedBy = sortedIssueCodes.get(j);
					if (followedBy.equals(right)) {
						return -1;
					}
				}
				return 0;
			} else if (candidate.equals(right)) {
				for( int j = i + 1; j < sortedIssueCodes.size(); j++) {
					String followedBy = sortedIssueCodes.get(j);
					if (followedBy.equals(left)) {
						return 1;
					}
				}
				return 0;
			}
		}
		return 0;
	}
	
	
	private IssueCodes() {
	}

}
