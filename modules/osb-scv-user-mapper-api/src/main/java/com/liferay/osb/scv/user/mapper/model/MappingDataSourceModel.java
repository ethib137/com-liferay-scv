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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the MappingDataSource service. Represents a row in the &quot;OSB_SCV_MappingDataSource&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.osb.scv.user.mapper.model.impl.MappingDataSourceModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.osb.scv.user.mapper.model.impl.MappingDataSourceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MappingDataSource
 * @see com.liferay.osb.scv.user.mapper.model.impl.MappingDataSourceImpl
 * @see com.liferay.osb.scv.user.mapper.model.impl.MappingDataSourceModelImpl
 * @generated
 */
@ProviderType
public interface MappingDataSourceModel extends AuditedModel,
	BaseModel<MappingDataSource>, ShardedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a mapping data source model instance should use the {@link MappingDataSource} interface instead.
	 */

	/**
	 * Returns the primary key of this mapping data source.
	 *
	 * @return the primary key of this mapping data source
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this mapping data source.
	 *
	 * @param primaryKey the primary key of this mapping data source
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mapping data source ID of this mapping data source.
	 *
	 * @return the mapping data source ID of this mapping data source
	 */
	public long getMappingDataSourceId();

	/**
	 * Sets the mapping data source ID of this mapping data source.
	 *
	 * @param mappingDataSourceId the mapping data source ID of this mapping data source
	 */
	public void setMappingDataSourceId(long mappingDataSourceId);

	/**
	 * Returns the company ID of this mapping data source.
	 *
	 * @return the company ID of this mapping data source
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this mapping data source.
	 *
	 * @param companyId the company ID of this mapping data source
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this mapping data source.
	 *
	 * @return the user ID of this mapping data source
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this mapping data source.
	 *
	 * @param userId the user ID of this mapping data source
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this mapping data source.
	 *
	 * @return the user uuid of this mapping data source
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this mapping data source.
	 *
	 * @param userUuid the user uuid of this mapping data source
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this mapping data source.
	 *
	 * @return the user name of this mapping data source
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this mapping data source.
	 *
	 * @param userName the user name of this mapping data source
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this mapping data source.
	 *
	 * @return the create date of this mapping data source
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this mapping data source.
	 *
	 * @param createDate the create date of this mapping data source
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this mapping data source.
	 *
	 * @return the modified date of this mapping data source
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this mapping data source.
	 *
	 * @param modifiedDate the modified date of this mapping data source
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the name of this mapping data source.
	 *
	 * @return the name of this mapping data source
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this mapping data source.
	 *
	 * @param name the name of this mapping data source
	 */
	public void setName(String name);

	/**
	 * Returns the url of this mapping data source.
	 *
	 * @return the url of this mapping data source
	 */
	@AutoEscape
	public String getUrl();

	/**
	 * Sets the url of this mapping data source.
	 *
	 * @param url the url of this mapping data source
	 */
	public void setUrl(String url);

	/**
	 * Returns the login of this mapping data source.
	 *
	 * @return the login of this mapping data source
	 */
	@AutoEscape
	public String getLogin();

	/**
	 * Sets the login of this mapping data source.
	 *
	 * @param login the login of this mapping data source
	 */
	public void setLogin(String login);

	/**
	 * Returns the password of this mapping data source.
	 *
	 * @return the password of this mapping data source
	 */
	@AutoEscape
	public String getPassword();

	/**
	 * Sets the password of this mapping data source.
	 *
	 * @param password the password of this mapping data source
	 */
	public void setPassword(String password);

	/**
	 * Returns the type of this mapping data source.
	 *
	 * @return the type of this mapping data source
	 */
	public long getType();

	/**
	 * Sets the type of this mapping data source.
	 *
	 * @param type the type of this mapping data source
	 */
	public void setType(long type);

	/**
	 * Returns the available fields of this mapping data source.
	 *
	 * @return the available fields of this mapping data source
	 */
	@AutoEscape
	public String getAvailableFields();

	/**
	 * Sets the available fields of this mapping data source.
	 *
	 * @param availableFields the available fields of this mapping data source
	 */
	public void setAvailableFields(String availableFields);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(MappingDataSource mappingDataSource);

	@Override
	public int hashCode();

	@Override
	public CacheModel<MappingDataSource> toCacheModel();

	@Override
	public MappingDataSource toEscapedModel();

	@Override
	public MappingDataSource toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}