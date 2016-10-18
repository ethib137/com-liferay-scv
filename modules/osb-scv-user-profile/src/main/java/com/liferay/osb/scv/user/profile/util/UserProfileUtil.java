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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

	public static void deleteAll() {
		_userProfileCommandUtil.deleteAll();
	}

	public static JSONObject getSCVUserProfile(long scvUserProfileId)
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

			if (mergeRule.equals("modifiedDate")) {
				for (DataSourceEntry dataSourceEntry : dataSourceEntries) {
					if (key.startsWith(UserProfileConstants.FIELD_ASSOCIATED)) {
						String idFieldName = StringUtil.replace(
							key, UserProfileConstants.FIELD_ASSOCIATED,
							StringPool.BLANK);

						int index = idFieldName.lastIndexOf("Ids");

						String tableName = idFieldName.substring(0, index);

						Object currentValue = dataSourceEntry.getProperty(key);

						if (currentValue == null) {
							continue;
						}

						String associatedFieldKey =
							UserProfileConstants.FIELD_ASSOCIATED + tableName;

						JSONArray associatedJSONArray = jsonObject.getJSONArray(
							associatedFieldKey);

						if (associatedJSONArray == null) {
							associatedJSONArray =
								JSONFactoryUtil.createJSONArray();
						}

						value = mergeJSONArray(
							associatedJSONArray, getAssociatedJSONArray(
								Long.valueOf(
									(String)dataSourceEntry.getSystemProperty(
										"mappingDataSourceId")),
								tableName,
								JSONFactoryUtil.createJSONArray(
									currentValue.toString())));

						jsonObject.put(associatedFieldKey, value);
					}
					else {
						long currentTimestamp = dataSourceEntry.getTimestamp(
							key);

						if (currentTimestamp > timestamp) {
							timestamp = currentTimestamp;

							jsonObject.put(
								key, dataSourceEntry.getProperty(key));
						}
					}
				}
			}
			else if (mergeRule.equals("dataSource")) {

				// need dataSourcePriority logic

			}
		}

		return jsonObject;
	}

	public static JSONArray getSCVUserProfiles(
			int from, int size) throws Exception {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<Long> scvUserProfileIds = _userProfileCommandUtil.search(
			"scvUserProfileId", from, size,
			UserProfileConstants.DOCUMENT_TYPE_USER_PROFILE);

		for (long scvUserProfileId : scvUserProfileIds) {
			jsonArray.put(getSCVUserProfile(scvUserProfileId));
		}

		return jsonArray;
	}

	public static JSONArray getSCVVersioning(
			long scvUserProfileId, String field)
		throws Exception {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<DataSourceEntry> dataSourceEntries =
			_userProfileCommandUtil.search(
				"scvUserProfileId", scvUserProfileId,
				UserProfileConstants.DOCUMENT_TYPE_VERSIONING);

		// TODO Improve the query in the future on the database end,
		// so we don't have to filter out the undesired field

		for (DataSourceEntry dataSourceEntry : dataSourceEntries) {
			List<String> keys = dataSourceEntry.getKeys();

			for (String key : keys) {
				if (key.equals(field)) {
					JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

					jsonObject.put(field, dataSourceEntry.getProperty(field));
					jsonObject.put(
						"mappingDataSourceId",
						dataSourceEntry.getSystemProperty(
							"mappingDataSourceId"));
					jsonObject.put(
						"timestamp", dataSourceEntry.getTimestamp("timestamp"));

					jsonArray.put(jsonObject);
				}
			}
		}

		return jsonArray;
	}

	public static void updateDataSourceEntries(
			long mappingDataSourceId, Map<String, List<String>> searchTermFieldNameMap,
			JSONObject jsonObject)
		throws Exception {

		List<String> tableNames = new ArrayList<>();
		List<String> pkFields = new ArrayList<>();

		Iterator<String> iterator = jsonObject.keys();

		while (iterator.hasNext()) {
			String tableName = iterator.next();

			tableNames.add(tableName);

			if (!StringUtil.equalsIgnoreCase(tableName, "user")) {
				pkFields.add(StringUtil.lowerCase(tableName) + "Ids");
			}
		}

		for (String tableName : tableNames) {
			List<String> searchTermFieldNames = searchTermFieldNameMap.get(
				tableName);

			JSONArray jsonArray = jsonObject.getJSONArray(tableName);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject entityJSONObject = jsonArray.getJSONObject(i);

				String id = entityJSONObject.getString(
					StringUtil.lowerCase(tableName) + "Id");

				List<String> searchTerms = new ArrayList<>();

				if (!ListUtil.isEmpty(searchTermFieldNames)) {
					for (String searchTermFieldName : searchTermFieldNames) {
						searchTerms.add(
							entityJSONObject.getString(searchTermFieldName));
					}

					for (String pkField : pkFields) {
						Object value = entityJSONObject.get(pkField);

						if (value == null) {
							continue;
						}

						entityJSONObject.put(
							UserProfileConstants.FIELD_ASSOCIATED + pkField,
							value);
						entityJSONObject.remove(pkField);
					}
				}

				updateDataSourceEntry(
					mappingDataSourceId, tableName, id, searchTerms, entityJSONObject);
			}
		}
	}

	public static void updateDataSourceEntry(
			long mappingDataSourceId, String tableName, String id,
			List<String> searchTerms, JSONObject jsonObject)
		throws Exception {

		String documentType = UserProfileConstants.DOCUMENT_TYPE_USER_PROFILE;

		if (searchTerms.isEmpty()) {
			documentType = UserProfileConstants.DOCUMENT_TYPE_ASSOCIATION;
		}

		VersionedDataSourceEntry versionedDataSourceEntry =
			getVersionedDataSourceEntry(
				mappingDataSourceId, tableName, id, searchTerms, documentType);

		long scvUserProfileId = 0;

		if (documentType == UserProfileConstants.DOCUMENT_TYPE_USER_PROFILE) {
			scvUserProfileId = getSCVUserProfileId(searchTerms);
		}

		versionedDataSourceEntry.addProperties(jsonObject);

		if (Validator.isNull(versionedDataSourceEntry.getDataSourceEntryId())) {
			if (documentType ==
					UserProfileConstants.DOCUMENT_TYPE_USER_PROFILE) {

				versionedDataSourceEntry.addSystemProperty(
					"scvUserProfileId", scvUserProfileId);
			}

			_userProfileCommandUtil.add(versionedDataSourceEntry, documentType);
		}
		else {
			_userProfileCommandUtil.update(
				versionedDataSourceEntry, documentType);
		}

		if (!versionedDataSourceEntry.hasChanges()) {
			return;
		}

		DataSourceEntry versioningDataSourceEntry =
			versionedDataSourceEntry.getVersioningDataSourceEntry();

		versioningDataSourceEntry.addSystemProperty(
			"mappingDataSourceId", mappingDataSourceId);

		if (documentType == UserProfileConstants.DOCUMENT_TYPE_USER_PROFILE) {
			versioningDataSourceEntry.addSystemProperty(
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

	protected static JSONArray getAssociatedJSONArray(
			long mappingDataSourceId, String tableName,
			JSONArray idFieldsJSONArray)
		throws Exception {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		JSONObject searchJSONObject = JSONFactoryUtil.createJSONObject();

		searchJSONObject.put("mappingDataSourceId", mappingDataSourceId);
		searchJSONObject.put("tableName", tableName);

		for (int i = 0; i < idFieldsJSONArray.length(); i++) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			String idField = idFieldsJSONArray.getString(i);

			searchJSONObject.put("id", idField);

			List<DataSourceEntry> dataSourceEntries =
				_userProfileCommandUtil.search(
					searchJSONObject,
					UserProfileConstants.DOCUMENT_TYPE_ASSOCIATION);

			if (dataSourceEntries.isEmpty()) {
				continue;
			}

			DataSourceEntry dataSourceEntry = dataSourceEntries.get(0);

			for (String key : dataSourceEntry.getKeys()) {
				jsonObject.put(key, dataSourceEntry.getProperty(key));
			}

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	protected static String getMergeRule(String key) {
		return "modifiedDate";
	}

	protected static long getSCVUserProfileId(List<String> searchTerms)
		throws Exception {

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

			long scvUserProfileId = GetterUtil.getLong(
				dataSourceEntry.getSystemProperty("scvUserProfileId"));

			if (Validator.isNotNull(scvUserProfileId)) {
				return scvUserProfileId;
			}
		}

		return CounterLocalServiceUtil.increment();
	}

	protected static VersionedDataSourceEntry getVersionedDataSourceEntry(
			long mappingDataSourceId, String tableName, String id,
			List<String> searchTerms, String documentType)
		throws Exception {

		DataSourceEntry dataSourceEntry = null;

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("mappingDataSourceId", mappingDataSourceId);
		jsonObject.put("id", id);
		jsonObject.put("tableName", StringUtil.lowerCase(tableName));

		List<DataSourceEntry> dataSourceEntries =
			_userProfileCommandUtil.search(jsonObject, documentType);

		if (!dataSourceEntries.isEmpty()) {
			dataSourceEntry = dataSourceEntries.get(0);
		}
		else {
			dataSourceEntry = new DataSourceEntry();

			dataSourceEntry.addSystemProperties(jsonObject);
		}

		if (!searchTerms.isEmpty()) {
			JSONArray searchTermsJSONArray = JSONFactoryUtil.createJSONArray();

			for (String searchTerm : searchTerms) {
				searchTermsJSONArray.put(searchTerm);
			}

			dataSourceEntry.addSystemProperty(
				"searchTerms", searchTermsJSONArray);
		}

		return new VersionedDataSourceEntry(dataSourceEntry);
	}

	protected static JSONArray mergeJSONArray(
		JSONArray jsonArray1, JSONArray jsonArray2) {

		for (int i = 0; i < jsonArray2.length(); i++) {
			jsonArray1.put(jsonArray2.get(i));
		}

		return jsonArray1;
	}

	@Reference(unbind = "-")
	protected void setUserProfileCommandUtil(
		UserProfileCommandUtil userProfileCommandUtil) {

		_userProfileCommandUtil = userProfileCommandUtil;
	}

	private static UserProfileCommandUtil _userProfileCommandUtil;

}