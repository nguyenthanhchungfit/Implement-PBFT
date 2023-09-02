package com.pbft.tendermint.node;

import com.pbft.tendermint.grpc.GTendermintConsensusServer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.Logger;

import java.security.KeyPair;
import java.util.List;

/**
 * @author chungnt
 * @version 1.0
 * @date 12/08/2023
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class ConsensusNode {
	protected int nodeId;
	protected KeyPair keyPair;
	protected List<NeighborNode> neighborNodes;
	protected String address;
	protected int port;
	protected GTendermintConsensusServer server;
	protected Logger logger;

	public abstract void startServer();

	public abstract void startConsensus();
}
