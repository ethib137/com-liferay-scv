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
 * Provides the remote service utility for MappingDataSource. This utility wraps
 * {@link com.liferay.osb.scv.user.mapper.service.impl.MappingDataSourceServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see MappingDataSourceService
 * @see com.liferay.osb.scv.user.mapper.service.base.MappingDataSourceServiceBaseImpl
 * @see com.liferay.osb.scv.user.mapper.service.impl.MappingDataSourceServiceImpl
 * @generated
 */
@ProviderType
public class MappingDataSourceServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.scv.user.mapper.service.impl.MappingDataSourceServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.scv.user.mapper.model.MappingDataSource addMappingDataSource(
		java.lang.String name, java.lang.String url, java.lang.String login,
		java.lang.String password, long type, java.lang.String availableFields)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addMappingDataSource(name, url, login, password, type,
			availableFields);
	}

	public static com.liferay.osb.scv.user.mapper.model.MappingDataSource deleteMappingDataSource(
		long mappingDataSourceId) throws java.lang.Exception {
		return getService().deleteMappingDataSource(mappingDataSourceId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.osb.scv.user.mapper.model.MappingDataSource> getMappingDataSources() {
		return getService().getMappingDataSources();
	}

	public static java.util.Map<java.lang.Long, java.lang.String> getMappingDataSourceTypes()
		throws java.lang.Exception {
		return getService().getMappingDataSourceTypes();
	}

	public static MappingDataSourceService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MappingDataSourceService, MappingDataSourceService> _serviceTracker =
		ServiceTrackerFactory.open(MappingDataSourceService.class);
}