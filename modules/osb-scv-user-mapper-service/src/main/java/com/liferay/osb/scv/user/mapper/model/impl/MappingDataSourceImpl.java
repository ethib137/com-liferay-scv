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

package com.liferay.osb.scv.user.mapper.model.impl;

import aQute.bnd.annotation.ProviderType;
import com.liferay.osb.scv.user.mapper.internal.event.Event;
import com.liferay.osb.scv.user.mapper.internal.event.GetFieldsEvent;
import com.liferay.osb.scv.user.mapper.internal.event.constants.MappingDataSourceConstants;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class MappingDataSourceImpl extends MappingDataSourceBaseImpl {

	public List<String> getTableNames() throws Exception {
		String availableFields = getAvailableFields();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			availableFields);

		Iterator<String> iterator = jsonObject.keys();

		List<String> list = new ArrayList<>();

		while (iterator.hasNext()) {
			list.add(iterator.next());
		}

		return list;
	}

	public Map<String, String> getAvailableFields(String tableName)
		throws Exception {

		if (Validator.isBlank(tableName)) {
			return Collections.emptyMap();
		}

		String availableFields = getAvailableFields();

		JSONObject fieldsJSONObject = JSONFactoryUtil.createJSONObject(
			availableFields);

		JSONObject modelFieldsJSONObject = fieldsJSONObject.getJSONObject(
			tableName);

		Iterator<String> iterator = modelFieldsJSONObject.keys();

		Map<String, String> map = new HashMap<>();

		while (iterator.hasNext()) {
			String key = iterator.next();

			map.put(key, modelFieldsJSONObject.getString(key));
		}

		return map;
	}

	public MappingDataSourceImpl() {
	}

}