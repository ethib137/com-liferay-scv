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

import com.liferay.osb.scv.user.mapper.internal.event.GetFieldsEvent;
import com.liferay.osb.scv.user.mapper.internal.event.constants.MappingDataSourceConstants;
import com.liferay.osb.scv.user.mapper.model.MappingDataSource;
import com.liferay.osb.scv.user.mapper.sample.FrequencyUtil;
import com.liferay.osb.scv.user.mapper.service.base.MappingDataSourceLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;

/**
 * @author Shinn Lok
 */
@ProviderType
public class MappingDataSourceLocalServiceImpl
	extends MappingDataSourceLocalServiceBaseImpl {

	public List<MappingDataSource> getMappingDataSources(int start, int end) {
		return mappingDataSourcePersistence.findAll(
			start, end, null, false);
	}

	public List<MappingDataSource> getMappingDataSources(long companyId) {
		return mappingDataSourcePersistence.findByCompanyId(companyId);
	}

	public MappingDataSource addMappingDataSource(
			long companyId, String name, String url, String login,
			String password, long type, String availableFields)
		throws PortalException {

		long mappingDataSourceId = counterLocalService.increment();

		MappingDataSource mappingDataSource =
			mappingDataSourcePersistence.create(mappingDataSourceId);

		mappingDataSource.setCompanyId(companyId);
		mappingDataSource.setName(name);
		mappingDataSource.setUrl(url);
		mappingDataSource.setLogin(login);
		mappingDataSource.setPassword(password);
		mappingDataSource.setType(type);
		mappingDataSource.setAvailableFields(availableFields);

		mappingDataSourcePersistence.update(mappingDataSource);

		if (type != MappingDataSourceConstants.LIFERAY) {
			return mappingDataSource;
		}

		return mappingDataSource;
	}

}