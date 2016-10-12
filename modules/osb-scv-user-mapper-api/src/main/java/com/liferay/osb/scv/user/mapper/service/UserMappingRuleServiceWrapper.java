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

package com.liferay.osb.scv.user.mapper.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UserMappingRuleService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserMappingRuleService
 * @generated
 */
@ProviderType
public class UserMappingRuleServiceWrapper implements UserMappingRuleService,
	ServiceWrapper<UserMappingRuleService> {
	public UserMappingRuleServiceWrapper(
		UserMappingRuleService userMappingRuleService) {
		_userMappingRuleService = userMappingRuleService;
	}

	@Override
	public com.liferay.osb.scv.user.mapper.model.UserMappingRule addUserMappingRule(
		long mappingDataSourceId, long fieldSetId, java.lang.String modelName,
		java.lang.String sourceField, java.lang.String destinationField,
		int frequency, boolean required)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userMappingRuleService.addUserMappingRule(mappingDataSourceId,
			fieldSetId, modelName, sourceField, destinationField, frequency,
			required);
	}

	@Override
	public com.liferay.osb.scv.user.mapper.model.UserMappingRule deleteUserMappingRule(
		long userMappingRuleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userMappingRuleService.deleteUserMappingRule(userMappingRuleId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _userMappingRuleService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<java.lang.String> getUserMappingRuleDestinationFields()
		throws java.lang.Exception {
		return _userMappingRuleService.getUserMappingRuleDestinationFields();
	}

	@Override
	public java.util.List<com.liferay.osb.scv.user.mapper.model.UserMappingRule> getUserMappingRules(
		int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userMappingRuleService.getUserMappingRules(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.scv.user.mapper.model.UserMappingRule> getUserMappingRules(
		java.lang.String destinationField)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userMappingRuleService.getUserMappingRules(destinationField);
	}

	@Override
	public UserMappingRuleService getWrappedService() {
		return _userMappingRuleService;
	}

	@Override
	public void setWrappedService(UserMappingRuleService userMappingRuleService) {
		_userMappingRuleService = userMappingRuleService;
	}

	private UserMappingRuleService _userMappingRuleService;
}