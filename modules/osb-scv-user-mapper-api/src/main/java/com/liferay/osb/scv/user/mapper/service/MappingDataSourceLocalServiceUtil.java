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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for MappingDataSource. This utility wraps
 * {@link com.liferay.osb.scv.user.mapper.service.impl.MappingDataSourceLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see MappingDataSourceLocalService
 * @see com.liferay.osb.scv.user.mapper.service.base.MappingDataSourceLocalServiceBaseImpl
 * @see com.liferay.osb.scv.user.mapper.service.impl.MappingDataSourceLocalServiceImpl
 * @generated
 */
@ProviderType
public class MappingDataSourceLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.scv.user.mapper.service.impl.MappingDataSourceLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the mapping data source to the database. Also notifies the appropriate model listeners.
	*
	* @param mappingDataSource the mapping data source
	* @return the mapping data source that was added
	*/
	public static com.liferay.osb.scv.user.mapper.model.MappingDataSource addMappingDataSource(
		com.liferay.osb.scv.user.mapper.model.MappingDataSource mappingDataSource) {
		return getService().addMappingDataSource(mappingDataSource);
	}

	public static com.liferay.osb.scv.user.mapper.model.MappingDataSource addMappingDataSource(
		long companyId, java.lang.String name, java.lang.String url,
		java.lang.String login, java.lang.String password, long type,
		java.lang.String availableFields)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addMappingDataSource(companyId, name, url, login, password,
			type, availableFields);
	}

	/**
	* Creates a new mapping data source with the primary key. Does not add the mapping data source to the database.
	*
	* @param mappingDataSourceId the primary key for the new mapping data source
	* @return the new mapping data source
	*/
	public static com.liferay.osb.scv.user.mapper.model.MappingDataSource createMappingDataSource(
		long mappingDataSourceId) {
		return getService().createMappingDataSource(mappingDataSourceId);
	}

	/**
	* Deletes the mapping data source from the database. Also notifies the appropriate model listeners.
	*
	* @param mappingDataSource the mapping data source
	* @return the mapping data source that was removed
	*/
	public static com.liferay.osb.scv.user.mapper.model.MappingDataSource deleteMappingDataSource(
		com.liferay.osb.scv.user.mapper.model.MappingDataSource mappingDataSource) {
		return getService().deleteMappingDataSource(mappingDataSource);
	}

	/**
	* Deletes the mapping data source with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param mappingDataSourceId the primary key of the mapping data source
	* @return the mapping data source that was removed
	* @throws PortalException if a mapping data source with the primary key could not be found
	*/
	public static com.liferay.osb.scv.user.mapper.model.MappingDataSource deleteMappingDataSource(
		long mappingDataSourceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteMappingDataSource(mappingDataSourceId);
	}

	public static com.liferay.osb.scv.user.mapper.model.MappingDataSource fetchMappingDataSource(
		long mappingDataSourceId) {
		return getService().fetchMappingDataSource(mappingDataSourceId);
	}

	/**
	* Returns the mapping data source with the primary key.
	*
	* @param mappingDataSourceId the primary key of the mapping data source
	* @return the mapping data source
	* @throws PortalException if a mapping data source with the primary key could not be found
	*/
	public static com.liferay.osb.scv.user.mapper.model.MappingDataSource getMappingDataSource(
		long mappingDataSourceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getMappingDataSource(mappingDataSourceId);
	}

	/**
	* Updates the mapping data source in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param mappingDataSource the mapping data source
	* @return the mapping data source that was updated
	*/
	public static com.liferay.osb.scv.user.mapper.model.MappingDataSource updateMappingDataSource(
		com.liferay.osb.scv.user.mapper.model.MappingDataSource mappingDataSource) {
		return getService().updateMappingDataSource(mappingDataSource);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of mapping data sources.
	*
	* @return the number of mapping data sources
	*/
	public static int getMappingDataSourcesCount() {
		return getService().getMappingDataSourcesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.scv.user.mapper.model.impl.MappingDataSourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.scv.user.mapper.model.impl.MappingDataSourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns a range of all the mapping data sources.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.scv.user.mapper.model.impl.MappingDataSourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of mapping data sources
	* @param end the upper bound of the range of mapping data sources (not inclusive)
	* @return the range of mapping data sources
	*/
	public static java.util.List<com.liferay.osb.scv.user.mapper.model.MappingDataSource> getMappingDataSources(
		int start, int end) {
		return getService().getMappingDataSources(start, end);
	}

	public static java.util.List<com.liferay.osb.scv.user.mapper.model.MappingDataSource> getMappingDataSources(
		long companyId) {
		return getService().getMappingDataSources(companyId);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static MappingDataSourceLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MappingDataSourceLocalService, MappingDataSourceLocalService> _serviceTracker =
		ServiceTrackerFactory.open(MappingDataSourceLocalService.class);
}