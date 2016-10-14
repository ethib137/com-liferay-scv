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

package com.liferay.osb.scv.user.mapper.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.scv.user.mapper.internal.event.Event;
import com.liferay.osb.scv.user.mapper.internal.event.UpdateUsersEvent;
import com.liferay.osb.scv.user.mapper.internal.event.constants.MappingDataSourceConstants;
import com.liferay.osb.scv.user.mapper.model.MappingDataSource;
import com.liferay.osb.scv.user.mapper.model.UserMappingRule;
import com.liferay.osb.scv.user.mapper.sample.FrequencyUtil;
import com.liferay.osb.scv.user.mapper.service.base.UserMappingRuleLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.UniqueList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Shinn Lok
 */
@ProviderType
public class UserMappingRuleLocalServiceImpl
	extends UserMappingRuleLocalServiceBaseImpl {

	public JSONArray getUserMappingRuleDestinationFieldsCount(long companyId)
		throws Exception {

		Map<String, Integer> map = new HashMap<>();

		List<UserMappingRule> userMappingRules = getUserMappingRules(
			companyId, -1, -1);

		for (UserMappingRule userMappingRule : userMappingRules) {
			Integer count = map.get(userMappingRule.getDestinationField());

			if (count == null) {
				count = 0;
			}

			count++;

			map.put(userMappingRule.getDestinationField(), count);
		}

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("name", entry.getKey());
			jsonObject.put("count", entry.getValue());

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	@Override
	public UserMappingRule addUserMappingRule(
			long companyId, long userId, long mappingDataSourceId,
			String modelName, long fieldSetId, String sourceField,
			String destinationField, int frequency, boolean required)
		throws PortalException {

		long userMappingRuleId = counterLocalService.increment();

		UserMappingRule userMappingRule = userMappingRulePersistence.create(
			userMappingRuleId);

		userMappingRule.setCompanyId(companyId);
		userMappingRule.setUserId(userId);
		userMappingRule.setCreateDate(new Date());
		userMappingRule.setMappingDataSourceId(mappingDataSourceId);
		userMappingRule.setFieldSetId(fieldSetId);
		userMappingRule.setModelName(modelName);
		userMappingRule.setSourceField(sourceField);
		userMappingRule.setDestinationField(destinationField);
		userMappingRule.setRequired(required);

		MappingDataSource mappingDataSource =
			mappingDataSourceLocalService.fetchMappingDataSource(
				mappingDataSourceId);

		// START TEMP FIX

		JSONObject fieldsJSONObject = JSONFactoryUtil.createJSONObject(
			mappingDataSource.getAvailableFields());

		if (fieldsJSONObject.length() == 0) {
			userMappingRule.setFieldType("String");
		}
		else {

		// END TEMP FIX

			JSONObject modelFieldsJSONObject = fieldsJSONObject.getJSONObject(
				modelName);

			userMappingRule.setFieldType(
				modelFieldsJSONObject.getString(sourceField));
		}

		userMappingRule.setFrequency(frequency);

		userMappingRulePersistence.update(userMappingRule);

		List<UserMappingRule> userMappingRules = new ArrayList<>();

		userMappingRules.add(userMappingRule);

		if ((mappingDataSource.getType() == MappingDataSourceConstants.CUSTOM) ||
			(frequency == FrequencyUtil.INSTANT)){

			return userMappingRule;
		}

//		Event updateUsersEvent = new UpdateUsersEvent(
//			mappingDataSourceId, userMappingRules);
//
//		updateUsersEvent.run();

		return userMappingRule;
	}

	@Override
	public UserMappingRule deleteUserMappingRule(long userMappingRuleId)
		throws PortalException {

		return userMappingRulePersistence.remove(userMappingRuleId);
	}

	@Override
	public List<UserMappingRule> getUserMappingRules(long mappingDataSourceId) {
		return userMappingRulePersistence.findByMappingDataSourceId(
			mappingDataSourceId);
	}

	@Override
	public List<UserMappingRule> getUserMappingRules(
		long mappingDataSourceId, int frequency) {

		return userMappingRulePersistence.findByD_F(
			mappingDataSourceId, frequency, -1, -1, null, false);
	}

	@Override
	public List<UserMappingRule> getUserMappingRules(
		long companyId, int start, int end) {

		return userMappingRulePersistence.findByCompanyId(
			companyId, start, end);
	}

	public List<UserMappingRule> getUserMappingRules(
		long companyId, String destinationField) {

		return userMappingRulePersistence.findByC_D(
			companyId, destinationField);
	}

	@Override
	public UserMappingRule updateUserMappingRules(
		long userMappingRuleId, long mappingDataSourceId, long fieldSetId,
		String modelName, String sourceField, String destinationField,
		String fieldType, int frequency) {

		UserMappingRule userMappingRule =
			userMappingRulePersistence.fetchByPrimaryKey(userMappingRuleId);

		userMappingRule.setMappingDataSourceId(mappingDataSourceId);
		userMappingRule.setFieldSetId(fieldSetId);
		userMappingRule.setModelName(modelName);
		userMappingRule.setSourceField(sourceField);
		userMappingRule.setDestinationField(destinationField);
		userMappingRule.setFieldType(fieldType);
		userMappingRule.setFrequency(frequency);

		userMappingRulePersistence.update(userMappingRule);

		return userMappingRule;
	}

}