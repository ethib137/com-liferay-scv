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
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

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

	@Activate
	public void activate() throws Exception {
		BeanInfo beanInfo = Introspector.getBeanInfo(BaseModel.class);

		PropertyDescriptor[] propertyDescriptors =
			beanInfo.getPropertyDescriptors();

		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			String name = propertyDescriptor.getName();

			if (!_ignoredFields.contains(name)) {
				_ignoredFields.add(name);
			}
		}
	}

	@Deactivate
	public void deactivate() throws Exception {
		_ignoredFields.clear();
	}

	public Class<?> getClass(String className) throws ClassNotFoundException {
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

	public JSONArray getFields() throws Exception {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (Class<?> clazz : _MODEL_CLASSES) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			List<String> fields = getFields(clazz);

			if (fields.isEmpty()) {
				continue;
			}

			jsonObject.put("className", clazz.getSimpleName());
			jsonObject.put("columns", fields);

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	public void getUser(
		JSONObject jsonObject, BaseModel baseModel, List<String> fields) {

		if ((fields == null) || fields.isEmpty()) {
			return;
		}

		Class<?> clazz = baseModel.getModelClass();

		String className = clazz.getSimpleName().concat("#");

		ExpandoBridge expandoBridge = baseModel.getExpandoBridge();

		for (String field : fields) {
			if (field.startsWith(_CUSTOM_FIELD)) {
				Serializable attribute = expandoBridge.getAttribute(
					field.replace(_CUSTOM_FIELD, StringPool.BLANK));

				if (attribute == null) {
					continue;
				}

				jsonObject.put(
					className.concat(field), String.valueOf(attribute));
			}
			else {
				String value = BeanPropertiesUtil.getString(baseModel, field);

				if (Validator.isBlank(value)) {
					continue;
				}

				jsonObject.put(className.concat(field), value);
			}
		}
	}

//	public JSONArray getUsers(String fields, int start, int end) {
//		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
//
//		List<User> users = _userLocalService.getUsers(start, end);
//
//		for (User user : users) {
//			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
//
//			ExpandoBridge expandoBridge = user.getExpandoBridge();
//
//			for (String field : StringUtil.split(fields)) {
//				if (field.startsWith(_EXPANDO_FIELD_)) {
//					Serializable attribute = expandoBridge.getAttribute(
//						field.replace(_EXPANDO_FIELD_, StringPool.BLANK));
//
//					jsonObject.put(field, String.valueOf(attribute));
//				}
//				else {
//					jsonObject.put(
//						field, BeanPropertiesUtil.getString(user, field));
//				}
//			}
//
//			jsonArray.put(jsonObject);
//		}
//
//		return jsonArray;
//	}

	public JSONArray getUsers(Map<String, List<String>> fields)
		throws Exception {

		List<String> userFields = fields.get("User");
		List<String> contactFields = fields.get("Contact");
		List<String> groupFields = fields.get("Group");

		JSONArray usersJSONArray = JSONFactoryUtil.createJSONArray();

		List<User> users = _userLocalService.getUsers(-1, -1);

		for (User user : users) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			// User

			getUser(jsonObject, user, userFields);

			// Contact

			getUser(jsonObject, user.getContact(), contactFields);

			if (jsonObject.length() == 0) {
				continue;
			}

			usersJSONArray.put(jsonObject);
		}

		return usersJSONArray;
	}

	public int getUsersCount() {
		return _userLocalService.getUsersCount();
	}

	protected List<String> getFields(Class<?> clazz) {
		try {
			List<String> fields = new ArrayList<>();

			BeanInfo beanInfo = Introspector.getBeanInfo(
				clazz.getInterfaces()[0]);

			PropertyDescriptor[] propertyDescriptors =
				beanInfo.getPropertyDescriptors();

			for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
				String name = propertyDescriptor.getName();

				if (!_ignoredFields.contains(name)) {
					fields.add(name);
				}
			}

			ExpandoBridge expandoBridge =
				ExpandoBridgeFactoryUtil.getExpandoBridge(
					CompanyThreadLocal.getCompanyId(), clazz.getName());

			Enumeration<String> attributeNames =
				expandoBridge.getAttributeNames();

			while (attributeNames.hasMoreElements()) {
				String attributeName = attributeNames.nextElement();

				fields.add(_CUSTOM_FIELD.concat(attributeName));
			}

			return fields;
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e.getMessage(), e);
			}

			return Collections.emptyList();
		}
	}

	private static final String _CUSTOM_FIELD = "CUSTOM_FIELD#";

	private static final Class[] _MODEL_CLASSES =
		new Class[] {Contact.class, User.class};

	private static final Log _log = LogFactoryUtil.getLog(SCVUserJSONWS.class);

	private static final List<String> _ignoredFields = new ArrayList<>();

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private UserLocalService _userLocalService;

}