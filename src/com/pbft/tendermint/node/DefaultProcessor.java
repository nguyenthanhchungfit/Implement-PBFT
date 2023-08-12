package com.pbft.tendermint.node;

import com.pbft.tendermint.core.grpc.GPreCommitMessage;
import com.pbft.tendermint.core.grpc.GPreVoteMessage;
import com.pbft.tendermint.core.grpc.GProposeMessage;
import com.pbft.tendermint.core.grpc.GResult;

import java.util.logging.Logger;

/**
 * @author chungnt
 * @version 1.0
 * @date 12/08/2023
 */
public class DefaultProcessor implements IProcessor {
	private static final Logger LOGGER = Logger.getLogger(DefaultProcessor.class.getName());

	@Override
	public GResult onReceiveProposeMessage(GProposeMessage message) {
		LOGGER.info("onReceiveProposeMessage: " + message);
		return GResult.newBuilder().setError(0).setData("OK").build();
	}

	@Override
	public GResult onReceivePreVoteMessage(GPreVoteMessage message) {
		LOGGER.info("onReceivePreVoteMessage: " + message);
		return GResult.newBuilder().setError(0).setData("OK").build();
	}

	@Override
	public GResult onReceivePreCommitMessage(GPreCommitMessage message) {
		LOGGER.info("onReceivePreCommitMessage: " + message);
		return GResult.newBuilder().setError(0).setData("OK").build();
	}
}
