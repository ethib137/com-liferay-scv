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

package com.liferay.osb.scv.user.mapper.internal.event;

import com.liferay.osb.scv.user.mapper.internal.event.constants.EventConstants;
import com.liferay.osb.scv.user.mapper.model.MappingDataSource;
import com.liferay.osb.scv.user.mapper.sample.DataSourceUtil;
import com.liferay.osb.scv.user.mapper.service.MappingDataSourceLocalServiceUtil;
import com.liferay.osb.scv.user.mapper.service.persistence.MappingDataSourceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.Message;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class GetFieldsEvent extends BaseEvent {

	public GetFieldsEvent(long mappingDataSourceId) {
		_mappingDataSourceId = mappingDataSourceId;
	}

	public void handleResponse(Message message) throws Exception {
		MappingDataSource mappingDataSource =
			MappingDataSourceUtil.fetchByPrimaryKey(_mappingDataSourceId);

		mappingDataSource.setAvailableFields(
			String.valueOf(message.getPayload()));

		MappingDataSourceLocalServiceUtil.updateMappingDataSource(
			mappingDataSource);
	}

	@Override
	public void run() {
		Map<String, Object> parameters = new HashMap<>();

		parameters.put("method", EventConstants.GET_FIELDS);
		parameters.put("mappingDataSourceId", _mappingDataSourceId);

		run(parameters);
	}

	private final long _mappingDataSourceId;

}