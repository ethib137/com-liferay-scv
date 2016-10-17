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
import com.liferay.osb.scv.user.mapper.model.MappingDataSource;
import com.liferay.osb.scv.user.mapper.model.UserMappingRule;
import com.liferay.osb.scv.user.mapper.sample.DataSource;
import com.liferay.osb.scv.user.mapper.sample.DataSourceUtil;
import com.liferay.osb.scv.user.mapper.sample.Frequency;
import com.liferay.osb.scv.user.mapper.sample.FrequencyUtil;
import com.liferay.osb.scv.user.mapper.service.MappingDataSourceLocalService;
import com.liferay.osb.scv.user.mapper.service.UserMappingRuleLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
		List<Frequency> frequencies = FrequencyUtil.getFrequencies();

		_scheduledExecutorService = Executors.newScheduledThreadPool(
			frequencies.size() - 2);

		for (final Frequency frequency : frequencies) {
			if ((frequency.getFrequencyId() == FrequencyUtil.ONCE) ||
				(frequency.getFrequencyId() == FrequencyUtil.INSTANT)) {

				continue;
			}

			Runnable runnable = new Runnable() {

				public void run() {
					for (MappingDataSource mappingDataSource :
							_mappingDataSourceLocalService.getMappingDataSources(-1, -1)) {

						try {
							List<UserMappingRule> userMappingRules =
								_userMappingRuleLocalService.getUserMappingRules(
									mappingDataSource.getMappingDataSourceId(),
									frequency.getFrequencyId());

							Event updateUsersEvent = new UpdateUsersEvent(
								mappingDataSource.getMappingDataSourceId(),
								userMappingRules);

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

	private static final Log _log = LogFactoryUtil.getLog(SchedulerUtil.class);

	private ScheduledExecutorService _scheduledExecutorService;

	@Reference
	private MappingDataSourceLocalService _mappingDataSourceLocalService;

	@Reference
	private UserMappingRuleLocalService _userMappingRuleLocalService;

}