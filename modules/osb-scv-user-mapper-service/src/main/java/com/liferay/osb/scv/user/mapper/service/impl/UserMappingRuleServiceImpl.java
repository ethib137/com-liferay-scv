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

import com.liferay.osb.scv.user.mapper.model.UserMappingRule;
import com.liferay.osb.scv.user.mapper.service.base.UserMappingRuleServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.auth.PrincipalException;

import java.util.List;

/**
 * @author Shinn Lok
 */
@ProviderType
public class UserMappingRuleServiceImpl extends UserMappingRuleServiceBaseImpl {

	@Override
	public UserMappingRule addUserMappingRule(
			long dataSourceId, long fieldSetId, String sourceField,
			String destinationField, int frequency)
		throws PortalException {

		return userMappingRuleLocalService.addUserMappingRule(
			CompanyThreadLocal.getCompanyId(), getUserId(), dataSourceId,
			fieldSetId, sourceField, destinationField, frequency);
	}

	@Override
	public UserMappingRule deleteUserMappingRule(long userMappingRuleId)
		throws PortalException {

		UserMappingRule userMappingRule =
			userMappingRulePersistence.fetchByPrimaryKey(userMappingRuleId);

		if (userMappingRule.getUserId() != getUserId()) {
			throw new PrincipalException();
		}

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