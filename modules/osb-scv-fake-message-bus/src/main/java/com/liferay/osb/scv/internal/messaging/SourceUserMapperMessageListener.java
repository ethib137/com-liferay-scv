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

package com.liferay.osb.scv.internal.messaging;

import com.liferay.osb.scv.internal.util.JSONWebServiceClientUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class SourceUserMapperMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		try {
			Map<String, Object> parameters = message.getValues();

			long dataSourceId = GetterUtil.getLong(
				parameters.get("dataSourceId"));
			String method = String.valueOf(parameters.get("method"));

			Map<String, List<String>> fields = (Map)parameters.get("fields");

			String fieldsString = null;

			if (fields != null) {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

				for (Map.Entry<String, List<String>> entrySet :
						fields.entrySet()) {

					jsonObject.put(
						entrySet.getKey(),
						StringUtil.merge(entrySet.getValue()));
				}

				fieldsString = jsonObject.toString();
			}

			sendResponse(
				message.getResponseDestinationName(), message.getResponseId(),
				dataSourceId, method, fieldsString);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void sendResponse(
			String destinationName, String responseId, long dataSourceId,
			String method, String fields)
		throws Exception {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("fields", fields);

		String response = JSONWebServiceClientUtil.doGet(
			dataSourceId, method, parameters);

		Message message = new Message();

		Map<String, Object> values = new HashMap<>();

		values.put("method", method);

		message.setValues(values);

		message.setPayload(response);
		message.setResponseId(responseId);

		MessageBusUtil.sendMessage(destinationName, message);
	}

}