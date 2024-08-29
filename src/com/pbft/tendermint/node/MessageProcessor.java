package com.pbft.tendermint.node;

import com.pbft.tendermint.common.ConstantConfig;
import com.pbft.tendermint.common.TendermintMessage;
import com.pbft.utils.SystemUtils;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author chungnt
 * @version 1.0
 * @date 26/09/2023
 */
@AllArgsConstructor
public class MessageProcessor implements Runnable {

	private Logger logger;
	private CompliantProcessor processor;

	@Override
	public void run() {
		ConcurrentLinkedQueue<TendermintMessage> queue = this.processor.getQueueMessage();
		boolean isOK = true;
		while (isOK) {
			try {
				if (queue.isEmpty()) {
					SystemUtils.sleep(ConstantConfig.DELAY_POLL_MESSAGE);
				}
				TendermintMessage message = queue.poll();
				switch (message.getType()) {
					case PROPOSE_MSG:
						processor.processReceivedProposeMessage(message.getProposeMessage());
						break;
					case TIMEOUT_PROPOSE:
						processor.doTimeoutPropose(message.getTimeoutMessage().getHeight(), message.getTimeoutMessage().getRound());
						break;
					case PRE_VOTE_MSG:
						processor.processReceivePreVoteMessage(message.getPreVoteMessage());
						break;
					case TIMEOUT_PRE_VOTE:
						processor.doTimeoutPreVote(message.getTimeoutMessage().getHeight(), message.getTimeoutMessage().getRound());
						break;
					case PRE_COMMIT_MSG:
						processor.processReceivePreCommitMessage(message.getPreCommitMessage());
						break;
					case TIMEOUT_PRE_COMMIT:
						processor.doTimeoutPreCommit(message.getTimeoutMessage().getHeight(), message.getTimeoutMessage().getRound());
						break;
					default:
						this.logger.info("Not support this message");
						break;
				}
			} catch (Exception ex) {
				this.logger.error(ex.getMessage(), ex);
				isOK = false;
				this.logger.error("STOP process message!!!");
			}
		}
	}
}
