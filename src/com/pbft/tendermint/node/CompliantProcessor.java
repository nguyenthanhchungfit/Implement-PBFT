package com.pbft.tendermint.node;

import com.google.protobuf.ByteString;
import com.pbft.common.ConsensusValue;
import com.pbft.common.NodeMeta;
import com.pbft.tendermint.common.*;
import com.pbft.tendermint.core.grpc.*;
import com.pbft.tendermint.node.schedule.TimeoutPreVoteHandler;
import com.pbft.tendermint.node.schedule.TimeoutProposeHandler;
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
import java.util.concurrent.*;


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
	private ConsensusTmpObject consensusTmp;
	private ConsensusValue[] decisions;

	public CompliantProcessor(NodeMeta nodeMeta, Logger logger, ProposeNodeSelector selector) {
		this(0, 0, null, null, -1, null, null,
				-1, nodeMeta, null, logger, selector, null,
				null, null);
		this.executor = Executors.newSingleThreadScheduledExecutor();
		this.consensusTmp = new ConsensusTmpObject();
		this.decisions = new ConsensusValue[ConstantConfig.MAX_HEIGHT];
	}

	@Override
	public GPongMessage onPingMessage(GPingMessage message) {
		return GPongMessage.newBuilder()
				.setError(0)
				.setNodeId(nodeMeta.getNodeId()).build();
	}

	@Override
	public GResult onReceiveProposeMessage(GProposeMessage message) {
		this.logger.info("Receive propose message: " + MessageUtils.beautifyMessage(message));
		if (this.step.getCode() != Step.PROPOSE.getCode() || message.getHeight() != this.height
				|| message.getRound() != this.round) {
//			this.logger.info("debug here");
			return GResult.newBuilder()
					.setError(ConstantResult.ERR_IGNORED)
					.build();
		}

		int currentProposeNodeId = this.selector.getProposeNodeId(this.height, this.round);
		if (message.getNodeId() == currentProposeNodeId) {
			if (message.getValidRound() == -1) {
				ConsensusValue value = new ConsensusValue(message.getData().getValue());
				if (this.isValidValue(value) && (this.lockedRound == -1 || value.equals(this.lockedValue))) {
//					this.logger.info("debug-1.1");
					this.consensusTmp.setReceivedProposeValue(value);
					this.consensusTmp.setReceivedProposeNodeId(message.getNodeId());
					this.broadcastPreVoteMessage(value);
				} else {
//					this.logger.info("debug-1.2");
					this.broadcastPreVoteMessage(null);
				}
				this.step = Step.PRE_VOTE;
//				this.logger.info("debug-1.3: " + this.step.getCode());
			} else {
				ConsensusValue value = new ConsensusValue(message.getData().getValue());
				this.consensusTmp.setReceivedProposeValue(value);
				this.consensusTmp.setReceivedProposeNodeId(message.getNodeId());
				this.consensusTmp.setReceivedProposeValidRound(message.getValidRound());
			}
		}
		return GResult.newBuilder()
				.setError(0)
				.setData(message.getData().getValue() + " pong!!")
				.build();
	}

	@Override
	public GResult onReceivePreVoteMessage(GPreVoteMessage message) {
		this.logger.info("Receive pre-vote message: " + MessageUtils.beautifyMessage(message));
		if (this.step.getCode() != Step.PROPOSE.getCode() || message.getHeight() != this.height) {
			return GResult.newBuilder()
					.setError(ConstantResult.ERR_FAILED)
					.build();
		}
		this.getConsensusTmp().getReceivedPreVoteValueMap()
				.computeIfAbsent(message.getNodeId(), k -> message.getHashValue());

		return GResult.newBuilder()
				.setError(ConstantResult.ERR_SUCCESS)
				.setData("received pre-vote msg success!!")
				.build();
	}

	@Override
	public GResult onReceivePreCommitMessage(GPreCommitMessage message) {
		this.logger.info("Receive pre-commit message: " + MessageUtils.beautifyMessage(message));
		if (this.decisions[this.height] != null) {
			return GResult.newBuilder().setError(ConstantResult.ERR_IGNORED)
					.build();
		}
		this.getConsensusTmp().getReceivedPreCommitValueMap()
				.computeIfAbsent(message.getNodeId(), k -> message.getHashValue());
		return GResult.newBuilder().setError(ConstantResult.ERR_SUCCESS)
				.setData("received pre-commit msg success!!")
				.build();
	}


	@Override
	public void startConsensus() {
		startRound(0);
		while (true) {
//			this.logger.info("Step 1");
			while (Step.PROPOSE.getCode() == this.step.getCode()) { // do anything
				// received propose msg and 2/3 pre-vote msg
				boolean isProposedNode = this.selector.getProposeNodeId(this.height, this.round) == this.node.getNodeId();
				if ((this.consensusTmp.getReceivedProposeValidRound() >= 0 || isProposedNode)
						&& this.calNumReceivedMessageByType(this.consensusTmp.getReceivedPreVoteValueMap()) != 0) {
					if ((this.isValidValue(this.consensusTmp.getReceivedProposeValue()) || isProposedNode)
							&& (this.lockedRound <= this.consensusTmp.getReceivedProposeValidRound()
							|| this.consensusTmp.getReceivedProposeValue().equals(this.lockedValue))) {
//						this.logger.info("debug-2.1");
						this.broadcastPreVoteMessage(this.consensusTmp.getReceivedProposeValue());
					} else {
//						this.logger.info("debug-2.2");
						this.broadcastPreVoteMessage(null);
					}
					this.step = Step.PRE_VOTE;
				}
//				this.logger.info("debug-2.3: " + this.step.getCode());
				SystemUtils.sleep(10);
			}
//			this.logger.info("Step 2");
			boolean isFirstTimePreVote = false;
			while (this.step.getCode() >= Step.PRE_VOTE.getCode()) {
				boolean isProposedNode = this.selector.getProposeNodeId(this.height, this.round) == this.node.getNodeId();
				boolean isValidValue = this.isValidValue(this.consensusTmp.getReceivedProposeValue()) || isProposedNode;

				if (!isFirstTimePreVote) {
					isFirstTimePreVote = true;
					TimeoutPreVoteHandler preVoteTimeoutHandler = new TimeoutPreVoteHandler(this.logger, this, this.height, this.round);
					this.executor.schedule(preVoteTimeoutHandler, ConstantConfig.TIMEOUT_PRE_VOTE, TimeUnit.MILLISECONDS);
					if (isValidValue) {
						if (this.step.getCode() == Step.PRE_VOTE.getCode()) {
							this.lockedValue = this.consensusTmp.getReceivedProposeValue();
							this.lockedRound = this.round;
							ConsensusValue consensusValue = isProposedNode ? this.tmpValue : this.consensusTmp.getReceivedProposeValue();
							this.broadcastPreCommitMessage(consensusValue);
							this.step = Step.PRE_COMMIT;
						}
						this.validValue = this.consensusTmp.getReceivedProposeValue();
						this.validRound = this.consensusTmp.getReceivedProposeValidRound();
					}
				}

				if (isValidValue && this.decisions[this.height] == null) {
					int numReceivedPreCommitMessage = this.calNumReceivedMessageByType(this.consensusTmp.getReceivedPreCommitValueMap());
					if (numReceivedPreCommitMessage == ConstantResult.RET_AGREE) {
						ConsensusValue consensusValue = isProposedNode ? this.tmpValue : this.consensusTmp.getReceivedProposeValue();
						this.decisions[this.height] = consensusValue;
						this.height++;
//						this.logger.info("reset");
						this._resetConsensusTmp();
						this.logger.info("Current decisions: " + Arrays.toString(this.decisions));
						this.logger.info("\n\n-------------WAITING TO START NEW HEIGHT-------\n\n");
						SystemUtils.sleep(ConstantConfig.TIME_DELAY_NEW_HEIGHT);
						this.startRound(0);
					}
				}
				SystemUtils.sleep(10);
			}

		}
	}

	public void startRound(int round) {
		this.logger.info(String.format("\n\n------------- START HEIGHT: %d, ROUND: %d -------\n\n", this.height, round));
		this.round = round;
		this.step = Step.PROPOSE;
		if (this.selector.getProposeNodeId(this.height, this.round) == this.node.getNodeId()) {
			this.logger.info("PROPOSE NODE ID: " + this.node.getNodeId());
			ConsensusValue proposeValue = null;
			if (validValue != null) {
				proposeValue = validValue;
			} else {
				proposeValue = getValue();
			}
			SystemUtils.sleep(500);
			this.tmpValue = proposeValue;
			this.broadcastProposeMessage(proposeValue);
			// broadcast PROPOSAL msg
		} else {
			TimeoutProposeHandler timeoutHandler = new TimeoutProposeHandler(this.logger, this, this.height, this.round);
			this.executor.schedule(timeoutHandler, ConstantConfig.TIMEOUT_PROPOSE, TimeUnit.MILLISECONDS);
		}
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

	public void broadcastPreVoteMessage(ConsensusValue value) {
		ByteString hashValue = ByteString.EMPTY;
		if (value != null && StringUtils.isNotBlank(value.getValue())) {
			hashValue = ByteString.copyFrom(CryptoUtils.hash(value.getValue().getBytes(StandardCharsets.UTF_8)));
		}
		GPreVoteMessage msg = GPreVoteMessage.newBuilder()
				.setNodeId(this.node.getNodeId())
				.setHeight(this.height)
				.setRound(this.round)
				.setHashValue(hashValue)
				.build();
		this.node.broadcastPreVoteMessage(msg);

	}

	public void broadcastPreCommitMessage(ConsensusValue value) {
		ByteString hashValue = ByteString.EMPTY;
		if (value != null && StringUtils.isNotBlank(value.getValue())) {
			hashValue = ByteString.copyFrom(CryptoUtils.hash(value.getValue().getBytes(StandardCharsets.UTF_8)));
		}
		GPreCommitMessage msg = GPreCommitMessage.newBuilder()
				.setNodeId(this.node.getNodeId())
				.setHeight(this.height)
				.setRound(this.round)
				.setHashValue(hashValue)
				.build();
		this.node.broadcastPreCommitMessage(msg);
	}

	// Process Timeout
	public void doTimeoutPropose(int height, int round) {
		if (height == this.height && round == this.round) {
			this.step = Step.PRE_VOTE;
			this.broadcastPreVoteMessage(null);
		}
	}

	public void doTimeoutPreVote(int height, int round) {
		if (this.height == height && this.round == round && this.step.getCode() == Step.PRE_VOTE.getCode()) {
			this.broadcastPreCommitMessage(null);
			this.step = Step.PRE_COMMIT;
		}
	}

	public void doTimeoutPreCommit(int height, int round) {
		if (this.height == height && this.round == round) {
			this.startRound(this.round + 1);
		}
	}
}
