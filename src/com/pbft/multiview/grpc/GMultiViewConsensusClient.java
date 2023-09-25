package com.pbft.multiview.grpc;

import com.pbft.multiview.core.grpc.*;
import io.grpc.Channel;
import io.grpc.Deadline;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;


/**
 * @author chungnt
 * @version 1.0
 * @date 12/08/2023
 */
public class GMultiViewConsensusClient {
	private static final Logger LOGGER = LogManager.getLogger(GMultiViewConsensusClient.class.getName());
	private static final int ERR_EXCEPTION = -100;
	private static final int ERR_BAD_NET_WORk = -1000;
	private static final int ERR_CONNECTION_CONFUSED = -1001;
	private static final int ERR_CONNECTION_TIMEOUT = -1002;
	private static final long DEFAULT_TIMEOUT = 2000;
	private long timeout = DEFAULT_TIMEOUT;

	private final GConsensusProtocolServiceGrpc.GConsensusProtocolServiceBlockingStub blockingStub;

	public GMultiViewConsensusClient(Channel channel) {
		this(channel, DEFAULT_TIMEOUT);
	}

	public GMultiViewConsensusClient(Channel channel, long timeoutInMillis) {
		this.blockingStub = GConsensusProtocolServiceGrpc.newBlockingStub(channel);
		this.timeout = timeoutInMillis;
	}

	private static int _convertCustomError(Status errorStatus) {
		int errorCode = errorStatus.getCode().value();
		if (Status.UNAVAILABLE.getCode().value() == errorCode) {
			return ERR_CONNECTION_CONFUSED;
		} else if (Status.DEADLINE_EXCEEDED.getCode().value() == errorCode) {
			return ERR_CONNECTION_TIMEOUT;
		}
		LOGGER.error("[Call RPC Failed]: " + errorStatus);
		return ERR_BAD_NET_WORk;
	}

	public GPongMessage sendPingMessage(GPingMessage message) {
		try {
			Deadline deadline = Deadline.after(this.timeout, TimeUnit.MILLISECONDS);
			return this.blockingStub.withDeadline(deadline).ping(message);
		} catch (StatusRuntimeException ex) {
			int error = _convertCustomError(ex.getStatus());
			return GPongMessage.newBuilder().setError(error).build();
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			return GPongMessage.newBuilder().setError(ERR_EXCEPTION).build();
		}
	}

	public GResult sendProposeMessage(GProposeMessage message) {
		try {
			Deadline deadline = Deadline.after(this.timeout, TimeUnit.MILLISECONDS);
			return this.blockingStub.withDeadline(deadline).onProposeMessage(message);
		} catch (StatusRuntimeException ex) {
			int error = _convertCustomError(ex.getStatus());
			return GResult.newBuilder().setError(error).build();
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			return GResult.newBuilder().setError(ERR_EXCEPTION).build();
		}
	}

	public GResult sendVoteMessage(GVoteMessage message) {
		try {
			Deadline deadline = Deadline.after(this.timeout, TimeUnit.MILLISECONDS);
			return this.blockingStub.withDeadline(deadline).onVoteMessage(message);
		} catch (StatusRuntimeException ex) {
			int error = _convertCustomError(ex.getStatus());
			return GResult.newBuilder().setError(error).build();
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			return GResult.newBuilder().setError(ERR_EXCEPTION).build();
		}
	}
}
