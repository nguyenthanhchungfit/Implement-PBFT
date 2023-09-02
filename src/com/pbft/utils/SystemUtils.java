package com.pbft.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author chungnt
 * @version 1.0
 * @date 22/08/2023
 */
public class SystemUtils {
	private static final Logger LOGGER = LogManager.getLogger(SystemUtils.class);

	public static void sleep(long timeInMillis) {
		try {
			Thread.sleep(timeInMillis);
		} catch (InterruptedException ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}

}
