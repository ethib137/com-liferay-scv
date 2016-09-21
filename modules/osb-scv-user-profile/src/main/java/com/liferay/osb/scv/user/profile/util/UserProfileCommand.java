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

/**
 * @author Matthew Kong
 */
public interface UserProfileCommand {

	public void add(String id, JSONObject jsonObject);

	public void delete(String id);

	public void deleteAll();

	public void deleteField(String field);

	public JSONObject getSCVUserProfile(String id) throws Exception;

	public JSONArray search(JSONObject jsonObject) throws Exception;

	public void update(String id, JSONObject jsonObject);

	public void update(String id, String field, Object value);

}