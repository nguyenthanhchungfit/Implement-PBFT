package com.pbft.tendermint.grpc;

import com.pbft.tendermint.core.grpc.GData;
import com.pbft.tendermint.core.grpc.GProposeMessage;
import com.pbft.tendermint.core.grpc.GResult;
import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import org.junit.Before;
import org.junit.Test;

/**
 * @author chungnt
 * @version 1.0
 * @date 12/08/2023
 */
public class GTendermintConsensusClientTest {

	GTendermintConsensusClient _client;

	@Before
	public void init() {
		String host = "localhost:8000";
		ManagedChannel channel = Grpc.newChannelBuilder(host, InsecureChannelCredentials.create())
				.build();
		_client = new GTendermintConsensusClient(channel);
	}

	@Test
//	@Ignore
	public void sendProposeMessageTest() {
		int height = 0;
		int round = 1;
		GData data = GData.newBuilder()
				.setData("hello")
				.setTimestamp(System.currentTimeMillis()).build();
		int validRound = 2;
		int nodeId = 5;

		GProposeMessage proposeMsg = GProposeMessage.newBuilder()
				.setHeight(height)
				.setRound(round)
				.setData(data)
				.setValidRound(validRound)
				.setNodeId(nodeId)
				.build();
		GResult result = _client.sendProposeMessage(proposeMsg);
		System.out.println("sendProposeMessageTest: " + result);
	}
}
