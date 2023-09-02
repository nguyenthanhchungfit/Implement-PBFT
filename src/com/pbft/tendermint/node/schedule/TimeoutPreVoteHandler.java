package com.pbft.tendermint.node.schedule;

import com.pbft.tendermint.node.CompliantProcessor;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.Logger;


/**
 * @author chungnt
 * @version 1.0
 * @date 19/08/2023
 */

@NoArgsConstructor
@AllArgsConstructor
public class TimeoutPreVoteHandler implements Runnable {
	private Logger logger;
	private CompliantProcessor processor;
	private int height;
	private int round;

	@Override
	public void run() {
//		logger.info("TimeoutPreVoteHandler is running");
	}
}
