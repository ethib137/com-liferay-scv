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

package com.liferay.osb.scv.user.mapper.sample;

import com.liferay.petra.json.web.service.client.JSONWebServiceClientImpl;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.net.URL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class DataSourceUtil {

	public static void clearDataSources() {
		_dataSources.clear();
		_availableFields.clear();
	}

	public static DataSource getDataSource(Long dataSourceId) {
		List<DataSource> dataSources = getDataSources();

		return dataSources.get(dataSourceId.intValue());
	}

	public static void setAvailableFields(
		long dataSourceId, Map<String, List<String>> availableFields) {

		_availableFields.put(dataSourceId, availableFields);
	}

	public static List<DataSource> getDataSources() {
		try {
			return doGetDataSources();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	protected static List<DataSource> doGetDataSources() throws Exception {
		if (!_dataSources.isEmpty()) {
			return _dataSources;
		}

		JSONWebServiceClientImpl jsonWebServiceClient =
			new JSONWebServiceClientImpl();

		URL url = new URL("https://shinnlok.github.io/get-data-sources");

		jsonWebServiceClient.setHostName(url.getHost());
		jsonWebServiceClient.setHostPort(url.getPort());
		jsonWebServiceClient.setLogin(null);
		jsonWebServiceClient.setPassword(null);

		String response = jsonWebServiceClient.doGet(
			"https://shinnlok.github.io/get-data-sources",
			Collections.<String, String>emptyMap());

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray(response);

		for (int i = 0; i < jsonArray.length(); i++) {
			final JSONObject jsonObject = jsonArray.getJSONObject(i);

			DataSource dataSource = new DataSource() {

				public List<String> getAvailableFields(String tableName) {
					if (Validator.isBlank(tableName)) {
						return Collections.emptyList();
					}

					Map<String, List<String>> availableFields =
						_availableFields.get(
							jsonObject.getLong("dataSourceId"));

					if (availableFields == null) {
						return Collections.emptyList();
					}

					return availableFields.get(tableName);
				}

				public List<String> getTableNames() {
					Map<String, List<String>> availableFields =
						_availableFields.get(getDataSourceId());

					if (availableFields == null) {
						return Collections.emptyList();
					}

					return ListUtil.fromCollection(availableFields.keySet());
				}

				public long getDataSourceId() {
					return jsonObject.getLong("dataSourceId");
				}

				public String getName() {
					return jsonObject.getString("name");
				}

				public String getPassword() {
					return jsonObject.getString("password");
				}

				public String getIdField() {
					return jsonObject.getString("idField");
				}

				public List<String> getRequiredFields() {
					String requiredFields = jsonObject.getString(
						"requiredFields");

					return Arrays.asList(StringUtil.split(requiredFields));
				}

				public String getType() {
					return jsonObject.getString("type");
				}

				public String getURL() {
					return jsonObject.getString("URL");
				}

				public String getUserName() {
					return jsonObject.getString("userName");
				}

			};

			if (!_dataSources.contains(dataSource)) {
				_dataSources.add(dataSource);
			}
		}

		return _dataSources;
	}

	private static final Map<Long, Map<String, List<String>>> _availableFields =
		new HashMap<>();
	private static final List<DataSource> _dataSources = new ArrayList<>();

}