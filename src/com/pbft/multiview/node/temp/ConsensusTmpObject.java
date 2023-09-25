package com.pbft.multiview.node.temp;

import com.google.protobuf.ByteString;
import com.pbft.common.ConsensusValue;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author chungnt
 * @version 1.0
 * @date 21/08/2023
 */
@Getter
@Setter
public class ConsensusTmpObject {
	private ConsensusValue receivedProposeValue;
	private int receivedProposeNodeId;
	private int receivedProposeValidRound;
	private ConcurrentMap<Integer, ByteString> receivedPreVoteValueMap;
	private ConcurrentMap<Integer, ByteString> receivedPreCommitValueMap;

	public ConsensusTmpObject() {
		receivedProposeNodeId = -1;
		receivedProposeValidRound = -1;
		receivedPreVoteValueMap = new ConcurrentHashMap<>();
		receivedPreCommitValueMap = new ConcurrentHashMap<>();
	}
}
