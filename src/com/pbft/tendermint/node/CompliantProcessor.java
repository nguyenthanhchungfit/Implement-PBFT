package com.pbft.tendermint.node;

import com.google.protobuf.ByteString;
import com.pbft.tendermint.common.ConsensusValue;
import com.pbft.tendermint.common.MessageUtils;
import com.pbft.tendermint.common.ProposeNodeSelector;
import com.pbft.tendermint.common.Step;
import com.pbft.tendermint.core.grpc.*;
import com.pbft.utils.CryptoUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


/**
 * @author chungnt
 * @version 1.0
 * @date 12/08/2023
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompliantProcessor implements IProcessor {
	private int height;
	private int round;
	private Step step;
	private ConsensusValue lockedValue;
	private int lockedRound;
	private ConsensusValue tmpValue;
	private ConsensusValue validValue;
	private int validRound;
	private NodeMeta nodeMeta;
	private CompliantNode node;
	private Logger logger;
	private ProposeNodeSelector selector;
	private ScheduledExecutorService executor = null;

	public CompliantProcessor(NodeMeta nodeMeta, Logger logger, ProposeNodeSelector selector) {
		this(0, 0, null, null, -1, null, null,
				-1, nodeMeta, null, logger, selector, null);
		this.executor = Executors.newSingleThreadScheduledExecutor();
	}

	@Override
	public GPongMessage onPingMessage(GPingMessage message) {
		return GPongMessage.newBuilder()
				.setError(0)
				.setNodeId(nodeMeta.getNodeId()).build();
	}

	@Override
	public GResult onReceiveProposeMessage(GProposeMessage message) {
		GResult result = GResult.newBuilder()
				.setError(0)
				.setData(message.getData().getData() + " pong!!").build();
		return result;
	}

	@Override
	public GResult onReceivePreVoteMessage(GPreVoteMessage message) {
		return null;
	}

	@Override
	public GResult onReceivePreCommitMessage(GPreCommitMessage message) {
		return null;
	}

	public void sendProposeMessage(ConsensusValue value) {
		GProposeMessage msg = GProposeMessage.newBuilder()
				.setNodeId(nodeMeta.getNodeId())
				.setHeight(this.height)
				.setRound(this.round)
				.setValidRound(this.validRound)
				.setData(GData.newBuilder().setData(value.getValue()).build())
				.build();
		node.broadcastProposeMessage(msg);
	}

	public void startRound(int round) {
		this.round = round;
		this.step = Step.PROPOSE;
		if (this.selector.getProposeNodeId(this.height, this.round) == this.nodeMeta.getNodeId()) {
			ConsensusValue proposeValue = null;
			if (validValue != null) {
				proposeValue = validValue;
			} else {
				proposeValue = getValue();
			}
			// broadcast PROPOSAL msg
		} else {
			// schedule onTimeout
		}
	}

	public ConsensusValue getValue() {
		ConsensusValue value = new ConsensusValue();
		value.setValue("value" + this.nodeMeta.getNodeId() + " at height " + this.height + " round " + this.round);
		return value;
	}
}
