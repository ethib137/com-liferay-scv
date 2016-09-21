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

import com.liferay.osb.scv.user.mapper.internal.messaging.constants.UserMapperDestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.security.SecureRandomUtil;

import java.util.Map;
import java.util.UUID;

/**
 * @author Shinn Lok
 */
public abstract class BaseEvent implements Event {

	@Override
	public void run(Map<String, Object> parameters) {
		String responseId = generateUUID();

		EventManager.addEvent(responseId, this);

		Message message = new Message();

		message.setResponseId(responseId);
		message.setValues(parameters);

		message.setResponseDestinationName(
			UserMapperDestinationNames.SCV_USER_MAPPER);

		MessageBusUtil.sendMessage(
			UserMapperDestinationNames.SCV_SOURCE, message);
	}

	protected String generateUUID() {
		UUID uuid = new UUID(
			SecureRandomUtil.nextLong(), SecureRandomUtil.nextLong());

		return uuid.toString();
	}

}