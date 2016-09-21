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
		StringBundler sb = new StringBundler(23);

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
		sb.append(", dataSourceId=");
		sb.append(dataSourceId);
		sb.append(", fieldSetId=");
		sb.append(fieldSetId);
		sb.append(", sourceField=");
		sb.append(sourceField);
		sb.append(", destinationField=");
		sb.append(destinationField);
		sb.append(", frequency=");
		sb.append(frequency);
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

		userMappingRuleImpl.setDataSourceId(dataSourceId);
		userMappingRuleImpl.setFieldSetId(fieldSetId);

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

		userMappingRuleImpl.setFrequency(frequency);

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

		dataSourceId = objectInput.readLong();

		fieldSetId = objectInput.readLong();
		sourceField = objectInput.readUTF();
		destinationField = objectInput.readUTF();

		frequency = objectInput.readInt();
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

		objectOutput.writeLong(dataSourceId);

		objectOutput.writeLong(fieldSetId);

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

		objectOutput.writeInt(frequency);
	}

	public long userMappingRuleId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long dataSourceId;
	public long fieldSetId;
	public String sourceField;
	public String destinationField;
	public int frequency;
}