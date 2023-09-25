package com.pbft.multiview.node;

import com.google.protobuf.ByteString;
import com.pbft.common.ConsensusValue;
import com.pbft.common.NodeMeta;
import com.pbft.multiview.core.grpc.GVoteMessage;
import com.pbft.multiview.common.*;
import com.pbft.multiview.core.grpc.*;
import com.pbft.multiview.node.IProcessor;
import com.pbft.tendermint.common.ConstantConfig;
import com.pbft.tendermint.common.ConstantResult;
import com.pbft.tendermint.common.ProposeNodeSelector;
import com.pbft.tendermint.node.temp.ConsensusTmpObject;
import com.pbft.utils.CryptoUtils;
import com.pbft.utils.SystemUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


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
	private ConsensusTmpObject consensusTmp;
	private ConsensusValue[] decisions;

//	public CompliantProcessor(NodeMeta nodeMeta, Logger logger, ProposeNodeSelector selector) {
//		this(0, 0, null, null, -1, null, null,
//				-1, nodeMeta, null, logger, selector, null,
//				null, null);
//		this.executor = Executors.newSingleThreadScheduledExecutor();
//		this.consensusTmp = new ConsensusTmpObject();
//		this.decisions = new ConsensusValue[ConstantConfig.MAX_HEIGHT];
//	}

	@Override
	public GPongMessage onPingMessage(GPingMessage message) {
		return GPongMessage.newBuilder()
				.setError(0)
				.setNodeId(nodeMeta.getNodeId()).build();
	}

	@Override
	public GResult onReceiveProposeMessage(GProposeMessage message) {
		this.logger.info("Receive propose message: " + MessageUtils.beautifyMessage(message));
		return GResult.newBuilder()
				.setError(0)
				.setData(message.getData().getValue() + " pong!!")
				.build();
	}

	@Override
	public GResult onReceiveVoteMessage(GVoteMessage message) {
		this.logger.info("Receive pre-vote message: " + MessageUtils.beautifyMessage(message));

		return GResult.newBuilder()
				.setError(ConstantResult.ERR_SUCCESS)
				.setData("received pre-vote msg success!!")
				.build();
	}


	@Override
	public void startConsensus() {
		startRound(0);
	}

	public void startRound(int round) {
	}

	// utils
	public ConsensusValue getValue() {
		ConsensusValue value = new ConsensusValue();
		value.setValue("value " + this.nodeMeta.getNodeId() + " at height " + this.height + " round " + this.round);
		return value;
	}

	public boolean isValidValue(ConsensusValue value) {
		return value != null && StringUtils.isNotBlank(value.getValue());
	}

	public int calNumReceivedMessageByType(ConcurrentMap<Integer, ByteString> receivedMsgValueMap) {
		int totalAgree = 0;
		int totalNull = 0;
		int threshold = getThresholdConsensus(this.node.getNeighborNodes().size());
		for (Map.Entry<Integer, ByteString> entry : receivedMsgValueMap.entrySet()) {
			ByteString hashValue = entry.getValue();
			if (!hashValue.equals(ByteString.EMPTY)) {
				totalAgree++;
			} else {
				totalNull++;
			}
			if (totalAgree >= threshold) {
				return ConstantResult.RET_AGREE;
			} else if (totalNull >= threshold) {
				return ConstantResult.RET_DISAGREE;
			}
		}
		return ConstantResult.RET_NOT_ENOUGH_VOTE;
	}

	public int getThresholdConsensus(int totalNeighborNodes) {
		return (int) Math.floor(totalNeighborNodes * 2.0 / 3.0);
	}

	private void _resetConsensusTmp() {
		this.lockedValue = null;
		this.lockedRound = -1;
		this.validValue = null;
		this.tmpValue = null;
		this.validRound = -1;
		this.consensusTmp.setReceivedProposeValue(null);
		this.consensusTmp.setReceivedProposeValidRound(-1);
		this.consensusTmp.setReceivedProposeValidRound(-1);
		this.consensusTmp.getReceivedPreVoteValueMap().clear();
		this.consensusTmp.getReceivedPreCommitValueMap().clear();
	}

	// broadcast msg
	public void broadcastProposeMessage(ConsensusValue value) {
		GProposeMessage msg = GProposeMessage.newBuilder()
				.setNodeId(this.node.getNodeId())
				.setHeight(this.height)
				.setRound(this.round)
				.setValidRound(this.validRound)
				.setData(GData.newBuilder().setValue(value.getValue()).build())
				.build();
		this.node.broadcastProposeMessage(msg);
	}

	public void broadcastVoteMessage(ConsensusValue value) {
		ByteString hashValue = ByteString.EMPTY;
		if (value != null && StringUtils.isNotBlank(value.getValue())) {
			hashValue = ByteString.copyFrom(CryptoUtils.hash(value.getValue().getBytes(StandardCharsets.UTF_8)));
		}
		GVoteMessage msg = GVoteMessage.newBuilder()
				.setNodeId(this.node.getNodeId())
				.setHeight(this.height)
				.setRound(this.round)
				.setHashValue(hashValue)
				.build();
		this.node.broadcastVoteMessage(msg);

	}
}
