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

package com.liferay.osb.scv.user.mapper.internal.util;

import com.liferay.osb.scv.user.mapper.internal.event.Event;
import com.liferay.osb.scv.user.mapper.internal.event.GetFieldsEvent;
import com.liferay.osb.scv.user.mapper.internal.event.UpdateUsersEvent;
import com.liferay.osb.scv.user.mapper.model.UserMappingRule;
import com.liferay.osb.scv.user.mapper.sample.DataSource;
import com.liferay.osb.scv.user.mapper.sample.DataSourceUtil;
import com.liferay.osb.scv.user.mapper.sample.Frequency;
import com.liferay.osb.scv.user.mapper.sample.FrequencyUtil;
import com.liferay.osb.scv.user.mapper.service.UserMappingRuleLocalService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Shinn Lok
 */
@Component(immediate = true)
public class SchedulerUtil {

	@Activate
	public void activate() throws Exception {
		DataSourceUtil.clearDataSources();

		List<Frequency> frequencies = FrequencyUtil.getFrequencies();

		_scheduledExecutorService = Executors.newScheduledThreadPool(
			frequencies.size() - 1);

		for (DataSource dataSource : DataSourceUtil.getDataSources()) {
			List<String> tableNames = dataSource.getTableNames();

			if (tableNames.isEmpty()) {
				GetFieldsEvent getFieldsEvent = new GetFieldsEvent(
					dataSource.getDataSourceId());

				getFieldsEvent.run();
			}
		}

		for (final Frequency frequency : frequencies) {
			if (frequency.getFrequencyId() == FrequencyUtil.ONCE) {
				continue;
			}

			Runnable runnable = new Runnable() {

				public void run() {
					for (DataSource dataSource : DataSourceUtil.getDataSources()) {
						String type = dataSource.getType();

						if (!type.equals("biographical")) {
							continue;
						}

						try {
							List<UserMappingRule> userMappingRules =
								_userMappingRuleLocalService.getUserMappingRules(
									dataSource.getDataSourceId(),
									frequency.getFrequencyId());

							Event updateUsersEvent = new UpdateUsersEvent(
								dataSource.getDataSourceId(), userMappingRules);

							updateUsersEvent.run();
						}
						catch (Exception e) {
							_log.error(e, e);
						}
					}
				}

			};

			_scheduledExecutorService.scheduleWithFixedDelay(
				runnable, 0, frequency.getDelay(), TimeUnit.SECONDS);
		}
	}

	@Deactivate
	protected void deactivate() {
		_scheduledExecutorService.shutdown();
	}

	private ScheduledExecutorService _scheduledExecutorService;

	private static final Log _log = LogFactoryUtil.getLog(
		SchedulerUtil.class);

	@Reference
	private UserMappingRuleLocalService _userMappingRuleLocalService;

}