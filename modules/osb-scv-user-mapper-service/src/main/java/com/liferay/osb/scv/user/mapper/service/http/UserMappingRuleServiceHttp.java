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

import com.liferay.osb.scv.user.mapper.service.UserMappingRuleServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link UserMappingRuleServiceUtil} service utility. The
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
 * @see UserMappingRuleServiceSoap
 * @see HttpPrincipal
 * @see UserMappingRuleServiceUtil
 * @generated
 */
@ProviderType
public class UserMappingRuleServiceHttp {
	public static com.liferay.portal.kernel.json.JSONArray getUserMappingRuleDestinationFields(
		HttpPrincipal httpPrincipal) throws java.lang.Exception {
		try {
			MethodKey methodKey = new MethodKey(UserMappingRuleServiceUtil.class,
					"getUserMappingRuleDestinationFields",
					_getUserMappingRuleDestinationFieldsParameterTypes0);

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

			return (com.liferay.portal.kernel.json.JSONArray)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONArray getUserMappingRuleFrequencies(
		HttpPrincipal httpPrincipal) {
		try {
			MethodKey methodKey = new MethodKey(UserMappingRuleServiceUtil.class,
					"getUserMappingRuleFrequencies",
					_getUserMappingRuleFrequenciesParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONArray)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONArray getUserMappingRules(
		HttpPrincipal httpPrincipal, java.lang.String destinationField)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(UserMappingRuleServiceUtil.class,
					"getUserMappingRules", _getUserMappingRulesParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					destinationField);

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

			return (com.liferay.portal.kernel.json.JSONArray)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.scv.user.mapper.model.UserMappingRule addUserMappingRule(
		HttpPrincipal httpPrincipal, long mappingDataSourceId, long fieldSetId,
		java.lang.String modelName, java.lang.String sourceField,
		java.lang.String destinationField, int frequency, boolean required)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(UserMappingRuleServiceUtil.class,
					"addUserMappingRule", _addUserMappingRuleParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					mappingDataSourceId, fieldSetId, modelName, sourceField,
					destinationField, frequency, required);

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

			return (com.liferay.osb.scv.user.mapper.model.UserMappingRule)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.scv.user.mapper.model.UserMappingRule addUserMappingRule(
		HttpPrincipal httpPrincipal, long mappingDataSourceId, long fieldSetId,
		java.lang.String modelName, java.lang.String sourceField,
		java.lang.String destinationField, int frequency, boolean sync,
		boolean required)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(UserMappingRuleServiceUtil.class,
					"addUserMappingRule", _addUserMappingRuleParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					mappingDataSourceId, fieldSetId, modelName, sourceField,
					destinationField, frequency, sync, required);

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

			return (com.liferay.osb.scv.user.mapper.model.UserMappingRule)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.scv.user.mapper.model.UserMappingRule> deleteUserMappingRules(
		HttpPrincipal httpPrincipal, java.lang.String destinationField)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(UserMappingRuleServiceUtil.class,
					"deleteUserMappingRules",
					_deleteUserMappingRulesParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					destinationField);

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

			return (java.util.List<com.liferay.osb.scv.user.mapper.model.UserMappingRule>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.scv.user.mapper.model.UserMappingRule deleteUserMappingRule(
		HttpPrincipal httpPrincipal, long userMappingRuleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(UserMappingRuleServiceUtil.class,
					"deleteUserMappingRule",
					_deleteUserMappingRuleParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					userMappingRuleId);

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

			return (com.liferay.osb.scv.user.mapper.model.UserMappingRule)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.scv.user.mapper.model.UserMappingRule> getUserMappingRules(
		HttpPrincipal httpPrincipal, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(UserMappingRuleServiceUtil.class,
					"getUserMappingRules", _getUserMappingRulesParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey, start,
					end);

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

			return (java.util.List<com.liferay.osb.scv.user.mapper.model.UserMappingRule>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(UserMappingRuleServiceHttp.class);
	private static final Class<?>[] _getUserMappingRuleDestinationFieldsParameterTypes0 =
		new Class[] {  };
	private static final Class<?>[] _getUserMappingRuleFrequenciesParameterTypes1 =
		new Class[] {  };
	private static final Class<?>[] _getUserMappingRulesParameterTypes2 = new Class[] {
			java.lang.String.class
		};
	private static final Class<?>[] _addUserMappingRuleParameterTypes3 = new Class[] {
			long.class, long.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class, int.class,
			boolean.class
		};
	private static final Class<?>[] _addUserMappingRuleParameterTypes4 = new Class[] {
			long.class, long.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class, int.class,
			boolean.class, boolean.class
		};
	private static final Class<?>[] _deleteUserMappingRulesParameterTypes5 = new Class[] {
			java.lang.String.class
		};
	private static final Class<?>[] _deleteUserMappingRuleParameterTypes6 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getUserMappingRulesParameterTypes7 = new Class[] {
			int.class, int.class
		};
}