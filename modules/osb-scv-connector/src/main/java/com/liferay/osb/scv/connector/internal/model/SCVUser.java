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

package com.liferay.osb.scv.connector.internal.model;

import com.liferay.portal.kernel.model.User;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Shinn Lok
 */
@Component(immediate = true, service = SCVModel.class)
public class SCVUser extends SCVModel<User> {

	@Override
	public String[] getAvailableFields() {
		return _AVAILABLE_FIELDS;
	}

	@Override
	public List<User> getModels(User user) throws Exception {
		return null;
	}

	@Override
	public String getPrimaryKeyField() {
		return _PRIMARY_KEY_FIELD;
	}

	@Override
	public List<Long> getPrimaryKeys(User user) throws Exception {
		return null;
	}

	@Override
	public String[] getRequiredFields() {
		return _requiredFields;
	}

	@Override
	public boolean isPrimary() {
		return true;
	}

	private static final String[] _AVAILABLE_FIELDS = new String[] {
		"emailAddress", "firstName", "jobTitle", "languageId", "lastName",
		"middleName", "portraitId", "screenName", "status", "timeZoneId"
	};

	private static final String _PRIMARY_KEY_FIELD = "userId";

	private static final String[] _requiredFields =
		new String[] {"emailAddress"};

}