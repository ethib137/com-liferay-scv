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
 * The extended model interface for the UserMappingRule service. Represents a row in the &quot;OSB_SCV_UserMappingRule&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see UserMappingRuleModel
 * @see com.liferay.osb.scv.user.mapper.model.impl.UserMappingRuleImpl
 * @see com.liferay.osb.scv.user.mapper.model.impl.UserMappingRuleModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.scv.user.mapper.model.impl.UserMappingRuleImpl")
@ProviderType
public interface UserMappingRule extends UserMappingRuleModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.scv.user.mapper.model.impl.UserMappingRuleImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<UserMappingRule, Long> USER_MAPPING_RULE_ID_ACCESSOR =
		new Accessor<UserMappingRule, Long>() {
			@Override
			public Long get(UserMappingRule userMappingRule) {
				return userMappingRule.getUserMappingRuleId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<UserMappingRule> getTypeClass() {
				return UserMappingRule.class;
			}
		};
}