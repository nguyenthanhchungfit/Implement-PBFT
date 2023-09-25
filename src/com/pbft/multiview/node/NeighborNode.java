package com.pbft.multiview.node;

import com.pbft.multiview.grpc.GMultiViewConsensusClient;
import com.pbft.multiview.core.grpc.*;
import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.PublicKey;

/**
 * @author chungnt
 * @version 1.0
 * @date 12/08/2023
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NeighborNode {
	private static final Logger LOGGER = LogManager.getLogger(NeighborNode.class);
	private int nodeId;
	private String host;
	private GMultiViewConsensusClient client;
	private PublicKey pubKey;

	public static NeighborNode buildNeighborNode(int nodeId, String host, PublicKey pubKey) {
		ManagedChannel channel = Grpc.newChannelBuilder(host, InsecureChannelCredentials.create())
				.build();
		GMultiViewConsensusClient client = new GMultiViewConsensusClient(channel);
		return new NeighborNode(nodeId, host, client, pubKey);
	}

	public GPongMessage sendPingMessage() {
		GPingMessage msg = GPingMessage.newBuilder()
				.setNodeId(nodeId).build();
		return this.client.sendPingMessage(msg);
	}

	public GResult sendProposeMessage(GProposeMessage msg) {
		return this.client.sendProposeMessage(msg);
	}

	public GResult sendVoteMessage(GVoteMessage msg) {
		return this.client.sendVoteMessage(msg);
	}

	@Override
	public String toString() {
		return "NeighborNode{" +
				"nodeId=" + nodeId +
				", host='" + host + '\'' +
				", pubKey=" + pubKey.getAlgorithm() +
				'}';
	}
}
