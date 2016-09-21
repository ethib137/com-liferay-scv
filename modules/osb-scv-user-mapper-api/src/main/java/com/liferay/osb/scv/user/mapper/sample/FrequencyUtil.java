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

package com.liferay.osb.scv.user.mapper.sample;

import com.liferay.portal.kernel.util.GetterUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shinn Lok
 */
public class FrequencyUtil {

	public static final int DAILY = 0;

	public static final int HOURLY = 1;

	public static final int ONCE = 2;

	public static final int SECOND = 3;

	public static List<Frequency> getFrequencies() {
		List<Frequency> frequencies = new ArrayList<>();

		for (final Object[] frequencyArray : _frequencies) {
			Frequency frequency = new Frequency() {

				public long getDelay() {
					return GetterUtil.getLong(frequencyArray[2]);
				}

				public int getFrequencyId() {
					return GetterUtil.getInteger(frequencyArray[0]);
				}

				public String getName() {
					return String.valueOf(frequencyArray[1]);
				}

			};

			frequencies.add(frequency);
		}

		return frequencies;
	}

	public static Frequency getFrequency(int frequencyId) {
		List<Frequency> frequencies = getFrequencies();

		return frequencies.get(frequencyId);
	}

	private static final Object[][] _frequencies = {
		{FrequencyUtil.DAILY, "daily", 60 * 60 * 24},
		{FrequencyUtil.HOURLY, "hourly", 60 * 60},
		{FrequencyUtil.ONCE, "once", 0},
		{FrequencyUtil.SECOND, "every-five-seconds", 5}
	};

}