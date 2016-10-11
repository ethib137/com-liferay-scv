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

package com.liferay.osb.scv.user.mapper.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.scv.user.mapper.model.UserMappingRule;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the user mapping rule service. This utility wraps {@link com.liferay.osb.scv.user.mapper.service.persistence.impl.UserMappingRulePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserMappingRulePersistence
 * @see com.liferay.osb.scv.user.mapper.service.persistence.impl.UserMappingRulePersistenceImpl
 * @generated
 */
@ProviderType
public class UserMappingRuleUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(UserMappingRule userMappingRule) {
		getPersistence().clearCache(userMappingRule);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<UserMappingRule> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UserMappingRule> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UserMappingRule> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<UserMappingRule> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static UserMappingRule update(UserMappingRule userMappingRule) {
		return getPersistence().update(userMappingRule);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static UserMappingRule update(UserMappingRule userMappingRule,
		ServiceContext serviceContext) {
		return getPersistence().update(userMappingRule, serviceContext);
	}

	/**
	* Returns all the user mapping rules where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching user mapping rules
	*/
	public static List<UserMappingRule> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the user mapping rules where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserMappingRuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of user mapping rules
	* @param end the upper bound of the range of user mapping rules (not inclusive)
	* @return the range of matching user mapping rules
	*/
	public static List<UserMappingRule> findByCompanyId(long companyId,
		int start, int end) {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the user mapping rules where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserMappingRuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of user mapping rules
	* @param end the upper bound of the range of user mapping rules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user mapping rules
	*/
	public static List<UserMappingRule> findByCompanyId(long companyId,
		int start, int end, OrderByComparator<UserMappingRule> orderByComparator) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the user mapping rules where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserMappingRuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of user mapping rules
	* @param end the upper bound of the range of user mapping rules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user mapping rules
	*/
	public static List<UserMappingRule> findByCompanyId(long companyId,
		int start, int end,
		OrderByComparator<UserMappingRule> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first user mapping rule in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user mapping rule
	* @throws NoSuchUserMappingRuleException if a matching user mapping rule could not be found
	*/
	public static UserMappingRule findByCompanyId_First(long companyId,
		OrderByComparator<UserMappingRule> orderByComparator)
		throws com.liferay.osb.scv.user.mapper.exception.NoSuchUserMappingRuleException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first user mapping rule in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user mapping rule, or <code>null</code> if a matching user mapping rule could not be found
	*/
	public static UserMappingRule fetchByCompanyId_First(long companyId,
		OrderByComparator<UserMappingRule> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last user mapping rule in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user mapping rule
	* @throws NoSuchUserMappingRuleException if a matching user mapping rule could not be found
	*/
	public static UserMappingRule findByCompanyId_Last(long companyId,
		OrderByComparator<UserMappingRule> orderByComparator)
		throws com.liferay.osb.scv.user.mapper.exception.NoSuchUserMappingRuleException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last user mapping rule in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user mapping rule, or <code>null</code> if a matching user mapping rule could not be found
	*/
	public static UserMappingRule fetchByCompanyId_Last(long companyId,
		OrderByComparator<UserMappingRule> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the user mapping rules before and after the current user mapping rule in the ordered set where companyId = &#63;.
	*
	* @param userMappingRuleId the primary key of the current user mapping rule
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user mapping rule
	* @throws NoSuchUserMappingRuleException if a user mapping rule with the primary key could not be found
	*/
	public static UserMappingRule[] findByCompanyId_PrevAndNext(
		long userMappingRuleId, long companyId,
		OrderByComparator<UserMappingRule> orderByComparator)
		throws com.liferay.osb.scv.user.mapper.exception.NoSuchUserMappingRuleException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(userMappingRuleId, companyId,
			orderByComparator);
	}

	/**
	* Removes all the user mapping rules where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of user mapping rules where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching user mapping rules
	*/
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns all the user mapping rules where mappingDataSourceId = &#63;.
	*
	* @param mappingDataSourceId the mapping data source ID
	* @return the matching user mapping rules
	*/
	public static List<UserMappingRule> findByMappingDataSourceId(
		long mappingDataSourceId) {
		return getPersistence().findByMappingDataSourceId(mappingDataSourceId);
	}

	/**
	* Returns a range of all the user mapping rules where mappingDataSourceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserMappingRuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param mappingDataSourceId the mapping data source ID
	* @param start the lower bound of the range of user mapping rules
	* @param end the upper bound of the range of user mapping rules (not inclusive)
	* @return the range of matching user mapping rules
	*/
	public static List<UserMappingRule> findByMappingDataSourceId(
		long mappingDataSourceId, int start, int end) {
		return getPersistence()
				   .findByMappingDataSourceId(mappingDataSourceId, start, end);
	}

	/**
	* Returns an ordered range of all the user mapping rules where mappingDataSourceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserMappingRuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param mappingDataSourceId the mapping data source ID
	* @param start the lower bound of the range of user mapping rules
	* @param end the upper bound of the range of user mapping rules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user mapping rules
	*/
	public static List<UserMappingRule> findByMappingDataSourceId(
		long mappingDataSourceId, int start, int end,
		OrderByComparator<UserMappingRule> orderByComparator) {
		return getPersistence()
				   .findByMappingDataSourceId(mappingDataSourceId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the user mapping rules where mappingDataSourceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserMappingRuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param mappingDataSourceId the mapping data source ID
	* @param start the lower bound of the range of user mapping rules
	* @param end the upper bound of the range of user mapping rules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user mapping rules
	*/
	public static List<UserMappingRule> findByMappingDataSourceId(
		long mappingDataSourceId, int start, int end,
		OrderByComparator<UserMappingRule> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByMappingDataSourceId(mappingDataSourceId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first user mapping rule in the ordered set where mappingDataSourceId = &#63;.
	*
	* @param mappingDataSourceId the mapping data source ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user mapping rule
	* @throws NoSuchUserMappingRuleException if a matching user mapping rule could not be found
	*/
	public static UserMappingRule findByMappingDataSourceId_First(
		long mappingDataSourceId,
		OrderByComparator<UserMappingRule> orderByComparator)
		throws com.liferay.osb.scv.user.mapper.exception.NoSuchUserMappingRuleException {
		return getPersistence()
				   .findByMappingDataSourceId_First(mappingDataSourceId,
			orderByComparator);
	}

	/**
	* Returns the first user mapping rule in the ordered set where mappingDataSourceId = &#63;.
	*
	* @param mappingDataSourceId the mapping data source ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user mapping rule, or <code>null</code> if a matching user mapping rule could not be found
	*/
	public static UserMappingRule fetchByMappingDataSourceId_First(
		long mappingDataSourceId,
		OrderByComparator<UserMappingRule> orderByComparator) {
		return getPersistence()
				   .fetchByMappingDataSourceId_First(mappingDataSourceId,
			orderByComparator);
	}

	/**
	* Returns the last user mapping rule in the ordered set where mappingDataSourceId = &#63;.
	*
	* @param mappingDataSourceId the mapping data source ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user mapping rule
	* @throws NoSuchUserMappingRuleException if a matching user mapping rule could not be found
	*/
	public static UserMappingRule findByMappingDataSourceId_Last(
		long mappingDataSourceId,
		OrderByComparator<UserMappingRule> orderByComparator)
		throws com.liferay.osb.scv.user.mapper.exception.NoSuchUserMappingRuleException {
		return getPersistence()
				   .findByMappingDataSourceId_Last(mappingDataSourceId,
			orderByComparator);
	}

	/**
	* Returns the last user mapping rule in the ordered set where mappingDataSourceId = &#63;.
	*
	* @param mappingDataSourceId the mapping data source ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user mapping rule, or <code>null</code> if a matching user mapping rule could not be found
	*/
	public static UserMappingRule fetchByMappingDataSourceId_Last(
		long mappingDataSourceId,
		OrderByComparator<UserMappingRule> orderByComparator) {
		return getPersistence()
				   .fetchByMappingDataSourceId_Last(mappingDataSourceId,
			orderByComparator);
	}

	/**
	* Returns the user mapping rules before and after the current user mapping rule in the ordered set where mappingDataSourceId = &#63;.
	*
	* @param userMappingRuleId the primary key of the current user mapping rule
	* @param mappingDataSourceId the mapping data source ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user mapping rule
	* @throws NoSuchUserMappingRuleException if a user mapping rule with the primary key could not be found
	*/
	public static UserMappingRule[] findByMappingDataSourceId_PrevAndNext(
		long userMappingRuleId, long mappingDataSourceId,
		OrderByComparator<UserMappingRule> orderByComparator)
		throws com.liferay.osb.scv.user.mapper.exception.NoSuchUserMappingRuleException {
		return getPersistence()
				   .findByMappingDataSourceId_PrevAndNext(userMappingRuleId,
			mappingDataSourceId, orderByComparator);
	}

	/**
	* Removes all the user mapping rules where mappingDataSourceId = &#63; from the database.
	*
	* @param mappingDataSourceId the mapping data source ID
	*/
	public static void removeByMappingDataSourceId(long mappingDataSourceId) {
		getPersistence().removeByMappingDataSourceId(mappingDataSourceId);
	}

	/**
	* Returns the number of user mapping rules where mappingDataSourceId = &#63;.
	*
	* @param mappingDataSourceId the mapping data source ID
	* @return the number of matching user mapping rules
	*/
	public static int countByMappingDataSourceId(long mappingDataSourceId) {
		return getPersistence().countByMappingDataSourceId(mappingDataSourceId);
	}

	/**
	* Returns all the user mapping rules where mappingDataSourceId = &#63; and frequency = &#63;.
	*
	* @param mappingDataSourceId the mapping data source ID
	* @param frequency the frequency
	* @return the matching user mapping rules
	*/
	public static List<UserMappingRule> findByD_F(long mappingDataSourceId,
		int frequency) {
		return getPersistence().findByD_F(mappingDataSourceId, frequency);
	}

	/**
	* Returns a range of all the user mapping rules where mappingDataSourceId = &#63; and frequency = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserMappingRuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param mappingDataSourceId the mapping data source ID
	* @param frequency the frequency
	* @param start the lower bound of the range of user mapping rules
	* @param end the upper bound of the range of user mapping rules (not inclusive)
	* @return the range of matching user mapping rules
	*/
	public static List<UserMappingRule> findByD_F(long mappingDataSourceId,
		int frequency, int start, int end) {
		return getPersistence()
				   .findByD_F(mappingDataSourceId, frequency, start, end);
	}

	/**
	* Returns an ordered range of all the user mapping rules where mappingDataSourceId = &#63; and frequency = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserMappingRuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param mappingDataSourceId the mapping data source ID
	* @param frequency the frequency
	* @param start the lower bound of the range of user mapping rules
	* @param end the upper bound of the range of user mapping rules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user mapping rules
	*/
	public static List<UserMappingRule> findByD_F(long mappingDataSourceId,
		int frequency, int start, int end,
		OrderByComparator<UserMappingRule> orderByComparator) {
		return getPersistence()
				   .findByD_F(mappingDataSourceId, frequency, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the user mapping rules where mappingDataSourceId = &#63; and frequency = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserMappingRuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param mappingDataSourceId the mapping data source ID
	* @param frequency the frequency
	* @param start the lower bound of the range of user mapping rules
	* @param end the upper bound of the range of user mapping rules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user mapping rules
	*/
	public static List<UserMappingRule> findByD_F(long mappingDataSourceId,
		int frequency, int start, int end,
		OrderByComparator<UserMappingRule> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByD_F(mappingDataSourceId, frequency, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first user mapping rule in the ordered set where mappingDataSourceId = &#63; and frequency = &#63;.
	*
	* @param mappingDataSourceId the mapping data source ID
	* @param frequency the frequency
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user mapping rule
	* @throws NoSuchUserMappingRuleException if a matching user mapping rule could not be found
	*/
	public static UserMappingRule findByD_F_First(long mappingDataSourceId,
		int frequency, OrderByComparator<UserMappingRule> orderByComparator)
		throws com.liferay.osb.scv.user.mapper.exception.NoSuchUserMappingRuleException {
		return getPersistence()
				   .findByD_F_First(mappingDataSourceId, frequency,
			orderByComparator);
	}

	/**
	* Returns the first user mapping rule in the ordered set where mappingDataSourceId = &#63; and frequency = &#63;.
	*
	* @param mappingDataSourceId the mapping data source ID
	* @param frequency the frequency
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user mapping rule, or <code>null</code> if a matching user mapping rule could not be found
	*/
	public static UserMappingRule fetchByD_F_First(long mappingDataSourceId,
		int frequency, OrderByComparator<UserMappingRule> orderByComparator) {
		return getPersistence()
				   .fetchByD_F_First(mappingDataSourceId, frequency,
			orderByComparator);
	}

	/**
	* Returns the last user mapping rule in the ordered set where mappingDataSourceId = &#63; and frequency = &#63;.
	*
	* @param mappingDataSourceId the mapping data source ID
	* @param frequency the frequency
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user mapping rule
	* @throws NoSuchUserMappingRuleException if a matching user mapping rule could not be found
	*/
	public static UserMappingRule findByD_F_Last(long mappingDataSourceId,
		int frequency, OrderByComparator<UserMappingRule> orderByComparator)
		throws com.liferay.osb.scv.user.mapper.exception.NoSuchUserMappingRuleException {
		return getPersistence()
				   .findByD_F_Last(mappingDataSourceId, frequency,
			orderByComparator);
	}

	/**
	* Returns the last user mapping rule in the ordered set where mappingDataSourceId = &#63; and frequency = &#63;.
	*
	* @param mappingDataSourceId the mapping data source ID
	* @param frequency the frequency
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user mapping rule, or <code>null</code> if a matching user mapping rule could not be found
	*/
	public static UserMappingRule fetchByD_F_Last(long mappingDataSourceId,
		int frequency, OrderByComparator<UserMappingRule> orderByComparator) {
		return getPersistence()
				   .fetchByD_F_Last(mappingDataSourceId, frequency,
			orderByComparator);
	}

	/**
	* Returns the user mapping rules before and after the current user mapping rule in the ordered set where mappingDataSourceId = &#63; and frequency = &#63;.
	*
	* @param userMappingRuleId the primary key of the current user mapping rule
	* @param mappingDataSourceId the mapping data source ID
	* @param frequency the frequency
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user mapping rule
	* @throws NoSuchUserMappingRuleException if a user mapping rule with the primary key could not be found
	*/
	public static UserMappingRule[] findByD_F_PrevAndNext(
		long userMappingRuleId, long mappingDataSourceId, int frequency,
		OrderByComparator<UserMappingRule> orderByComparator)
		throws com.liferay.osb.scv.user.mapper.exception.NoSuchUserMappingRuleException {
		return getPersistence()
				   .findByD_F_PrevAndNext(userMappingRuleId,
			mappingDataSourceId, frequency, orderByComparator);
	}

	/**
	* Removes all the user mapping rules where mappingDataSourceId = &#63; and frequency = &#63; from the database.
	*
	* @param mappingDataSourceId the mapping data source ID
	* @param frequency the frequency
	*/
	public static void removeByD_F(long mappingDataSourceId, int frequency) {
		getPersistence().removeByD_F(mappingDataSourceId, frequency);
	}

	/**
	* Returns the number of user mapping rules where mappingDataSourceId = &#63; and frequency = &#63;.
	*
	* @param mappingDataSourceId the mapping data source ID
	* @param frequency the frequency
	* @return the number of matching user mapping rules
	*/
	public static int countByD_F(long mappingDataSourceId, int frequency) {
		return getPersistence().countByD_F(mappingDataSourceId, frequency);
	}

	/**
	* Caches the user mapping rule in the entity cache if it is enabled.
	*
	* @param userMappingRule the user mapping rule
	*/
	public static void cacheResult(UserMappingRule userMappingRule) {
		getPersistence().cacheResult(userMappingRule);
	}

	/**
	* Caches the user mapping rules in the entity cache if it is enabled.
	*
	* @param userMappingRules the user mapping rules
	*/
	public static void cacheResult(List<UserMappingRule> userMappingRules) {
		getPersistence().cacheResult(userMappingRules);
	}

	/**
	* Creates a new user mapping rule with the primary key. Does not add the user mapping rule to the database.
	*
	* @param userMappingRuleId the primary key for the new user mapping rule
	* @return the new user mapping rule
	*/
	public static UserMappingRule create(long userMappingRuleId) {
		return getPersistence().create(userMappingRuleId);
	}

	/**
	* Removes the user mapping rule with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userMappingRuleId the primary key of the user mapping rule
	* @return the user mapping rule that was removed
	* @throws NoSuchUserMappingRuleException if a user mapping rule with the primary key could not be found
	*/
	public static UserMappingRule remove(long userMappingRuleId)
		throws com.liferay.osb.scv.user.mapper.exception.NoSuchUserMappingRuleException {
		return getPersistence().remove(userMappingRuleId);
	}

	public static UserMappingRule updateImpl(UserMappingRule userMappingRule) {
		return getPersistence().updateImpl(userMappingRule);
	}

	/**
	* Returns the user mapping rule with the primary key or throws a {@link NoSuchUserMappingRuleException} if it could not be found.
	*
	* @param userMappingRuleId the primary key of the user mapping rule
	* @return the user mapping rule
	* @throws NoSuchUserMappingRuleException if a user mapping rule with the primary key could not be found
	*/
	public static UserMappingRule findByPrimaryKey(long userMappingRuleId)
		throws com.liferay.osb.scv.user.mapper.exception.NoSuchUserMappingRuleException {
		return getPersistence().findByPrimaryKey(userMappingRuleId);
	}

	/**
	* Returns the user mapping rule with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userMappingRuleId the primary key of the user mapping rule
	* @return the user mapping rule, or <code>null</code> if a user mapping rule with the primary key could not be found
	*/
	public static UserMappingRule fetchByPrimaryKey(long userMappingRuleId) {
		return getPersistence().fetchByPrimaryKey(userMappingRuleId);
	}

	public static java.util.Map<java.io.Serializable, UserMappingRule> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the user mapping rules.
	*
	* @return the user mapping rules
	*/
	public static List<UserMappingRule> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the user mapping rules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserMappingRuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user mapping rules
	* @param end the upper bound of the range of user mapping rules (not inclusive)
	* @return the range of user mapping rules
	*/
	public static List<UserMappingRule> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the user mapping rules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserMappingRuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user mapping rules
	* @param end the upper bound of the range of user mapping rules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of user mapping rules
	*/
	public static List<UserMappingRule> findAll(int start, int end,
		OrderByComparator<UserMappingRule> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the user mapping rules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserMappingRuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user mapping rules
	* @param end the upper bound of the range of user mapping rules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of user mapping rules
	*/
	public static List<UserMappingRule> findAll(int start, int end,
		OrderByComparator<UserMappingRule> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the user mapping rules from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of user mapping rules.
	*
	* @return the number of user mapping rules
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static UserMappingRulePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<UserMappingRulePersistence, UserMappingRulePersistence> _serviceTracker =
		ServiceTrackerFactory.open(UserMappingRulePersistence.class);
}