package com.pbft.tendermint.node;

import com.pbft.tendermint.core.grpc.*;

public interface IProcessor {
	public GPongMessage onPingMessage(GPingMessage message);

	public GResult onReceiveProposeMessage(GProposeMessage message);

	public GResult onReceivePreVoteMessage(GPreVoteMessage message);

	public GResult onReceivePreCommitMessage(GPreCommitMessage message);
	public void startConsensus();
}
