package com.pbft.tendermint.common;

import com.google.protobuf.ByteString;
import com.pbft.tendermint.core.grpc.GPreCommitMessage;
import com.pbft.tendermint.core.grpc.GPreVoteMessage;
import com.pbft.tendermint.core.grpc.GProposeMessage;

/**
 * @author chungnt
 * @version 1.0
 * @date 19/08/2023
 */
public class MessageUtils {
	public static String getDataToHash(GProposeMessage msg) {
		return String.format("%d:%d:%d:%d:%s", msg.getNodeId(), msg.getHeight(), msg.getRound(),
				msg.getValidRound(), msg.getData().getValue());
	}

	public static String beautifyMessage(GProposeMessage msg) {
		return String.format("GProposeMessage{nodeId=%d, height=%d, round=%d, validRound=%d, data=%s}", msg.getNodeId(), msg.getHeight(),
				msg.getRound(), msg.getValidRound(), msg.getData().getValue());
	}

	public static String beautifyMessage(GPreVoteMessage msg) {
		boolean agree = !msg.getHashValue().equals(ByteString.EMPTY);
		return String.format("GPreVoteMessage{nodeId=%d, height=%d, round=%d, agree=%b}", msg.getNodeId(), msg.getHeight(),
				msg.getRound(), agree);
	}

	public static String beautifyMessage(GPreCommitMessage msg) {
		boolean agree = !msg.getHashValue().equals(ByteString.EMPTY);
		return String.format("GPreCommitMessage{nodeId=%d, height=%d, round=%d, agree=%b}", msg.getNodeId(), msg.getHeight(),
				msg.getRound(), agree);
	}
}
