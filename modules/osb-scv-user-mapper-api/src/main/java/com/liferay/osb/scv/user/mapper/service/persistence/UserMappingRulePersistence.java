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

import com.liferay.osb.scv.user.mapper.exception.NoSuchUserMappingRuleException;
import com.liferay.osb.scv.user.mapper.model.UserMappingRule;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the user mapping rule service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.scv.user.mapper.service.persistence.impl.UserMappingRulePersistenceImpl
 * @see UserMappingRuleUtil
 * @generated
 */
@ProviderType
public interface UserMappingRulePersistence extends BasePersistence<UserMappingRule> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserMappingRuleUtil} to access the user mapping rule persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the user mapping rules where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching user mapping rules
	*/
	public java.util.List<UserMappingRule> findByCompanyId(long companyId);

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
	public java.util.List<UserMappingRule> findByCompanyId(long companyId,
		int start, int end);

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
	public java.util.List<UserMappingRule> findByCompanyId(long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserMappingRule> orderByComparator);

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
	public java.util.List<UserMappingRule> findByCompanyId(long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserMappingRule> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user mapping rule in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user mapping rule
	* @throws NoSuchUserMappingRuleException if a matching user mapping rule could not be found
	*/
	public UserMappingRule findByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<UserMappingRule> orderByComparator)
		throws NoSuchUserMappingRuleException;

	/**
	* Returns the first user mapping rule in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user mapping rule, or <code>null</code> if a matching user mapping rule could not be found
	*/
	public UserMappingRule fetchByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<UserMappingRule> orderByComparator);

	/**
	* Returns the last user mapping rule in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user mapping rule
	* @throws NoSuchUserMappingRuleException if a matching user mapping rule could not be found
	*/
	public UserMappingRule findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<UserMappingRule> orderByComparator)
		throws NoSuchUserMappingRuleException;

	/**
	* Returns the last user mapping rule in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user mapping rule, or <code>null</code> if a matching user mapping rule could not be found
	*/
	public UserMappingRule fetchByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<UserMappingRule> orderByComparator);

	/**
	* Returns the user mapping rules before and after the current user mapping rule in the ordered set where companyId = &#63;.
	*
	* @param userMappingRuleId the primary key of the current user mapping rule
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user mapping rule
	* @throws NoSuchUserMappingRuleException if a user mapping rule with the primary key could not be found
	*/
	public UserMappingRule[] findByCompanyId_PrevAndNext(
		long userMappingRuleId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<UserMappingRule> orderByComparator)
		throws NoSuchUserMappingRuleException;

	/**
	* Removes all the user mapping rules where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public void removeByCompanyId(long companyId);

	/**
	* Returns the number of user mapping rules where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching user mapping rules
	*/
	public int countByCompanyId(long companyId);

	/**
	* Returns all the user mapping rules where dataSourceId = &#63; and frequency = &#63;.
	*
	* @param dataSourceId the data source ID
	* @param frequency the frequency
	* @return the matching user mapping rules
	*/
	public java.util.List<UserMappingRule> findByD_F(long dataSourceId,
		int frequency);

	/**
	* Returns a range of all the user mapping rules where dataSourceId = &#63; and frequency = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserMappingRuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dataSourceId the data source ID
	* @param frequency the frequency
	* @param start the lower bound of the range of user mapping rules
	* @param end the upper bound of the range of user mapping rules (not inclusive)
	* @return the range of matching user mapping rules
	*/
	public java.util.List<UserMappingRule> findByD_F(long dataSourceId,
		int frequency, int start, int end);

	/**
	* Returns an ordered range of all the user mapping rules where dataSourceId = &#63; and frequency = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserMappingRuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dataSourceId the data source ID
	* @param frequency the frequency
	* @param start the lower bound of the range of user mapping rules
	* @param end the upper bound of the range of user mapping rules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user mapping rules
	*/
	public java.util.List<UserMappingRule> findByD_F(long dataSourceId,
		int frequency, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserMappingRule> orderByComparator);

	/**
	* Returns an ordered range of all the user mapping rules where dataSourceId = &#63; and frequency = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserMappingRuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dataSourceId the data source ID
	* @param frequency the frequency
	* @param start the lower bound of the range of user mapping rules
	* @param end the upper bound of the range of user mapping rules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user mapping rules
	*/
	public java.util.List<UserMappingRule> findByD_F(long dataSourceId,
		int frequency, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserMappingRule> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user mapping rule in the ordered set where dataSourceId = &#63; and frequency = &#63;.
	*
	* @param dataSourceId the data source ID
	* @param frequency the frequency
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user mapping rule
	* @throws NoSuchUserMappingRuleException if a matching user mapping rule could not be found
	*/
	public UserMappingRule findByD_F_First(long dataSourceId, int frequency,
		com.liferay.portal.kernel.util.OrderByComparator<UserMappingRule> orderByComparator)
		throws NoSuchUserMappingRuleException;

	/**
	* Returns the first user mapping rule in the ordered set where dataSourceId = &#63; and frequency = &#63;.
	*
	* @param dataSourceId the data source ID
	* @param frequency the frequency
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user mapping rule, or <code>null</code> if a matching user mapping rule could not be found
	*/
	public UserMappingRule fetchByD_F_First(long dataSourceId, int frequency,
		com.liferay.portal.kernel.util.OrderByComparator<UserMappingRule> orderByComparator);

	/**
	* Returns the last user mapping rule in the ordered set where dataSourceId = &#63; and frequency = &#63;.
	*
	* @param dataSourceId the data source ID
	* @param frequency the frequency
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user mapping rule
	* @throws NoSuchUserMappingRuleException if a matching user mapping rule could not be found
	*/
	public UserMappingRule findByD_F_Last(long dataSourceId, int frequency,
		com.liferay.portal.kernel.util.OrderByComparator<UserMappingRule> orderByComparator)
		throws NoSuchUserMappingRuleException;

	/**
	* Returns the last user mapping rule in the ordered set where dataSourceId = &#63; and frequency = &#63;.
	*
	* @param dataSourceId the data source ID
	* @param frequency the frequency
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user mapping rule, or <code>null</code> if a matching user mapping rule could not be found
	*/
	public UserMappingRule fetchByD_F_Last(long dataSourceId, int frequency,
		com.liferay.portal.kernel.util.OrderByComparator<UserMappingRule> orderByComparator);

	/**
	* Returns the user mapping rules before and after the current user mapping rule in the ordered set where dataSourceId = &#63; and frequency = &#63;.
	*
	* @param userMappingRuleId the primary key of the current user mapping rule
	* @param dataSourceId the data source ID
	* @param frequency the frequency
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user mapping rule
	* @throws NoSuchUserMappingRuleException if a user mapping rule with the primary key could not be found
	*/
	public UserMappingRule[] findByD_F_PrevAndNext(long userMappingRuleId,
		long dataSourceId, int frequency,
		com.liferay.portal.kernel.util.OrderByComparator<UserMappingRule> orderByComparator)
		throws NoSuchUserMappingRuleException;

	/**
	* Removes all the user mapping rules where dataSourceId = &#63; and frequency = &#63; from the database.
	*
	* @param dataSourceId the data source ID
	* @param frequency the frequency
	*/
	public void removeByD_F(long dataSourceId, int frequency);

	/**
	* Returns the number of user mapping rules where dataSourceId = &#63; and frequency = &#63;.
	*
	* @param dataSourceId the data source ID
	* @param frequency the frequency
	* @return the number of matching user mapping rules
	*/
	public int countByD_F(long dataSourceId, int frequency);

	/**
	* Caches the user mapping rule in the entity cache if it is enabled.
	*
	* @param userMappingRule the user mapping rule
	*/
	public void cacheResult(UserMappingRule userMappingRule);

	/**
	* Caches the user mapping rules in the entity cache if it is enabled.
	*
	* @param userMappingRules the user mapping rules
	*/
	public void cacheResult(java.util.List<UserMappingRule> userMappingRules);

	/**
	* Creates a new user mapping rule with the primary key. Does not add the user mapping rule to the database.
	*
	* @param userMappingRuleId the primary key for the new user mapping rule
	* @return the new user mapping rule
	*/
	public UserMappingRule create(long userMappingRuleId);

	/**
	* Removes the user mapping rule with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userMappingRuleId the primary key of the user mapping rule
	* @return the user mapping rule that was removed
	* @throws NoSuchUserMappingRuleException if a user mapping rule with the primary key could not be found
	*/
	public UserMappingRule remove(long userMappingRuleId)
		throws NoSuchUserMappingRuleException;

	public UserMappingRule updateImpl(UserMappingRule userMappingRule);

	/**
	* Returns the user mapping rule with the primary key or throws a {@link NoSuchUserMappingRuleException} if it could not be found.
	*
	* @param userMappingRuleId the primary key of the user mapping rule
	* @return the user mapping rule
	* @throws NoSuchUserMappingRuleException if a user mapping rule with the primary key could not be found
	*/
	public UserMappingRule findByPrimaryKey(long userMappingRuleId)
		throws NoSuchUserMappingRuleException;

	/**
	* Returns the user mapping rule with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userMappingRuleId the primary key of the user mapping rule
	* @return the user mapping rule, or <code>null</code> if a user mapping rule with the primary key could not be found
	*/
	public UserMappingRule fetchByPrimaryKey(long userMappingRuleId);

	@Override
	public java.util.Map<java.io.Serializable, UserMappingRule> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the user mapping rules.
	*
	* @return the user mapping rules
	*/
	public java.util.List<UserMappingRule> findAll();

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
	public java.util.List<UserMappingRule> findAll(int start, int end);

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
	public java.util.List<UserMappingRule> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserMappingRule> orderByComparator);

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
	public java.util.List<UserMappingRule> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserMappingRule> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the user mapping rules from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of user mapping rules.
	*
	* @return the number of user mapping rules
	*/
	public int countAll();
}