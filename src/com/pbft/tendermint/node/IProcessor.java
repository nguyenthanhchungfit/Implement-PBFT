package com.pbft.tendermint.node;

import com.pbft.tendermint.core.grpc.GPreCommitMessage;
import com.pbft.tendermint.core.grpc.GPreVoteMessage;
import com.pbft.tendermint.core.grpc.GProposeMessage;
import com.pbft.tendermint.core.grpc.GResult;

public interface IProcessor {
	public GResult onReceiveProposeMessage(GProposeMessage message);

	public GResult onReceivePreVoteMessage(GPreVoteMessage message);

	public GResult onReceivePreCommitMessage(GPreCommitMessage message);
}
