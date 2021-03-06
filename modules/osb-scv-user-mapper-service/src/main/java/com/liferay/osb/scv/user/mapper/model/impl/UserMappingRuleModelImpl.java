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

package com.liferay.osb.scv.user.mapper.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.osb.scv.user.mapper.model.UserMappingRule;
import com.liferay.osb.scv.user.mapper.model.UserMappingRuleModel;
import com.liferay.osb.scv.user.mapper.model.UserMappingRuleSoap;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the UserMappingRule service. Represents a row in the &quot;OSB_SCV_UserMappingRule&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link UserMappingRuleModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link UserMappingRuleImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserMappingRuleImpl
 * @see UserMappingRule
 * @see UserMappingRuleModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class UserMappingRuleModelImpl extends BaseModelImpl<UserMappingRule>
	implements UserMappingRuleModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a user mapping rule model instance should use the {@link UserMappingRule} interface instead.
	 */
	public static final String TABLE_NAME = "OSB_SCV_UserMappingRule";
	public static final Object[][] TABLE_COLUMNS = {
			{ "userMappingRuleId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "mappingDataSourceId", Types.BIGINT },
			{ "fieldSetId", Types.BIGINT },
			{ "modelName", Types.VARCHAR },
			{ "sourceField", Types.VARCHAR },
			{ "destinationField", Types.VARCHAR },
			{ "fieldType", Types.VARCHAR },
			{ "frequency", Types.INTEGER },
			{ "required", Types.BOOLEAN }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("userMappingRuleId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("mappingDataSourceId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("fieldSetId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("modelName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("sourceField", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("destinationField", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("fieldType", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("frequency", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("required", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE = "create table OSB_SCV_UserMappingRule (userMappingRuleId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,mappingDataSourceId LONG,fieldSetId LONG,modelName VARCHAR(75) null,sourceField VARCHAR(75) null,destinationField VARCHAR(75) null,fieldType VARCHAR(75) null,frequency INTEGER,required BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table OSB_SCV_UserMappingRule";
	public static final String ORDER_BY_JPQL = " ORDER BY userMappingRule.userMappingRuleId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY OSB_SCV_UserMappingRule.userMappingRuleId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.osb.scv.user.mapper.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.osb.scv.user.mapper.model.UserMappingRule"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.osb.scv.user.mapper.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.osb.scv.user.mapper.model.UserMappingRule"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.osb.scv.user.mapper.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.osb.scv.user.mapper.model.UserMappingRule"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long DESTINATIONFIELD_COLUMN_BITMASK = 2L;
	public static final long FREQUENCY_COLUMN_BITMASK = 4L;
	public static final long MAPPINGDATASOURCEID_COLUMN_BITMASK = 8L;
	public static final long USERMAPPINGRULEID_COLUMN_BITMASK = 16L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static UserMappingRule toModel(UserMappingRuleSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		UserMappingRule model = new UserMappingRuleImpl();

		model.setUserMappingRuleId(soapModel.getUserMappingRuleId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setMappingDataSourceId(soapModel.getMappingDataSourceId());
		model.setFieldSetId(soapModel.getFieldSetId());
		model.setModelName(soapModel.getModelName());
		model.setSourceField(soapModel.getSourceField());
		model.setDestinationField(soapModel.getDestinationField());
		model.setFieldType(soapModel.getFieldType());
		model.setFrequency(soapModel.getFrequency());
		model.setRequired(soapModel.getRequired());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<UserMappingRule> toModels(
		UserMappingRuleSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<UserMappingRule> models = new ArrayList<UserMappingRule>(soapModels.length);

		for (UserMappingRuleSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.osb.scv.user.mapper.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.osb.scv.user.mapper.model.UserMappingRule"));

	public UserMappingRuleModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _userMappingRuleId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setUserMappingRuleId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _userMappingRuleId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return UserMappingRule.class;
	}

	@Override
	public String getModelClassName() {
		return UserMappingRule.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("userMappingRuleId", getUserMappingRuleId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("mappingDataSourceId", getMappingDataSourceId());
		attributes.put("fieldSetId", getFieldSetId());
		attributes.put("modelName", getModelName());
		attributes.put("sourceField", getSourceField());
		attributes.put("destinationField", getDestinationField());
		attributes.put("fieldType", getFieldType());
		attributes.put("frequency", getFrequency());
		attributes.put("required", getRequired());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long userMappingRuleId = (Long)attributes.get("userMappingRuleId");

		if (userMappingRuleId != null) {
			setUserMappingRuleId(userMappingRuleId);
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

		Long mappingDataSourceId = (Long)attributes.get("mappingDataSourceId");

		if (mappingDataSourceId != null) {
			setMappingDataSourceId(mappingDataSourceId);
		}

		Long fieldSetId = (Long)attributes.get("fieldSetId");

		if (fieldSetId != null) {
			setFieldSetId(fieldSetId);
		}

		String modelName = (String)attributes.get("modelName");

		if (modelName != null) {
			setModelName(modelName);
		}

		String sourceField = (String)attributes.get("sourceField");

		if (sourceField != null) {
			setSourceField(sourceField);
		}

		String destinationField = (String)attributes.get("destinationField");

		if (destinationField != null) {
			setDestinationField(destinationField);
		}

		String fieldType = (String)attributes.get("fieldType");

		if (fieldType != null) {
			setFieldType(fieldType);
		}

		Integer frequency = (Integer)attributes.get("frequency");

		if (frequency != null) {
			setFrequency(frequency);
		}

		Boolean required = (Boolean)attributes.get("required");

		if (required != null) {
			setRequired(required);
		}
	}

	@JSON
	@Override
	public long getUserMappingRuleId() {
		return _userMappingRuleId;
	}

	@Override
	public void setUserMappingRuleId(long userMappingRuleId) {
		_userMappingRuleId = userMappingRuleId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getMappingDataSourceId() {
		return _mappingDataSourceId;
	}

	@Override
	public void setMappingDataSourceId(long mappingDataSourceId) {
		_columnBitmask |= MAPPINGDATASOURCEID_COLUMN_BITMASK;

		if (!_setOriginalMappingDataSourceId) {
			_setOriginalMappingDataSourceId = true;

			_originalMappingDataSourceId = _mappingDataSourceId;
		}

		_mappingDataSourceId = mappingDataSourceId;
	}

	public long getOriginalMappingDataSourceId() {
		return _originalMappingDataSourceId;
	}

	@JSON
	@Override
	public long getFieldSetId() {
		return _fieldSetId;
	}

	@Override
	public void setFieldSetId(long fieldSetId) {
		_fieldSetId = fieldSetId;
	}

	@JSON
	@Override
	public String getModelName() {
		if (_modelName == null) {
			return StringPool.BLANK;
		}
		else {
			return _modelName;
		}
	}

	@Override
	public void setModelName(String modelName) {
		_modelName = modelName;
	}

	@JSON
	@Override
	public String getSourceField() {
		if (_sourceField == null) {
			return StringPool.BLANK;
		}
		else {
			return _sourceField;
		}
	}

	@Override
	public void setSourceField(String sourceField) {
		_sourceField = sourceField;
	}

	@JSON
	@Override
	public String getDestinationField() {
		if (_destinationField == null) {
			return StringPool.BLANK;
		}
		else {
			return _destinationField;
		}
	}

	@Override
	public void setDestinationField(String destinationField) {
		_columnBitmask |= DESTINATIONFIELD_COLUMN_BITMASK;

		if (_originalDestinationField == null) {
			_originalDestinationField = _destinationField;
		}

		_destinationField = destinationField;
	}

	public String getOriginalDestinationField() {
		return GetterUtil.getString(_originalDestinationField);
	}

	@JSON
	@Override
	public String getFieldType() {
		if (_fieldType == null) {
			return StringPool.BLANK;
		}
		else {
			return _fieldType;
		}
	}

	@Override
	public void setFieldType(String fieldType) {
		_fieldType = fieldType;
	}

	@JSON
	@Override
	public int getFrequency() {
		return _frequency;
	}

	@Override
	public void setFrequency(int frequency) {
		_columnBitmask |= FREQUENCY_COLUMN_BITMASK;

		if (!_setOriginalFrequency) {
			_setOriginalFrequency = true;

			_originalFrequency = _frequency;
		}

		_frequency = frequency;
	}

	public int getOriginalFrequency() {
		return _originalFrequency;
	}

	@JSON
	@Override
	public boolean getRequired() {
		return _required;
	}

	@JSON
	@Override
	public boolean isRequired() {
		return _required;
	}

	@Override
	public void setRequired(boolean required) {
		_required = required;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			UserMappingRule.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public UserMappingRule toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (UserMappingRule)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		UserMappingRuleImpl userMappingRuleImpl = new UserMappingRuleImpl();

		userMappingRuleImpl.setUserMappingRuleId(getUserMappingRuleId());
		userMappingRuleImpl.setCompanyId(getCompanyId());
		userMappingRuleImpl.setUserId(getUserId());
		userMappingRuleImpl.setUserName(getUserName());
		userMappingRuleImpl.setCreateDate(getCreateDate());
		userMappingRuleImpl.setModifiedDate(getModifiedDate());
		userMappingRuleImpl.setMappingDataSourceId(getMappingDataSourceId());
		userMappingRuleImpl.setFieldSetId(getFieldSetId());
		userMappingRuleImpl.setModelName(getModelName());
		userMappingRuleImpl.setSourceField(getSourceField());
		userMappingRuleImpl.setDestinationField(getDestinationField());
		userMappingRuleImpl.setFieldType(getFieldType());
		userMappingRuleImpl.setFrequency(getFrequency());
		userMappingRuleImpl.setRequired(getRequired());

		userMappingRuleImpl.resetOriginalValues();

		return userMappingRuleImpl;
	}

	@Override
	public int compareTo(UserMappingRule userMappingRule) {
		long primaryKey = userMappingRule.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserMappingRule)) {
			return false;
		}

		UserMappingRule userMappingRule = (UserMappingRule)obj;

		long primaryKey = userMappingRule.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		UserMappingRuleModelImpl userMappingRuleModelImpl = this;

		userMappingRuleModelImpl._originalCompanyId = userMappingRuleModelImpl._companyId;

		userMappingRuleModelImpl._setOriginalCompanyId = false;

		userMappingRuleModelImpl._setModifiedDate = false;

		userMappingRuleModelImpl._originalMappingDataSourceId = userMappingRuleModelImpl._mappingDataSourceId;

		userMappingRuleModelImpl._setOriginalMappingDataSourceId = false;

		userMappingRuleModelImpl._originalDestinationField = userMappingRuleModelImpl._destinationField;

		userMappingRuleModelImpl._originalFrequency = userMappingRuleModelImpl._frequency;

		userMappingRuleModelImpl._setOriginalFrequency = false;

		userMappingRuleModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<UserMappingRule> toCacheModel() {
		UserMappingRuleCacheModel userMappingRuleCacheModel = new UserMappingRuleCacheModel();

		userMappingRuleCacheModel.userMappingRuleId = getUserMappingRuleId();

		userMappingRuleCacheModel.companyId = getCompanyId();

		userMappingRuleCacheModel.userId = getUserId();

		userMappingRuleCacheModel.userName = getUserName();

		String userName = userMappingRuleCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			userMappingRuleCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			userMappingRuleCacheModel.createDate = createDate.getTime();
		}
		else {
			userMappingRuleCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			userMappingRuleCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			userMappingRuleCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		userMappingRuleCacheModel.mappingDataSourceId = getMappingDataSourceId();

		userMappingRuleCacheModel.fieldSetId = getFieldSetId();

		userMappingRuleCacheModel.modelName = getModelName();

		String modelName = userMappingRuleCacheModel.modelName;

		if ((modelName != null) && (modelName.length() == 0)) {
			userMappingRuleCacheModel.modelName = null;
		}

		userMappingRuleCacheModel.sourceField = getSourceField();

		String sourceField = userMappingRuleCacheModel.sourceField;

		if ((sourceField != null) && (sourceField.length() == 0)) {
			userMappingRuleCacheModel.sourceField = null;
		}

		userMappingRuleCacheModel.destinationField = getDestinationField();

		String destinationField = userMappingRuleCacheModel.destinationField;

		if ((destinationField != null) && (destinationField.length() == 0)) {
			userMappingRuleCacheModel.destinationField = null;
		}

		userMappingRuleCacheModel.fieldType = getFieldType();

		String fieldType = userMappingRuleCacheModel.fieldType;

		if ((fieldType != null) && (fieldType.length() == 0)) {
			userMappingRuleCacheModel.fieldType = null;
		}

		userMappingRuleCacheModel.frequency = getFrequency();

		userMappingRuleCacheModel.required = getRequired();

		return userMappingRuleCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{userMappingRuleId=");
		sb.append(getUserMappingRuleId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", mappingDataSourceId=");
		sb.append(getMappingDataSourceId());
		sb.append(", fieldSetId=");
		sb.append(getFieldSetId());
		sb.append(", modelName=");
		sb.append(getModelName());
		sb.append(", sourceField=");
		sb.append(getSourceField());
		sb.append(", destinationField=");
		sb.append(getDestinationField());
		sb.append(", fieldType=");
		sb.append(getFieldType());
		sb.append(", frequency=");
		sb.append(getFrequency());
		sb.append(", required=");
		sb.append(getRequired());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(46);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.scv.user.mapper.model.UserMappingRule");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>userMappingRuleId</column-name><column-value><![CDATA[");
		sb.append(getUserMappingRuleId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>mappingDataSourceId</column-name><column-value><![CDATA[");
		sb.append(getMappingDataSourceId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fieldSetId</column-name><column-value><![CDATA[");
		sb.append(getFieldSetId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modelName</column-name><column-value><![CDATA[");
		sb.append(getModelName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sourceField</column-name><column-value><![CDATA[");
		sb.append(getSourceField());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>destinationField</column-name><column-value><![CDATA[");
		sb.append(getDestinationField());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fieldType</column-name><column-value><![CDATA[");
		sb.append(getFieldType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>frequency</column-name><column-value><![CDATA[");
		sb.append(getFrequency());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>required</column-name><column-value><![CDATA[");
		sb.append(getRequired());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = UserMappingRule.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			UserMappingRule.class
		};
	private long _userMappingRuleId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _mappingDataSourceId;
	private long _originalMappingDataSourceId;
	private boolean _setOriginalMappingDataSourceId;
	private long _fieldSetId;
	private String _modelName;
	private String _sourceField;
	private String _destinationField;
	private String _originalDestinationField;
	private String _fieldType;
	private int _frequency;
	private int _originalFrequency;
	private boolean _setOriginalFrequency;
	private boolean _required;
	private long _columnBitmask;
	private UserMappingRule _escapedModel;
}