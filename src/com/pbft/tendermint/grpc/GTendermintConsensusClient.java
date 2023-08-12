package com.pbft.tendermint.grpc;

import com.pbft.tendermint.core.grpc.*;
import io.grpc.Channel;

import java.util.logging.Logger;

/**
 * @author chungnt
 * @version 1.0
 * @date 12/08/2023
 */
public class GTendermintConsensusClient {
	private static final Logger LOGGER = Logger.getLogger(GTendermintConsensusClient.class.getName());

	private final GConsensusProtocolServiceGrpc.GConsensusProtocolServiceBlockingStub blockingStub;

	public GTendermintConsensusClient(Channel channel) {
		this.blockingStub = GConsensusProtocolServiceGrpc.newBlockingStub(channel);
	}

	public GResult sendProposeMessage(GProposeMessage message) {
		return this.blockingStub.onProposeMessage(message);
	}

	public GResult sendPreVoteMessage(GPreVoteMessage message) {
		return this.blockingStub.onPreVoteMessage(message);
	}

	public GResult sendPreCommitMessage(GPreCommitMessage message) {
		return this.blockingStub.onPreCommitMessage(message);
	}
}
