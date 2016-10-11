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

import com.liferay.osb.scv.user.mapper.model.MappingDataSource;

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
 * The cache model class for representing MappingDataSource in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see MappingDataSource
 * @generated
 */
@ProviderType
public class MappingDataSourceCacheModel implements CacheModel<MappingDataSource>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MappingDataSourceCacheModel)) {
			return false;
		}

		MappingDataSourceCacheModel mappingDataSourceCacheModel = (MappingDataSourceCacheModel)obj;

		if (mappingDataSourceId == mappingDataSourceCacheModel.mappingDataSourceId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, mappingDataSourceId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{mappingDataSourceId=");
		sb.append(mappingDataSourceId);
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
		sb.append(", name=");
		sb.append(name);
		sb.append(", url=");
		sb.append(url);
		sb.append(", login=");
		sb.append(login);
		sb.append(", password=");
		sb.append(password);
		sb.append(", type=");
		sb.append(type);
		sb.append(", availableFields=");
		sb.append(availableFields);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MappingDataSource toEntityModel() {
		MappingDataSourceImpl mappingDataSourceImpl = new MappingDataSourceImpl();

		mappingDataSourceImpl.setMappingDataSourceId(mappingDataSourceId);
		mappingDataSourceImpl.setCompanyId(companyId);
		mappingDataSourceImpl.setUserId(userId);

		if (userName == null) {
			mappingDataSourceImpl.setUserName(StringPool.BLANK);
		}
		else {
			mappingDataSourceImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			mappingDataSourceImpl.setCreateDate(null);
		}
		else {
			mappingDataSourceImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			mappingDataSourceImpl.setModifiedDate(null);
		}
		else {
			mappingDataSourceImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			mappingDataSourceImpl.setName(StringPool.BLANK);
		}
		else {
			mappingDataSourceImpl.setName(name);
		}

		if (url == null) {
			mappingDataSourceImpl.setUrl(StringPool.BLANK);
		}
		else {
			mappingDataSourceImpl.setUrl(url);
		}

		if (login == null) {
			mappingDataSourceImpl.setLogin(StringPool.BLANK);
		}
		else {
			mappingDataSourceImpl.setLogin(login);
		}

		if (password == null) {
			mappingDataSourceImpl.setPassword(StringPool.BLANK);
		}
		else {
			mappingDataSourceImpl.setPassword(password);
		}

		mappingDataSourceImpl.setType(type);

		if (availableFields == null) {
			mappingDataSourceImpl.setAvailableFields(StringPool.BLANK);
		}
		else {
			mappingDataSourceImpl.setAvailableFields(availableFields);
		}

		mappingDataSourceImpl.resetOriginalValues();

		return mappingDataSourceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mappingDataSourceId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		url = objectInput.readUTF();
		login = objectInput.readUTF();
		password = objectInput.readUTF();

		type = objectInput.readLong();
		availableFields = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(mappingDataSourceId);

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

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (url == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(url);
		}

		if (login == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(login);
		}

		if (password == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(password);
		}

		objectOutput.writeLong(type);

		if (availableFields == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(availableFields);
		}
	}

	public long mappingDataSourceId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String url;
	public String login;
	public String password;
	public long type;
	public String availableFields;
}