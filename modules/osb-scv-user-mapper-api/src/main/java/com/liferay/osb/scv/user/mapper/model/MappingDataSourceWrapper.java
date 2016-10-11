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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link MappingDataSource}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MappingDataSource
 * @generated
 */
@ProviderType
public class MappingDataSourceWrapper implements MappingDataSource,
	ModelWrapper<MappingDataSource> {
	public MappingDataSourceWrapper(MappingDataSource mappingDataSource) {
		_mappingDataSource = mappingDataSource;
	}

	@Override
	public Class<?> getModelClass() {
		return MappingDataSource.class;
	}

	@Override
	public String getModelClassName() {
		return MappingDataSource.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mappingDataSourceId", getMappingDataSourceId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("url", getUrl());
		attributes.put("login", getLogin());
		attributes.put("password", getPassword());
		attributes.put("type", getType());
		attributes.put("availableFields", getAvailableFields());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mappingDataSourceId = (Long)attributes.get("mappingDataSourceId");

		if (mappingDataSourceId != null) {
			setMappingDataSourceId(mappingDataSourceId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		String login = (String)attributes.get("login");

		if (login != null) {
			setLogin(login);
		}

		String password = (String)attributes.get("password");

		if (password != null) {
			setPassword(password);
		}

		Long type = (Long)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String availableFields = (String)attributes.get("availableFields");

		if (availableFields != null) {
			setAvailableFields(availableFields);
		}
	}

	@Override
	public MappingDataSource toEscapedModel() {
		return new MappingDataSourceWrapper(_mappingDataSource.toEscapedModel());
	}

	@Override
	public MappingDataSource toUnescapedModel() {
		return new MappingDataSourceWrapper(_mappingDataSource.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _mappingDataSource.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _mappingDataSource.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _mappingDataSource.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _mappingDataSource.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<MappingDataSource> toCacheModel() {
		return _mappingDataSource.toCacheModel();
	}

	@Override
	public int compareTo(MappingDataSource mappingDataSource) {
		return _mappingDataSource.compareTo(mappingDataSource);
	}

	@Override
	public int hashCode() {
		return _mappingDataSource.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _mappingDataSource.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new MappingDataSourceWrapper((MappingDataSource)_mappingDataSource.clone());
	}

	/**
	* Returns the available fields of this mapping data source.
	*
	* @return the available fields of this mapping data source
	*/
	@Override
	public java.lang.String getAvailableFields() {
		return _mappingDataSource.getAvailableFields();
	}

	/**
	* Returns the login of this mapping data source.
	*
	* @return the login of this mapping data source
	*/
	@Override
	public java.lang.String getLogin() {
		return _mappingDataSource.getLogin();
	}

	/**
	* Returns the name of this mapping data source.
	*
	* @return the name of this mapping data source
	*/
	@Override
	public java.lang.String getName() {
		return _mappingDataSource.getName();
	}

	/**
	* Returns the password of this mapping data source.
	*
	* @return the password of this mapping data source
	*/
	@Override
	public java.lang.String getPassword() {
		return _mappingDataSource.getPassword();
	}

	/**
	* Returns the url of this mapping data source.
	*
	* @return the url of this mapping data source
	*/
	@Override
	public java.lang.String getUrl() {
		return _mappingDataSource.getUrl();
	}

	/**
	* Returns the user name of this mapping data source.
	*
	* @return the user name of this mapping data source
	*/
	@Override
	public java.lang.String getUserName() {
		return _mappingDataSource.getUserName();
	}

	/**
	* Returns the user uuid of this mapping data source.
	*
	* @return the user uuid of this mapping data source
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _mappingDataSource.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _mappingDataSource.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _mappingDataSource.toXmlString();
	}

	/**
	* Returns the create date of this mapping data source.
	*
	* @return the create date of this mapping data source
	*/
	@Override
	public Date getCreateDate() {
		return _mappingDataSource.getCreateDate();
	}

	/**
	* Returns the modified date of this mapping data source.
	*
	* @return the modified date of this mapping data source
	*/
	@Override
	public Date getModifiedDate() {
		return _mappingDataSource.getModifiedDate();
	}

	@Override
	public java.util.List<java.lang.String> getTableNames()
		throws java.lang.Exception {
		return _mappingDataSource.getTableNames();
	}

	@Override
	public Map<java.lang.String, java.lang.String> getAvailableFields(
		java.lang.String tableName) throws java.lang.Exception {
		return _mappingDataSource.getAvailableFields(tableName);
	}

	/**
	* Returns the company ID of this mapping data source.
	*
	* @return the company ID of this mapping data source
	*/
	@Override
	public long getCompanyId() {
		return _mappingDataSource.getCompanyId();
	}

	/**
	* Returns the mapping data source ID of this mapping data source.
	*
	* @return the mapping data source ID of this mapping data source
	*/
	@Override
	public long getMappingDataSourceId() {
		return _mappingDataSource.getMappingDataSourceId();
	}

	/**
	* Returns the primary key of this mapping data source.
	*
	* @return the primary key of this mapping data source
	*/
	@Override
	public long getPrimaryKey() {
		return _mappingDataSource.getPrimaryKey();
	}

	/**
	* Returns the type of this mapping data source.
	*
	* @return the type of this mapping data source
	*/
	@Override
	public long getType() {
		return _mappingDataSource.getType();
	}

	/**
	* Returns the user ID of this mapping data source.
	*
	* @return the user ID of this mapping data source
	*/
	@Override
	public long getUserId() {
		return _mappingDataSource.getUserId();
	}

	@Override
	public void persist() {
		_mappingDataSource.persist();
	}

	/**
	* Sets the available fields of this mapping data source.
	*
	* @param availableFields the available fields of this mapping data source
	*/
	@Override
	public void setAvailableFields(java.lang.String availableFields) {
		_mappingDataSource.setAvailableFields(availableFields);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_mappingDataSource.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this mapping data source.
	*
	* @param companyId the company ID of this mapping data source
	*/
	@Override
	public void setCompanyId(long companyId) {
		_mappingDataSource.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this mapping data source.
	*
	* @param createDate the create date of this mapping data source
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_mappingDataSource.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_mappingDataSource.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_mappingDataSource.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_mappingDataSource.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the login of this mapping data source.
	*
	* @param login the login of this mapping data source
	*/
	@Override
	public void setLogin(java.lang.String login) {
		_mappingDataSource.setLogin(login);
	}

	/**
	* Sets the mapping data source ID of this mapping data source.
	*
	* @param mappingDataSourceId the mapping data source ID of this mapping data source
	*/
	@Override
	public void setMappingDataSourceId(long mappingDataSourceId) {
		_mappingDataSource.setMappingDataSourceId(mappingDataSourceId);
	}

	/**
	* Sets the modified date of this mapping data source.
	*
	* @param modifiedDate the modified date of this mapping data source
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_mappingDataSource.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this mapping data source.
	*
	* @param name the name of this mapping data source
	*/
	@Override
	public void setName(java.lang.String name) {
		_mappingDataSource.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_mappingDataSource.setNew(n);
	}

	/**
	* Sets the password of this mapping data source.
	*
	* @param password the password of this mapping data source
	*/
	@Override
	public void setPassword(java.lang.String password) {
		_mappingDataSource.setPassword(password);
	}

	/**
	* Sets the primary key of this mapping data source.
	*
	* @param primaryKey the primary key of this mapping data source
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_mappingDataSource.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_mappingDataSource.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the type of this mapping data source.
	*
	* @param type the type of this mapping data source
	*/
	@Override
	public void setType(long type) {
		_mappingDataSource.setType(type);
	}

	/**
	* Sets the url of this mapping data source.
	*
	* @param url the url of this mapping data source
	*/
	@Override
	public void setUrl(java.lang.String url) {
		_mappingDataSource.setUrl(url);
	}

	/**
	* Sets the user ID of this mapping data source.
	*
	* @param userId the user ID of this mapping data source
	*/
	@Override
	public void setUserId(long userId) {
		_mappingDataSource.setUserId(userId);
	}

	/**
	* Sets the user name of this mapping data source.
	*
	* @param userName the user name of this mapping data source
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_mappingDataSource.setUserName(userName);
	}

	/**
	* Sets the user uuid of this mapping data source.
	*
	* @param userUuid the user uuid of this mapping data source
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_mappingDataSource.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MappingDataSourceWrapper)) {
			return false;
		}

		MappingDataSourceWrapper mappingDataSourceWrapper = (MappingDataSourceWrapper)obj;

		if (Objects.equals(_mappingDataSource,
					mappingDataSourceWrapper._mappingDataSource)) {
			return true;
		}

		return false;
	}

	@Override
	public MappingDataSource getWrappedModel() {
		return _mappingDataSource;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _mappingDataSource.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _mappingDataSource.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_mappingDataSource.resetOriginalValues();
	}

	private final MappingDataSource _mappingDataSource;
}