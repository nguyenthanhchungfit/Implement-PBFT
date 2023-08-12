package com.pbft.tendermint.node;

import com.pbft.tendermint.grpc.GTendermintConsensusClient;
import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
