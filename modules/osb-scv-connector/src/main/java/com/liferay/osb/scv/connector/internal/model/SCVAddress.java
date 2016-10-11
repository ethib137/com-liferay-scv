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

import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.User;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Shinn Lok
 */
@Component(immediate = true, service = SCVModel.class)
public class SCVAddress extends SCVModel<Address> {

	@Override
	public String[] getAvailableFields() {
		return _availableFields;
	}

	@Override
	public List<Address> getModels(User user) throws Exception {
		return user.getAddresses();
	}

	@Override
	public String getPrimaryKeyField() {
		return _primaryKeyField;
	}

	@Override
	public List<Long> getPrimaryKeys(User user) throws Exception {
		List<Address> addresses = user.getAddresses();

		List<Long> addressIds = new ArrayList<>();

		for (Address address : addresses) {
			addressIds.add(address.getAddressId());
		}

		return addressIds;
	}

	private static String[] _availableFields =
		new String[] {
			"city", "countryId", "street1", "street2", "street3", "zip"
		};
	private static String _primaryKeyField = "addressId";

}