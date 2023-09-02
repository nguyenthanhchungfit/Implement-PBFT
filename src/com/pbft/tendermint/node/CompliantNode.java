package com.pbft.tendermint.node;

import com.google.protobuf.ByteString;
import com.pbft.tendermint.common.MessageUtils;
import com.pbft.tendermint.core.grpc.*;
import com.pbft.tendermint.grpc.GTendermintConsensusClient;
import com.pbft.tendermint.grpc.GTendermintConsensusServer;
import com.pbft.utils.CryptoUtils;
import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.util.Collections;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author chungnt
 * @version 1.0
 * @date 12/08/2023
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompliantNode extends ConsensusNode {
	private IProcessor processor;
	private ThreadPoolExecutor executor;

	public CompliantNode(String address, int port, int nodeId, KeyPair keyPair, IProcessor processor, Logger logger) {
		super(nodeId, keyPair, Collections.EMPTY_LIST, address, port, null, null);
		this.processor = processor;
		this.logger = logger;
		this.server = new GTendermintConsensusServer(port, processor);
		this.executor = new ThreadPoolExecutor(8, 8, 0L, java.util.concurrent.TimeUnit.MILLISECONDS, new java.util.concurrent.LinkedBlockingQueue<Runnable>());
	}

	@Override
	public void startServer() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					logger.info("Start listening at port: " + server.getPort());
					server.start();
				} catch (IOException ex) {
					logger.error(ex.getMessage(), ex);
				}
			}
		}).start();
		checkServerIsStarted();
	}

	@Override
	public void startConsensus() {
		this.logger.info("Node " + this.nodeId + " start consensus");
		processor.startConsensus();
	}

	private void checkServerIsStarted() {
		String host = address + ":" + port;
		ManagedChannel channel = Grpc.newChannelBuilder(host, InsecureChannelCredentials.create())
				.build();
		GTendermintConsensusClient client = new GTendermintConsensusClient(channel);
		GPingMessage msg = GPingMessage.newBuilder()
				.setNodeId(nodeId).build();
		for (int i = 0; i < 10; i++) {
			GPongMessage pongMsg = client.sendPingMessage(msg);
			if (pongMsg.getError() == 0) {
				logger.info("Ping Server success at: " + i);
				break;
			} else {
				try {
					Thread.sleep(100);
				} catch (InterruptedException ex) {
					logger.error(ex.getMessage());
				}
			}
		}
	}

	public void broadcastProposeMessage(GProposeMessage msg) {
		String dataToHash = MessageUtils.getDataToHash(msg);
		byte[] hashData = CryptoUtils.hash(dataToHash).getBytes(StandardCharsets.UTF_8);
		byte[] encryptData = CryptoUtils.encrypt(hashData, keyPair.getPrivate());
		if (encryptData == null) {
			this.logger.error("Encrypt data error");
			return;
		}
		GProposeMessage proposeMsg = msg.toBuilder().setHashValue(ByteString.copyFrom(hashData))
				.setSignature(ByteString.copyFrom(encryptData))
				.build();
		for (NeighborNode node : this.getNeighborNodes()) {
			this.getExecutor().execute(new Runnable() {
				@Override
				public void run() {
					node.sendProposeMessage(proposeMsg);
				}
			});
		}
	}

	public void broadcastPreVoteMessage(GPreVoteMessage msg) {
		for (NeighborNode node : this.getNeighborNodes()) {
			this.getExecutor().execute(new Runnable() {
				@Override
				public void run() {
					node.sendPreVoteMessage(msg);
				}
			});
		}
	}

	public void broadcastPreCommitMessage(GPreCommitMessage msg) {
		for (NeighborNode node : this.getNeighborNodes()) {
			this.getExecutor().execute(new Runnable() {
				@Override
				public void run() {
					node.sendPreCommitMessage(msg);
				}
			});
		}
	}

	@Override
	public String toString() {
		return "CompliantNode{" +
				"nodeId=" + nodeId +
				", address='" + address + '\'' +
				", port=" + port +
				", server=" + server +
				", neighborNodes=" + neighborNodes +
				'}';
	}
}
