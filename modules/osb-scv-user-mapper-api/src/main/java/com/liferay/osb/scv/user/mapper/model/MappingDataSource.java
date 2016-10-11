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

package com.liferay.osb.scv.user.mapper.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the MappingDataSource service. Represents a row in the &quot;OSB_SCV_MappingDataSource&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see MappingDataSourceModel
 * @see com.liferay.osb.scv.user.mapper.model.impl.MappingDataSourceImpl
 * @see com.liferay.osb.scv.user.mapper.model.impl.MappingDataSourceModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.scv.user.mapper.model.impl.MappingDataSourceImpl")
@ProviderType
public interface MappingDataSource extends MappingDataSourceModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.scv.user.mapper.model.impl.MappingDataSourceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<MappingDataSource, Long> MAPPING_DATA_SOURCE_ID_ACCESSOR =
		new Accessor<MappingDataSource, Long>() {
			@Override
			public Long get(MappingDataSource mappingDataSource) {
				return mappingDataSource.getMappingDataSourceId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<MappingDataSource> getTypeClass() {
				return MappingDataSource.class;
			}
		};

	public java.util.List<java.lang.String> getTableNames()
		throws java.lang.Exception;

	public java.util.Map<java.lang.String, java.lang.String> getAvailableFields(
		java.lang.String tableName) throws java.lang.Exception;
}