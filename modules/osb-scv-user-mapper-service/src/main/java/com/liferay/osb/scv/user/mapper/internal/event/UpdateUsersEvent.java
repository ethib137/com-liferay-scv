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
import com.liferay.osb.scv.user.mapper.internal.messaging.constants.UserMapperDestinationNames;
import com.liferay.osb.scv.user.mapper.model.UserMappingRule;
import com.liferay.osb.scv.user.mapper.sample.DataSource;
import com.liferay.osb.scv.user.mapper.sample.DataSourceUtil;
import com.liferay.osb.scv.user.mapper.service.UserMappingRuleLocalService;
import com.liferay.osb.scv.user.profile.util.UserProfileUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class UpdateUsersEvent extends BaseEvent {

	public UpdateUsersEvent(
		long dataSourceId, List<UserMappingRule> userMappingRules) {

		_dataSourceId = dataSourceId;
		_userMappingRules = userMappingRules;
	}

	@Override
	public void run() {
		try {
			if (_userMappingRules.isEmpty()) {
				return;
			}

			Map<String, Object> parameters = new HashMap<>();

			Map<String, List<String>> fields = new HashMap<>();

			parameters.put("method", EventConstants.GET_USERS);

			for (UserMappingRule userMappingRule : _userMappingRules) {
				addField(fields, userMappingRule.getSourceField());
			}

			DataSource dataSource = DataSourceUtil.getDataSource(_dataSourceId);

			addField(fields, dataSource.getIdField());

			for (String requiredField : dataSource.getRequiredFields()) {
				addField(fields, requiredField);
			}

			parameters.put("fields", fields);
			parameters.put("dataSourceId", _dataSourceId);

			run(parameters);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void addField(Map<String, List<String>> fields, String field) {
		int index = field.indexOf("#");

		String tableName = field.substring(0, index);
		String fieldName = field.substring(index + 1);

		List<String> tableFields = fields.get(tableName);

		if (tableFields == null) {
			tableFields = new ArrayList<>();
		}

		if (!tableFields.contains(fieldName)) {
			tableFields.add(fieldName);
		}

		fields.put(tableName, tableFields);
	}

	public void handleResponse(Message message) throws Exception {
		JSONArray sourceJSONArray = JSONFactoryUtil.createJSONArray(
			String.valueOf(message.getPayload()));

		DataSource dataSource = DataSourceUtil.getDataSource(_dataSourceId);

		for (int i = 0; i < sourceJSONArray.length(); i++) {
			JSONObject sourceJSONObject = sourceJSONArray.getJSONObject(i);

			JSONObject destinationJSONObject =
				JSONFactoryUtil.createJSONObject();

			for (UserMappingRule userMappingRule : _userMappingRules) {
				destinationJSONObject.put(
					userMappingRule.getDestinationField(),
					sourceJSONObject.get(userMappingRule.getSourceField()));
			}

			Map<String, String> idFields = new HashMap<>();

			idFields.put(
				"idField", _dataSourceId + "#" +
					sourceJSONObject.get(dataSource.getIdField()));

			String searchTerm = dataSource.getRequiredFields().get(0);

			idFields.put(
				"searchTerm", sourceJSONObject.getString(searchTerm));

			UserProfileUtil.addSCVUserProfile(idFields, destinationJSONObject);
		}
	}

	private final long _dataSourceId;
	private final List<UserMappingRule> _userMappingRules;

}