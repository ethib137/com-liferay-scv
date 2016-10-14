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

package com.liferay.osb.scv.internal.jsonws;

import com.liferay.osb.scv.internal.messaging.constants.UserMapperDestinationNames;
import com.liferay.osb.scv.internal.util.JSONWebServiceClientUtil;
import com.liferay.osb.scv.user.mapper.model.MappingDataSource;
import com.liferay.osb.scv.user.mapper.model.UserMappingRule;
import com.liferay.osb.scv.user.mapper.service.MappingDataSourceLocalServiceUtil;
import com.liferay.osb.scv.user.mapper.service.UserMappingRuleLocalServiceUtil;
import com.liferay.petra.json.web.service.client.JSONWebServiceClient;
import com.liferay.petra.json.web.service.client.JSONWebServiceClientImpl;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;

import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shinn Lok
 */
@Component(
	immediate = true,
	property = {
		"json.web.service.context.name=Cloud",
		"json.web.service.context.path=Cloud"
	},
	service = Cloud.class
)
@JSONWebService
public class Cloud {

	public static Object addDataSource(
			String name, String url, String login, String password, long type)
		throws Exception {

		Message message = new Message();

		Map<String, Object> parameters = new HashMap<>();

		parameters.put("method", "addDataSource");
		parameters.put("name", name);
		parameters.put("url", url);
		parameters.put("login", login);
		parameters.put("password", password);
		parameters.put("type", type);

		message.setValues(parameters);

		MappingDataSource mappingDataSource =
			(MappingDataSource)MessageBusUtil.sendSynchronousMessage(
				UserMapperDestinationNames.SCV_USER_MAPPER, message);

		// **** START DEV ONLY ****

		JSONWebServiceClient jsonWebServiceClient =
			new JSONWebServiceClientImpl();

		URL urlObject = new URL(url);

		jsonWebServiceClient.setHostName(urlObject.getHost());
		jsonWebServiceClient.setHostPort(urlObject.getPort());
		jsonWebServiceClient.setLogin(login);
		jsonWebServiceClient.setPassword(password);

		Map<String, String> responseParameters = new HashMap<>();

		responseParameters.put(
			"mappingDataSourceId",
			String.valueOf(mappingDataSource.getMappingDataSourceId()));

		jsonWebServiceClient.doPost(
			url + "/set-mapping-data-source-id", responseParameters);

		getFields(mappingDataSource.getMappingDataSourceId());

		// **** END DEV ONLY ****

		return mappingDataSource;
	}

	public static void getFields(long mappingDataSourceId) {
		Message message = new Message();

		Map<String, Object> parameters = new HashMap<>();

		parameters.put("method", "getFields");
		parameters.put("mappingDataSourceId", mappingDataSourceId);

		message.setValues(parameters);

		MessageBusUtil.sendMessage(
			UserMapperDestinationNames.SCV_USER_MAPPER, message);
	}

	public static Object addDataSource(String name, String availableFields)
		throws Exception {

		Message message = new Message();

		Map<String, Object> parameters = new HashMap<>();

		parameters.put("method", "addDataSource");
		parameters.put("name", name);
		parameters.put("availableFields", availableFields);

		message.setValues(parameters);

		return MessageBusUtil.sendSynchronousMessage(
			UserMapperDestinationNames.SCV_USER_MAPPER, message);
	}

	public static void addUserMappingRule(
			long mappingDataSourceId, String modelName, String sourceField,
			String destinationField)
		throws Exception {

		Message message = new Message();

		Map<String, Object> parameters = new HashMap<>();

		parameters.put("method", "addUserMappingRule");
		parameters.put("mappingDataSourceId", mappingDataSourceId);
		parameters.put("modelName", modelName);
		parameters.put("sourceField", sourceField);
		parameters.put("destinationField", destinationField);

		message.setValues(parameters);

		MessageBusUtil.sendMessage(
			UserMapperDestinationNames.SCV_USER_MAPPER, message);
	}

	public static void addData(long mappingDataSourceId, String json)
		throws Exception {

		Message message = new Message();

		Map<String, Object> parameters = new HashMap<>();

		parameters.put("method", "addData");
		parameters.put("mappingDataSourceId", mappingDataSourceId);

		message.setValues(parameters);
		message.setPayload(json);

		MessageBusUtil.sendMessage(
			UserMapperDestinationNames.SCV_USER_MAPPER, message);
	}

	public static void deleteAll() {

		// **** START DEV ONLY ****

		deleteDataSources();
		deleteRules();

		// **** END DEV ONLY ****

	}

	public static void deleteDataSources() {

		// **** START DEV ONLY ****

		List<MappingDataSource> mappingDataSources =
			MappingDataSourceLocalServiceUtil.getMappingDataSources(-1, -1);

		for (MappingDataSource mappingDataSource : mappingDataSources) {
			MappingDataSourceLocalServiceUtil.deleteMappingDataSource(
				mappingDataSource);
		}

		// **** END DEV ONLY ****

	}

	public static void deleteRules() {

		// **** START DEV ONLY ****

		List<UserMappingRule> userMappingRules =
			UserMappingRuleLocalServiceUtil.getUserMappingRules(-1, -1);

		for (UserMappingRule userMappingRule : userMappingRules) {
			UserMappingRuleLocalServiceUtil.deleteUserMappingRule(
				userMappingRule);
		}

		// **** END DEV ONLY ****

	}

}