package com.pbft.multiview.node;

import com.pbft.multiview.core.grpc.*;
import com.pbft.multiview.node.IProcessor;

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
	public GResult onReceiveVoteMessage(GVoteMessage message) {
		LOGGER.info("onReceiveVoteMessage: " + message);
		return GResult.newBuilder().setError(0).setData("OK").build();
	}

	@Override
	public void startConsensus() {
		LOGGER.info("NOT IMPLEMENT");
	}
}
