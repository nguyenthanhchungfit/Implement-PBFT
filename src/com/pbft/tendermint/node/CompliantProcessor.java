package com.pbft.tendermint.node;

import com.pbft.tendermint.common.ConsensusValue;
import com.pbft.tendermint.common.Step;
import com.pbft.tendermint.core.grpc.GPreCommitMessage;
import com.pbft.tendermint.core.grpc.GPreVoteMessage;
import com.pbft.tendermint.core.grpc.GProposeMessage;
import com.pbft.tendermint.core.grpc.GResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

	@Override
	public GResult onReceiveProposeMessage(GProposeMessage message) {
		return null;
	}

	@Override
	public GResult onReceivePreVoteMessage(GPreVoteMessage message) {
		return null;
	}

	@Override
	public GResult onReceivePreCommitMessage(GPreCommitMessage message) {
		return null;
	}
}
