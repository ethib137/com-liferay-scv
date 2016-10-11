/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.scv.connector.internal.jsonws;

import com.liferay.expando.kernel.model.ExpandoColumnConstants;

import java.util.Date;

/**
 * @author Shinn Lok
 */
public class SCVExpandoColumnConstants extends ExpandoColumnConstants {

	public static final String BOOLEAN_ARRAY_LABEL =
		Boolean[].class.getSimpleName();

	public static final String BOOLEAN_LABEL = Boolean.class.getSimpleName();

	public static final String DATE_ARRAY_LABEL = Date[].class.getSimpleName();

	public static final String DATE_LABEL = Date.class.getSimpleName();

	public static final String DOUBLE_ARRAY_LABEL =
		Double[].class.getSimpleName();

	public static final String DOUBLE_LABEL = Double.class.getSimpleName();

	public static final String FLOAT_ARRAY_LABEL =
		Float[].class.getSimpleName();

	public static final String FLOAT_LABEL = Float.class.getSimpleName();

	public static final String INTEGER_ARRAY_LABEL =
		Integer[].class.getSimpleName();

	public static final String INTEGER_LABEL = Integer.class.getSimpleName();

	public static final String LONG_ARRAY_LABEL = long[].class.getSimpleName();

	public static final String LONG_LABEL = long.class.getSimpleName();

	public static final String NUMBER_ARRAY_LABEL =
		Number[].class.getSimpleName();

	public static final String NUMBER_LABEL = Number.class.getSimpleName();

	public static final String SHORT_ARRAY_LABEL =
		Short[].class.getSimpleName();

	public static final String SHORT_LABEL = Short.class.getSimpleName();

	public static final String STRING_ARRAY_LABEL =
		String[].class.getSimpleName();

	public static final String STRING_LABEL = String.class.getSimpleName();

	public static final String getSCVTypeLabel(int type) {
		if (type == BOOLEAN) {
			return BOOLEAN_LABEL;
		}
		else if (type == BOOLEAN_ARRAY) {
			return BOOLEAN_ARRAY_LABEL;
		}
		else if (type == DATE) {
			return DATE_LABEL;
		}
		else if (type == DATE_ARRAY) {
			return DATE_ARRAY_LABEL;
		}
		else if (type == DOUBLE) {
			return DOUBLE_LABEL;
		}
		else if (type == DOUBLE_ARRAY) {
			return DOUBLE_ARRAY_LABEL;
		}
		else if (type == FLOAT) {
			return FLOAT_LABEL;
		}
		else if (type == FLOAT_ARRAY) {
			return FLOAT_ARRAY_LABEL;
		}
		else if (type == INTEGER) {
			return INTEGER_LABEL;
		}
		else if (type == INTEGER_ARRAY) {
			return INTEGER_ARRAY_LABEL;
		}
		else if (type == LONG) {
			return LONG_LABEL;
		}
		else if (type == LONG_ARRAY) {
			return LONG_ARRAY_LABEL;
		}
		else if (type == NUMBER) {
			return NUMBER_LABEL;
		}
		else if (type == NUMBER_ARRAY) {
			return NUMBER_ARRAY_LABEL;
		}
		else if (type == SHORT) {
			return SHORT_LABEL;
		}
		else if (type == SHORT_ARRAY) {
			return SHORT_ARRAY_LABEL;
		}
		else if (type == STRING) {
			return STRING_LABEL;
		}
		else if (type == STRING_ARRAY) {
			return STRING_ARRAY_LABEL;
		}
		else if (type == STRING_ARRAY_LOCALIZED) {
			return STRING_ARRAY_LABEL;
		}
		else if (type == STRING_LOCALIZED) {
			return STRING_LABEL;
		}

		return STRING_LABEL;
	}

}