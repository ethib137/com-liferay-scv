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

package com.liferay.osb.scv.user.mapper.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UserMappingRuleLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserMappingRuleLocalService
 * @generated
 */
@ProviderType
public class UserMappingRuleLocalServiceWrapper
	implements UserMappingRuleLocalService,
		ServiceWrapper<UserMappingRuleLocalService> {
	public UserMappingRuleLocalServiceWrapper(
		UserMappingRuleLocalService userMappingRuleLocalService) {
		_userMappingRuleLocalService = userMappingRuleLocalService;
	}

	/**
	* Adds the user mapping rule to the database. Also notifies the appropriate model listeners.
	*
	* @param userMappingRule the user mapping rule
	* @return the user mapping rule that was added
	*/
	@Override
	public com.liferay.osb.scv.user.mapper.model.UserMappingRule addUserMappingRule(
		com.liferay.osb.scv.user.mapper.model.UserMappingRule userMappingRule) {
		return _userMappingRuleLocalService.addUserMappingRule(userMappingRule);
	}

	@Override
	public com.liferay.osb.scv.user.mapper.model.UserMappingRule addUserMappingRule(
		long companyId, long userId, long dataSourceId,
		java.lang.String modelName, long fieldSetId,
		java.lang.String sourceField, java.lang.String destinationField,
		int frequency) {
		return _userMappingRuleLocalService.addUserMappingRule(companyId,
			userId, dataSourceId, modelName, fieldSetId, sourceField,
			destinationField, frequency);
	}

	/**
	* Creates a new user mapping rule with the primary key. Does not add the user mapping rule to the database.
	*
	* @param userMappingRuleId the primary key for the new user mapping rule
	* @return the new user mapping rule
	*/
	@Override
	public com.liferay.osb.scv.user.mapper.model.UserMappingRule createUserMappingRule(
		long userMappingRuleId) {
		return _userMappingRuleLocalService.createUserMappingRule(userMappingRuleId);
	}

	/**
	* Deletes the user mapping rule from the database. Also notifies the appropriate model listeners.
	*
	* @param userMappingRule the user mapping rule
	* @return the user mapping rule that was removed
	*/
	@Override
	public com.liferay.osb.scv.user.mapper.model.UserMappingRule deleteUserMappingRule(
		com.liferay.osb.scv.user.mapper.model.UserMappingRule userMappingRule) {
		return _userMappingRuleLocalService.deleteUserMappingRule(userMappingRule);
	}

	/**
	* Deletes the user mapping rule with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userMappingRuleId the primary key of the user mapping rule
	* @return the user mapping rule that was removed
	* @throws PortalException if a user mapping rule with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.scv.user.mapper.model.UserMappingRule deleteUserMappingRule(
		long userMappingRuleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userMappingRuleLocalService.deleteUserMappingRule(userMappingRuleId);
	}

	@Override
	public com.liferay.osb.scv.user.mapper.model.UserMappingRule fetchUserMappingRule(
		long userMappingRuleId) {
		return _userMappingRuleLocalService.fetchUserMappingRule(userMappingRuleId);
	}

	/**
	* Returns the user mapping rule with the primary key.
	*
	* @param userMappingRuleId the primary key of the user mapping rule
	* @return the user mapping rule
	* @throws PortalException if a user mapping rule with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.scv.user.mapper.model.UserMappingRule getUserMappingRule(
		long userMappingRuleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userMappingRuleLocalService.getUserMappingRule(userMappingRuleId);
	}

	/**
	* Updates the user mapping rule in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userMappingRule the user mapping rule
	* @return the user mapping rule that was updated
	*/
	@Override
	public com.liferay.osb.scv.user.mapper.model.UserMappingRule updateUserMappingRule(
		com.liferay.osb.scv.user.mapper.model.UserMappingRule userMappingRule) {
		return _userMappingRuleLocalService.updateUserMappingRule(userMappingRule);
	}

	@Override
	public com.liferay.osb.scv.user.mapper.model.UserMappingRule updateUserMappingRules(
		long userMappingRuleId, long dataSourceId, long fieldSetId,
		java.lang.String sourceField, java.lang.String destinationField,
		int frequency) {
		return _userMappingRuleLocalService.updateUserMappingRules(userMappingRuleId,
			dataSourceId, fieldSetId, sourceField, destinationField, frequency);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _userMappingRuleLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _userMappingRuleLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _userMappingRuleLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userMappingRuleLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userMappingRuleLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of user mapping rules.
	*
	* @return the number of user mapping rules
	*/
	@Override
	public int getUserMappingRulesCount() {
		return _userMappingRuleLocalService.getUserMappingRulesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _userMappingRuleLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _userMappingRuleLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.scv.user.mapper.model.impl.UserMappingRuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _userMappingRuleLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.scv.user.mapper.model.impl.UserMappingRuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _userMappingRuleLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the user mapping rules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.scv.user.mapper.model.impl.UserMappingRuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user mapping rules
	* @param end the upper bound of the range of user mapping rules (not inclusive)
	* @return the range of user mapping rules
	*/
	@Override
	public java.util.List<com.liferay.osb.scv.user.mapper.model.UserMappingRule> getUserMappingRules(
		int start, int end) {
		return _userMappingRuleLocalService.getUserMappingRules(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.scv.user.mapper.model.UserMappingRule> getUserMappingRules(
		long companyId, int start, int end) {
		return _userMappingRuleLocalService.getUserMappingRules(companyId,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.scv.user.mapper.model.UserMappingRule> getUserMappingRules(
		long dataSourceId, int frequency) {
		return _userMappingRuleLocalService.getUserMappingRules(dataSourceId,
			frequency);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _userMappingRuleLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _userMappingRuleLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public UserMappingRuleLocalService getWrappedService() {
		return _userMappingRuleLocalService;
	}

	@Override
	public void setWrappedService(
		UserMappingRuleLocalService userMappingRuleLocalService) {
		_userMappingRuleLocalService = userMappingRuleLocalService;
	}

	private UserMappingRuleLocalService _userMappingRuleLocalService;
}