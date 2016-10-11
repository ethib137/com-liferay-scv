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
import com.liferay.osb.scv.user.mapper.internal.event.constants.MappingDataSourceConstants;
import com.liferay.osb.scv.user.mapper.model.MappingDataSource;
import com.liferay.osb.scv.user.mapper.model.UserMappingRule;
import com.liferay.osb.scv.user.mapper.sample.DataSource;
import com.liferay.osb.scv.user.mapper.sample.DataSourceUtil;
import com.liferay.osb.scv.user.mapper.service.MappingDataSourceLocalServiceUtil;
import com.liferay.osb.scv.user.profile.util.UserProfileUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.ArrayList;
import java.util.Arrays;
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
		long mappingDataSourceId, List<UserMappingRule> userMappingRules) {

		this(mappingDataSourceId, userMappingRules, false);
	}

	public UpdateUsersEvent(
		long mappingDataSourceId, List<UserMappingRule> userMappingRules,
		boolean override) {

		_mappingDataSourceId = mappingDataSourceId;
		_override = override;

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

	public void handleResponse(Message message) throws Exception {
		if (_userMappingRulesMap.isEmpty()) {
			return;
		}

		MappingDataSource mappingDataSource =
			MappingDataSourceLocalServiceUtil.fetchMappingDataSource(
				_mappingDataSourceId);

		JSONObject sourceJSONObject = JSONFactoryUtil.createJSONObject(
			String.valueOf(message.getPayload()));

		JSONObject destinationJSONObject = JSONFactoryUtil.createJSONObject();

		Iterator<String> keys = sourceJSONObject.keys();

		Map<String, List<String>> map = new HashMap<>();

		while (keys.hasNext()) {
			String key = keys.next();

			List<UserMappingRule> userMappingRules = _userMappingRulesMap.get(
				key);

			if (ListUtil.isNotNull(userMappingRules)) {
				Set<String> processedKeys = new HashSet<>();

				JSONArray sourceModelJSONArray = sourceJSONObject.getJSONArray(
					key);

				JSONArray destinationModelJSONArray =
					JSONFactoryUtil.createJSONArray();

				for (int i = 0; i < sourceModelJSONArray.length(); i++) {
					JSONObject sourceModelJSONObject =
						sourceModelJSONArray.getJSONObject(i);
					JSONObject destinationModelJSONObject =
						JSONFactoryUtil.createJSONObject();

					for (UserMappingRule userMappingRule : userMappingRules) {
						processedKeys.add(userMappingRule.getSourceField());

						String value = sourceModelJSONObject.getString(
							userMappingRule.getSourceField());

						if ((value != null) && !_override) {
							destinationModelJSONObject.put(
								userMappingRule.getDestinationField(), value);
						}

						if (userMappingRule.getSourceField().startsWith("email") || userMappingRule.getDestinationField().startsWith("email")) {
							map.put(userMappingRule.getModelName(), Arrays.asList(userMappingRule.getDestinationField()));
						}
					}

					Iterator<String> allKeys = sourceModelJSONObject.keys();

					if (mappingDataSource.getType() !=
							MappingDataSourceConstants.CUSTOM) {

						while (allKeys.hasNext()) {
							String curKey = allKeys.next();

							if (processedKeys.contains(curKey)) {
								continue;
							}

							destinationModelJSONObject.put(
								curKey, sourceModelJSONObject.getString(curKey));
						}
					}

					destinationModelJSONArray.put(destinationModelJSONObject);
				}

				destinationJSONObject.put(key, destinationModelJSONArray);
			}
		}

		if (destinationJSONObject.length() == 0) {
			return;
		}

		JSONArray sourceUserJSONArray = sourceJSONObject.getJSONArray("User");

		if ((mappingDataSource.getType() !=
				MappingDataSourceConstants.CUSTOM) &&
				!_userMappingRulesMap.containsKey("User") &&
				(sourceUserJSONArray != null)) {

			JSONArray destinationUserJSONArray =
				JSONFactoryUtil.createJSONArray();

			for (int i = 0; i < sourceUserJSONArray.length(); i++) {
				JSONObject sourceUserJSONObject =
						sourceUserJSONArray.getJSONObject(i);
				JSONObject destinationUserJSONObject =
						JSONFactoryUtil.createJSONObject();

				keys = sourceUserJSONObject.keys();

				while (keys.hasNext()) {
					String key = keys.next();

					destinationUserJSONObject.put(
						key, sourceUserJSONObject.getString(key));
				}

				destinationUserJSONArray.put(destinationUserJSONObject);
			}

			destinationJSONObject.put("User", destinationUserJSONArray);
		}

		UserProfileUtil.updateDataSourceEntries(
			_mappingDataSourceId, map, destinationJSONObject);
	}

	@Override
	public void run() {
		try {
			if (_userMappingRulesMap.isEmpty()) {
				return;
			}

			Map<String, Object> parameters = new HashMap<>();

			Map<String, List<String>> fieldsMap = new HashMap<>();

			parameters.put("method", EventConstants.GET_USERS);

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

			parameters.put("fields", fieldsMap);
			parameters.put("mappingDataSourceId", _mappingDataSourceId);

			run(parameters);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private final long _mappingDataSourceId;
	private final boolean _override;
	private final Map<String, List<UserMappingRule>> _userMappingRulesMap =
		new HashMap<>();

}