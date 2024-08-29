package com.pbft.tendermint.common;

/**
 * @author chungnt
 * @version 1.0
 * @date 26/09/2023
 */
public enum MessageType {
	PROPOSE_MSG(1),
	TIMEOUT_PROPOSE(2),
	PRE_VOTE_MSG(3),
	TIMEOUT_PRE_VOTE(4),
	PRE_COMMIT_MSG(5),
	TIMEOUT_PRE_COMMIT(6);
	private int value;

	private MessageType(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}
