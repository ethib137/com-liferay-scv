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
import com.liferay.osb.scv.user.mapper.model.MappingDataSource;
import com.liferay.osb.scv.user.mapper.service.base.MappingDataSourceServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Shinn Lok
 */
@ProviderType
public class MappingDataSourceServiceImpl
	extends MappingDataSourceServiceBaseImpl {

	@Override
	public MappingDataSource deleteMappingDataSource(long mappingDataSourceId)
		throws Exception {

		MappingDataSource mappingDataSource =
			mappingDataSourcePersistence.fetchByPrimaryKey(mappingDataSourceId);

		mappingDataSourcePersistence.remove(mappingDataSource);

		return mappingDataSource;
	}

	public Map<Long, String> getMappingDataSourceTypes() throws Exception {
		return MappingDataSourceConstants.getMappingDataSourceTypes();
	}

	public List<MappingDataSource> getMappingDataSources() {
		return mappingDataSourcePersistence.findByCompanyId(CompanyThreadLocal.getCompanyId());
	}

	public JSONArray getMappingDataSourceNames() {
		List<MappingDataSource> mappingDataSources =
			mappingDataSourcePersistence.findByCompanyId(
				CompanyThreadLocal.getCompanyId());

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (MappingDataSource mappingDataSource : mappingDataSources) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("name", mappingDataSource.getName());
			jsonObject.put(
				"mappingDataSourceId",
				mappingDataSource.getMappingDataSourceId());

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	public List<String> getMappingDataSourceTableNames(long mappingDataSourceId)
		throws Exception {

		MappingDataSource mappingDataSource =
			mappingDataSourcePersistence.fetchByPrimaryKey(mappingDataSourceId);

		return mappingDataSource.getTableNames();
	}

	public List<String> getMappingDataSourceFieldNames(
			long mappingDataSourceId, String tableName)
		throws Exception {

		MappingDataSource mappingDataSource =
			mappingDataSourcePersistence.fetchByPrimaryKey(mappingDataSourceId);

		Map<String, String> availableFields =
			mappingDataSource.getAvailableFields(tableName);

		return new ArrayList<>(availableFields.keySet());
	}

	@Override
	public MappingDataSource addMappingDataSource(
			String name, String url, String login, String password, long type,
			String availableFields)
		throws PortalException {

		return mappingDataSourceLocalService.addMappingDataSource(
			CompanyThreadLocal.getCompanyId(), name, url, login, password, type,
			availableFields);
	}

}