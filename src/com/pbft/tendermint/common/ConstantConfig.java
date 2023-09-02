package com.pbft.tendermint.common;

/**
 * @author chungnt
 * @version 1.0
 * @date 21/08/2023
 */
public class ConstantConfig {
	public static final long TIMEOUT_PROPOSE = 5000; // ms
	public static final long TIMEOUT_PRE_VOTE = 5000; // ms
	public static final long TIMEOUT_PRE_COMMIT = 5000; // ms

	public static final long TIME_DELAY_NEW_HEIGHT = 10000; // ms

	public static final int MAX_HEIGHT = 10;
}
