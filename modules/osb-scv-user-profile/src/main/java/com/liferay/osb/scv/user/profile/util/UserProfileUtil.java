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
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Matthew Kong
 */
@Component(immediate = true, service = UserProfileUtil.class)
public class UserProfileUtil {

	public static void addSCVUserProfile(
			Map<String, String> idMap, JSONObject jsonObject)
		throws Exception {

		String id = getId(idMap);

		JSONObject userProfileJSONObject = JSONFactoryUtil.createJSONObject();

		if (Validator.isNotNull(id)) {
			userProfileJSONObject = _userProfileCommandUtil.getSCVUserProfile(
				id);
		}

		mergeIdMap(idMap, userProfileJSONObject);

		Iterator<String> iterator = jsonObject.keys();

		while (iterator.hasNext()) {
			String key = iterator.next();

			userProfileJSONObject.put(key, jsonObject.get(key));
		}

		if (Validator.isNotNull(id)) {
			_userProfileCommandUtil.update(id, userProfileJSONObject);
		}
		else {
			_userProfileCommandUtil.add(null, userProfileJSONObject);
		}
	}

	protected static boolean contains(JSONArray jsonArray, String value) {
		Iterator iterator = jsonArray.iterator();

		while (iterator.hasNext()) {
			if (Objects.equals(value, iterator.next())) {
				return true;
			}
		}

		return false;
	}

	protected static String getId(Map<String, String> idMap) throws Exception {
		for (String matchField : _MATCH_FIELDS) {
			String valuesString = idMap.get(matchField);

			String id = getId(matchField, valuesString);

			if (Validator.isNotNull(id)) {
				return id;
			}
		}

		return null;
	}

	protected static String getId(String searchField, String searchString)
		throws Exception {

		if (Validator.isNull(searchString)) {
			return null;
		}

		String[] idFields = StringUtil.split(searchString);

		for (String idField : idFields) {
			JSONObject searchJSONObject = JSONFactoryUtil.createJSONObject();

			searchJSONObject.put(searchField, idField);

			JSONArray jsonArray = _userProfileCommandUtil.search(
				searchJSONObject);

			if (jsonArray.length() != 0) {
				JSONObject searchResultJSONObject = jsonArray.getJSONObject(0);

				return searchResultJSONObject.getString("id");
			}
		}

		return null;
	}

	protected static void mergeIdMap(
		Map<String, String> idMap, JSONObject userProfileJSONObject) {

		for (String matchField : _MATCH_FIELDS) {
			String valuesString = idMap.get(matchField);

			String[] values = StringUtil.split(valuesString);

			JSONArray jsonArray = userProfileJSONObject.getJSONArray(
				matchField);

			if (jsonArray == null) {
				jsonArray = JSONFactoryUtil.createJSONArray();
			}

			for (String value : values) {
				if (!contains(jsonArray, value)) {
					jsonArray.put(value);
				}
			}

			userProfileJSONObject.put(matchField, jsonArray);
		}
	}

	@Reference(unbind = "-")
	protected void setUserProfileCommandUtil(
		UserProfileCommandUtil userProfileCommandUtil) {

		_userProfileCommandUtil = userProfileCommandUtil;
	}

	private static UserProfileCommandUtil _userProfileCommandUtil;

	private static final String[] _MATCH_FIELDS = {"idField", "searchTerm"};

}