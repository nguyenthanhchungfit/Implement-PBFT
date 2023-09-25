package com.pbft.multiview.grpc;

import com.pbft.multiview.core.grpc.*;
import com.pbft.multiview.node.DefaultProcessor;
import com.pbft.multiview.node.IProcessor;
import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author chungnt
 * @version 1.0
 * @date 08/08/2023
 */
@Getter
public class GMultiViewConsensusServer {
	private static final Logger LOGGER = LogManager.getLogger(GMultiViewConsensusServer.class.getName());

	private final int port;
	private final Server server;
	private IProcessor processor;

	public GMultiViewConsensusServer(int port) {
		this(Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create()), port);
	}

	public GMultiViewConsensusServer(int port, IProcessor processor) {
		this(Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create()), port, processor);
	}

	public GMultiViewConsensusServer(ServerBuilder<?> serverBuilder, int port) {
		this(serverBuilder, port, new DefaultProcessor());
	}

	public GMultiViewConsensusServer(ServerBuilder<?> serverBuilder, int port, IProcessor processor) {
		this.port = port;
		this.processor = processor;
		Assert.assertTrue("Invalid Tendermint Server", this.processor != null && this.port > 0);
		this.server = serverBuilder.addService(new MultiViewConsensusService(this.processor)).build();
	}

	/**
	 * Start serving requests.
	 */
	public void start() throws IOException {
		server.start();
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				// Use stderr here since the logger may have been reset by its JVM shutdown hook.
				LOGGER.error("*** shutting down gRPC server since JVM is shutting down");
				try {
					GMultiViewConsensusServer.this.stop();
				} catch (InterruptedException ex) {
					LOGGER.error(ex.getMessage(), ex);
				}
				LOGGER.error("*** server shut down");
			}
		});
	}

	/**
	 * Stop serving requests and shutdown resources.
	 */
	public void stop() throws InterruptedException {
		if (server != null) {
			server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
		}
	}

	/**
	 * Await termination on the main thread since the grpc library uses daemon threads.
	 */
	public void blockUntilShutdown() throws InterruptedException {
		if (server != null) {
			server.awaitTermination();
		}
	}

	@NoArgsConstructor
	@AllArgsConstructor
	private static class MultiViewConsensusService extends GConsensusProtocolServiceGrpc.GConsensusProtocolServiceImplBase {
		private IProcessor processor;

		@Override
		public void ping(GPingMessage request, StreamObserver<GPongMessage> responseObserver) {
			GPongMessage result = processor.onPingMessage(request);
			responseObserver.onNext(result);
			responseObserver.onCompleted();
		}

		@Override
		public void onProposeMessage(GProposeMessage request, StreamObserver<GResult> responseObserver) {
			GResult result = processor.onReceiveProposeMessage(request);
			responseObserver.onNext(result);
			responseObserver.onCompleted();
		}

		@Override
		public void onVoteMessage(GVoteMessage request, StreamObserver<GResult> responseObserver) {
			GResult result = processor.onReceiveVoteMessage(request);
			responseObserver.onNext(result);
			responseObserver.onCompleted();
		}

		@Override
		public void startConsensus(GRequest request, StreamObserver<GResult> responseObserver) {
			LOGGER.info("startConsensus: " + request);
		}
	}
}
