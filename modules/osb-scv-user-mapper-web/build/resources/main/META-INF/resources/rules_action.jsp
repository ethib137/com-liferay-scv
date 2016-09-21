<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

UserMappingRule userMappingRule = (UserMappingRule)row.getObject();

String userMappingRuleId = String.valueOf(userMappingRule.getUserMappingRuleId());
%>

<liferay-ui:icon-menu direction="left-side" icon="<%= StringPool.BLANK %>" markupView="lexicon" message="<%= StringPool.BLANK %>" showWhenSingleIcon="<%= true %>">
		<portlet:actionURL name="deleteUserMappingRule" var="deleteUserMappingRuleURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="userMappingRuleId" value="<%= userMappingRuleId %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			label="<%= true %>"
			message="delete-user-mapping-rule"
			url="<%= deleteUserMappingRuleURL %>"
		/>
</liferay-ui:icon-menu>