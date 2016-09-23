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

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.osb.scv.user.profile.model.DataSourceEntry;
import com.liferay.osb.scv.user.profile.model.VersionedDataSourceEntry;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Matthew Kong
 */
@Component(
	immediate = true,
	property = {
		"json.web.service.context.name=SCVUserProfileUtil",
		"json.web.service.context.path=SCVUserProfileUtil"
	},
	service = UserProfileUtil.class
)
@JSONWebService
public class UserProfileUtil {

	public static JSONArray getSCVUserProfiles() {

	}

	private static

	public static JSONObject getSCVUserProfile(String scvUserProfileId)
		throws Exception {

		List<DataSourceEntry> dataSourceEntries =
			_userProfileCommandUtil.search(
				"scvUserProfileId", scvUserProfileId,
				UserProfileConstants.DOCUMENT_TYPE_USER_PROFILE);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		Set<String> keys = getAllKeys(dataSourceEntries);

		for (String key : keys) {
			Object value = null;
			long timestamp = 0;

			String mergeRule = getMergeRule(key);

			if (Objects.equals(mergeRule, "modifiedDate")) {
				for (DataSourceEntry dataSourceEntry : dataSourceEntries) {
					long currentTimestamp = (long)dataSourceEntry.getTimestamp(
						key);

					if (currentTimestamp > timestamp) {
						timestamp = currentTimestamp;
						value = dataSourceEntry.getProperty(key);
					}
				}
			}
			else if (Objects.equals(mergeRule, "dataSource")) {

				// need dataSourcePriority logic

			}

			jsonObject.put(key, value);
		}

		return jsonObject;
	}

	public static void updateDataSourceEntries(
			long dataSourceId, String idFieldName, JSONObject jsonObject)
		throws Exception {

		Iterator<String> iterator = jsonObject.keys();

		while (iterator.hasNext()) {
			String tableName = iterator.next();

			JSONArray jsonArray = jsonObject.getJSONArray(tableName);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject entityJSONObject = jsonArray.getJSONObject(i);

				Iterator<String> entityIterator = entityJSONObject.keys();

				while (entityIterator.hasNext()) {
					String field = entityIterator.next();


				}
			}



		}


	}

	public static void updateDataSourceEntry(
			long dataSourceId, String idField, String searchTermsString,
			JSONObject jsonObject)
		throws Exception {

		String documentType = UserProfileConstants.DOCUMENT_TYPE_USER_PROFILE;

		if (Validator.isNull(searchTermsString)) {
			documentType = UserProfileConstants.DOCUMENT_TYPE_ASSOCIATION;
		}

		VersionedDataSourceEntry versionedDataSourceEntry =
			getVersionedDataSourceEntry(
				dataSourceId, idField, searchTermsString, documentType);

		String scvUserProfileId = null;

		if (documentType == UserProfileConstants.DOCUMENT_TYPE_USER_PROFILE) {
			scvUserProfileId = getSCVUserProfileId(searchTermsString);
		}

		versionedDataSourceEntry.addProperties(jsonObject);

		if (Validator.isNull(versionedDataSourceEntry.getDataSourceEntryId())) {
			if (documentType ==
					UserProfileConstants.DOCUMENT_TYPE_USER_PROFILE) {

				versionedDataSourceEntry.addProperty(
					"scvUserProfileId", scvUserProfileId);
			}

			_userProfileCommandUtil.add(
				versionedDataSourceEntry,
				UserProfileConstants.DOCUMENT_TYPE_USER_PROFILE);
		}
		else {
			_userProfileCommandUtil.update(
				versionedDataSourceEntry,
				UserProfileConstants.DOCUMENT_TYPE_USER_PROFILE);
		}

		if (!versionedDataSourceEntry.hasChanges()) {
			return;
		}

		DataSourceEntry versioningDataSourceEntry =
			versionedDataSourceEntry.getVersioningDataSourceEntry();

		versioningDataSourceEntry.addProperty("dataSourceId", dataSourceId);

		if (documentType == UserProfileConstants.DOCUMENT_TYPE_USER_PROFILE) {
			versioningDataSourceEntry.addProperty(
				"scvUserProfileId", scvUserProfileId);
		}

		_userProfileCommandUtil.add(
			versioningDataSourceEntry,
			UserProfileConstants.DOCUMENT_TYPE_VERSIONING);
	}

	protected static Set<String> getAllKeys(
		List<DataSourceEntry> dataSourceEntries) {

		Set<String> keys = new HashSet<>();

		for (DataSourceEntry dataSourceEntry : dataSourceEntries) {
			keys.addAll(dataSourceEntry.getKeys());
		}

		return keys;
	}

	protected static String getMergeRule(String key) {
		return "modifiedDate";
	}

	protected static VersionedDataSourceEntry getVersionedDataSourceEntry(
			long dataSourceId, String idField, String searchTermsString,
			String documentType)
		throws Exception {

		DataSourceEntry dataSourceEntry = null;

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("dataSourceId", dataSourceId);
		jsonObject.put("idField", idField);

		List<DataSourceEntry> dataSourceEntries =
			_userProfileCommandUtil.search(jsonObject, documentType);

		if (!dataSourceEntries.isEmpty()) {
			dataSourceEntry = dataSourceEntries.get(0);
		}
		else {
			dataSourceEntry = new DataSourceEntry();

			dataSourceEntry.addProperties(jsonObject);
		}

		if (Validator.isNotNull(searchTermsString)) {
			JSONArray searchTermsJSONArray = JSONFactoryUtil.createJSONArray();

			String[] searchTerms = StringUtil.split(searchTermsString);

			for (String searchTerm : searchTerms) {
				searchTermsJSONArray.put(searchTerm);
			}

			dataSourceEntry.addProperty("searchTerms", searchTermsJSONArray);
		}

		return new VersionedDataSourceEntry(dataSourceEntry);
	}

	protected static String getSCVUserProfileId(String searchTermString)
		throws Exception {

		String[] searchTerms = StringUtil.split(searchTermString);

		for (String searchTerm : searchTerms) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("searchTerms", searchTerm);

			List<DataSourceEntry> dataSourceEntries =
				_userProfileCommandUtil.search(
					jsonObject,
					UserProfileConstants.DOCUMENT_TYPE_USER_PROFILE);

			if (dataSourceEntries.isEmpty()) {
				continue;
			}

			DataSourceEntry dataSourceEntry = dataSourceEntries.get(0);

			String scvUserProfileId = (String)dataSourceEntry.getProperty(
				"scvUserProfileId");

			if (Validator.isNotNull(scvUserProfileId)) {
				return scvUserProfileId;
			}
		}

		return String.valueOf(CounterLocalServiceUtil.increment());
	}

	@Reference(unbind = "-")
	protected void setUserProfileCommandUtil(
		UserProfileCommandUtil userProfileCommandUtil) {

		_userProfileCommandUtil = userProfileCommandUtil;
	}

	private static UserProfileCommandUtil _userProfileCommandUtil;

}