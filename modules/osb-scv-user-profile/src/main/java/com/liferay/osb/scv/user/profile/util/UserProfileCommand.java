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
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;

/**
 * @author Matthew Kong
 */
public interface UserProfileCommand {

	public void add(DataSourceEntry dataSourceEntry, String documentType);

	public void delete(String dataSourceEntryId, String documentType);

	public void deleteAll();

	public void deleteField(String field, String documentType);

	public DataSourceEntry getDataSourceEntry(
		String dataSourceEntryId, String documentType);

	public List<String> search(String field, String documentType);

	public List<DataSourceEntry> search(
		JSONObject jsonObject, String documentType);

	public void update(DataSourceEntry dataSourceEntry, String documentType);

	public void update(
		String dataSourceEntryId, String field, Object value,
		String documentType);

}