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

package com.liferay.osb.scv.user.mapper.internal.event.constants;

import com.liferay.osb.scv.user.mapper.sample.Frequency;
import com.liferay.osb.scv.user.mapper.sample.FrequencyUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class MappingDataSourceConstants {

	public static final long CUSTOM = 0;

	public static final long LIFERAY = 1;

	public static final long SALESFORCE = 2;

	public static final long HUBSPOT = 3;

	public static Map<Long, String> getMappingDataSourceTypes() {
		Map<Long, String> map = new HashMap<>();

		for (Object[] mappingDataSource : _mappingDataSources) {
			map.put((Long)mappingDataSource[0], (String)mappingDataSource[1]);
		}

		return map;
	}

	private static final Object[][] _mappingDataSources = {
		{MappingDataSourceConstants.CUSTOM, "custom"},
		{MappingDataSourceConstants.LIFERAY, "liferay"},
		{MappingDataSourceConstants.SALESFORCE, "salesforce"},
		{MappingDataSourceConstants.HUBSPOT, "hubspot"},
	};

}