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
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.EmailAddress;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.Team;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.Website;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UniqueList;
import com.liferay.portal.kernel.util.Validator;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	protected void activate() throws Exception {
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
	protected void deactivate() throws Exception {
		_ignoredFields.clear();
	}

	protected Class<?> getClass(String className) throws ClassNotFoundException {
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
		System.out.println(SCVExpandoColumnConstants.LONG_LABEL);

		JSONArray fieldsJSONArray = JSONFactoryUtil.createJSONArray();

		for (Class<?> clazz : _MODEL_CLASSES) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			JSONArray modelFieldsJSONArray = getFields(clazz);

			if (modelFieldsJSONArray.length() == 0) {
				continue;
			}

			jsonObject.put("className", clazz.getSimpleName());
			jsonObject.put("fields", modelFieldsJSONArray);

			fieldsJSONArray.put(jsonObject);
		}

		return fieldsJSONArray;
	}

	protected void addUser(
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

	protected JSONObject addUser2(
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
				String value = BeanPropertiesUtil.getString(baseModel, field);

				jsonObject.put(field, value);
			}
		}

		return jsonObject;
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

			addUser(jsonObject, user, userFields);

			// Contact

			addUser(jsonObject, user.getContact(), contactFields);

			if (jsonObject.length() == 0) {
				continue;
			}

			usersJSONArray.put(jsonObject);
		}

		return usersJSONArray;
	}

	public JSONObject getUsers2(Map<String, List<String>> fields)
		throws Exception {

		JSONObject usersJSONObject = JSONFactoryUtil.createJSONObject();

		List<String> userFields = fields.get("User");

		Map<String, Set<BaseModel<?>>> associatedModels = new HashMap<>();

		JSONArray usersJSONArray = JSONFactoryUtil.createJSONArray();

		for (User user : _userLocalService.getUsers(-1, -1)) {
			JSONObject userJSONObject = JSONFactoryUtil.createJSONObject();

			addUser2(userJSONObject, user, userFields);

			addAssociations(userJSONObject, user, fields, associatedModels);

			usersJSONArray.put(userJSONObject);
		}

		usersJSONObject.put("User", usersJSONArray);

		for (Map.Entry<String, Set<BaseModel<?>>> entrySet :
				associatedModels.entrySet()) {

			JSONArray modelsJSONArray = JSONFactoryUtil.createJSONArray();

			List<String> modelFields = fields.get(entrySet.getKey());

			for (BaseModel<?> baseModel : entrySet.getValue()) {
				JSONObject modelJSONObject = JSONFactoryUtil.createJSONObject();

				addUser2(modelJSONObject, baseModel, modelFields);

				modelsJSONArray.put(modelJSONObject);
			}

			usersJSONObject.put(entrySet.getKey(), modelsJSONArray);
		}

		return usersJSONObject;
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

	protected void addAssociations(
			JSONObject jsonObject, User user,
			Map<String, List<String>> fields,
			Map<String, Set<BaseModel<?>>> models)
		throws Exception {

		// Address

		if (fields.containsKey("Address")) {
			List<Address> addresses = user.getAddresses();

			List<Long> addressIds = new ArrayList<>();

			for (Address address : addresses) {
				addressIds.add(address.getAddressId());
			}

			jsonObject.put("addressIds", addressIds);

			addModel(models, "Address", addresses);
		}

		// Contact

		if (fields.containsKey("Contact")) {
			List<Object> contactIds = new ArrayList<>();

			contactIds.add(user.getContactId());

			jsonObject.put("contactIds", contactIds);

			List<BaseModel<?>> contacts = new ArrayList<>();

			contacts.add(user.getContact());

			addModel(models, "Contact", contacts);
		}

		// Email address

		if (fields.containsKey("Email")) {
			List<EmailAddress> emailAddresses = user.getEmailAddresses();

			List<Long> emailAddressIds = new ArrayList<>();

			for (EmailAddress emailAddress : emailAddresses) {
				emailAddressIds.add(emailAddress.getEmailAddressId());
			}

			jsonObject.put("emailAddressIds", emailAddressIds);

			addModel(models, "EmailAddress", emailAddresses);
		}

		// Group

		if (fields.containsKey("Group")) {
			jsonObject.put("groupIds", user.getGroupIds());

			addModel(models, "Group", user.getGroups());
		}

		// Organization

		if (fields.containsKey("Organization")) {
			jsonObject.put("organizationIds", user.getOrganizationIds());

			addModel(models, "Organization", user.getOrganizations());
		}

		// Role

		if (fields.containsKey("Role")) {
			jsonObject.put("roleIds", user.getRoleIds());

			addModel(models, "Role", user.getRoles());
		}


		// Team

		if (fields.containsKey("Team")) {
			jsonObject.put("teamIds", user.getTeamIds());

			addModel(models, "Team", user.getTeams());
		}

		// Phone

		if (fields.containsKey("Phone")) {
			List<Phone> phones = user.getPhones();
			List<Long> phoneIds = new ArrayList<>();

			for (Phone phone : phones) {
				phoneIds.add(phone.getPhoneId());
			}

			jsonObject.put("phoneIds", phoneIds);

			addModel(models, "Phone", phones);
		}

		// User group

		if (fields.containsKey("UserGroup")) {
			jsonObject.put("userGroupIds", user.getUserGroupIds());

			addModel(models, "UserGroup", user.getUserGroups());
		}

		// Website

		if (fields.containsKey("Website")) {
			List<Website> websites = user.getWebsites();
			List<Long> websiteIds = new ArrayList<>();

			for (Website website : websites) {
				websiteIds.add(website.getWebsiteId());
			}

			jsonObject.put("websiteIds", websiteIds);

			addModel(models, "Website", websites);
		}
	}

	public int getUsersCount() {
		return _userLocalService.getUsersCount();
	}

	protected JSONArray getFields(Class<?> clazz) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(
				clazz.getInterfaces()[0]);

			PropertyDescriptor[] propertyDescriptors =
				beanInfo.getPropertyDescriptors();

			for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
				String name = propertyDescriptor.getName();

				if (!_ignoredFields.contains(name)) {
					JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

					jsonObject.put("field", name);

					Class<?> propertyTypeClass =
						propertyDescriptor.getPropertyType();

					jsonObject.put("type", propertyTypeClass.getSimpleName());

					jsonArray.put(jsonObject);
				}
			}

			ExpandoBridge expandoBridge =
				ExpandoBridgeFactoryUtil.getExpandoBridge(
					CompanyThreadLocal.getCompanyId(), clazz.getName());

			Enumeration<String> attributeNames =
				expandoBridge.getAttributeNames();

			while (attributeNames.hasMoreElements()) {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

				String attributeName = attributeNames.nextElement();

				jsonObject.put("field", _CUSTOM_FIELD.concat(attributeName));

				int attributeType = expandoBridge.getAttributeType(
					attributeName);

				jsonObject.put(
					"type",
					SCVExpandoColumnConstants.getSCVTypeLabel(attributeType));

				jsonArray.put(jsonObject);
			}

			return jsonArray;
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e.getMessage(), e);
			}

			return jsonArray;
		}
	}

	private static final String _CUSTOM_FIELD = "CUSTOM_FIELD#";

	private static final Class[] _MODEL_CLASSES =
		new Class[] {Address.class,
					 Contact.class,
					 EmailAddress.class,
					 Group.class,
					 Organization.class,
					 Phone.class,
					 Role.class,
					 Team.class,
					 User.class,
					 Website.class};

	private static final Log _log = LogFactoryUtil.getLog(SCVUserJSONWS.class);

	private static final List<String> _ignoredFields = new ArrayList<>();

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private UserLocalService _userLocalService;

}