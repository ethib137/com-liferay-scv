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

package com.liferay.osb.scv.user.profile.model;

import com.liferay.portal.kernel.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @author Matthew Kong
 */
public class VersionedDataSourceEntry extends DataSourceEntry {

	public VersionedDataSourceEntry() {
		this(null, null);
	}

	public VersionedDataSourceEntry(DataSourceEntry dataSourceEntry) {
		this(
			dataSourceEntry.getDataSourceEntryId(),
			dataSourceEntry.getSource());
	}

	public VersionedDataSourceEntry(String dataSourceEntryId, String source) {
		super(dataSourceEntryId, source);

		_versioningDataSourceEntry = new DataSourceEntry();

		_versioningDataSourceEntry.addProperty("timestamp", _timestamp);
	}

	@Override
	public void addProperties(JSONObject jsonObject) {
		Iterator<String> iterator = jsonObject.keys();

		while (iterator.hasNext()) {
			String key = iterator.next();

			addProperty(key, jsonObject.get(key));
		}
	}

	@Override
	public void addProperty(String key, Object value) {
		Object currentValue = getProperty(key);

		if (!Objects.equals(value, currentValue)) {
			super.addProperty(key + "_timestamp", _timestamp);

			_versioningDataSourceEntry.addProperty(key, value);
		}

		sourceJSONObject.put(key, value);
	}

	@Override
	public List<String> getKeys() {
		List<String> keys = new ArrayList<>();

		Iterator<String> iterator = sourceJSONObject.keys();

		while (iterator.hasNext()) {
			String key = iterator.next();

			if (!key.endsWith("_timestamp")) {
				keys.add(key);
			}
		}

		return keys;
	}

	public DataSourceEntry getVersioningDataSourceEntry() {
		return _versioningDataSourceEntry;
	}

	public boolean hasChanges() {
		List<String> keys = _versioningDataSourceEntry.getKeys();

		if (keys.size() > 1) {
			return true;
		}

		return false;
	}

	private long _timestamp = System.currentTimeMillis();
	private DataSourceEntry _versioningDataSourceEntry;

}