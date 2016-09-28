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

package com.liferay.osb.scv.user.mapper.sample;

import java.util.List;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public interface DataSource {

	public Map<String, String> getAvailableFields(String tableName);

	public long getDataSourceId();

	public String getName();

	public String getPassword();

	public String getIdField();

	public Map<String, Map<String, String>> getIdFields();

	public Map<String, Map<String, String>> getRequiredFields();

	public List<String> getTableNames();

	public String getType();

	public String getURL();

	public String getUserName();

}