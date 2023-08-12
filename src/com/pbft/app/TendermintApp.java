package com.pbft.app;

import com.pbft.tendermint.common.ConsensusValue;
import com.pbft.tendermint.grpc.GTendermintConsensusServer;
import com.pbft.tendermint.node.CompliantNode;
import com.pbft.tendermint.node.CompliantProcessor;
import com.pbft.tendermint.node.ConsensusNode;
import com.pbft.tendermint.node.NeighborNode;
import com.pbft.utils.CryptoUtils;

import java.io.IOException;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chungnt
 * @version 1.0
 * @date 08/08/2023
 */
public class TendermintApp {
	public static void main(String[] args) throws IOException, InterruptedException {
		int totalNodes = 5;
		List<Integer> ports = new ArrayList<>();
		List<ConsensusNode> nodes = new ArrayList<>();
		int fromPort = 8000;
		for (int i = 1; i <= totalNodes; i++) {
			int nodeId = i;
			KeyPair keyPair = CryptoUtils.generateKeyPair();
			if (keyPair == null) {
				System.out.println("Cannot generate key pair for node " + nodeId);
				System.exit(1);
			}
			String address = "localhost";
			int port = fromPort + i;
			nodes.add(new CompliantNode(address, port, nodeId, keyPair, new CompliantProcessor()));
		}

		// build list neighbor nodes
		for (ConsensusNode node : nodes) {
			List<NeighborNode> neighborNodes = new ArrayList<>();
			for (ConsensusNode otherNode : nodes) {
				if (otherNode.getNodeId() != node.getNodeId()) {
					int nodeId = otherNode.getNodeId();
					String host = otherNode.getAddress() + ":" + otherNode.getPort();
					PublicKey pubKey = otherNode.getKeyPair().getPublic();
					neighborNodes.add(new NeighborNode());
				}
			}
		}
	}
}
