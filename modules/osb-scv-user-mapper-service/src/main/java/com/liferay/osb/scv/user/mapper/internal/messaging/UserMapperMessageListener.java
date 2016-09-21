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

import com.liferay.osb.scv.user.mapper.internal.event.Event;
import com.liferay.osb.scv.user.mapper.internal.event.EventManager;
import com.liferay.osb.scv.user.mapper.internal.event.constants.EventConstants;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Map;

/**
 * @author Shinn Lok
 */
public class UserMapperMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		String responseId = message.getResponseId();

		Event event = EventManager.getEvent(responseId);

		event.handleResponse(message);
	}

}