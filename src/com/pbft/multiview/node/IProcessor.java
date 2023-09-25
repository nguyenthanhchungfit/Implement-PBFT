package com.pbft.multiview.node;

import com.pbft.multiview.core.grpc.*;

public interface IProcessor {
	public GPongMessage onPingMessage(GPingMessage message);

	public GResult onReceiveProposeMessage(GProposeMessage message);

	public GResult onReceiveVoteMessage(GVoteMessage message);
	public void startConsensus();
}
