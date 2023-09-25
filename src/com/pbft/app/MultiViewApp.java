package com.pbft.app;

import com.pbft.common.NodeMeta;
import com.pbft.tendermint.common.ProposeNodeSelector;
import com.pbft.tendermint.node.*;
import com.pbft.utils.CryptoUtils;
import com.pbft.utils.SystemUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.KeyPair;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chungnt
 * @version 1.0
 * @date 08/08/2023
 */
public class MultiViewApp {
	private static final Logger LOGGER = LogManager.getLogger(MultiViewApp.class);

	public static void main(String[] args) {
		int totalNodes = 3;
		List<ConsensusNode> nodes = new ArrayList<>();
		int fromPort = 8000;
		ProposeNodeSelector nodeSelector = new ProposeNodeSelector();
		for (int i = 1; i <= totalNodes; i++) {
			int nodeId = i;
			KeyPair keyPair = CryptoUtils.generateKeyPair();
			if (keyPair == null) {
				LOGGER.error("Cannot generate key pair for node " + nodeId);
				System.exit(1);
			}
			nodeSelector.addNewNodeId(nodeId);
			String address = "localhost";
			int port = fromPort + i;
			NodeMeta nodeMeta = new NodeMeta(nodeId);
			Logger customLog = LogManager.getLogger("node-" + nodeId);
			CompliantProcessor processor = new CompliantProcessor(nodeMeta, customLog, nodeSelector);
			CompliantNode node = new CompliantNode(address, port, nodeId, keyPair, processor, customLog);
			nodes.add(node);
			processor.setNode(node);
		}

		// build list neighbor nodes
		for (ConsensusNode node : nodes) {
			List<NeighborNode> neighborNodes = new ArrayList<>();
			node.startServer();
			for (ConsensusNode otherNode : nodes) {
				if (otherNode.getNodeId() != node.getNodeId()) {
					int nodeId = otherNode.getNodeId();
					String host = otherNode.getAddress() + ":" + otherNode.getPort();
					PublicKey pubKey = otherNode.getKeyPair().getPublic();
					NeighborNode neighborNode = NeighborNode.buildNeighborNode(nodeId, host, pubKey);
					neighborNodes.add(neighborNode);
					neighborNode.sendPingMessage();
				}
			}
			node.setNeighborNodes(neighborNodes);
			LOGGER.info("Setup done for node: " + node);
		}

		LOGGER.info("Sleep 5s");
		SystemUtils.sleep(5000);
		for (ConsensusNode node : nodes) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					node.startConsensus();
				}
			}).start();
		}

		SystemUtils.sleep(100000000);
	}
}
