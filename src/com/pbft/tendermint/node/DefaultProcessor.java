package com.pbft.tendermint.node;

import com.pbft.tendermint.core.grpc.*;

import java.util.logging.Logger;

/**
 * @author chungnt
 * @version 1.0
 * @date 12/08/2023
 */
public class DefaultProcessor implements IProcessor {
	private static final Logger LOGGER = Logger.getLogger(DefaultProcessor.class.getName());

	@Override
	public GPongMessage onPingMessage(GPingMessage message) {
		LOGGER.info("onPingMessage: " + message);
		return GPongMessage.newBuilder().setError(0).setNodeId(0).build();
	}

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
