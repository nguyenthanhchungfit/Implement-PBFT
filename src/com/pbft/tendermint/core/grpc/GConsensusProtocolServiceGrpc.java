package com.pbft.tendermint.core.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.56.1)",
    comments = "Source: tendermint_consensus_protocol.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class GConsensusProtocolServiceGrpc {

  private GConsensusProtocolServiceGrpc() {}

  public static final String SERVICE_NAME = "tendermint.GConsensusProtocolService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.pbft.tendermint.core.grpc.GPingMessage,
      com.pbft.tendermint.core.grpc.GPongMessage> getPingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Ping",
      requestType = com.pbft.tendermint.core.grpc.GPingMessage.class,
      responseType = com.pbft.tendermint.core.grpc.GPongMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.pbft.tendermint.core.grpc.GPingMessage,
      com.pbft.tendermint.core.grpc.GPongMessage> getPingMethod() {
    io.grpc.MethodDescriptor<com.pbft.tendermint.core.grpc.GPingMessage, com.pbft.tendermint.core.grpc.GPongMessage> getPingMethod;
    if ((getPingMethod = GConsensusProtocolServiceGrpc.getPingMethod) == null) {
      synchronized (GConsensusProtocolServiceGrpc.class) {
        if ((getPingMethod = GConsensusProtocolServiceGrpc.getPingMethod) == null) {
          GConsensusProtocolServiceGrpc.getPingMethod = getPingMethod =
              io.grpc.MethodDescriptor.<com.pbft.tendermint.core.grpc.GPingMessage, com.pbft.tendermint.core.grpc.GPongMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Ping"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.pbft.tendermint.core.grpc.GPingMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.pbft.tendermint.core.grpc.GPongMessage.getDefaultInstance()))
              .setSchemaDescriptor(new GConsensusProtocolServiceMethodDescriptorSupplier("Ping"))
              .build();
        }
      }
    }
    return getPingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.pbft.tendermint.core.grpc.GProposeMessage,
      com.pbft.tendermint.core.grpc.GResult> getOnProposeMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OnProposeMessage",
      requestType = com.pbft.tendermint.core.grpc.GProposeMessage.class,
      responseType = com.pbft.tendermint.core.grpc.GResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.pbft.tendermint.core.grpc.GProposeMessage,
      com.pbft.tendermint.core.grpc.GResult> getOnProposeMessageMethod() {
    io.grpc.MethodDescriptor<com.pbft.tendermint.core.grpc.GProposeMessage, com.pbft.tendermint.core.grpc.GResult> getOnProposeMessageMethod;
    if ((getOnProposeMessageMethod = GConsensusProtocolServiceGrpc.getOnProposeMessageMethod) == null) {
      synchronized (GConsensusProtocolServiceGrpc.class) {
        if ((getOnProposeMessageMethod = GConsensusProtocolServiceGrpc.getOnProposeMessageMethod) == null) {
          GConsensusProtocolServiceGrpc.getOnProposeMessageMethod = getOnProposeMessageMethod =
              io.grpc.MethodDescriptor.<com.pbft.tendermint.core.grpc.GProposeMessage, com.pbft.tendermint.core.grpc.GResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OnProposeMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.pbft.tendermint.core.grpc.GProposeMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.pbft.tendermint.core.grpc.GResult.getDefaultInstance()))
              .setSchemaDescriptor(new GConsensusProtocolServiceMethodDescriptorSupplier("OnProposeMessage"))
              .build();
        }
      }
    }
    return getOnProposeMessageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.pbft.tendermint.core.grpc.GPreVoteMessage,
      com.pbft.tendermint.core.grpc.GResult> getOnPreVoteMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OnPreVoteMessage",
      requestType = com.pbft.tendermint.core.grpc.GPreVoteMessage.class,
      responseType = com.pbft.tendermint.core.grpc.GResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.pbft.tendermint.core.grpc.GPreVoteMessage,
      com.pbft.tendermint.core.grpc.GResult> getOnPreVoteMessageMethod() {
    io.grpc.MethodDescriptor<com.pbft.tendermint.core.grpc.GPreVoteMessage, com.pbft.tendermint.core.grpc.GResult> getOnPreVoteMessageMethod;
    if ((getOnPreVoteMessageMethod = GConsensusProtocolServiceGrpc.getOnPreVoteMessageMethod) == null) {
      synchronized (GConsensusProtocolServiceGrpc.class) {
        if ((getOnPreVoteMessageMethod = GConsensusProtocolServiceGrpc.getOnPreVoteMessageMethod) == null) {
          GConsensusProtocolServiceGrpc.getOnPreVoteMessageMethod = getOnPreVoteMessageMethod =
              io.grpc.MethodDescriptor.<com.pbft.tendermint.core.grpc.GPreVoteMessage, com.pbft.tendermint.core.grpc.GResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OnPreVoteMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.pbft.tendermint.core.grpc.GPreVoteMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.pbft.tendermint.core.grpc.GResult.getDefaultInstance()))
              .setSchemaDescriptor(new GConsensusProtocolServiceMethodDescriptorSupplier("OnPreVoteMessage"))
              .build();
        }
      }
    }
    return getOnPreVoteMessageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.pbft.tendermint.core.grpc.GPreCommitMessage,
      com.pbft.tendermint.core.grpc.GResult> getOnPreCommitMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OnPreCommitMessage",
      requestType = com.pbft.tendermint.core.grpc.GPreCommitMessage.class,
      responseType = com.pbft.tendermint.core.grpc.GResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.pbft.tendermint.core.grpc.GPreCommitMessage,
      com.pbft.tendermint.core.grpc.GResult> getOnPreCommitMessageMethod() {
    io.grpc.MethodDescriptor<com.pbft.tendermint.core.grpc.GPreCommitMessage, com.pbft.tendermint.core.grpc.GResult> getOnPreCommitMessageMethod;
    if ((getOnPreCommitMessageMethod = GConsensusProtocolServiceGrpc.getOnPreCommitMessageMethod) == null) {
      synchronized (GConsensusProtocolServiceGrpc.class) {
        if ((getOnPreCommitMessageMethod = GConsensusProtocolServiceGrpc.getOnPreCommitMessageMethod) == null) {
          GConsensusProtocolServiceGrpc.getOnPreCommitMessageMethod = getOnPreCommitMessageMethod =
              io.grpc.MethodDescriptor.<com.pbft.tendermint.core.grpc.GPreCommitMessage, com.pbft.tendermint.core.grpc.GResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OnPreCommitMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.pbft.tendermint.core.grpc.GPreCommitMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.pbft.tendermint.core.grpc.GResult.getDefaultInstance()))
              .setSchemaDescriptor(new GConsensusProtocolServiceMethodDescriptorSupplier("OnPreCommitMessage"))
              .build();
        }
      }
    }
    return getOnPreCommitMessageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.pbft.tendermint.core.grpc.GRequest,
      com.pbft.tendermint.core.grpc.GResult> getStartConsensusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StartConsensus",
      requestType = com.pbft.tendermint.core.grpc.GRequest.class,
      responseType = com.pbft.tendermint.core.grpc.GResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.pbft.tendermint.core.grpc.GRequest,
      com.pbft.tendermint.core.grpc.GResult> getStartConsensusMethod() {
    io.grpc.MethodDescriptor<com.pbft.tendermint.core.grpc.GRequest, com.pbft.tendermint.core.grpc.GResult> getStartConsensusMethod;
    if ((getStartConsensusMethod = GConsensusProtocolServiceGrpc.getStartConsensusMethod) == null) {
      synchronized (GConsensusProtocolServiceGrpc.class) {
        if ((getStartConsensusMethod = GConsensusProtocolServiceGrpc.getStartConsensusMethod) == null) {
          GConsensusProtocolServiceGrpc.getStartConsensusMethod = getStartConsensusMethod =
              io.grpc.MethodDescriptor.<com.pbft.tendermint.core.grpc.GRequest, com.pbft.tendermint.core.grpc.GResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StartConsensus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.pbft.tendermint.core.grpc.GRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.pbft.tendermint.core.grpc.GResult.getDefaultInstance()))
              .setSchemaDescriptor(new GConsensusProtocolServiceMethodDescriptorSupplier("StartConsensus"))
              .build();
        }
      }
    }
    return getStartConsensusMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GConsensusProtocolServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GConsensusProtocolServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GConsensusProtocolServiceStub>() {
        @java.lang.Override
        public GConsensusProtocolServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GConsensusProtocolServiceStub(channel, callOptions);
        }
      };
    return GConsensusProtocolServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GConsensusProtocolServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GConsensusProtocolServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GConsensusProtocolServiceBlockingStub>() {
        @java.lang.Override
        public GConsensusProtocolServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GConsensusProtocolServiceBlockingStub(channel, callOptions);
        }
      };
    return GConsensusProtocolServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GConsensusProtocolServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GConsensusProtocolServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GConsensusProtocolServiceFutureStub>() {
        @java.lang.Override
        public GConsensusProtocolServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GConsensusProtocolServiceFutureStub(channel, callOptions);
        }
      };
    return GConsensusProtocolServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void ping(com.pbft.tendermint.core.grpc.GPingMessage request,
        io.grpc.stub.StreamObserver<com.pbft.tendermint.core.grpc.GPongMessage> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPingMethod(), responseObserver);
    }

    /**
     */
    default void onProposeMessage(com.pbft.tendermint.core.grpc.GProposeMessage request,
        io.grpc.stub.StreamObserver<com.pbft.tendermint.core.grpc.GResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOnProposeMessageMethod(), responseObserver);
    }

    /**
     */
    default void onPreVoteMessage(com.pbft.tendermint.core.grpc.GPreVoteMessage request,
        io.grpc.stub.StreamObserver<com.pbft.tendermint.core.grpc.GResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOnPreVoteMessageMethod(), responseObserver);
    }

    /**
     */
    default void onPreCommitMessage(com.pbft.tendermint.core.grpc.GPreCommitMessage request,
        io.grpc.stub.StreamObserver<com.pbft.tendermint.core.grpc.GResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOnPreCommitMessageMethod(), responseObserver);
    }

    /**
     */
    default void startConsensus(com.pbft.tendermint.core.grpc.GRequest request,
        io.grpc.stub.StreamObserver<com.pbft.tendermint.core.grpc.GResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStartConsensusMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service GConsensusProtocolService.
   */
  public static abstract class GConsensusProtocolServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return GConsensusProtocolServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service GConsensusProtocolService.
   */
  public static final class GConsensusProtocolServiceStub
      extends io.grpc.stub.AbstractAsyncStub<GConsensusProtocolServiceStub> {
    private GConsensusProtocolServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GConsensusProtocolServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GConsensusProtocolServiceStub(channel, callOptions);
    }

    /**
     */
    public void ping(com.pbft.tendermint.core.grpc.GPingMessage request,
        io.grpc.stub.StreamObserver<com.pbft.tendermint.core.grpc.GPongMessage> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void onProposeMessage(com.pbft.tendermint.core.grpc.GProposeMessage request,
        io.grpc.stub.StreamObserver<com.pbft.tendermint.core.grpc.GResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getOnProposeMessageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void onPreVoteMessage(com.pbft.tendermint.core.grpc.GPreVoteMessage request,
        io.grpc.stub.StreamObserver<com.pbft.tendermint.core.grpc.GResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getOnPreVoteMessageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void onPreCommitMessage(com.pbft.tendermint.core.grpc.GPreCommitMessage request,
        io.grpc.stub.StreamObserver<com.pbft.tendermint.core.grpc.GResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getOnPreCommitMessageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void startConsensus(com.pbft.tendermint.core.grpc.GRequest request,
        io.grpc.stub.StreamObserver<com.pbft.tendermint.core.grpc.GResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getStartConsensusMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service GConsensusProtocolService.
   */
  public static final class GConsensusProtocolServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<GConsensusProtocolServiceBlockingStub> {
    private GConsensusProtocolServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GConsensusProtocolServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GConsensusProtocolServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.pbft.tendermint.core.grpc.GPongMessage ping(com.pbft.tendermint.core.grpc.GPingMessage request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPingMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.pbft.tendermint.core.grpc.GResult onProposeMessage(com.pbft.tendermint.core.grpc.GProposeMessage request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getOnProposeMessageMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.pbft.tendermint.core.grpc.GResult onPreVoteMessage(com.pbft.tendermint.core.grpc.GPreVoteMessage request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getOnPreVoteMessageMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.pbft.tendermint.core.grpc.GResult onPreCommitMessage(com.pbft.tendermint.core.grpc.GPreCommitMessage request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getOnPreCommitMessageMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.pbft.tendermint.core.grpc.GResult startConsensus(com.pbft.tendermint.core.grpc.GRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getStartConsensusMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service GConsensusProtocolService.
   */
  public static final class GConsensusProtocolServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<GConsensusProtocolServiceFutureStub> {
    private GConsensusProtocolServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GConsensusProtocolServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GConsensusProtocolServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.pbft.tendermint.core.grpc.GPongMessage> ping(
        com.pbft.tendermint.core.grpc.GPingMessage request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.pbft.tendermint.core.grpc.GResult> onProposeMessage(
        com.pbft.tendermint.core.grpc.GProposeMessage request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getOnProposeMessageMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.pbft.tendermint.core.grpc.GResult> onPreVoteMessage(
        com.pbft.tendermint.core.grpc.GPreVoteMessage request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getOnPreVoteMessageMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.pbft.tendermint.core.grpc.GResult> onPreCommitMessage(
        com.pbft.tendermint.core.grpc.GPreCommitMessage request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getOnPreCommitMessageMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.pbft.tendermint.core.grpc.GResult> startConsensus(
        com.pbft.tendermint.core.grpc.GRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getStartConsensusMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PING = 0;
  private static final int METHODID_ON_PROPOSE_MESSAGE = 1;
  private static final int METHODID_ON_PRE_VOTE_MESSAGE = 2;
  private static final int METHODID_ON_PRE_COMMIT_MESSAGE = 3;
  private static final int METHODID_START_CONSENSUS = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PING:
          serviceImpl.ping((com.pbft.tendermint.core.grpc.GPingMessage) request,
              (io.grpc.stub.StreamObserver<com.pbft.tendermint.core.grpc.GPongMessage>) responseObserver);
          break;
        case METHODID_ON_PROPOSE_MESSAGE:
          serviceImpl.onProposeMessage((com.pbft.tendermint.core.grpc.GProposeMessage) request,
              (io.grpc.stub.StreamObserver<com.pbft.tendermint.core.grpc.GResult>) responseObserver);
          break;
        case METHODID_ON_PRE_VOTE_MESSAGE:
          serviceImpl.onPreVoteMessage((com.pbft.tendermint.core.grpc.GPreVoteMessage) request,
              (io.grpc.stub.StreamObserver<com.pbft.tendermint.core.grpc.GResult>) responseObserver);
          break;
        case METHODID_ON_PRE_COMMIT_MESSAGE:
          serviceImpl.onPreCommitMessage((com.pbft.tendermint.core.grpc.GPreCommitMessage) request,
              (io.grpc.stub.StreamObserver<com.pbft.tendermint.core.grpc.GResult>) responseObserver);
          break;
        case METHODID_START_CONSENSUS:
          serviceImpl.startConsensus((com.pbft.tendermint.core.grpc.GRequest) request,
              (io.grpc.stub.StreamObserver<com.pbft.tendermint.core.grpc.GResult>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getPingMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.pbft.tendermint.core.grpc.GPingMessage,
              com.pbft.tendermint.core.grpc.GPongMessage>(
                service, METHODID_PING)))
        .addMethod(
          getOnProposeMessageMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.pbft.tendermint.core.grpc.GProposeMessage,
              com.pbft.tendermint.core.grpc.GResult>(
                service, METHODID_ON_PROPOSE_MESSAGE)))
        .addMethod(
          getOnPreVoteMessageMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.pbft.tendermint.core.grpc.GPreVoteMessage,
              com.pbft.tendermint.core.grpc.GResult>(
                service, METHODID_ON_PRE_VOTE_MESSAGE)))
        .addMethod(
          getOnPreCommitMessageMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.pbft.tendermint.core.grpc.GPreCommitMessage,
              com.pbft.tendermint.core.grpc.GResult>(
                service, METHODID_ON_PRE_COMMIT_MESSAGE)))
        .addMethod(
          getStartConsensusMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.pbft.tendermint.core.grpc.GRequest,
              com.pbft.tendermint.core.grpc.GResult>(
                service, METHODID_START_CONSENSUS)))
        .build();
  }

  private static abstract class GConsensusProtocolServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GConsensusProtocolServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.pbft.tendermint.core.grpc.TendermintConsensusProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GConsensusProtocolService");
    }
  }

  private static final class GConsensusProtocolServiceFileDescriptorSupplier
      extends GConsensusProtocolServiceBaseDescriptorSupplier {
    GConsensusProtocolServiceFileDescriptorSupplier() {}
  }

  private static final class GConsensusProtocolServiceMethodDescriptorSupplier
      extends GConsensusProtocolServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GConsensusProtocolServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GConsensusProtocolServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GConsensusProtocolServiceFileDescriptorSupplier())
              .addMethod(getPingMethod())
              .addMethod(getOnProposeMessageMethod())
              .addMethod(getOnPreVoteMessageMethod())
              .addMethod(getOnPreCommitMessageMethod())
              .addMethod(getStartConsensusMethod())
              .build();
        }
      }
    }
    return result;
  }
}
