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

package com.liferay.osb.scv.connector.internal.model;

import com.liferay.osb.scv.connector.internal.MappingDataSourceUtil;
import com.liferay.petra.json.web.service.client.JSONWebServiceClient;
import com.liferay.petra.json.web.service.client.JSONWebServiceClientImpl;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author Shinn Lok
 */
public abstract class SCVModel<T extends BaseModel<T>>
	extends BaseModelListener<T> {

	public abstract String[] getAvailableFields();

	public abstract List<T> getModels(User user) throws Exception;

	public abstract String getPrimaryKeyField();

	public abstract List<Long> getPrimaryKeys(User user) throws Exception;

	public String[] getRequiredFields() {
		return null;
	}

	public boolean isPrimary() {
		return false;
	}

	protected void addData(Map<String, String> parameters)
		throws Exception {

		getJSONWebServiceClient().doPost(
			"http://localhost:8080/api/jsonws/Cloud.cloud/add-data",
			parameters);
	}

	@Override
	public void onBeforeCreate(T model) throws ModelListenerException {
	}

	@Override
	public void onBeforeUpdate(T model) throws ModelListenerException {
		long mappingDataSourceId = MappingDataSourceUtil.getMappingDataSourceId();

		if (mappingDataSourceId == 0) {
			return;
		}

		PersistedModel originalModel = null;

		try {
			PersistedModelLocalService persistedModelLocalService =
					PersistedModelLocalServiceRegistryUtil.
							getPersistedModelLocalService(model.getModelClassName());
			originalModel = persistedModelLocalService.getPersistedModel(
					model.getPrimaryKeyObj());

			_models.put(model.getPrimaryKeyObj(), originalModel);
		}
		catch (Exception e) {
			throw new ModelListenerException();
		}
	}

	@Override
	public void onAfterCreate(T model) throws ModelListenerException {
		long mappingDataSourceId = MappingDataSourceUtil.getMappingDataSourceId();

		if (mappingDataSourceId == 0) {
			return;
		}

		Map<String, Object> modelAttributes = model.getModelAttributes();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		Map<String, String> map = new HashMap<>();


		for (Map.Entry<String, Object> entry : modelAttributes.entrySet()) {
			String key = entry.getKey();

			if (!ArrayUtil.contains(getAvailableFields(), key) &&
				(!ArrayUtil.contains(getRequiredFields(), key) ||
					!key.equals(getPrimaryKeyField()))) {

				continue;
			}

			map.put(key, String.valueOf(entry.getValue()));
		}


		if (map.isEmpty()) {
			return;
		}

		List<Object> objects = new ArrayList<>();

		objects.add(map);

		jsonObject.put(model.getModelClass().getSimpleName(), objects);

		Map<String, String> parameters = new HashMap<>();

		parameters.put("mappingDataSourceId", String.valueOf(MappingDataSourceUtil.getMappingDataSourceId()));
		parameters.put("json", jsonObject.toJSONString());

		try {
			addData(parameters);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onAfterUpdate(T model) throws ModelListenerException {
		long mappingDataSourceId = MappingDataSourceUtil.getMappingDataSourceId();

		if (mappingDataSourceId == 0) {
			return;
		}

		Map<String, Object> modelAttributes = model.getModelAttributes();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		Map<String, String> map = new HashMap<>();

		for (Map.Entry<String, Object> entry : modelAttributes.entrySet()) {
			String key = entry.getKey();

			if (ArrayUtil.contains(getRequiredFields(), key) ||
				key.equals(getPrimaryKeyField())) {

				map.put(entry.getKey(), String.valueOf(entry.getValue()));

				continue;
			}

			if (!ArrayUtil.contains(getAvailableFields(), key)) {
				continue;
			}

			String value = BeanPropertiesUtil.getString(
				_models.get(model.getPrimaryKeyObj()), entry.getKey());

			if (value.equals(BeanPropertiesUtil.getString(model, entry.getKey()))) {
				continue;
			}

			map.put(entry.getKey(), String.valueOf(entry.getValue()));
		}

		if (map.isEmpty()) {
			return;
		}

		List<Object> objects = new ArrayList<>();

		objects.add(map);

		jsonObject.put(model.getModelClass().getSimpleName(), objects);

		Map<String, String> parameters = new HashMap<>();

		parameters.put("mappingDataSourceId", String.valueOf(MappingDataSourceUtil.getMappingDataSourceId()));
		parameters.put("json", jsonObject.toJSONString());

		try {
			addData(parameters);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	private static final Map<Serializable, PersistedModel> _models =
		new HashMap<Serializable, PersistedModel>();

	protected static JSONWebServiceClient getJSONWebServiceClient()
		throws Exception {

		if (_jsonWebServiceClient != null) {
			return _jsonWebServiceClient;
		}

		_jsonWebServiceClient = new JSONWebServiceClientImpl();

		URL url = new URL("http://localhost:8080");

		_jsonWebServiceClient.setHostName(url.getHost());
		_jsonWebServiceClient.setHostPort(url.getPort());
		_jsonWebServiceClient.setLogin("test@liferay.com");
		_jsonWebServiceClient.setPassword("test");

		return _jsonWebServiceClient;
	}

	protected List<Long> getList(long[] ids) {
		LongStream longStream = Arrays.stream(ids);

		Stream<Long> stream = longStream.boxed();

		return stream.collect(Collectors.toList());
	}

	private static JSONWebServiceClient _jsonWebServiceClient;
}