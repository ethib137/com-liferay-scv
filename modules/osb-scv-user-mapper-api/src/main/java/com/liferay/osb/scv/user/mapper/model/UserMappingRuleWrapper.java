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
 * This class is a wrapper for {@link UserMappingRule}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserMappingRule
 * @generated
 */
@ProviderType
public class UserMappingRuleWrapper implements UserMappingRule,
	ModelWrapper<UserMappingRule> {
	public UserMappingRuleWrapper(UserMappingRule userMappingRule) {
		_userMappingRule = userMappingRule;
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
		attributes.put("dataSourceId", getDataSourceId());
		attributes.put("fieldSetId", getFieldSetId());
		attributes.put("sourceField", getSourceField());
		attributes.put("destinationField", getDestinationField());
		attributes.put("frequency", getFrequency());

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

		Long dataSourceId = (Long)attributes.get("dataSourceId");

		if (dataSourceId != null) {
			setDataSourceId(dataSourceId);
		}

		Long fieldSetId = (Long)attributes.get("fieldSetId");

		if (fieldSetId != null) {
			setFieldSetId(fieldSetId);
		}

		String sourceField = (String)attributes.get("sourceField");

		if (sourceField != null) {
			setSourceField(sourceField);
		}

		String destinationField = (String)attributes.get("destinationField");

		if (destinationField != null) {
			setDestinationField(destinationField);
		}

		Integer frequency = (Integer)attributes.get("frequency");

		if (frequency != null) {
			setFrequency(frequency);
		}
	}

	@Override
	public UserMappingRule toEscapedModel() {
		return new UserMappingRuleWrapper(_userMappingRule.toEscapedModel());
	}

	@Override
	public UserMappingRule toUnescapedModel() {
		return new UserMappingRuleWrapper(_userMappingRule.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _userMappingRule.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _userMappingRule.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _userMappingRule.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _userMappingRule.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<UserMappingRule> toCacheModel() {
		return _userMappingRule.toCacheModel();
	}

	@Override
	public int compareTo(UserMappingRule userMappingRule) {
		return _userMappingRule.compareTo(userMappingRule);
	}

	/**
	* Returns the frequency of this user mapping rule.
	*
	* @return the frequency of this user mapping rule
	*/
	@Override
	public int getFrequency() {
		return _userMappingRule.getFrequency();
	}

	@Override
	public int hashCode() {
		return _userMappingRule.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _userMappingRule.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new UserMappingRuleWrapper((UserMappingRule)_userMappingRule.clone());
	}

	/**
	* Returns the destination field of this user mapping rule.
	*
	* @return the destination field of this user mapping rule
	*/
	@Override
	public java.lang.String getDestinationField() {
		return _userMappingRule.getDestinationField();
	}

	/**
	* Returns the source field of this user mapping rule.
	*
	* @return the source field of this user mapping rule
	*/
	@Override
	public java.lang.String getSourceField() {
		return _userMappingRule.getSourceField();
	}

	/**
	* Returns the user name of this user mapping rule.
	*
	* @return the user name of this user mapping rule
	*/
	@Override
	public java.lang.String getUserName() {
		return _userMappingRule.getUserName();
	}

	/**
	* Returns the user uuid of this user mapping rule.
	*
	* @return the user uuid of this user mapping rule
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _userMappingRule.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _userMappingRule.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _userMappingRule.toXmlString();
	}

	/**
	* Returns the create date of this user mapping rule.
	*
	* @return the create date of this user mapping rule
	*/
	@Override
	public Date getCreateDate() {
		return _userMappingRule.getCreateDate();
	}

	/**
	* Returns the modified date of this user mapping rule.
	*
	* @return the modified date of this user mapping rule
	*/
	@Override
	public Date getModifiedDate() {
		return _userMappingRule.getModifiedDate();
	}

	/**
	* Returns the company ID of this user mapping rule.
	*
	* @return the company ID of this user mapping rule
	*/
	@Override
	public long getCompanyId() {
		return _userMappingRule.getCompanyId();
	}

	/**
	* Returns the data source ID of this user mapping rule.
	*
	* @return the data source ID of this user mapping rule
	*/
	@Override
	public long getDataSourceId() {
		return _userMappingRule.getDataSourceId();
	}

	/**
	* Returns the field set ID of this user mapping rule.
	*
	* @return the field set ID of this user mapping rule
	*/
	@Override
	public long getFieldSetId() {
		return _userMappingRule.getFieldSetId();
	}

	/**
	* Returns the primary key of this user mapping rule.
	*
	* @return the primary key of this user mapping rule
	*/
	@Override
	public long getPrimaryKey() {
		return _userMappingRule.getPrimaryKey();
	}

	/**
	* Returns the user ID of this user mapping rule.
	*
	* @return the user ID of this user mapping rule
	*/
	@Override
	public long getUserId() {
		return _userMappingRule.getUserId();
	}

	/**
	* Returns the user mapping rule ID of this user mapping rule.
	*
	* @return the user mapping rule ID of this user mapping rule
	*/
	@Override
	public long getUserMappingRuleId() {
		return _userMappingRule.getUserMappingRuleId();
	}

	@Override
	public void persist() {
		_userMappingRule.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_userMappingRule.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this user mapping rule.
	*
	* @param companyId the company ID of this user mapping rule
	*/
	@Override
	public void setCompanyId(long companyId) {
		_userMappingRule.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this user mapping rule.
	*
	* @param createDate the create date of this user mapping rule
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_userMappingRule.setCreateDate(createDate);
	}

	/**
	* Sets the data source ID of this user mapping rule.
	*
	* @param dataSourceId the data source ID of this user mapping rule
	*/
	@Override
	public void setDataSourceId(long dataSourceId) {
		_userMappingRule.setDataSourceId(dataSourceId);
	}

	/**
	* Sets the destination field of this user mapping rule.
	*
	* @param destinationField the destination field of this user mapping rule
	*/
	@Override
	public void setDestinationField(java.lang.String destinationField) {
		_userMappingRule.setDestinationField(destinationField);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_userMappingRule.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_userMappingRule.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_userMappingRule.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the field set ID of this user mapping rule.
	*
	* @param fieldSetId the field set ID of this user mapping rule
	*/
	@Override
	public void setFieldSetId(long fieldSetId) {
		_userMappingRule.setFieldSetId(fieldSetId);
	}

	/**
	* Sets the frequency of this user mapping rule.
	*
	* @param frequency the frequency of this user mapping rule
	*/
	@Override
	public void setFrequency(int frequency) {
		_userMappingRule.setFrequency(frequency);
	}

	/**
	* Sets the modified date of this user mapping rule.
	*
	* @param modifiedDate the modified date of this user mapping rule
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_userMappingRule.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_userMappingRule.setNew(n);
	}

	/**
	* Sets the primary key of this user mapping rule.
	*
	* @param primaryKey the primary key of this user mapping rule
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_userMappingRule.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_userMappingRule.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the source field of this user mapping rule.
	*
	* @param sourceField the source field of this user mapping rule
	*/
	@Override
	public void setSourceField(java.lang.String sourceField) {
		_userMappingRule.setSourceField(sourceField);
	}

	/**
	* Sets the user ID of this user mapping rule.
	*
	* @param userId the user ID of this user mapping rule
	*/
	@Override
	public void setUserId(long userId) {
		_userMappingRule.setUserId(userId);
	}

	/**
	* Sets the user mapping rule ID of this user mapping rule.
	*
	* @param userMappingRuleId the user mapping rule ID of this user mapping rule
	*/
	@Override
	public void setUserMappingRuleId(long userMappingRuleId) {
		_userMappingRule.setUserMappingRuleId(userMappingRuleId);
	}

	/**
	* Sets the user name of this user mapping rule.
	*
	* @param userName the user name of this user mapping rule
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_userMappingRule.setUserName(userName);
	}

	/**
	* Sets the user uuid of this user mapping rule.
	*
	* @param userUuid the user uuid of this user mapping rule
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_userMappingRule.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserMappingRuleWrapper)) {
			return false;
		}

		UserMappingRuleWrapper userMappingRuleWrapper = (UserMappingRuleWrapper)obj;

		if (Objects.equals(_userMappingRule,
					userMappingRuleWrapper._userMappingRule)) {
			return true;
		}

		return false;
	}

	@Override
	public UserMappingRule getWrappedModel() {
		return _userMappingRule;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _userMappingRule.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _userMappingRule.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_userMappingRule.resetOriginalValues();
	}

	private final UserMappingRule _userMappingRule;
}