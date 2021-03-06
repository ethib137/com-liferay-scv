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

import com.liferay.osb.scv.user.mapper.model.UserMappingRule;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing UserMappingRule in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see UserMappingRule
 * @generated
 */
@ProviderType
public class UserMappingRuleCacheModel implements CacheModel<UserMappingRule>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserMappingRuleCacheModel)) {
			return false;
		}

		UserMappingRuleCacheModel userMappingRuleCacheModel = (UserMappingRuleCacheModel)obj;

		if (userMappingRuleId == userMappingRuleCacheModel.userMappingRuleId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, userMappingRuleId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{userMappingRuleId=");
		sb.append(userMappingRuleId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", mappingDataSourceId=");
		sb.append(mappingDataSourceId);
		sb.append(", fieldSetId=");
		sb.append(fieldSetId);
		sb.append(", modelName=");
		sb.append(modelName);
		sb.append(", sourceField=");
		sb.append(sourceField);
		sb.append(", destinationField=");
		sb.append(destinationField);
		sb.append(", fieldType=");
		sb.append(fieldType);
		sb.append(", frequency=");
		sb.append(frequency);
		sb.append(", required=");
		sb.append(required);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public UserMappingRule toEntityModel() {
		UserMappingRuleImpl userMappingRuleImpl = new UserMappingRuleImpl();

		userMappingRuleImpl.setUserMappingRuleId(userMappingRuleId);
		userMappingRuleImpl.setCompanyId(companyId);
		userMappingRuleImpl.setUserId(userId);

		if (userName == null) {
			userMappingRuleImpl.setUserName(StringPool.BLANK);
		}
		else {
			userMappingRuleImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			userMappingRuleImpl.setCreateDate(null);
		}
		else {
			userMappingRuleImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			userMappingRuleImpl.setModifiedDate(null);
		}
		else {
			userMappingRuleImpl.setModifiedDate(new Date(modifiedDate));
		}

		userMappingRuleImpl.setMappingDataSourceId(mappingDataSourceId);
		userMappingRuleImpl.setFieldSetId(fieldSetId);

		if (modelName == null) {
			userMappingRuleImpl.setModelName(StringPool.BLANK);
		}
		else {
			userMappingRuleImpl.setModelName(modelName);
		}

		if (sourceField == null) {
			userMappingRuleImpl.setSourceField(StringPool.BLANK);
		}
		else {
			userMappingRuleImpl.setSourceField(sourceField);
		}

		if (destinationField == null) {
			userMappingRuleImpl.setDestinationField(StringPool.BLANK);
		}
		else {
			userMappingRuleImpl.setDestinationField(destinationField);
		}

		if (fieldType == null) {
			userMappingRuleImpl.setFieldType(StringPool.BLANK);
		}
		else {
			userMappingRuleImpl.setFieldType(fieldType);
		}

		userMappingRuleImpl.setFrequency(frequency);
		userMappingRuleImpl.setRequired(required);

		userMappingRuleImpl.resetOriginalValues();

		return userMappingRuleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		userMappingRuleId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		mappingDataSourceId = objectInput.readLong();

		fieldSetId = objectInput.readLong();
		modelName = objectInput.readUTF();
		sourceField = objectInput.readUTF();
		destinationField = objectInput.readUTF();
		fieldType = objectInput.readUTF();

		frequency = objectInput.readInt();

		required = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(userMappingRuleId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(mappingDataSourceId);

		objectOutput.writeLong(fieldSetId);

		if (modelName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(modelName);
		}

		if (sourceField == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(sourceField);
		}

		if (destinationField == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(destinationField);
		}

		if (fieldType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fieldType);
		}

		objectOutput.writeInt(frequency);

		objectOutput.writeBoolean(required);
	}

	public long userMappingRuleId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long mappingDataSourceId;
	public long fieldSetId;
	public String modelName;
	public String sourceField;
	public String destinationField;
	public String fieldType;
	public int frequency;
	public boolean required;
}