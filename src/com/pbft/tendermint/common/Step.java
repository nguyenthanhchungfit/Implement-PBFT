package com.pbft.tendermint.common;

public enum Step {
	PROPOSE(1),
	PRE_VOTE(2),
	PRE_COMMIT(3);
	private int code;

	private Step(int code) {
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}
}
