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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.scv.user.mapper.service.http.UserMappingRuleServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.scv.user.mapper.service.http.UserMappingRuleServiceSoap
 * @generated
 */
@ProviderType
public class UserMappingRuleSoap implements Serializable {
	public static UserMappingRuleSoap toSoapModel(UserMappingRule model) {
		UserMappingRuleSoap soapModel = new UserMappingRuleSoap();

		soapModel.setUserMappingRuleId(model.getUserMappingRuleId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDataSourceId(model.getDataSourceId());
		soapModel.setFieldSetId(model.getFieldSetId());
		soapModel.setModelName(model.getModelName());
		soapModel.setSourceField(model.getSourceField());
		soapModel.setDestinationField(model.getDestinationField());
		soapModel.setFrequency(model.getFrequency());

		return soapModel;
	}

	public static UserMappingRuleSoap[] toSoapModels(UserMappingRule[] models) {
		UserMappingRuleSoap[] soapModels = new UserMappingRuleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserMappingRuleSoap[][] toSoapModels(
		UserMappingRule[][] models) {
		UserMappingRuleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new UserMappingRuleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserMappingRuleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserMappingRuleSoap[] toSoapModels(
		List<UserMappingRule> models) {
		List<UserMappingRuleSoap> soapModels = new ArrayList<UserMappingRuleSoap>(models.size());

		for (UserMappingRule model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserMappingRuleSoap[soapModels.size()]);
	}

	public UserMappingRuleSoap() {
	}

	public long getPrimaryKey() {
		return _userMappingRuleId;
	}

	public void setPrimaryKey(long pk) {
		setUserMappingRuleId(pk);
	}

	public long getUserMappingRuleId() {
		return _userMappingRuleId;
	}

	public void setUserMappingRuleId(long userMappingRuleId) {
		_userMappingRuleId = userMappingRuleId;
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

	public long getDataSourceId() {
		return _dataSourceId;
	}

	public void setDataSourceId(long dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	public long getFieldSetId() {
		return _fieldSetId;
	}

	public void setFieldSetId(long fieldSetId) {
		_fieldSetId = fieldSetId;
	}

	public String getModelName() {
		return _modelName;
	}

	public void setModelName(String modelName) {
		_modelName = modelName;
	}

	public String getSourceField() {
		return _sourceField;
	}

	public void setSourceField(String sourceField) {
		_sourceField = sourceField;
	}

	public String getDestinationField() {
		return _destinationField;
	}

	public void setDestinationField(String destinationField) {
		_destinationField = destinationField;
	}

	public int getFrequency() {
		return _frequency;
	}

	public void setFrequency(int frequency) {
		_frequency = frequency;
	}

	private long _userMappingRuleId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _dataSourceId;
	private long _fieldSetId;
	private String _modelName;
	private String _sourceField;
	private String _destinationField;
	private int _frequency;
}