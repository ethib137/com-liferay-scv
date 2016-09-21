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

package com.liferay.osb.scv.internal.util;

import com.liferay.osb.scv.user.mapper.sample.DataSource;
import com.liferay.osb.scv.user.mapper.sample.DataSourceUtil;
import com.liferay.petra.json.web.service.client.JSONWebServiceClient;
import com.liferay.petra.json.web.service.client.JSONWebServiceClientImpl;

import java.net.URL;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class JSONWebServiceClientUtil {

	public static String doGet(
			long dataSourceId, String method, Map<String, String> parameters)
		throws Exception {

//		DataSourceUtil.clearDataSources();

		DataSource dataSource = DataSourceUtil.getDataSource(dataSourceId);

		JSONWebServiceClient jsonWebServiceClient = getJSONWebServiceClient(
			dataSourceId);

		String url = dataSource.getURL();

		return jsonWebServiceClient.doGet(url.concat(method), parameters);
	}

	protected static JSONWebServiceClient getJSONWebServiceClient(
			long dataSourceId)
		throws Exception {

		JSONWebServiceClient jsonWebServiceClient =
			_jsonWebServiceClientMap.get(dataSourceId);

		if (jsonWebServiceClient != null) {
			return jsonWebServiceClient;
		}

		DataSource dataSource = DataSourceUtil.getDataSource(dataSourceId);

		jsonWebServiceClient = new JSONWebServiceClientImpl();

		URL url = new URL(dataSource.getURL());

		jsonWebServiceClient.setHostName(url.getHost());
		jsonWebServiceClient.setHostPort(url.getPort());
		jsonWebServiceClient.setLogin(dataSource.getUserName());
		jsonWebServiceClient.setPassword(dataSource.getPassword());

		_jsonWebServiceClientMap.put(dataSourceId, jsonWebServiceClient);

		return jsonWebServiceClient;
	}

	private static final Map<Long, JSONWebServiceClient>
		_jsonWebServiceClientMap = new HashMap<>();

}