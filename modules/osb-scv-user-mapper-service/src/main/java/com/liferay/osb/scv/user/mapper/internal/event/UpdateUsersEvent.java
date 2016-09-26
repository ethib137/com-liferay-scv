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
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Shinn Lok
 */
public class UpdateUsersEvent extends BaseEvent {

	public UpdateUsersEvent(
		long dataSourceId, List<UserMappingRule> userMappingRules) {

		_dataSourceId = dataSourceId;

		for (UserMappingRule userMappingRule : userMappingRules) {
			List<UserMappingRule> userMapperRulesList =
				_userMappingRulesMap.get(userMappingRule.getModelName());

			if (userMapperRulesList == null) {
				userMapperRulesList = new ArrayList<>();

				_userMappingRulesMap.put(
					userMappingRule.getModelName(), userMapperRulesList);
			}

			userMapperRulesList.add(userMappingRule);
		}
	}

	@Override
	public void run() {
		try {
			if (_userMappingRulesMap.isEmpty()) {
				return;
			}

			Map<String, Object> parameters = new HashMap<>();

			Map<String, List<String>> fieldsMap = new HashMap<>();

			parameters.put("method", EventConstants.GET_USERS + "2");

			for (Map.Entry<String, List<UserMappingRule>> entry :
					_userMappingRulesMap.entrySet()) {

				List<UserMappingRule> userMappingRules = entry.getValue();

				for (UserMappingRule userMappingRule : userMappingRules) {
					List<String> fields = fieldsMap.get(entry.getKey());

					if (fields == null) {
						fields = new ArrayList<>();

						fieldsMap.put(entry.getKey(), fields);
					}

					fields.add(userMappingRule.getSourceField());
				}
			}

			DataSource dataSource = DataSourceUtil.getDataSource(_dataSourceId);

			Map<String, List<String>> idFields = dataSource.getIdFields();

			for (Map.Entry<String, List<String>> entry : idFields.entrySet()) {
				String key = entry.getKey();

				List<String> fields = fieldsMap.get(key);

				if ((fields == null)) {
					if (!key.equals("User")) {
						continue;
					}

					fields = new ArrayList<>();

					fieldsMap.put(key, fields);
				}

				for (String field : entry.getValue()) {
					fields.add(field);
				}
			}

			Map<String, List<String>> requiredFields =
				dataSource.getRequiredFields();

			for (Map.Entry<String, List<String>> entry :
					requiredFields.entrySet()) {

				String key = entry.getKey();

				List<String> fields = fieldsMap.get(key);

				if ((fields != null)) {
					for (String field : entry.getValue()) {
						fields.add(field);
					}
				}
			}

			parameters.put("fields", fieldsMap);
			parameters.put("dataSourceId", _dataSourceId);

			run(parameters);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handleResponse(Message message) throws Exception {
		JSONObject sourceJSONObject = JSONFactoryUtil.createJSONObject(
			String.valueOf(message.getPayload()));

		JSONObject destinationJSONObject = JSONFactoryUtil.createJSONObject();

		DataSource dataSource = DataSourceUtil.getDataSource(_dataSourceId);

		Map<String, List<String>> idFields = dataSource.getIdFields();
		Map<String, List<String>> requiredFields =
			dataSource.getRequiredFields();

		if (!_userMappingRulesMap.containsKey("User")) {
			destinationJSONObject.put(
				"User", sourceJSONObject.getJSONArray("User"));
		}

		Iterator<String> keys = sourceJSONObject.keys();

		while (keys.hasNext()) {
			String key = keys.next();

			List<UserMappingRule> userMappingRules = _userMappingRulesMap.get(
				key);

			if (ListUtil.isNotNull(userMappingRules)) {
				Set<String> processKeys = new HashSet<>();

				JSONArray sourceModelJSONArray =
					sourceJSONObject.getJSONArray(key);

				JSONArray destinationModelJSONArray =
					JSONFactoryUtil.createJSONArray();

				for (int i = 0; i < sourceModelJSONArray.length(); i++) {
					JSONObject sourceModelJSONObject =
						sourceModelJSONArray.getJSONObject(i);
					JSONObject destinationModelJSONObject =
						JSONFactoryUtil.createJSONObject();

					for (UserMappingRule userMappingRule : userMappingRules) {
						processKeys.add(userMappingRule.getSourceField());

						destinationModelJSONObject.put(
							userMappingRule.getDestinationField(),
							sourceModelJSONObject.getString(
								userMappingRule.getSourceField()));

						List<String> idFieldsList = idFields.get(
							key);

						if (!ListUtil.isEmpty(idFieldsList)) {
							for (String idField : idFields.get(key)) {
								destinationModelJSONObject.put(
									idField,
									sourceModelJSONObject.getString(idField));
							}
						}

						List<String> requiredFieldsList = requiredFields.get(
							key);

						if (!ListUtil.isEmpty(requiredFieldsList)) {
							for (String requiredField :
									requiredFields.get(key)) {

								destinationModelJSONObject.put(
									requiredField,
									sourceModelJSONObject.getString(
										requiredField));
							}
						}
					}

					Iterator<String> allKeys = sourceModelJSONObject.keys();

					while (allKeys.hasNext()) {
						String curKey = allKeys.next();

						if (processKeys.contains(curKey)) {
							continue;
						}

						destinationModelJSONObject.put(
							curKey, sourceModelJSONObject.getString(curKey));
					}

					destinationModelJSONArray.put(destinationModelJSONObject);
				}

				destinationJSONObject.put(key, destinationModelJSONArray);
			}
		}

		UserProfileUtil.updateDataSourceEntries(
			_dataSourceId, dataSource.getRequiredFields(),
			destinationJSONObject);
	}

	private final long _dataSourceId;
	private final Map<String, List<UserMappingRule>> _userMappingRulesMap =
		new HashMap<>();

}