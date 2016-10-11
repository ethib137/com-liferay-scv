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

package com.liferay.osb.scv.user.mapper.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.scv.user.mapper.exception.NoSuchMappingDataSourceException;
import com.liferay.osb.scv.user.mapper.model.MappingDataSource;
import com.liferay.osb.scv.user.mapper.model.impl.MappingDataSourceImpl;
import com.liferay.osb.scv.user.mapper.model.impl.MappingDataSourceModelImpl;
import com.liferay.osb.scv.user.mapper.service.persistence.MappingDataSourcePersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the mapping data source service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MappingDataSourcePersistence
 * @see com.liferay.osb.scv.user.mapper.service.persistence.MappingDataSourceUtil
 * @generated
 */
@ProviderType
public class MappingDataSourcePersistenceImpl extends BasePersistenceImpl<MappingDataSource>
	implements MappingDataSourcePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MappingDataSourceUtil} to access the mapping data source persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MappingDataSourceImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MappingDataSourceModelImpl.ENTITY_CACHE_ENABLED,
			MappingDataSourceModelImpl.FINDER_CACHE_ENABLED,
			MappingDataSourceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MappingDataSourceModelImpl.ENTITY_CACHE_ENABLED,
			MappingDataSourceModelImpl.FINDER_CACHE_ENABLED,
			MappingDataSourceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MappingDataSourceModelImpl.ENTITY_CACHE_ENABLED,
			MappingDataSourceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(MappingDataSourceModelImpl.ENTITY_CACHE_ENABLED,
			MappingDataSourceModelImpl.FINDER_CACHE_ENABLED,
			MappingDataSourceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(MappingDataSourceModelImpl.ENTITY_CACHE_ENABLED,
			MappingDataSourceModelImpl.FINDER_CACHE_ENABLED,
			MappingDataSourceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			MappingDataSourceModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(MappingDataSourceModelImpl.ENTITY_CACHE_ENABLED,
			MappingDataSourceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the mapping data sources where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching mapping data sources
	 */
	@Override
	public List<MappingDataSource> findByCompanyId(long companyId) {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
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
	@Override
	public List<MappingDataSource> findByCompanyId(long companyId, int start,
		int end) {
		return findByCompanyId(companyId, start, end, null);
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
	@Override
	public List<MappingDataSource> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<MappingDataSource> orderByComparator) {
		return findByCompanyId(companyId, start, end, orderByComparator, true);
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
	@Override
	public List<MappingDataSource> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<MappingDataSource> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId, start, end, orderByComparator };
		}

		List<MappingDataSource> list = null;

		if (retrieveFromCache) {
			list = (List<MappingDataSource>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MappingDataSource mappingDataSource : list) {
					if ((companyId != mappingDataSource.getCompanyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_MAPPINGDATASOURCE_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(MappingDataSourceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<MappingDataSource>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MappingDataSource>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first mapping data source in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mapping data source
	 * @throws NoSuchMappingDataSourceException if a matching mapping data source could not be found
	 */
	@Override
	public MappingDataSource findByCompanyId_First(long companyId,
		OrderByComparator<MappingDataSource> orderByComparator)
		throws NoSuchMappingDataSourceException {
		MappingDataSource mappingDataSource = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (mappingDataSource != null) {
			return mappingDataSource;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMappingDataSourceException(msg.toString());
	}

	/**
	 * Returns the first mapping data source in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching mapping data source, or <code>null</code> if a matching mapping data source could not be found
	 */
	@Override
	public MappingDataSource fetchByCompanyId_First(long companyId,
		OrderByComparator<MappingDataSource> orderByComparator) {
		List<MappingDataSource> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last mapping data source in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mapping data source
	 * @throws NoSuchMappingDataSourceException if a matching mapping data source could not be found
	 */
	@Override
	public MappingDataSource findByCompanyId_Last(long companyId,
		OrderByComparator<MappingDataSource> orderByComparator)
		throws NoSuchMappingDataSourceException {
		MappingDataSource mappingDataSource = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (mappingDataSource != null) {
			return mappingDataSource;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMappingDataSourceException(msg.toString());
	}

	/**
	 * Returns the last mapping data source in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching mapping data source, or <code>null</code> if a matching mapping data source could not be found
	 */
	@Override
	public MappingDataSource fetchByCompanyId_Last(long companyId,
		OrderByComparator<MappingDataSource> orderByComparator) {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<MappingDataSource> list = findByCompanyId(companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public MappingDataSource[] findByCompanyId_PrevAndNext(
		long mappingDataSourceId, long companyId,
		OrderByComparator<MappingDataSource> orderByComparator)
		throws NoSuchMappingDataSourceException {
		MappingDataSource mappingDataSource = findByPrimaryKey(mappingDataSourceId);

		Session session = null;

		try {
			session = openSession();

			MappingDataSource[] array = new MappingDataSourceImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, mappingDataSource,
					companyId, orderByComparator, true);

			array[1] = mappingDataSource;

			array[2] = getByCompanyId_PrevAndNext(session, mappingDataSource,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MappingDataSource getByCompanyId_PrevAndNext(Session session,
		MappingDataSource mappingDataSource, long companyId,
		OrderByComparator<MappingDataSource> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MAPPINGDATASOURCE_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(MappingDataSourceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(mappingDataSource);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MappingDataSource> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the mapping data sources where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (MappingDataSource mappingDataSource : findByCompanyId(companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(mappingDataSource);
		}
	}

	/**
	 * Returns the number of mapping data sources where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching mapping data sources
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MAPPINGDATASOURCE_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "mappingDataSource.companyId = ?";

	public MappingDataSourcePersistenceImpl() {
		setModelClass(MappingDataSource.class);
	}

	/**
	 * Caches the mapping data source in the entity cache if it is enabled.
	 *
	 * @param mappingDataSource the mapping data source
	 */
	@Override
	public void cacheResult(MappingDataSource mappingDataSource) {
		entityCache.putResult(MappingDataSourceModelImpl.ENTITY_CACHE_ENABLED,
			MappingDataSourceImpl.class, mappingDataSource.getPrimaryKey(),
			mappingDataSource);

		mappingDataSource.resetOriginalValues();
	}

	/**
	 * Caches the mapping data sources in the entity cache if it is enabled.
	 *
	 * @param mappingDataSources the mapping data sources
	 */
	@Override
	public void cacheResult(List<MappingDataSource> mappingDataSources) {
		for (MappingDataSource mappingDataSource : mappingDataSources) {
			if (entityCache.getResult(
						MappingDataSourceModelImpl.ENTITY_CACHE_ENABLED,
						MappingDataSourceImpl.class,
						mappingDataSource.getPrimaryKey()) == null) {
				cacheResult(mappingDataSource);
			}
			else {
				mappingDataSource.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all mapping data sources.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MappingDataSourceImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the mapping data source.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MappingDataSource mappingDataSource) {
		entityCache.removeResult(MappingDataSourceModelImpl.ENTITY_CACHE_ENABLED,
			MappingDataSourceImpl.class, mappingDataSource.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<MappingDataSource> mappingDataSources) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MappingDataSource mappingDataSource : mappingDataSources) {
			entityCache.removeResult(MappingDataSourceModelImpl.ENTITY_CACHE_ENABLED,
				MappingDataSourceImpl.class, mappingDataSource.getPrimaryKey());
		}
	}

	/**
	 * Creates a new mapping data source with the primary key. Does not add the mapping data source to the database.
	 *
	 * @param mappingDataSourceId the primary key for the new mapping data source
	 * @return the new mapping data source
	 */
	@Override
	public MappingDataSource create(long mappingDataSourceId) {
		MappingDataSource mappingDataSource = new MappingDataSourceImpl();

		mappingDataSource.setNew(true);
		mappingDataSource.setPrimaryKey(mappingDataSourceId);

		mappingDataSource.setCompanyId(companyProvider.getCompanyId());

		return mappingDataSource;
	}

	/**
	 * Removes the mapping data source with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param mappingDataSourceId the primary key of the mapping data source
	 * @return the mapping data source that was removed
	 * @throws NoSuchMappingDataSourceException if a mapping data source with the primary key could not be found
	 */
	@Override
	public MappingDataSource remove(long mappingDataSourceId)
		throws NoSuchMappingDataSourceException {
		return remove((Serializable)mappingDataSourceId);
	}

	/**
	 * Removes the mapping data source with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the mapping data source
	 * @return the mapping data source that was removed
	 * @throws NoSuchMappingDataSourceException if a mapping data source with the primary key could not be found
	 */
	@Override
	public MappingDataSource remove(Serializable primaryKey)
		throws NoSuchMappingDataSourceException {
		Session session = null;

		try {
			session = openSession();

			MappingDataSource mappingDataSource = (MappingDataSource)session.get(MappingDataSourceImpl.class,
					primaryKey);

			if (mappingDataSource == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMappingDataSourceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(mappingDataSource);
		}
		catch (NoSuchMappingDataSourceException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected MappingDataSource removeImpl(MappingDataSource mappingDataSource) {
		mappingDataSource = toUnwrappedModel(mappingDataSource);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(mappingDataSource)) {
				mappingDataSource = (MappingDataSource)session.get(MappingDataSourceImpl.class,
						mappingDataSource.getPrimaryKeyObj());
			}

			if (mappingDataSource != null) {
				session.delete(mappingDataSource);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (mappingDataSource != null) {
			clearCache(mappingDataSource);
		}

		return mappingDataSource;
	}

	@Override
	public MappingDataSource updateImpl(MappingDataSource mappingDataSource) {
		mappingDataSource = toUnwrappedModel(mappingDataSource);

		boolean isNew = mappingDataSource.isNew();

		MappingDataSourceModelImpl mappingDataSourceModelImpl = (MappingDataSourceModelImpl)mappingDataSource;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (mappingDataSource.getCreateDate() == null)) {
			if (serviceContext == null) {
				mappingDataSource.setCreateDate(now);
			}
			else {
				mappingDataSource.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!mappingDataSourceModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				mappingDataSource.setModifiedDate(now);
			}
			else {
				mappingDataSource.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (mappingDataSource.isNew()) {
				session.save(mappingDataSource);

				mappingDataSource.setNew(false);
			}
			else {
				mappingDataSource = (MappingDataSource)session.merge(mappingDataSource);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !MappingDataSourceModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((mappingDataSourceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						mappingDataSourceModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { mappingDataSourceModelImpl.getCompanyId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}
		}

		entityCache.putResult(MappingDataSourceModelImpl.ENTITY_CACHE_ENABLED,
			MappingDataSourceImpl.class, mappingDataSource.getPrimaryKey(),
			mappingDataSource, false);

		mappingDataSource.resetOriginalValues();

		return mappingDataSource;
	}

	protected MappingDataSource toUnwrappedModel(
		MappingDataSource mappingDataSource) {
		if (mappingDataSource instanceof MappingDataSourceImpl) {
			return mappingDataSource;
		}

		MappingDataSourceImpl mappingDataSourceImpl = new MappingDataSourceImpl();

		mappingDataSourceImpl.setNew(mappingDataSource.isNew());
		mappingDataSourceImpl.setPrimaryKey(mappingDataSource.getPrimaryKey());

		mappingDataSourceImpl.setMappingDataSourceId(mappingDataSource.getMappingDataSourceId());
		mappingDataSourceImpl.setCompanyId(mappingDataSource.getCompanyId());
		mappingDataSourceImpl.setUserId(mappingDataSource.getUserId());
		mappingDataSourceImpl.setUserName(mappingDataSource.getUserName());
		mappingDataSourceImpl.setCreateDate(mappingDataSource.getCreateDate());
		mappingDataSourceImpl.setModifiedDate(mappingDataSource.getModifiedDate());
		mappingDataSourceImpl.setName(mappingDataSource.getName());
		mappingDataSourceImpl.setUrl(mappingDataSource.getUrl());
		mappingDataSourceImpl.setLogin(mappingDataSource.getLogin());
		mappingDataSourceImpl.setPassword(mappingDataSource.getPassword());
		mappingDataSourceImpl.setType(mappingDataSource.getType());
		mappingDataSourceImpl.setAvailableFields(mappingDataSource.getAvailableFields());

		return mappingDataSourceImpl;
	}

	/**
	 * Returns the mapping data source with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the mapping data source
	 * @return the mapping data source
	 * @throws NoSuchMappingDataSourceException if a mapping data source with the primary key could not be found
	 */
	@Override
	public MappingDataSource findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMappingDataSourceException {
		MappingDataSource mappingDataSource = fetchByPrimaryKey(primaryKey);

		if (mappingDataSource == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMappingDataSourceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return mappingDataSource;
	}

	/**
	 * Returns the mapping data source with the primary key or throws a {@link NoSuchMappingDataSourceException} if it could not be found.
	 *
	 * @param mappingDataSourceId the primary key of the mapping data source
	 * @return the mapping data source
	 * @throws NoSuchMappingDataSourceException if a mapping data source with the primary key could not be found
	 */
	@Override
	public MappingDataSource findByPrimaryKey(long mappingDataSourceId)
		throws NoSuchMappingDataSourceException {
		return findByPrimaryKey((Serializable)mappingDataSourceId);
	}

	/**
	 * Returns the mapping data source with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the mapping data source
	 * @return the mapping data source, or <code>null</code> if a mapping data source with the primary key could not be found
	 */
	@Override
	public MappingDataSource fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(MappingDataSourceModelImpl.ENTITY_CACHE_ENABLED,
				MappingDataSourceImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		MappingDataSource mappingDataSource = (MappingDataSource)serializable;

		if (mappingDataSource == null) {
			Session session = null;

			try {
				session = openSession();

				mappingDataSource = (MappingDataSource)session.get(MappingDataSourceImpl.class,
						primaryKey);

				if (mappingDataSource != null) {
					cacheResult(mappingDataSource);
				}
				else {
					entityCache.putResult(MappingDataSourceModelImpl.ENTITY_CACHE_ENABLED,
						MappingDataSourceImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(MappingDataSourceModelImpl.ENTITY_CACHE_ENABLED,
					MappingDataSourceImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return mappingDataSource;
	}

	/**
	 * Returns the mapping data source with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param mappingDataSourceId the primary key of the mapping data source
	 * @return the mapping data source, or <code>null</code> if a mapping data source with the primary key could not be found
	 */
	@Override
	public MappingDataSource fetchByPrimaryKey(long mappingDataSourceId) {
		return fetchByPrimaryKey((Serializable)mappingDataSourceId);
	}

	@Override
	public Map<Serializable, MappingDataSource> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, MappingDataSource> map = new HashMap<Serializable, MappingDataSource>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			MappingDataSource mappingDataSource = fetchByPrimaryKey(primaryKey);

			if (mappingDataSource != null) {
				map.put(primaryKey, mappingDataSource);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(MappingDataSourceModelImpl.ENTITY_CACHE_ENABLED,
					MappingDataSourceImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (MappingDataSource)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_MAPPINGDATASOURCE_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (MappingDataSource mappingDataSource : (List<MappingDataSource>)q.list()) {
				map.put(mappingDataSource.getPrimaryKeyObj(), mappingDataSource);

				cacheResult(mappingDataSource);

				uncachedPrimaryKeys.remove(mappingDataSource.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(MappingDataSourceModelImpl.ENTITY_CACHE_ENABLED,
					MappingDataSourceImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the mapping data sources.
	 *
	 * @return the mapping data sources
	 */
	@Override
	public List<MappingDataSource> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<MappingDataSource> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<MappingDataSource> findAll(int start, int end,
		OrderByComparator<MappingDataSource> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<MappingDataSource> findAll(int start, int end,
		OrderByComparator<MappingDataSource> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<MappingDataSource> list = null;

		if (retrieveFromCache) {
			list = (List<MappingDataSource>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MAPPINGDATASOURCE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MAPPINGDATASOURCE;

				if (pagination) {
					sql = sql.concat(MappingDataSourceModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MappingDataSource>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MappingDataSource>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the mapping data sources from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MappingDataSource mappingDataSource : findAll()) {
			remove(mappingDataSource);
		}
	}

	/**
	 * Returns the number of mapping data sources.
	 *
	 * @return the number of mapping data sources
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MAPPINGDATASOURCE);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return MappingDataSourceModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the mapping data source persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(MappingDataSourceImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_MAPPINGDATASOURCE = "SELECT mappingDataSource FROM MappingDataSource mappingDataSource";
	private static final String _SQL_SELECT_MAPPINGDATASOURCE_WHERE_PKS_IN = "SELECT mappingDataSource FROM MappingDataSource mappingDataSource WHERE mappingDataSourceId IN (";
	private static final String _SQL_SELECT_MAPPINGDATASOURCE_WHERE = "SELECT mappingDataSource FROM MappingDataSource mappingDataSource WHERE ";
	private static final String _SQL_COUNT_MAPPINGDATASOURCE = "SELECT COUNT(mappingDataSource) FROM MappingDataSource mappingDataSource";
	private static final String _SQL_COUNT_MAPPINGDATASOURCE_WHERE = "SELECT COUNT(mappingDataSource) FROM MappingDataSource mappingDataSource WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "mappingDataSource.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MappingDataSource exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MappingDataSource exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(MappingDataSourcePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"password", "type"
			});
}