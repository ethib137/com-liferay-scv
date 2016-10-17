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

import com.liferay.osb.scv.user.mapper.internal.event.constants.MappingDataSourceConstants;
import com.liferay.osb.scv.user.mapper.model.UserMappingRule;
import com.liferay.osb.scv.user.mapper.sample.Frequency;
import com.liferay.osb.scv.user.mapper.sample.FrequencyUtil;
import com.liferay.osb.scv.user.mapper.service.base.UserMappingRuleServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.auth.PrincipalException;

import java.util.List;
import java.util.Map;

/**
 * @author Shinn Lok
 */
@ProviderType
public class UserMappingRuleServiceImpl extends UserMappingRuleServiceBaseImpl {

	public JSONArray getUserMappingRuleDestinationFields()
		throws Exception {

		return
			userMappingRuleLocalService.getUserMappingRuleDestinationFields(
				CompanyThreadLocal.getCompanyId());
	}

	public JSONArray getUserMappingRuleFrequencies() {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (Frequency frequency : FrequencyUtil.getFrequencies()) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("name", frequency.getName());
			jsonObject.put("frequencyId", frequency.getFrequencyId());

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	@Override
	public JSONArray getUserMappingRules(String destinationField)
			throws PortalException {

		return userMappingRuleLocalService.getUserMappingRules(
			CompanyThreadLocal.getCompanyId(), destinationField);
	}

	@Override
	public UserMappingRule addUserMappingRule(
			long mappingDataSourceId, long fieldSetId, String modelName,
			String sourceField, String destinationField, int frequency,
			boolean required)
		throws PortalException {

		return userMappingRuleLocalService.addUserMappingRule(
			CompanyThreadLocal.getCompanyId(), getUserId(), mappingDataSourceId,
			modelName, fieldSetId, sourceField, destinationField,
			frequency, required);
	}

	public List<UserMappingRule> deleteUserMappingRules(String destinationField)
		throws PortalException {

		return userMappingRuleLocalService.deleteUserMappingRules(
			CompanyThreadLocal.getCompanyId(), destinationField);
	}

	@Override
	public UserMappingRule deleteUserMappingRule(long userMappingRuleId)
		throws PortalException {

		return userMappingRuleLocalService.deleteUserMappingRule(
			userMappingRuleId);
	}

	@Override
	public List<UserMappingRule> getUserMappingRules(int start, int end)
		throws PortalException {

		return userMappingRuleLocalService.getUserMappingRules(
			CompanyThreadLocal.getCompanyId(), start, end);
	}

}