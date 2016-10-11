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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class EventManager {

	public static synchronized void addEvent(String eventId, Event event) {
		_events.put(eventId, event);
	}

	public static synchronized Event getEvent(String eventId) {
		return _events.remove(eventId);
	}

	private static final Map<String, Event> _events = new HashMap<>();

}