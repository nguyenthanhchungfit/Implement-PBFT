package com.pbft.tendermint.common;

import com.pbft.tendermint.core.grpc.GPreCommitMessage;
import com.pbft.tendermint.core.grpc.GPreVoteMessage;
import com.pbft.tendermint.core.grpc.GProposeMessage;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

/**
 * @author chungnt
 * @version 1.0
 * @date 26/09/2023
 */
@Getter
@Setter
public class TendermintMessage {
	private MessageType type;
	private long createTime;
	private GProposeMessage proposeMessage;
	private GPreVoteMessage preVoteMessage;
	private GPreCommitMessage preCommitMessage;
	private TimeoutMessage timeoutMessage;

	public TendermintMessage(MessageType type) {
		this.type = type;
		this.createTime = System.currentTimeMillis();
	}

	public TendermintMessage(GProposeMessage proposeMessage) {
		this(MessageType.PROPOSE_MSG);
		this.proposeMessage = proposeMessage;
	}

	public TendermintMessage(GPreVoteMessage preVoteMessage) {
		this(MessageType.PRE_VOTE_MSG);
		this.preVoteMessage = preVoteMessage;
	}

	public TendermintMessage(GPreCommitMessage preCommitMessage) {
		this(MessageType.PRE_COMMIT_MSG);
		this.preCommitMessage = preCommitMessage;
	}

	public TendermintMessage(MessageType type, TimeoutMessage timeoutMessage) {
		this(type);
		this.timeoutMessage = timeoutMessage;
	}
}
