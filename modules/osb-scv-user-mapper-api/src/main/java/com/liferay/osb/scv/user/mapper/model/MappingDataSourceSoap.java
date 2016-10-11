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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.scv.user.mapper.service.http.MappingDataSourceServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.scv.user.mapper.service.http.MappingDataSourceServiceSoap
 * @generated
 */
@ProviderType
public class MappingDataSourceSoap implements Serializable {
	public static MappingDataSourceSoap toSoapModel(MappingDataSource model) {
		MappingDataSourceSoap soapModel = new MappingDataSourceSoap();

		soapModel.setMappingDataSourceId(model.getMappingDataSourceId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setUrl(model.getUrl());
		soapModel.setLogin(model.getLogin());
		soapModel.setPassword(model.getPassword());
		soapModel.setType(model.getType());
		soapModel.setAvailableFields(model.getAvailableFields());

		return soapModel;
	}

	public static MappingDataSourceSoap[] toSoapModels(
		MappingDataSource[] models) {
		MappingDataSourceSoap[] soapModels = new MappingDataSourceSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MappingDataSourceSoap[][] toSoapModels(
		MappingDataSource[][] models) {
		MappingDataSourceSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MappingDataSourceSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MappingDataSourceSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MappingDataSourceSoap[] toSoapModels(
		List<MappingDataSource> models) {
		List<MappingDataSourceSoap> soapModels = new ArrayList<MappingDataSourceSoap>(models.size());

		for (MappingDataSource model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MappingDataSourceSoap[soapModels.size()]);
	}

	public MappingDataSourceSoap() {
	}

	public long getPrimaryKey() {
		return _mappingDataSourceId;
	}

	public void setPrimaryKey(long pk) {
		setMappingDataSourceId(pk);
	}

	public long getMappingDataSourceId() {
		return _mappingDataSourceId;
	}

	public void setMappingDataSourceId(long mappingDataSourceId) {
		_mappingDataSourceId = mappingDataSourceId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public String getLogin() {
		return _login;
	}

	public void setLogin(String login) {
		_login = login;
	}

	public String getPassword() {
		return _password;
	}

	public void setPassword(String password) {
		_password = password;
	}

	public long getType() {
		return _type;
	}

	public void setType(long type) {
		_type = type;
	}

	public String getAvailableFields() {
		return _availableFields;
	}

	public void setAvailableFields(String availableFields) {
		_availableFields = availableFields;
	}

	private long _mappingDataSourceId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _url;
	private String _login;
	private String _password;
	private long _type;
	private String _availableFields;
}