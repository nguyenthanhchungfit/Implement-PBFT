package com.pbft.tendermint.node.schedule;

import com.pbft.tendermint.node.CompliantProcessor;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.logging.Logger;

/**
 * @author chungnt
 * @version 1.0
 * @date 19/08/2023
 */

@NoArgsConstructor
@AllArgsConstructor
public class TimeoutProposeHandler implements Runnable {
	private Logger logger;
	private CompliantProcessor processor;

	@Override
	public void run() {
		logger.info("TimeoutProposeHandler is running");
	}
}
