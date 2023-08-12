package com.pbft.tendermint.grpc;

import com.pbft.tendermint.core.grpc.*;
import com.pbft.tendermint.node.DefaultProcessor;
import com.pbft.tendermint.node.IProcessor;
import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.junit.Assert;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @author chungnt
 * @version 1.0
 * @date 08/08/2023
 */
public class GTendermintConsensusServer {
	private static final Logger LOGGER = Logger.getLogger(GTendermintConsensusServer.class.getName());

	private final int port;
	private final Server server;
	private IProcessor processor;

	public GTendermintConsensusServer(int port) {
		this(Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create()), port);
	}

	public GTendermintConsensusServer(int port, IProcessor processor) {
		this(Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create()), port, processor);
	}

	public GTendermintConsensusServer(ServerBuilder<?> serverBuilder, int port) {
		this(serverBuilder, port, new DefaultProcessor());
	}

	public GTendermintConsensusServer(ServerBuilder<?> serverBuilder, int port, IProcessor processor) {
		this.port = port;
		this.processor = processor;
		Assert.assertTrue("Invalid Tendermint Server", this.processor != null && this.port > 0);
		this.server = serverBuilder.addService(new TendermintConsensusService(this.processor)).build();
	}

	/**
	 * Start serving requests.
	 */
	public void start() throws IOException {
		server.start();
		LOGGER.info("Server started, listening on " + port);
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				// Use stderr here since the logger may have been reset by its JVM shutdown hook.
				System.err.println("*** shutting down gRPC server since JVM is shutting down");
				try {
					GTendermintConsensusServer.this.stop();
				} catch (InterruptedException e) {
					e.printStackTrace(System.err);
				}
				System.err.println("*** server shut down");
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
	private static class TendermintConsensusService extends GConsensusProtocolServiceGrpc.GConsensusProtocolServiceImplBase {
		private IProcessor processor;

		@Override
		public void onProposeMessage(GProposeMessage request, StreamObserver<GResult> responseObserver) {
			GResult result = processor.onReceiveProposeMessage(request);
			responseObserver.onNext(result);
			responseObserver.onCompleted();
		}

		@Override
		public void onPreVoteMessage(GPreVoteMessage request, StreamObserver<GResult> responseObserver) {
			GResult result = processor.onReceivePreVoteMessage(request);
			responseObserver.onNext(result);
			responseObserver.onCompleted();
		}

		@Override
		public void onPreCommitMessage(GPreCommitMessage request, StreamObserver<GResult> responseObserver) {
			GResult result = processor.onReceivePreCommitMessage(request);
			responseObserver.onNext(result);
			responseObserver.onCompleted();
		}

		@Override
		public void startConsensus(GRequest request, StreamObserver<GResult> responseObserver) {
			LOGGER.info("startConsensus: " + request);
		}
	}
}
