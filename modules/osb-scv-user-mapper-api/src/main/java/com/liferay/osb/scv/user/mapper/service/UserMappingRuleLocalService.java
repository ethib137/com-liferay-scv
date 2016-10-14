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

import com.liferay.osb.scv.user.mapper.model.UserMappingRule;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for UserMappingRule. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see UserMappingRuleLocalServiceUtil
 * @see com.liferay.osb.scv.user.mapper.service.base.UserMappingRuleLocalServiceBaseImpl
 * @see com.liferay.osb.scv.user.mapper.service.impl.UserMappingRuleLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface UserMappingRuleLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserMappingRuleLocalServiceUtil} to access the user mapping rule local service. Add custom service methods to {@link com.liferay.osb.scv.user.mapper.service.impl.UserMappingRuleLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the user mapping rule to the database. Also notifies the appropriate model listeners.
	*
	* @param userMappingRule the user mapping rule
	* @return the user mapping rule that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public UserMappingRule addUserMappingRule(UserMappingRule userMappingRule);

	public UserMappingRule addUserMappingRule(long companyId, long userId,
		long mappingDataSourceId, java.lang.String modelName, long fieldSetId,
		java.lang.String sourceField, java.lang.String destinationField,
		int frequency, boolean required) throws PortalException;

	/**
	* Creates a new user mapping rule with the primary key. Does not add the user mapping rule to the database.
	*
	* @param userMappingRuleId the primary key for the new user mapping rule
	* @return the new user mapping rule
	*/
	public UserMappingRule createUserMappingRule(long userMappingRuleId);

	/**
	* Deletes the user mapping rule from the database. Also notifies the appropriate model listeners.
	*
	* @param userMappingRule the user mapping rule
	* @return the user mapping rule that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public UserMappingRule deleteUserMappingRule(
		UserMappingRule userMappingRule);

	/**
	* Deletes the user mapping rule with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userMappingRuleId the primary key of the user mapping rule
	* @return the user mapping rule that was removed
	* @throws PortalException if a user mapping rule with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public UserMappingRule deleteUserMappingRule(long userMappingRuleId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public UserMappingRule fetchUserMappingRule(long userMappingRuleId);

	/**
	* Returns the user mapping rule with the primary key.
	*
	* @param userMappingRuleId the primary key of the user mapping rule
	* @return the user mapping rule
	* @throws PortalException if a user mapping rule with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public UserMappingRule getUserMappingRule(long userMappingRuleId)
		throws PortalException;

	/**
	* Updates the user mapping rule in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userMappingRule the user mapping rule
	* @return the user mapping rule that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public UserMappingRule updateUserMappingRule(
		UserMappingRule userMappingRule);

	public UserMappingRule updateUserMappingRules(long userMappingRuleId,
		long mappingDataSourceId, long fieldSetId, java.lang.String modelName,
		java.lang.String sourceField, java.lang.String destinationField,
		java.lang.String fieldType, int frequency);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONArray getUserMappingRuleDestinationFieldsCount(long companyId)
		throws java.lang.Exception;

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the number of user mapping rules.
	*
	* @return the number of user mapping rules
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getUserMappingRulesCount();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserMappingRule> getUserMappingRules(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserMappingRule> getUserMappingRules(long companyId, int start,
		int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserMappingRule> getUserMappingRules(long companyId,
		java.lang.String destinationField);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserMappingRule> getUserMappingRules(long mappingDataSourceId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserMappingRule> getUserMappingRules(long mappingDataSourceId,
		int frequency);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);
}