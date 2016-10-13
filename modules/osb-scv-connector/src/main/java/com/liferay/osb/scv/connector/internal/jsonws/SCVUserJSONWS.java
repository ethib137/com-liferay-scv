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

package com.liferay.osb.scv.connector.internal.jsonws;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.osb.scv.connector.internal.MappingDataSourceUtil;
import com.liferay.osb.scv.connector.internal.model.SCVModel;
import com.liferay.osgi.util.ServiceTrackerFactory;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.bean.ClassLoaderBeanHandler;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ModelListenerRegistrationUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Shinn Lok
 */
@Component(
	immediate = true,
	property = {
		"json.web.service.context.name=SCVUser",
		"json.web.service.context.path=SCVUser"
	},
	service = SCVUserJSONWS.class
)
@JSONWebService
public class SCVUserJSONWS {

	public void setMappingDataSourceId(long mappingDataSourceId)
		throws Exception {

		MappingDataSourceUtil.setMappingDataSourceId(mappingDataSourceId);
	}

	public JSONObject getFields() throws Exception {
		JSONObject fieldsJSONObject = JSONFactoryUtil.createJSONObject();

		for (Class<?> clazz : _modelClasses.values()) {
			JSONObject modelFieldsJSONObject = getFields(clazz);

			if (modelFieldsJSONObject.length() == 0) {
				continue;
			}

			fieldsJSONObject.put(clazz.getSimpleName(), modelFieldsJSONObject);
		}

		return fieldsJSONObject;
	}

	public JSONObject getUsers(Map<String, List<String>> fields)
		throws Exception {

		JSONObject usersJSONObject = JSONFactoryUtil.createJSONObject();

		List<String> userFields = fields.get("User");

		if (userFields == null) {
			userFields = new ArrayList<>();
		}

		SCVModel scvModel = _scvModelMap.get("User");

		userFields.add(scvModel.getPrimaryKeyField());
		userFields.addAll(Arrays.asList(scvModel.getRequiredFields()));

		Map<String, Set<BaseModel<?>>> associatedModels = new HashMap<>();

		JSONArray usersJSONArray = JSONFactoryUtil.createJSONArray();

		for (User user : _userLocalService.getUsers(-1, -1)) {
			JSONObject userJSONObject = JSONFactoryUtil.createJSONObject();

			addUser(userJSONObject, user, userFields);

			addAssociations(userJSONObject, user, fields, associatedModels);

			usersJSONArray.put(userJSONObject);
		}

		usersJSONObject.put("User", usersJSONArray);

		for (Map.Entry<String, Set<BaseModel<?>>> entrySet :
				associatedModels.entrySet()) {

			JSONArray modelsJSONArray = JSONFactoryUtil.createJSONArray();

			List<String> modelFields = fields.get(entrySet.getKey());

			scvModel = _scvModelMap.get(entrySet.getKey());

			modelFields.add(scvModel.getPrimaryKeyField());
			modelFields.addAll(Arrays.asList(scvModel.getRequiredFields()));

			for (BaseModel<?> baseModel : entrySet.getValue()) {
				JSONObject modelJSONObject = JSONFactoryUtil.createJSONObject();

				addUser(modelJSONObject, baseModel, modelFields);

				modelsJSONArray.put(modelJSONObject);
			}

			usersJSONObject.put(entrySet.getKey(), modelsJSONArray);
		}

		return usersJSONObject;
	}

	public int getUsersCount() {
		return _userLocalService.getUsersCount();
	}

	@Activate
	protected void activate(BundleContext bundleContext) throws Exception {
		_bundleContext = bundleContext;

		initServiceTracker();
	}

	protected void addAssociations(
			JSONObject jsonObject, User user, Map<String, List<String>> fields,
			Map<String, Set<BaseModel<?>>> models)
		throws Exception {

		for (Map.Entry<String, SCVModel> entry : _scvModelMap.entrySet()) {
			SCVModel scvModel = entry.getValue();

			if (scvModel.isPrimary()) {
				continue;
			}

			List<String> fieldsList = fields.get(entry.getKey());

			if ((fieldsList == null) || fieldsList.isEmpty()) {
				continue;
			}

			jsonObject.put(
				scvModel.getPrimaryKeyField().concat("s"),
				scvModel.getPrimaryKeys(user));

			addModel(models, entry.getKey(), scvModel.getModels(user));
		}
	}

	protected void addModel(
		Map<String, Set<BaseModel<?>>> modelMap, String key,
		List<? extends BaseModel<?>> modelList) {

		Set<BaseModel<?>> models = modelMap.get(key);

		if (models == null) {
			models = new HashSet<>();
		}

		models.addAll(modelList);

		modelMap.put(key, models);
	}

	protected JSONObject addUser(
		JSONObject jsonObject, BaseModel baseModel, List<String> fields) {

		if ((fields == null) || fields.isEmpty()) {
			return null;
		}

		ExpandoBridge expandoBridge = baseModel.getExpandoBridge();

		for (String field : fields) {
			if (field.startsWith(_CUSTOM_FIELD)) {
				Serializable attribute = expandoBridge.getAttribute(
					field.replace(_CUSTOM_FIELD, StringPool.BLANK));

				jsonObject.put(field, String.valueOf(attribute));
			}
			else {
				Object object = BeanPropertiesUtil.getObject(baseModel, field);

				if (object instanceof BaseModel) {
					String name = BeanPropertiesUtil.getStringSilent(
						object, "name");

					jsonObject.put(field, name);
				}
				else {
					jsonObject.put(field, object);
				}
			}
		}

		return jsonObject;
	}

	protected Class<?> getClass(String className)
		throws ClassNotFoundException {

		try {
			Thread currentThread = Thread.currentThread();

			ClassLoader classLoader = currentThread.getContextClassLoader();

			return Class.forName(className, false, classLoader);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e.getMessage(), e);
			}

			return null;
		}
	}

	protected void getFields(JSONObject jsonObject, Class<?> clazz, SCVModel scvModel)
		throws Exception {

		BeanInfo beanInfo = Introspector.getBeanInfo(clazz);

		PropertyDescriptor[] propertyDescriptors =
			beanInfo.getPropertyDescriptors();

		List<String> availableFields = Arrays.asList(
			scvModel.getAvailableFields());

		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			String name = propertyDescriptor.getName();

			if (!availableFields.contains(name)) {
				continue;
			}

			Class<?> propertyTypeClass =
				propertyDescriptor.getPropertyType();

			jsonObject.put(name, propertyTypeClass.getSimpleName());
		}
	}

	protected JSONObject getFields(Class<?> clazz) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			SCVModel scvModel = _scvModelMap.get(clazz.getSimpleName());

			getFields(jsonObject, clazz, scvModel);
			getFields(jsonObject, clazz.getInterfaces()[0], scvModel);

			ExpandoBridge expandoBridge =
				ExpandoBridgeFactoryUtil.getExpandoBridge(
					CompanyThreadLocal.getCompanyId(), clazz.getName());

			Enumeration<String> attributeNames =
				expandoBridge.getAttributeNames();

			while (attributeNames.hasMoreElements()) {
				String attributeName = attributeNames.nextElement();

				int attributeType = expandoBridge.getAttributeType(
					attributeName);

				jsonObject.put(
					_CUSTOM_FIELD.concat(attributeName),
					SCVExpandoColumnConstants.getSCVTypeLabel(attributeType));
			}

			return jsonObject;
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e.getMessage(), e);
			}

			return jsonObject;
		}
	}

	protected void initServiceTracker() throws Exception {
		_serviceTracker = ServiceTrackerFactory.open(
			_bundleContext, SCVModel.class,
			new ServiceTrackerCustomizer<SCVModel, SCVModel>() {

				@Override
				public SCVModel addingService(
					ServiceReference<SCVModel> serviceReference) {

					SCVModel scvModel = _bundleContext.getService(
						serviceReference);

					Class<?> clazz = getModelClass(scvModel);

					_scvModelMap.put(clazz.getSimpleName(), scvModel);
					_modelClasses.put(
						clazz.getSimpleName(), getModelClass(scvModel));

					ModelListenerRegistrationUtil.register(scvModel);

					return scvModel;
				}

				@Override
				public void modifiedService(
					ServiceReference<SCVModel> serviceReference,
					SCVModel scvModel) {

					removedService(serviceReference, scvModel);

					addingService(serviceReference);
				}

				@Override
				public void removedService(
					ServiceReference<SCVModel> serviceReference,
					SCVModel scvModel) {

					Class<?> clazz = getModelClass(scvModel);

					_scvModelMap.remove(clazz.getSimpleName(), scvModel);
					_modelClasses.remove(
						clazz.getSimpleName(), getModelClass(scvModel));

					ModelListenerRegistrationUtil.unregister(scvModel);

					_bundleContext.ungetService(serviceReference);
				}

				protected Class<?> getModelClass(SCVModel scvModel) {
					Class<?> clazz = scvModel.getClass();

					if (ProxyUtil.isProxyClass(clazz)) {
						InvocationHandler invocationHandler =
							ProxyUtil.getInvocationHandler(scvModel);

						if (invocationHandler instanceof ClassLoaderBeanHandler) {
							ClassLoaderBeanHandler classLoaderBeanHandler =
								(ClassLoaderBeanHandler)invocationHandler;

							Object bean = classLoaderBeanHandler.getBean();

							clazz = bean.getClass();
						}
					}

					return ReflectionUtil.getGenericSuperType(clazz);
				}

			});
	}

	@Reference
	private PortletPreferencesLocalService _portletPreferencesLocalService;

	protected static final Map<String, Class<?>> _modelClasses =
		new HashMap<>();
	protected static final Map<String, SCVModel> _scvModelMap = new HashMap<>();

	private static final String _CUSTOM_FIELD = "CUSTOM_FIELD#";

	private static final Log _log = LogFactoryUtil.getLog(SCVUserJSONWS.class);

	private static BundleContext _bundleContext;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	private ServiceTracker<SCVModel, SCVModel> _serviceTracker;

	@Reference
	private UserLocalService _userLocalService;

}