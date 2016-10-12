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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for UserMappingRule. This utility wraps
 * {@link com.liferay.osb.scv.user.mapper.service.impl.UserMappingRuleServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see UserMappingRuleService
 * @see com.liferay.osb.scv.user.mapper.service.base.UserMappingRuleServiceBaseImpl
 * @see com.liferay.osb.scv.user.mapper.service.impl.UserMappingRuleServiceImpl
 * @generated
 */
@ProviderType
public class UserMappingRuleServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.scv.user.mapper.service.impl.UserMappingRuleServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.scv.user.mapper.model.UserMappingRule addUserMappingRule(
		long mappingDataSourceId, long fieldSetId, java.lang.String modelName,
		java.lang.String sourceField, java.lang.String destinationField,
		int frequency, boolean required)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addUserMappingRule(mappingDataSourceId, fieldSetId,
			modelName, sourceField, destinationField, frequency, required);
	}

	public static com.liferay.osb.scv.user.mapper.model.UserMappingRule deleteUserMappingRule(
		long userMappingRuleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteUserMappingRule(userMappingRuleId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.osb.scv.user.mapper.model.UserMappingRule> getUserMappingRules(
		int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserMappingRules(start, end);
	}

	public static java.util.List<com.liferay.osb.scv.user.mapper.model.UserMappingRule> getUserMappingRules(
		java.lang.String destinationField)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserMappingRules(destinationField);
	}

	public static java.util.Map<java.lang.String, java.lang.Integer> getUserMappingRuleDestinationFields()
		throws java.lang.Exception {
		return getService().getUserMappingRuleDestinationFields();
	}

	public static UserMappingRuleService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<UserMappingRuleService, UserMappingRuleService> _serviceTracker =
		ServiceTrackerFactory.open(UserMappingRuleService.class);
}