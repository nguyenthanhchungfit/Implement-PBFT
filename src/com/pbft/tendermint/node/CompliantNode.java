package com.pbft.tendermint.node;

import com.pbft.tendermint.core.grpc.GPreCommitMessage;
import com.pbft.tendermint.core.grpc.GPreVoteMessage;
import com.pbft.tendermint.core.grpc.GProposeMessage;
import com.pbft.tendermint.core.grpc.GResult;
import com.pbft.tendermint.grpc.GTendermintConsensusServer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.KeyPair;
import java.util.Collections;
import java.util.List;

/**
 * @author chungnt
 * @version 1.0
 * @date 12/08/2023
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompliantNode extends ConsensusNode {
	private IProcessor processor;

	public CompliantNode(String address, int port, int nodeId, KeyPair keyPair, IProcessor processor) {
		super(nodeId, keyPair, Collections.EMPTY_LIST, address, port, null);
		this.processor = processor;
		this.server = new GTendermintConsensusServer(port, processor);
	}

	@Override
	public void start() {

	}
}
