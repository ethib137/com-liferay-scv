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

package com.liferay.osb.scv.user.mapper.sample;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shinn Lok
 */
public class FieldSetUtil {

	public static FieldSet getFieldSet(Long fieldSetId) {
		List<FieldSet> fieldSets = getFieldSets();

		return fieldSets.get(fieldSetId.intValue());
	}

	public static List<FieldSet> getFieldSets() {
		List<FieldSet> dataSources = new ArrayList<>();

		for (final Object[] dataSourceString : _fieldSets) {
			FieldSet fieldSet = new FieldSet() {

				public long getFieldSetId() {
					return (long)dataSourceString[0];
				}

				public String getName() {
					return String.valueOf(dataSourceString[1]);
				}

			};

			dataSources.add(fieldSet);
		}

		return dataSources;
	}

	private static final Object[][] _fieldSets = new Object[][] {
		{0L, "default"}, {1L, "activity"}, {2L, "employment"}, {3L, "device"}
	};

}