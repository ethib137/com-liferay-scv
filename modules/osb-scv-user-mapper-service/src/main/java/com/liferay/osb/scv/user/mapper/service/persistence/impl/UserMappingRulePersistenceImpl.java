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

import com.liferay.osb.scv.user.mapper.exception.NoSuchUserMappingRuleException;
import com.liferay.osb.scv.user.mapper.model.UserMappingRule;
import com.liferay.osb.scv.user.mapper.model.impl.UserMappingRuleImpl;
import com.liferay.osb.scv.user.mapper.model.impl.UserMappingRuleModelImpl;
import com.liferay.osb.scv.user.mapper.service.persistence.UserMappingRulePersistence;

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
 * The persistence implementation for the user mapping rule service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserMappingRulePersistence
 * @see com.liferay.osb.scv.user.mapper.service.persistence.UserMappingRuleUtil
 * @generated
 */
@ProviderType
public class UserMappingRulePersistenceImpl extends BasePersistenceImpl<UserMappingRule>
	implements UserMappingRulePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link UserMappingRuleUtil} to access the user mapping rule persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = UserMappingRuleImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(UserMappingRuleModelImpl.ENTITY_CACHE_ENABLED,
			UserMappingRuleModelImpl.FINDER_CACHE_ENABLED,
			UserMappingRuleImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(UserMappingRuleModelImpl.ENTITY_CACHE_ENABLED,
			UserMappingRuleModelImpl.FINDER_CACHE_ENABLED,
			UserMappingRuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(UserMappingRuleModelImpl.ENTITY_CACHE_ENABLED,
			UserMappingRuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(UserMappingRuleModelImpl.ENTITY_CACHE_ENABLED,
			UserMappingRuleModelImpl.FINDER_CACHE_ENABLED,
			UserMappingRuleImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(UserMappingRuleModelImpl.ENTITY_CACHE_ENABLED,
			UserMappingRuleModelImpl.FINDER_CACHE_ENABLED,
			UserMappingRuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			UserMappingRuleModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(UserMappingRuleModelImpl.ENTITY_CACHE_ENABLED,
			UserMappingRuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the user mapping rules where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching user mapping rules
	 */
	@Override
	public List<UserMappingRule> findByCompanyId(long companyId) {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
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
	@Override
	public List<UserMappingRule> findByCompanyId(long companyId, int start,
		int end) {
		return findByCompanyId(companyId, start, end, null);
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
	@Override
	public List<UserMappingRule> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<UserMappingRule> orderByComparator) {
		return findByCompanyId(companyId, start, end, orderByComparator, true);
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
	@Override
	public List<UserMappingRule> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<UserMappingRule> orderByComparator,
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

		List<UserMappingRule> list = null;

		if (retrieveFromCache) {
			list = (List<UserMappingRule>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (UserMappingRule userMappingRule : list) {
					if ((companyId != userMappingRule.getCompanyId())) {
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

			query.append(_SQL_SELECT_USERMAPPINGRULE_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(UserMappingRuleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<UserMappingRule>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UserMappingRule>)QueryUtil.list(q,
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
	 * Returns the first user mapping rule in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user mapping rule
	 * @throws NoSuchUserMappingRuleException if a matching user mapping rule could not be found
	 */
	@Override
	public UserMappingRule findByCompanyId_First(long companyId,
		OrderByComparator<UserMappingRule> orderByComparator)
		throws NoSuchUserMappingRuleException {
		UserMappingRule userMappingRule = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (userMappingRule != null) {
			return userMappingRule;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserMappingRuleException(msg.toString());
	}

	/**
	 * Returns the first user mapping rule in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user mapping rule, or <code>null</code> if a matching user mapping rule could not be found
	 */
	@Override
	public UserMappingRule fetchByCompanyId_First(long companyId,
		OrderByComparator<UserMappingRule> orderByComparator) {
		List<UserMappingRule> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user mapping rule in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user mapping rule
	 * @throws NoSuchUserMappingRuleException if a matching user mapping rule could not be found
	 */
	@Override
	public UserMappingRule findByCompanyId_Last(long companyId,
		OrderByComparator<UserMappingRule> orderByComparator)
		throws NoSuchUserMappingRuleException {
		UserMappingRule userMappingRule = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (userMappingRule != null) {
			return userMappingRule;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserMappingRuleException(msg.toString());
	}

	/**
	 * Returns the last user mapping rule in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user mapping rule, or <code>null</code> if a matching user mapping rule could not be found
	 */
	@Override
	public UserMappingRule fetchByCompanyId_Last(long companyId,
		OrderByComparator<UserMappingRule> orderByComparator) {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<UserMappingRule> list = findByCompanyId(companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public UserMappingRule[] findByCompanyId_PrevAndNext(
		long userMappingRuleId, long companyId,
		OrderByComparator<UserMappingRule> orderByComparator)
		throws NoSuchUserMappingRuleException {
		UserMappingRule userMappingRule = findByPrimaryKey(userMappingRuleId);

		Session session = null;

		try {
			session = openSession();

			UserMappingRule[] array = new UserMappingRuleImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, userMappingRule,
					companyId, orderByComparator, true);

			array[1] = userMappingRule;

			array[2] = getByCompanyId_PrevAndNext(session, userMappingRule,
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

	protected UserMappingRule getByCompanyId_PrevAndNext(Session session,
		UserMappingRule userMappingRule, long companyId,
		OrderByComparator<UserMappingRule> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_USERMAPPINGRULE_WHERE);

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
			query.append(UserMappingRuleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(userMappingRule);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<UserMappingRule> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the user mapping rules where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (UserMappingRule userMappingRule : findByCompanyId(companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(userMappingRule);
		}
	}

	/**
	 * Returns the number of user mapping rules where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching user mapping rules
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_USERMAPPINGRULE_WHERE);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "userMappingRule.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_D_F = new FinderPath(UserMappingRuleModelImpl.ENTITY_CACHE_ENABLED,
			UserMappingRuleModelImpl.FINDER_CACHE_ENABLED,
			UserMappingRuleImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByD_F",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_D_F = new FinderPath(UserMappingRuleModelImpl.ENTITY_CACHE_ENABLED,
			UserMappingRuleModelImpl.FINDER_CACHE_ENABLED,
			UserMappingRuleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByD_F",
			new String[] { Long.class.getName(), Integer.class.getName() },
			UserMappingRuleModelImpl.DATASOURCEID_COLUMN_BITMASK |
			UserMappingRuleModelImpl.FREQUENCY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_D_F = new FinderPath(UserMappingRuleModelImpl.ENTITY_CACHE_ENABLED,
			UserMappingRuleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByD_F",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the user mapping rules where dataSourceId = &#63; and frequency = &#63;.
	 *
	 * @param dataSourceId the data source ID
	 * @param frequency the frequency
	 * @return the matching user mapping rules
	 */
	@Override
	public List<UserMappingRule> findByD_F(long dataSourceId, int frequency) {
		return findByD_F(dataSourceId, frequency, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<UserMappingRule> findByD_F(long dataSourceId, int frequency,
		int start, int end) {
		return findByD_F(dataSourceId, frequency, start, end, null);
	}

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
	@Override
	public List<UserMappingRule> findByD_F(long dataSourceId, int frequency,
		int start, int end, OrderByComparator<UserMappingRule> orderByComparator) {
		return findByD_F(dataSourceId, frequency, start, end,
			orderByComparator, true);
	}

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
	@Override
	public List<UserMappingRule> findByD_F(long dataSourceId, int frequency,
		int start, int end,
		OrderByComparator<UserMappingRule> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_D_F;
			finderArgs = new Object[] { dataSourceId, frequency };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_D_F;
			finderArgs = new Object[] {
					dataSourceId, frequency,
					
					start, end, orderByComparator
				};
		}

		List<UserMappingRule> list = null;

		if (retrieveFromCache) {
			list = (List<UserMappingRule>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (UserMappingRule userMappingRule : list) {
					if ((dataSourceId != userMappingRule.getDataSourceId()) ||
							(frequency != userMappingRule.getFrequency())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_USERMAPPINGRULE_WHERE);

			query.append(_FINDER_COLUMN_D_F_DATASOURCEID_2);

			query.append(_FINDER_COLUMN_D_F_FREQUENCY_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(UserMappingRuleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dataSourceId);

				qPos.add(frequency);

				if (!pagination) {
					list = (List<UserMappingRule>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UserMappingRule>)QueryUtil.list(q,
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
	 * Returns the first user mapping rule in the ordered set where dataSourceId = &#63; and frequency = &#63;.
	 *
	 * @param dataSourceId the data source ID
	 * @param frequency the frequency
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user mapping rule
	 * @throws NoSuchUserMappingRuleException if a matching user mapping rule could not be found
	 */
	@Override
	public UserMappingRule findByD_F_First(long dataSourceId, int frequency,
		OrderByComparator<UserMappingRule> orderByComparator)
		throws NoSuchUserMappingRuleException {
		UserMappingRule userMappingRule = fetchByD_F_First(dataSourceId,
				frequency, orderByComparator);

		if (userMappingRule != null) {
			return userMappingRule;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dataSourceId=");
		msg.append(dataSourceId);

		msg.append(", frequency=");
		msg.append(frequency);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserMappingRuleException(msg.toString());
	}

	/**
	 * Returns the first user mapping rule in the ordered set where dataSourceId = &#63; and frequency = &#63;.
	 *
	 * @param dataSourceId the data source ID
	 * @param frequency the frequency
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user mapping rule, or <code>null</code> if a matching user mapping rule could not be found
	 */
	@Override
	public UserMappingRule fetchByD_F_First(long dataSourceId, int frequency,
		OrderByComparator<UserMappingRule> orderByComparator) {
		List<UserMappingRule> list = findByD_F(dataSourceId, frequency, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user mapping rule in the ordered set where dataSourceId = &#63; and frequency = &#63;.
	 *
	 * @param dataSourceId the data source ID
	 * @param frequency the frequency
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user mapping rule
	 * @throws NoSuchUserMappingRuleException if a matching user mapping rule could not be found
	 */
	@Override
	public UserMappingRule findByD_F_Last(long dataSourceId, int frequency,
		OrderByComparator<UserMappingRule> orderByComparator)
		throws NoSuchUserMappingRuleException {
		UserMappingRule userMappingRule = fetchByD_F_Last(dataSourceId,
				frequency, orderByComparator);

		if (userMappingRule != null) {
			return userMappingRule;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("dataSourceId=");
		msg.append(dataSourceId);

		msg.append(", frequency=");
		msg.append(frequency);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserMappingRuleException(msg.toString());
	}

	/**
	 * Returns the last user mapping rule in the ordered set where dataSourceId = &#63; and frequency = &#63;.
	 *
	 * @param dataSourceId the data source ID
	 * @param frequency the frequency
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user mapping rule, or <code>null</code> if a matching user mapping rule could not be found
	 */
	@Override
	public UserMappingRule fetchByD_F_Last(long dataSourceId, int frequency,
		OrderByComparator<UserMappingRule> orderByComparator) {
		int count = countByD_F(dataSourceId, frequency);

		if (count == 0) {
			return null;
		}

		List<UserMappingRule> list = findByD_F(dataSourceId, frequency,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	@Override
	public UserMappingRule[] findByD_F_PrevAndNext(long userMappingRuleId,
		long dataSourceId, int frequency,
		OrderByComparator<UserMappingRule> orderByComparator)
		throws NoSuchUserMappingRuleException {
		UserMappingRule userMappingRule = findByPrimaryKey(userMappingRuleId);

		Session session = null;

		try {
			session = openSession();

			UserMappingRule[] array = new UserMappingRuleImpl[3];

			array[0] = getByD_F_PrevAndNext(session, userMappingRule,
					dataSourceId, frequency, orderByComparator, true);

			array[1] = userMappingRule;

			array[2] = getByD_F_PrevAndNext(session, userMappingRule,
					dataSourceId, frequency, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected UserMappingRule getByD_F_PrevAndNext(Session session,
		UserMappingRule userMappingRule, long dataSourceId, int frequency,
		OrderByComparator<UserMappingRule> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_USERMAPPINGRULE_WHERE);

		query.append(_FINDER_COLUMN_D_F_DATASOURCEID_2);

		query.append(_FINDER_COLUMN_D_F_FREQUENCY_2);

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
			query.append(UserMappingRuleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(dataSourceId);

		qPos.add(frequency);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(userMappingRule);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<UserMappingRule> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the user mapping rules where dataSourceId = &#63; and frequency = &#63; from the database.
	 *
	 * @param dataSourceId the data source ID
	 * @param frequency the frequency
	 */
	@Override
	public void removeByD_F(long dataSourceId, int frequency) {
		for (UserMappingRule userMappingRule : findByD_F(dataSourceId,
				frequency, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(userMappingRule);
		}
	}

	/**
	 * Returns the number of user mapping rules where dataSourceId = &#63; and frequency = &#63;.
	 *
	 * @param dataSourceId the data source ID
	 * @param frequency the frequency
	 * @return the number of matching user mapping rules
	 */
	@Override
	public int countByD_F(long dataSourceId, int frequency) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_D_F;

		Object[] finderArgs = new Object[] { dataSourceId, frequency };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_USERMAPPINGRULE_WHERE);

			query.append(_FINDER_COLUMN_D_F_DATASOURCEID_2);

			query.append(_FINDER_COLUMN_D_F_FREQUENCY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(dataSourceId);

				qPos.add(frequency);

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

	private static final String _FINDER_COLUMN_D_F_DATASOURCEID_2 = "userMappingRule.dataSourceId = ? AND ";
	private static final String _FINDER_COLUMN_D_F_FREQUENCY_2 = "userMappingRule.frequency = ?";

	public UserMappingRulePersistenceImpl() {
		setModelClass(UserMappingRule.class);
	}

	/**
	 * Caches the user mapping rule in the entity cache if it is enabled.
	 *
	 * @param userMappingRule the user mapping rule
	 */
	@Override
	public void cacheResult(UserMappingRule userMappingRule) {
		entityCache.putResult(UserMappingRuleModelImpl.ENTITY_CACHE_ENABLED,
			UserMappingRuleImpl.class, userMappingRule.getPrimaryKey(),
			userMappingRule);

		userMappingRule.resetOriginalValues();
	}

	/**
	 * Caches the user mapping rules in the entity cache if it is enabled.
	 *
	 * @param userMappingRules the user mapping rules
	 */
	@Override
	public void cacheResult(List<UserMappingRule> userMappingRules) {
		for (UserMappingRule userMappingRule : userMappingRules) {
			if (entityCache.getResult(
						UserMappingRuleModelImpl.ENTITY_CACHE_ENABLED,
						UserMappingRuleImpl.class,
						userMappingRule.getPrimaryKey()) == null) {
				cacheResult(userMappingRule);
			}
			else {
				userMappingRule.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all user mapping rules.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(UserMappingRuleImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the user mapping rule.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UserMappingRule userMappingRule) {
		entityCache.removeResult(UserMappingRuleModelImpl.ENTITY_CACHE_ENABLED,
			UserMappingRuleImpl.class, userMappingRule.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<UserMappingRule> userMappingRules) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (UserMappingRule userMappingRule : userMappingRules) {
			entityCache.removeResult(UserMappingRuleModelImpl.ENTITY_CACHE_ENABLED,
				UserMappingRuleImpl.class, userMappingRule.getPrimaryKey());
		}
	}

	/**
	 * Creates a new user mapping rule with the primary key. Does not add the user mapping rule to the database.
	 *
	 * @param userMappingRuleId the primary key for the new user mapping rule
	 * @return the new user mapping rule
	 */
	@Override
	public UserMappingRule create(long userMappingRuleId) {
		UserMappingRule userMappingRule = new UserMappingRuleImpl();

		userMappingRule.setNew(true);
		userMappingRule.setPrimaryKey(userMappingRuleId);

		userMappingRule.setCompanyId(companyProvider.getCompanyId());

		return userMappingRule;
	}

	/**
	 * Removes the user mapping rule with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userMappingRuleId the primary key of the user mapping rule
	 * @return the user mapping rule that was removed
	 * @throws NoSuchUserMappingRuleException if a user mapping rule with the primary key could not be found
	 */
	@Override
	public UserMappingRule remove(long userMappingRuleId)
		throws NoSuchUserMappingRuleException {
		return remove((Serializable)userMappingRuleId);
	}

	/**
	 * Removes the user mapping rule with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the user mapping rule
	 * @return the user mapping rule that was removed
	 * @throws NoSuchUserMappingRuleException if a user mapping rule with the primary key could not be found
	 */
	@Override
	public UserMappingRule remove(Serializable primaryKey)
		throws NoSuchUserMappingRuleException {
		Session session = null;

		try {
			session = openSession();

			UserMappingRule userMappingRule = (UserMappingRule)session.get(UserMappingRuleImpl.class,
					primaryKey);

			if (userMappingRule == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUserMappingRuleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(userMappingRule);
		}
		catch (NoSuchUserMappingRuleException nsee) {
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
	protected UserMappingRule removeImpl(UserMappingRule userMappingRule) {
		userMappingRule = toUnwrappedModel(userMappingRule);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(userMappingRule)) {
				userMappingRule = (UserMappingRule)session.get(UserMappingRuleImpl.class,
						userMappingRule.getPrimaryKeyObj());
			}

			if (userMappingRule != null) {
				session.delete(userMappingRule);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (userMappingRule != null) {
			clearCache(userMappingRule);
		}

		return userMappingRule;
	}

	@Override
	public UserMappingRule updateImpl(UserMappingRule userMappingRule) {
		userMappingRule = toUnwrappedModel(userMappingRule);

		boolean isNew = userMappingRule.isNew();

		UserMappingRuleModelImpl userMappingRuleModelImpl = (UserMappingRuleModelImpl)userMappingRule;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (userMappingRule.getCreateDate() == null)) {
			if (serviceContext == null) {
				userMappingRule.setCreateDate(now);
			}
			else {
				userMappingRule.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!userMappingRuleModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				userMappingRule.setModifiedDate(now);
			}
			else {
				userMappingRule.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (userMappingRule.isNew()) {
				session.save(userMappingRule);

				userMappingRule.setNew(false);
			}
			else {
				userMappingRule = (UserMappingRule)session.merge(userMappingRule);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !UserMappingRuleModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((userMappingRuleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						userMappingRuleModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { userMappingRuleModelImpl.getCompanyId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_COMPANYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((userMappingRuleModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_D_F.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						userMappingRuleModelImpl.getOriginalDataSourceId(),
						userMappingRuleModelImpl.getOriginalFrequency()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_D_F, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_D_F,
					args);

				args = new Object[] {
						userMappingRuleModelImpl.getDataSourceId(),
						userMappingRuleModelImpl.getFrequency()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_D_F, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_D_F,
					args);
			}
		}

		entityCache.putResult(UserMappingRuleModelImpl.ENTITY_CACHE_ENABLED,
			UserMappingRuleImpl.class, userMappingRule.getPrimaryKey(),
			userMappingRule, false);

		userMappingRule.resetOriginalValues();

		return userMappingRule;
	}

	protected UserMappingRule toUnwrappedModel(UserMappingRule userMappingRule) {
		if (userMappingRule instanceof UserMappingRuleImpl) {
			return userMappingRule;
		}

		UserMappingRuleImpl userMappingRuleImpl = new UserMappingRuleImpl();

		userMappingRuleImpl.setNew(userMappingRule.isNew());
		userMappingRuleImpl.setPrimaryKey(userMappingRule.getPrimaryKey());

		userMappingRuleImpl.setUserMappingRuleId(userMappingRule.getUserMappingRuleId());
		userMappingRuleImpl.setCompanyId(userMappingRule.getCompanyId());
		userMappingRuleImpl.setUserId(userMappingRule.getUserId());
		userMappingRuleImpl.setUserName(userMappingRule.getUserName());
		userMappingRuleImpl.setCreateDate(userMappingRule.getCreateDate());
		userMappingRuleImpl.setModifiedDate(userMappingRule.getModifiedDate());
		userMappingRuleImpl.setDataSourceId(userMappingRule.getDataSourceId());
		userMappingRuleImpl.setFieldSetId(userMappingRule.getFieldSetId());
		userMappingRuleImpl.setModelName(userMappingRule.getModelName());
		userMappingRuleImpl.setSourceField(userMappingRule.getSourceField());
		userMappingRuleImpl.setDestinationField(userMappingRule.getDestinationField());
		userMappingRuleImpl.setFrequency(userMappingRule.getFrequency());

		return userMappingRuleImpl;
	}

	/**
	 * Returns the user mapping rule with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the user mapping rule
	 * @return the user mapping rule
	 * @throws NoSuchUserMappingRuleException if a user mapping rule with the primary key could not be found
	 */
	@Override
	public UserMappingRule findByPrimaryKey(Serializable primaryKey)
		throws NoSuchUserMappingRuleException {
		UserMappingRule userMappingRule = fetchByPrimaryKey(primaryKey);

		if (userMappingRule == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchUserMappingRuleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return userMappingRule;
	}

	/**
	 * Returns the user mapping rule with the primary key or throws a {@link NoSuchUserMappingRuleException} if it could not be found.
	 *
	 * @param userMappingRuleId the primary key of the user mapping rule
	 * @return the user mapping rule
	 * @throws NoSuchUserMappingRuleException if a user mapping rule with the primary key could not be found
	 */
	@Override
	public UserMappingRule findByPrimaryKey(long userMappingRuleId)
		throws NoSuchUserMappingRuleException {
		return findByPrimaryKey((Serializable)userMappingRuleId);
	}

	/**
	 * Returns the user mapping rule with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the user mapping rule
	 * @return the user mapping rule, or <code>null</code> if a user mapping rule with the primary key could not be found
	 */
	@Override
	public UserMappingRule fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(UserMappingRuleModelImpl.ENTITY_CACHE_ENABLED,
				UserMappingRuleImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		UserMappingRule userMappingRule = (UserMappingRule)serializable;

		if (userMappingRule == null) {
			Session session = null;

			try {
				session = openSession();

				userMappingRule = (UserMappingRule)session.get(UserMappingRuleImpl.class,
						primaryKey);

				if (userMappingRule != null) {
					cacheResult(userMappingRule);
				}
				else {
					entityCache.putResult(UserMappingRuleModelImpl.ENTITY_CACHE_ENABLED,
						UserMappingRuleImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(UserMappingRuleModelImpl.ENTITY_CACHE_ENABLED,
					UserMappingRuleImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return userMappingRule;
	}

	/**
	 * Returns the user mapping rule with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userMappingRuleId the primary key of the user mapping rule
	 * @return the user mapping rule, or <code>null</code> if a user mapping rule with the primary key could not be found
	 */
	@Override
	public UserMappingRule fetchByPrimaryKey(long userMappingRuleId) {
		return fetchByPrimaryKey((Serializable)userMappingRuleId);
	}

	@Override
	public Map<Serializable, UserMappingRule> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, UserMappingRule> map = new HashMap<Serializable, UserMappingRule>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			UserMappingRule userMappingRule = fetchByPrimaryKey(primaryKey);

			if (userMappingRule != null) {
				map.put(primaryKey, userMappingRule);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(UserMappingRuleModelImpl.ENTITY_CACHE_ENABLED,
					UserMappingRuleImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (UserMappingRule)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_USERMAPPINGRULE_WHERE_PKS_IN);

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

			for (UserMappingRule userMappingRule : (List<UserMappingRule>)q.list()) {
				map.put(userMappingRule.getPrimaryKeyObj(), userMappingRule);

				cacheResult(userMappingRule);

				uncachedPrimaryKeys.remove(userMappingRule.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(UserMappingRuleModelImpl.ENTITY_CACHE_ENABLED,
					UserMappingRuleImpl.class, primaryKey, nullModel);
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
	 * Returns all the user mapping rules.
	 *
	 * @return the user mapping rules
	 */
	@Override
	public List<UserMappingRule> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<UserMappingRule> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<UserMappingRule> findAll(int start, int end,
		OrderByComparator<UserMappingRule> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<UserMappingRule> findAll(int start, int end,
		OrderByComparator<UserMappingRule> orderByComparator,
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

		List<UserMappingRule> list = null;

		if (retrieveFromCache) {
			list = (List<UserMappingRule>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_USERMAPPINGRULE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_USERMAPPINGRULE;

				if (pagination) {
					sql = sql.concat(UserMappingRuleModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<UserMappingRule>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<UserMappingRule>)QueryUtil.list(q,
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
	 * Removes all the user mapping rules from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (UserMappingRule userMappingRule : findAll()) {
			remove(userMappingRule);
		}
	}

	/**
	 * Returns the number of user mapping rules.
	 *
	 * @return the number of user mapping rules
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_USERMAPPINGRULE);

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
	protected Map<String, Integer> getTableColumnsMap() {
		return UserMappingRuleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the user mapping rule persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(UserMappingRuleImpl.class.getName());
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
	private static final String _SQL_SELECT_USERMAPPINGRULE = "SELECT userMappingRule FROM UserMappingRule userMappingRule";
	private static final String _SQL_SELECT_USERMAPPINGRULE_WHERE_PKS_IN = "SELECT userMappingRule FROM UserMappingRule userMappingRule WHERE userMappingRuleId IN (";
	private static final String _SQL_SELECT_USERMAPPINGRULE_WHERE = "SELECT userMappingRule FROM UserMappingRule userMappingRule WHERE ";
	private static final String _SQL_COUNT_USERMAPPINGRULE = "SELECT COUNT(userMappingRule) FROM UserMappingRule userMappingRule";
	private static final String _SQL_COUNT_USERMAPPINGRULE_WHERE = "SELECT COUNT(userMappingRule) FROM UserMappingRule userMappingRule WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "userMappingRule.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No UserMappingRule exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No UserMappingRule exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(UserMappingRulePersistenceImpl.class);
}