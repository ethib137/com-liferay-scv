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
import com.liferay.osb.scv.user.mapper.model.UserMappingRule;
import com.liferay.osb.scv.user.mapper.service.base.UserMappingRuleLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Shinn Lok
 */
@ProviderType
public class UserMappingRuleLocalServiceImpl
	extends UserMappingRuleLocalServiceBaseImpl {

	@Override
	public UserMappingRule addUserMappingRule(
		long companyId, long userId, long dataSourceId, String modelName,
		long fieldSetId, String sourceField, String destinationField,
		int frequency) {

		long userMappingRuleId = counterLocalService.increment();

		UserMappingRule userMappingRule = userMappingRulePersistence.create(
			userMappingRuleId);

		userMappingRule.setCompanyId(companyId);
		userMappingRule.setUserId(userId);
		userMappingRule.setCreateDate(new Date());
		userMappingRule.setDataSourceId(dataSourceId);
		userMappingRule.setFieldSetId(fieldSetId);
		userMappingRule.setModelName(modelName);
		userMappingRule.setSourceField(sourceField);
		userMappingRule.setDestinationField(destinationField);
		userMappingRule.setFrequency(frequency);

		userMappingRulePersistence.update(userMappingRule);

		List<UserMappingRule> userMappingRules = new ArrayList<>();

		userMappingRules.add(userMappingRule);

		Event updateUsersEvent = new UpdateUsersEvent(
			dataSourceId, userMappingRules);

		updateUsersEvent.run();

		return userMappingRule;
	}

	@Override
	public UserMappingRule deleteUserMappingRule(long userMappingRuleId)
		throws PortalException {

		return userMappingRulePersistence.remove(userMappingRuleId);
	}

	@Override
	public List<UserMappingRule> getUserMappingRules(
		long dataSourceId, int frequency) {

		return userMappingRulePersistence.findByD_F(
			dataSourceId, frequency, -1, -1, null, false);
	}

	@Override
	public List<UserMappingRule> getUserMappingRules(
		long companyId, int start, int end) {

		return userMappingRulePersistence.findByCompanyId(
			companyId, start, end);
	}

	@Override
	public UserMappingRule updateUserMappingRules(
		long userMappingRuleId, long dataSourceId, long fieldSetId,
		String sourceField, String destinationField, int frequency) {

		UserMappingRule userMappingRule =
			userMappingRulePersistence.fetchByPrimaryKey(userMappingRuleId);

		userMappingRule.setDataSourceId(dataSourceId);
		userMappingRule.setFieldSetId(fieldSetId);
		userMappingRule.setSourceField(sourceField);
		userMappingRule.setDestinationField(destinationField);
		userMappingRule.setFrequency(frequency);

		userMappingRulePersistence.update(userMappingRule);

		return userMappingRule;
	}

}