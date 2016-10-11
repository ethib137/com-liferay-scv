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

package com.liferay.osb.scv.connector.internal;

import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.User;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * ********************** DEV ONLY  **********************
 */
public class MappingDataSourceUtil {


	public static void setMappingDataSourceId(long mappingDataSourceId) {

			// **** START DEV ONLY ****

			_MAPPPING_DATA_SOURCE_ID = mappingDataSourceId;

			// **** END DEV ONLY ****

	}

	public static long getMappingDataSourceId() {
		return _MAPPPING_DATA_SOURCE_ID;
	}

	private static long _MAPPPING_DATA_SOURCE_ID;

}