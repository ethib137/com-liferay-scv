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
 * Provides a wrapper for {@link MappingDataSourceService}.
 *
 * @author Brian Wing Shun Chan
 * @see MappingDataSourceService
 * @generated
 */
@ProviderType
public class MappingDataSourceServiceWrapper implements MappingDataSourceService,
	ServiceWrapper<MappingDataSourceService> {
	public MappingDataSourceServiceWrapper(
		MappingDataSourceService mappingDataSourceService) {
		_mappingDataSourceService = mappingDataSourceService;
	}

	@Override
	public com.liferay.osb.scv.user.mapper.model.MappingDataSource addMappingDataSource(
		java.lang.String name, java.lang.String url, java.lang.String login,
		java.lang.String password, long type, java.lang.String availableFields)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mappingDataSourceService.addMappingDataSource(name, url, login,
			password, type, availableFields);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _mappingDataSourceService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.osb.scv.user.mapper.model.MappingDataSource> getMappingDataSources() {
		return _mappingDataSourceService.getMappingDataSources();
	}

	@Override
	public java.util.Map<java.lang.Long, java.lang.String> getMappingDataSourceTypes()
		throws java.lang.Exception {
		return _mappingDataSourceService.getMappingDataSourceTypes();
	}

	@Override
	public MappingDataSourceService getWrappedService() {
		return _mappingDataSourceService;
	}

	@Override
	public void setWrappedService(
		MappingDataSourceService mappingDataSourceService) {
		_mappingDataSourceService = mappingDataSourceService;
	}

	private MappingDataSourceService _mappingDataSourceService;
}