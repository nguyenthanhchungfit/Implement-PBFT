package com.pbft.tendermint.node.schedule;

import com.pbft.tendermint.common.MessageType;
import com.pbft.tendermint.common.TendermintMessage;
import com.pbft.tendermint.common.TimeoutMessage;
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
public class TimeoutProposeHandler implements Runnable {
	private CompliantProcessor processor;
	private int height;
	private int round;

	@Override
	public void run() {
		TimeoutMessage timeoutMsg = new TimeoutMessage(height, round);
		TendermintMessage msg = new TendermintMessage(MessageType.TIMEOUT_PROPOSE, timeoutMsg);
		processor.submitJob(msg);
	}
}
