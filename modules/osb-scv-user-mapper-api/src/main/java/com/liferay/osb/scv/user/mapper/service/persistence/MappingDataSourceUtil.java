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

import com.liferay.osb.scv.user.mapper.model.MappingDataSource;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the mapping data source service. This utility wraps {@link com.liferay.osb.scv.user.mapper.service.persistence.impl.MappingDataSourcePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MappingDataSourcePersistence
 * @see com.liferay.osb.scv.user.mapper.service.persistence.impl.MappingDataSourcePersistenceImpl
 * @generated
 */
@ProviderType
public class MappingDataSourceUtil {
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
	public static void clearCache(MappingDataSource mappingDataSource) {
		getPersistence().clearCache(mappingDataSource);
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
	public static List<MappingDataSource> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MappingDataSource> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MappingDataSource> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MappingDataSource> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MappingDataSource update(MappingDataSource mappingDataSource) {
		return getPersistence().update(mappingDataSource);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MappingDataSource update(
		MappingDataSource mappingDataSource, ServiceContext serviceContext) {
		return getPersistence().update(mappingDataSource, serviceContext);
	}

	/**
	* Returns all the mapping data sources where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching mapping data sources
	*/
	public static List<MappingDataSource> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the mapping data sources where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MappingDataSourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of mapping data sources
	* @param end the upper bound of the range of mapping data sources (not inclusive)
	* @return the range of matching mapping data sources
	*/
	public static List<MappingDataSource> findByCompanyId(long companyId,
		int start, int end) {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the mapping data sources where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MappingDataSourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of mapping data sources
	* @param end the upper bound of the range of mapping data sources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching mapping data sources
	*/
	public static List<MappingDataSource> findByCompanyId(long companyId,
		int start, int end,
		OrderByComparator<MappingDataSource> orderByComparator) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the mapping data sources where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MappingDataSourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of mapping data sources
	* @param end the upper bound of the range of mapping data sources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching mapping data sources
	*/
	public static List<MappingDataSource> findByCompanyId(long companyId,
		int start, int end,
		OrderByComparator<MappingDataSource> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first mapping data source in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching mapping data source
	* @throws NoSuchMappingDataSourceException if a matching mapping data source could not be found
	*/
	public static MappingDataSource findByCompanyId_First(long companyId,
		OrderByComparator<MappingDataSource> orderByComparator)
		throws com.liferay.osb.scv.user.mapper.exception.NoSuchMappingDataSourceException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first mapping data source in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching mapping data source, or <code>null</code> if a matching mapping data source could not be found
	*/
	public static MappingDataSource fetchByCompanyId_First(long companyId,
		OrderByComparator<MappingDataSource> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last mapping data source in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching mapping data source
	* @throws NoSuchMappingDataSourceException if a matching mapping data source could not be found
	*/
	public static MappingDataSource findByCompanyId_Last(long companyId,
		OrderByComparator<MappingDataSource> orderByComparator)
		throws com.liferay.osb.scv.user.mapper.exception.NoSuchMappingDataSourceException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last mapping data source in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching mapping data source, or <code>null</code> if a matching mapping data source could not be found
	*/
	public static MappingDataSource fetchByCompanyId_Last(long companyId,
		OrderByComparator<MappingDataSource> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the mapping data sources before and after the current mapping data source in the ordered set where companyId = &#63;.
	*
	* @param mappingDataSourceId the primary key of the current mapping data source
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next mapping data source
	* @throws NoSuchMappingDataSourceException if a mapping data source with the primary key could not be found
	*/
	public static MappingDataSource[] findByCompanyId_PrevAndNext(
		long mappingDataSourceId, long companyId,
		OrderByComparator<MappingDataSource> orderByComparator)
		throws com.liferay.osb.scv.user.mapper.exception.NoSuchMappingDataSourceException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(mappingDataSourceId, companyId,
			orderByComparator);
	}

	/**
	* Removes all the mapping data sources where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of mapping data sources where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching mapping data sources
	*/
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Caches the mapping data source in the entity cache if it is enabled.
	*
	* @param mappingDataSource the mapping data source
	*/
	public static void cacheResult(MappingDataSource mappingDataSource) {
		getPersistence().cacheResult(mappingDataSource);
	}

	/**
	* Caches the mapping data sources in the entity cache if it is enabled.
	*
	* @param mappingDataSources the mapping data sources
	*/
	public static void cacheResult(List<MappingDataSource> mappingDataSources) {
		getPersistence().cacheResult(mappingDataSources);
	}

	/**
	* Creates a new mapping data source with the primary key. Does not add the mapping data source to the database.
	*
	* @param mappingDataSourceId the primary key for the new mapping data source
	* @return the new mapping data source
	*/
	public static MappingDataSource create(long mappingDataSourceId) {
		return getPersistence().create(mappingDataSourceId);
	}

	/**
	* Removes the mapping data source with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param mappingDataSourceId the primary key of the mapping data source
	* @return the mapping data source that was removed
	* @throws NoSuchMappingDataSourceException if a mapping data source with the primary key could not be found
	*/
	public static MappingDataSource remove(long mappingDataSourceId)
		throws com.liferay.osb.scv.user.mapper.exception.NoSuchMappingDataSourceException {
		return getPersistence().remove(mappingDataSourceId);
	}

	public static MappingDataSource updateImpl(
		MappingDataSource mappingDataSource) {
		return getPersistence().updateImpl(mappingDataSource);
	}

	/**
	* Returns the mapping data source with the primary key or throws a {@link NoSuchMappingDataSourceException} if it could not be found.
	*
	* @param mappingDataSourceId the primary key of the mapping data source
	* @return the mapping data source
	* @throws NoSuchMappingDataSourceException if a mapping data source with the primary key could not be found
	*/
	public static MappingDataSource findByPrimaryKey(long mappingDataSourceId)
		throws com.liferay.osb.scv.user.mapper.exception.NoSuchMappingDataSourceException {
		return getPersistence().findByPrimaryKey(mappingDataSourceId);
	}

	/**
	* Returns the mapping data source with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param mappingDataSourceId the primary key of the mapping data source
	* @return the mapping data source, or <code>null</code> if a mapping data source with the primary key could not be found
	*/
	public static MappingDataSource fetchByPrimaryKey(long mappingDataSourceId) {
		return getPersistence().fetchByPrimaryKey(mappingDataSourceId);
	}

	public static java.util.Map<java.io.Serializable, MappingDataSource> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the mapping data sources.
	*
	* @return the mapping data sources
	*/
	public static List<MappingDataSource> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the mapping data sources.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MappingDataSourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of mapping data sources
	* @param end the upper bound of the range of mapping data sources (not inclusive)
	* @return the range of mapping data sources
	*/
	public static List<MappingDataSource> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the mapping data sources.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MappingDataSourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of mapping data sources
	* @param end the upper bound of the range of mapping data sources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of mapping data sources
	*/
	public static List<MappingDataSource> findAll(int start, int end,
		OrderByComparator<MappingDataSource> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the mapping data sources.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MappingDataSourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of mapping data sources
	* @param end the upper bound of the range of mapping data sources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of mapping data sources
	*/
	public static List<MappingDataSource> findAll(int start, int end,
		OrderByComparator<MappingDataSource> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the mapping data sources from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of mapping data sources.
	*
	* @return the number of mapping data sources
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static MappingDataSourcePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MappingDataSourcePersistence, MappingDataSourcePersistence> _serviceTracker =
		ServiceTrackerFactory.open(MappingDataSourcePersistence.class);
}