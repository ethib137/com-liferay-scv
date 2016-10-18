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
import com.liferay.portal.kernel.model.Website;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Shinn Lok
 */
@Component(immediate = true, service = SCVModel.class)
public class SCVWebsite extends SCVModel<Website> {

	@Override
	public String[] getAvailableFields() {
		return _AVAILABLE_FIELDS;
	}

	@Override
	public List<Website> getModels(User user) throws Exception {
		return user.getWebsites();
	}

	@Override
	public String getPrimaryKeyField() {
		return _PRIMARY_KEY_FIELD;
	}

	@Override
	public List<Long> getPrimaryKeys(User user) throws Exception {
		List<Website> websites = user.getWebsites();

		List<Long> websiteIds = new ArrayList<>();

		for (Website website : websites) {
			websiteIds.add(website.getWebsiteId());
		}

		return websiteIds;
	}

	private static final String[] _AVAILABLE_FIELDS = new String[0];

	private static final String _PRIMARY_KEY_FIELD = "websiteId";

}