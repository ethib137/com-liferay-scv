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

package com.liferay.osb.scv.user.mapper.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.scv.user.mapper.service.MappingDataSourceServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link MappingDataSourceServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MappingDataSourceServiceSoap
 * @see HttpPrincipal
 * @see MappingDataSourceServiceUtil
 * @generated
 */
@ProviderType
public class MappingDataSourceServiceHttp {
	public static com.liferay.osb.scv.user.mapper.model.MappingDataSource deleteMappingDataSource(
		HttpPrincipal httpPrincipal, long mappingDataSourceId)
		throws java.lang.Exception {
		try {
			MethodKey methodKey = new MethodKey(MappingDataSourceServiceUtil.class,
					"deleteMappingDataSource",
					_deleteMappingDataSourceParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					mappingDataSourceId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof java.lang.Exception) {
					throw (java.lang.Exception)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.osb.scv.user.mapper.model.MappingDataSource)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.Map<java.lang.Long, java.lang.String> getMappingDataSourceTypes(
		HttpPrincipal httpPrincipal) throws java.lang.Exception {
		try {
			MethodKey methodKey = new MethodKey(MappingDataSourceServiceUtil.class,
					"getMappingDataSourceTypes",
					_getMappingDataSourceTypesParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof java.lang.Exception) {
					throw (java.lang.Exception)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.Map<java.lang.Long, java.lang.String>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.scv.user.mapper.model.MappingDataSource> getMappingDataSources(
		HttpPrincipal httpPrincipal) {
		try {
			MethodKey methodKey = new MethodKey(MappingDataSourceServiceUtil.class,
					"getMappingDataSources",
					_getMappingDataSourcesParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.osb.scv.user.mapper.model.MappingDataSource>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.scv.user.mapper.model.MappingDataSource addMappingDataSource(
		HttpPrincipal httpPrincipal, java.lang.String name,
		java.lang.String url, java.lang.String login,
		java.lang.String password, long type, java.lang.String availableFields)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(MappingDataSourceServiceUtil.class,
					"addMappingDataSource", _addMappingDataSourceParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, name,
					url, login, password, type, availableFields);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.osb.scv.user.mapper.model.MappingDataSource)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(MappingDataSourceServiceHttp.class);
	private static final Class<?>[] _deleteMappingDataSourceParameterTypes0 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getMappingDataSourceTypesParameterTypes1 = new Class[] {
			
		};
	private static final Class<?>[] _getMappingDataSourcesParameterTypes2 = new Class[] {
			
		};
	private static final Class<?>[] _addMappingDataSourceParameterTypes3 = new Class[] {
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class, long.class,
			java.lang.String.class
		};
}