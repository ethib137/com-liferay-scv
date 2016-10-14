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

package com.liferay.osb.scv.user.mapper.internal.messaging;

import com.liferay.osb.scv.user.mapper.internal.event.BaseEvent;
import com.liferay.osb.scv.user.mapper.internal.event.Event;
import com.liferay.osb.scv.user.mapper.internal.event.EventManager;
import com.liferay.osb.scv.user.mapper.internal.event.GetFieldsEvent;
import com.liferay.osb.scv.user.mapper.internal.event.UpdateUsersEvent;
import com.liferay.osb.scv.user.mapper.internal.event.constants.MappingDataSourceConstants;
import com.liferay.osb.scv.user.mapper.model.MappingDataSource;
import com.liferay.osb.scv.user.mapper.model.UserMappingRule;
import com.liferay.osb.scv.user.mapper.sample.Frequency;
import com.liferay.osb.scv.user.mapper.sample.FrequencyUtil;
import com.liferay.osb.scv.user.mapper.service.MappingDataSourceLocalServiceUtil;
import com.liferay.osb.scv.user.mapper.service.MappingDataSourceServiceUtil;
import com.liferay.osb.scv.user.mapper.service.UserMappingRuleLocalServiceUtil;
import com.liferay.osb.scv.user.mapper.service.UserMappingRuleServiceUtil;
import com.liferay.portal.kernel.json.JSONDeserializer;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class UserMapperMessageListener extends BaseMessageListener {

	@Override
	@SuppressWarnings("unchecked")
	protected void doReceive(Message message) throws Exception {
		String responseId = message.getResponseId();

		Event event = EventManager.getEvent(responseId);

		if (event != null) {
			event.handleResponse(message);

			return;
		}

		String method = message.getString("method");
		long mappingDataSourceId = message.getLong("mappingDataSourceId");

		if (method.equals("addData")) {
			List<UserMappingRule> userMappingRules = null;

			MappingDataSource mappingDataSource =
				MappingDataSourceLocalServiceUtil.fetchMappingDataSource(
					mappingDataSourceId);

			if (mappingDataSource.getType() ==
					MappingDataSourceConstants.CUSTOM) {

				userMappingRules =
					UserMappingRuleLocalServiceUtil.getUserMappingRules(
						mappingDataSourceId);
			}
			else {
				userMappingRules =
					UserMappingRuleLocalServiceUtil.getUserMappingRules(
						mappingDataSourceId, FrequencyUtil.INSTANT);
			}

			event = new UpdateUsersEvent(mappingDataSourceId, userMappingRules);

			event.handleResponse(message);
		}
		else if (method.equals("addDataSource")) {
			String name = message.getString("name");
			String url = message.getString("url");
			String login = message.getString("login");
			String password = message.getString("password");
			long type = message.getLong("type");
			String availableFields = message.getString("availableFields");

			MappingDataSource mappingDataSource =
				MappingDataSourceServiceUtil.addMappingDataSource(
					name, url, login, password, type, availableFields);

			if (type == MappingDataSourceConstants.LIFERAY) {
				GetFieldsEvent getFieldsEvent = new GetFieldsEvent(
						mappingDataSource.getMappingDataSourceId());

				getFieldsEvent.run();

				for (Object[] liferayField : _LIFERAY_FIELDS) {
					UserMappingRuleLocalServiceUtil.addUserMappingRule(
							CompanyThreadLocal.getCompanyId(), 0,
							mappingDataSource.getMappingDataSourceId(),
							String.valueOf(liferayField[0]), 0,
							String.valueOf(liferayField[1]),
							String.valueOf(liferayField[2]), FrequencyUtil.INSTANT,
							(Boolean) liferayField[3]);
				}

				List<UserMappingRule> userMappingRules =
					UserMappingRuleLocalServiceUtil.getUserMappingRules(
						mappingDataSource.getMappingDataSourceId(),
							FrequencyUtil.INSTANT);

				UpdateUsersEvent updateUsersEvent = new UpdateUsersEvent(
					mappingDataSource.getMappingDataSourceId(),
					userMappingRules);

				updateUsersEvent.run();

				Message responseMessage = MessageBusUtil.createResponseMessage(
					message);

				responseMessage.setPayload(mappingDataSource);

				MessageBusUtil.sendMessage(
					responseMessage.getDestinationName(), responseMessage);
			}
			else {
				Message responseMessage = MessageBusUtil.createResponseMessage(
					message);

				responseMessage.setPayload(
					mappingDataSource.getMappingDataSourceId());

				MessageBusUtil.sendMessage(
					responseMessage.getDestinationName(), responseMessage);
			}
		}
		else if (method.equals("addUserMappingRule")) {
			String modelName = message.getString("modelName");
			String sourceField = message.getString("sourceField");
			String destinationField = message.getString("destinationField");

			UserMappingRuleServiceUtil.addUserMappingRule(
				mappingDataSourceId, 0, modelName, sourceField,
				destinationField, FrequencyUtil.ONCE, false);
		}
		else if (method.equals("getFields")) {
			GetFieldsEvent getFieldsEvent = new GetFieldsEvent(
				mappingDataSourceId);

			getFieldsEvent.run();
		}
	}

	private static final Object[][] _LIFERAY_FIELDS = {
			{"User", "screenName", "Screen Name", false},
			{"User", "emailAddress", "Email Address", true},
//		{"User", "portraitId", "Portrait Id", true},
			{"User", "firstName", "First Name", true},
			{"User", "middleName", "Middle Name", false},
			{"User", "lastName", "Last Name", true},
			{"User", "jobTitle", "Job Title", false},
			{"User", "openId", "Open ID", false},
			{"User", "googleUserId", "Google User Id", false},

			{"Contact", "male", "Gender", false},
			{"Contact", "birthday", "Date of Birth", false},
			{"Contact", "facebookSn", "Facebook SN", false},
			{"Contact", "twitterSn", "Twitter SN", false},
			{"Contact", "skypeSn", "Skype SN", false},

			{"Phone", "number", "Number", false},
			{"Phone", "extension", "Extension", false},
			{"Phone", "primary", "Primary", false},

			{"Address", "street1", "Street", false},
			{"Address", "city", "City", false},
			{"Address", "region", "State", false},
			{"Address", "zip", "Zip", false},
			{"Address", "country", "Country", false},
	};

}