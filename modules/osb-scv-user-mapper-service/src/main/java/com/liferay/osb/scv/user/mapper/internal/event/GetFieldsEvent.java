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

package com.liferay.osb.scv.user.mapper.internal.event;

import com.liferay.osb.scv.user.mapper.internal.event.constants.EventConstants;
import com.liferay.osb.scv.user.mapper.model.UserMappingRule;
import com.liferay.osb.scv.user.mapper.sample.DataSource;
import com.liferay.osb.scv.user.mapper.sample.DataSourceUtil;
import com.liferay.osb.scv.user.profile.util.UserProfileUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class GetFieldsEvent extends BaseEvent {

	public GetFieldsEvent(long dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	@Override
	public void run() {
		Map<String, Object> parameters = new HashMap<>();

		parameters.put("method", EventConstants.GET_FIELDS);
		parameters.put("dataSourceId", _dataSourceId);

		run(parameters);
	}

	public void handleResponse(Message message) throws Exception {
		Map<String, List<String>> map = new HashMap<>();

		JSONArray sourceJSONArray = JSONFactoryUtil.createJSONArray(
			String.valueOf(message.getPayload()));

		for (int i = 0; i < sourceJSONArray.length(); i++) {
			JSONObject sourceJSONObject = sourceJSONArray.getJSONObject(i);

			String className = sourceJSONObject.getString("className");

			Iterator iterator =
				sourceJSONObject.getJSONArray("columns").iterator();

			List<String> columns = new ArrayList<>();

			while (iterator.hasNext()) {
				columns.add(String.valueOf(iterator.next()));
			}

			map.put(className, columns);
		}

		DataSourceUtil.setAvailableFields(_dataSourceId, map);
	}

	private final long _dataSourceId;
}