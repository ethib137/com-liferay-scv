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

package com.liferay.osb.scv.user.mapper.web.internal.portlet;

import com.liferay.osb.scv.user.mapper.service.UserMappingRuleLocalService;
import com.liferay.osb.scv.user.mapper.service.UserMappingRuleService;
import com.liferay.osb.scv.user.mapper.web.internal.constants.UserMapperPortletKeys;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Shinn Lok
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=portlet-user-mapper",
		"com.liferay.portlet.display-category=category.tools",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=User Mapper",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + UserMapperPortletKeys.USER_MAPPER,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class UserMapperPortlet extends MVCPortlet {

	public void addUserMappingRule(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long dataSourceId = ParamUtil.getLong(
			actionRequest, "dataSourceId");
		long fieldSetId = ParamUtil.getLong(actionRequest, "fieldSetId");
		String tableName = ParamUtil.getString(actionRequest, "tableName");
		String sourceField = ParamUtil.getString(actionRequest, "sourceField");
		String destinationField = ParamUtil.getString(
			actionRequest, "destinationField");
		int frequency = ParamUtil.getInteger(actionRequest, "frequency");

		_userMappingRuleService.addUserMappingRule(
			dataSourceId, fieldSetId, tableName.concat("#").concat(sourceField),
			destinationField, frequency);
	}

	public void deleteUserMappingRule(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long userMappingRuleId = ParamUtil.getLong(
			actionRequest, "userMappingRuleId");

		_userMappingRuleService.deleteUserMappingRule(userMappingRuleId);
	}

	@Reference
	protected UserMappingRuleService _userMappingRuleService;

}