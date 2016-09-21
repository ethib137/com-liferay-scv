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

package com.liferay.osb.scv.user.profile.util;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Matthew Kong
 */
@Component(immediate = true, service = UserProfileCommandUtil.class)
public class UserProfileCommandUtil {

	public static void delete(String id) {
		_userProfileCommand.delete(id);
	}

	public static void deleteAll() {
		_userProfileCommand.deleteAll();
	}

	public static void deleteField(String field) {
		_userProfileCommand.deleteField(field);
	}

	public static JSONObject getSCVUserProfile(String id) throws Exception {
		return _userProfileCommand.getSCVUserProfile(id);
	}

	public static JSONArray search(JSONObject jsonObject) throws Exception {
		return _userProfileCommand.search(jsonObject);
	}

	public static void update(String id, JSONObject jsonObject) {
		_userProfileCommand.update(id, jsonObject);
	}

	public static void update(String id, String field, Object value) {
		_userProfileCommand.update(id, field, value);
	}

	public void add(String id, JSONObject jsonObject) {
		_userProfileCommand.add(id, jsonObject);
	}

	@Reference(unbind = "-")
	protected void setUserProfileCommand(
		UserProfileCommand userProfileCommand) {

		_userProfileCommand = userProfileCommand;
	}

	private static UserProfileCommand _userProfileCommand;

}