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

import com.liferay.osb.scv.user.mapper.exception.NoSuchMappingDataSourceException;
import com.liferay.osb.scv.user.mapper.model.MappingDataSource;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the mapping data source service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.scv.user.mapper.service.persistence.impl.MappingDataSourcePersistenceImpl
 * @see MappingDataSourceUtil
 * @generated
 */
@ProviderType
public interface MappingDataSourcePersistence extends BasePersistence<MappingDataSource> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MappingDataSourceUtil} to access the mapping data source persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the mapping data sources where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching mapping data sources
	*/
	public java.util.List<MappingDataSource> findByCompanyId(long companyId);

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
	public java.util.List<MappingDataSource> findByCompanyId(long companyId,
		int start, int end);

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
	public java.util.List<MappingDataSource> findByCompanyId(long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MappingDataSource> orderByComparator);

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
	public java.util.List<MappingDataSource> findByCompanyId(long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MappingDataSource> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first mapping data source in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching mapping data source
	* @throws NoSuchMappingDataSourceException if a matching mapping data source could not be found
	*/
	public MappingDataSource findByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MappingDataSource> orderByComparator)
		throws NoSuchMappingDataSourceException;

	/**
	* Returns the first mapping data source in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching mapping data source, or <code>null</code> if a matching mapping data source could not be found
	*/
	public MappingDataSource fetchByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MappingDataSource> orderByComparator);

	/**
	* Returns the last mapping data source in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching mapping data source
	* @throws NoSuchMappingDataSourceException if a matching mapping data source could not be found
	*/
	public MappingDataSource findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MappingDataSource> orderByComparator)
		throws NoSuchMappingDataSourceException;

	/**
	* Returns the last mapping data source in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching mapping data source, or <code>null</code> if a matching mapping data source could not be found
	*/
	public MappingDataSource fetchByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MappingDataSource> orderByComparator);

	/**
	* Returns the mapping data sources before and after the current mapping data source in the ordered set where companyId = &#63;.
	*
	* @param mappingDataSourceId the primary key of the current mapping data source
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next mapping data source
	* @throws NoSuchMappingDataSourceException if a mapping data source with the primary key could not be found
	*/
	public MappingDataSource[] findByCompanyId_PrevAndNext(
		long mappingDataSourceId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MappingDataSource> orderByComparator)
		throws NoSuchMappingDataSourceException;

	/**
	* Removes all the mapping data sources where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public void removeByCompanyId(long companyId);

	/**
	* Returns the number of mapping data sources where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching mapping data sources
	*/
	public int countByCompanyId(long companyId);

	/**
	* Caches the mapping data source in the entity cache if it is enabled.
	*
	* @param mappingDataSource the mapping data source
	*/
	public void cacheResult(MappingDataSource mappingDataSource);

	/**
	* Caches the mapping data sources in the entity cache if it is enabled.
	*
	* @param mappingDataSources the mapping data sources
	*/
	public void cacheResult(
		java.util.List<MappingDataSource> mappingDataSources);

	/**
	* Creates a new mapping data source with the primary key. Does not add the mapping data source to the database.
	*
	* @param mappingDataSourceId the primary key for the new mapping data source
	* @return the new mapping data source
	*/
	public MappingDataSource create(long mappingDataSourceId);

	/**
	* Removes the mapping data source with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param mappingDataSourceId the primary key of the mapping data source
	* @return the mapping data source that was removed
	* @throws NoSuchMappingDataSourceException if a mapping data source with the primary key could not be found
	*/
	public MappingDataSource remove(long mappingDataSourceId)
		throws NoSuchMappingDataSourceException;

	public MappingDataSource updateImpl(MappingDataSource mappingDataSource);

	/**
	* Returns the mapping data source with the primary key or throws a {@link NoSuchMappingDataSourceException} if it could not be found.
	*
	* @param mappingDataSourceId the primary key of the mapping data source
	* @return the mapping data source
	* @throws NoSuchMappingDataSourceException if a mapping data source with the primary key could not be found
	*/
	public MappingDataSource findByPrimaryKey(long mappingDataSourceId)
		throws NoSuchMappingDataSourceException;

	/**
	* Returns the mapping data source with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param mappingDataSourceId the primary key of the mapping data source
	* @return the mapping data source, or <code>null</code> if a mapping data source with the primary key could not be found
	*/
	public MappingDataSource fetchByPrimaryKey(long mappingDataSourceId);

	@Override
	public java.util.Map<java.io.Serializable, MappingDataSource> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the mapping data sources.
	*
	* @return the mapping data sources
	*/
	public java.util.List<MappingDataSource> findAll();

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
	public java.util.List<MappingDataSource> findAll(int start, int end);

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
	public java.util.List<MappingDataSource> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MappingDataSource> orderByComparator);

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
	public java.util.List<MappingDataSource> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MappingDataSource> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the mapping data sources from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of mapping data sources.
	*
	* @return the number of mapping data sources
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}