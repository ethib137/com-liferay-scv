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

import com.liferay.osb.scv.user.profile.model.DataSourceEntry;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Matthew Kong
 */
@Component(immediate = true, service = UserProfileCommandUtil.class)
public class UserProfileCommandUtil {

	public static void add(
		DataSourceEntry dataSourceEntry, String documentType) {

		_userProfileCommand.add(dataSourceEntry, documentType);
	}

	public static void delete(String id, String documentType) {
		_userProfileCommand.delete(id, documentType);
	}

	public static void deleteAll() {
		_userProfileCommand.deleteAll();
	}

	public static void deleteField(String field, String documentType) {
		_userProfileCommand.deleteField(field, documentType);
	}

	public static DataSourceEntry getDataSourceEntry(
		String getDataSourceEntryId, String documentType) {

		return _userProfileCommand.getDataSourceEntry(
			getDataSourceEntryId, documentType);
	}

	public static List<DataSourceEntry> search(
		JSONObject jsonObject, String documentType) {

		return _userProfileCommand.search(jsonObject, documentType);
	}

	public static void update(
		DataSourceEntry dataSourceEntry, String documentType) {

		_userProfileCommand.update(dataSourceEntry, documentType);
	}

	public static void update(
		String id, String field, Object value, String documentType) {

		_userProfileCommand.update(id, field, value, documentType);
	}

	public List<DataSourceEntry> search(
		String key, String value, String documentType) {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(key, value);

		return _userProfileCommand.search(jsonObject, documentType);
	}

	@Reference(unbind = "-")
	protected void setUserProfileCommand(
		UserProfileCommand userProfileCommand) {

		_userProfileCommand = userProfileCommand;
	}

	private static UserProfileCommand _userProfileCommand;

}