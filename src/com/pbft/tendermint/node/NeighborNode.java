package com.pbft.tendermint.node;

import com.pbft.tendermint.core.grpc.*;
import com.pbft.tendermint.grpc.GTendermintConsensusClient;
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
	private GTendermintConsensusClient client;
	private PublicKey pubKey;

	public static NeighborNode buildNeighborNode(int nodeId, String host, PublicKey pubKey) {
		ManagedChannel channel = Grpc.newChannelBuilder(host, InsecureChannelCredentials.create())
				.build();
		GTendermintConsensusClient client = new GTendermintConsensusClient(channel);
		return new NeighborNode(nodeId, host, client, pubKey);
	}

	public GPongMessage sendPingMessage() {
		GPingMessage msg = GPingMessage.newBuilder()
				.setNodeId(nodeId).build();
		return this.client.sendPingMessage(msg);
	}

	public GResult sendProposeMessage(GProposeMessage msg) {
		GResult result = this.client.sendProposeMessage(msg);
//		LOGGER.info(String.format("NodeId: " + nodeId + ", resp: " + result));
		return result;
	}

	public GResult sendPreVoteMessage(GPreVoteMessage msg) {
		GResult result = this.client.sendPreVoteMessage(msg);
//		LOGGER.info(String.format("NodeId: " + nodeId + ", resp: " + result));
		return result;
	}

	public GResult sendPreCommitMessage(GPreCommitMessage msg) {
		GResult result = this.client.sendPreCommitMessage(msg);
//		LOGGER.info(String.format("NodeId: " + nodeId + ", resp: " + result));
		return result;
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
