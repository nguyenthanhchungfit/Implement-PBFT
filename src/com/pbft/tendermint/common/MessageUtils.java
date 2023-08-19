package com.pbft.tendermint.common;

import com.pbft.tendermint.core.grpc.GProposeMessage;

/**
 * @author chungnt
 * @version 1.0
 * @date 19/08/2023
 */
public class MessageUtils {
	public static String getDataToHash(GProposeMessage msg) {
		return String.format("%d:%d:%d:%d:%s", msg.getNodeId(), msg.getHeight(), msg.getRound(),
				msg.getValidRound(), msg.getData().getData());
	}
}
