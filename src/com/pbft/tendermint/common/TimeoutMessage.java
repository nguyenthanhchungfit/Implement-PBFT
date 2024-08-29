package com.pbft.tendermint.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author chungnt
 * @version 1.0
 * @date 26/09/2023
 */
@Getter
@AllArgsConstructor
public class TimeoutMessage {
	private int height;
	private int round;
}
