// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tendermint_consensus_protocol.proto

package com.pbft.tendermint.core.grpc;

/**
 * Protobuf type {@code tendermint.GPreCommitMessage}
 */
public final class GPreCommitMessage extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:tendermint.GPreCommitMessage)
    GPreCommitMessageOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GPreCommitMessage.newBuilder() to construct.
  private GPreCommitMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GPreCommitMessage() {
    hashValue_ = com.google.protobuf.ByteString.EMPTY;
    signature_ = com.google.protobuf.ByteString.EMPTY;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new GPreCommitMessage();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GPreCommitMessage(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {

            height_ = input.readInt32();
            break;
          }
          case 16: {

            round_ = input.readInt32();
            break;
          }
          case 26: {

            hashValue_ = input.readBytes();
            break;
          }
          case 34: {

            signature_ = input.readBytes();
            break;
          }
          case 40: {

            nodeId_ = input.readInt32();
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.pbft.tendermint.core.grpc.TendermintConsensusProto.internal_static_tendermint_GPreCommitMessage_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.pbft.tendermint.core.grpc.TendermintConsensusProto.internal_static_tendermint_GPreCommitMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.pbft.tendermint.core.grpc.GPreCommitMessage.class, com.pbft.tendermint.core.grpc.GPreCommitMessage.Builder.class);
  }

  public static final int HEIGHT_FIELD_NUMBER = 1;
  private int height_;
  /**
   * <code>int32 height = 1;</code>
   * @return The height.
   */
  @java.lang.Override
  public int getHeight() {
    return height_;
  }

  public static final int ROUND_FIELD_NUMBER = 2;
  private int round_;
  /**
   * <code>int32 round = 2;</code>
   * @return The round.
   */
  @java.lang.Override
  public int getRound() {
    return round_;
  }

  public static final int HASHVALUE_FIELD_NUMBER = 3;
  private com.google.protobuf.ByteString hashValue_;
  /**
   * <code>bytes hashValue = 3;</code>
   * @return The hashValue.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getHashValue() {
    return hashValue_;
  }

  public static final int SIGNATURE_FIELD_NUMBER = 4;
  private com.google.protobuf.ByteString signature_;
  /**
   * <code>bytes signature = 4;</code>
   * @return The signature.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getSignature() {
    return signature_;
  }

  public static final int NODEID_FIELD_NUMBER = 5;
  private int nodeId_;
  /**
   * <code>int32 nodeId = 5;</code>
   * @return The nodeId.
   */
  @java.lang.Override
  public int getNodeId() {
    return nodeId_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (height_ != 0) {
      output.writeInt32(1, height_);
    }
    if (round_ != 0) {
      output.writeInt32(2, round_);
    }
    if (!hashValue_.isEmpty()) {
      output.writeBytes(3, hashValue_);
    }
    if (!signature_.isEmpty()) {
      output.writeBytes(4, signature_);
    }
    if (nodeId_ != 0) {
      output.writeInt32(5, nodeId_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (height_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, height_);
    }
    if (round_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, round_);
    }
    if (!hashValue_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(3, hashValue_);
    }
    if (!signature_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(4, signature_);
    }
    if (nodeId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, nodeId_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.pbft.tendermint.core.grpc.GPreCommitMessage)) {
      return super.equals(obj);
    }
    com.pbft.tendermint.core.grpc.GPreCommitMessage other = (com.pbft.tendermint.core.grpc.GPreCommitMessage) obj;

    if (getHeight()
        != other.getHeight()) return false;
    if (getRound()
        != other.getRound()) return false;
    if (!getHashValue()
        .equals(other.getHashValue())) return false;
    if (!getSignature()
        .equals(other.getSignature())) return false;
    if (getNodeId()
        != other.getNodeId()) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + HEIGHT_FIELD_NUMBER;
    hash = (53 * hash) + getHeight();
    hash = (37 * hash) + ROUND_FIELD_NUMBER;
    hash = (53 * hash) + getRound();
    hash = (37 * hash) + HASHVALUE_FIELD_NUMBER;
    hash = (53 * hash) + getHashValue().hashCode();
    hash = (37 * hash) + SIGNATURE_FIELD_NUMBER;
    hash = (53 * hash) + getSignature().hashCode();
    hash = (37 * hash) + NODEID_FIELD_NUMBER;
    hash = (53 * hash) + getNodeId();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.pbft.tendermint.core.grpc.GPreCommitMessage parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.pbft.tendermint.core.grpc.GPreCommitMessage parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.pbft.tendermint.core.grpc.GPreCommitMessage parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.pbft.tendermint.core.grpc.GPreCommitMessage parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.pbft.tendermint.core.grpc.GPreCommitMessage parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.pbft.tendermint.core.grpc.GPreCommitMessage parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.pbft.tendermint.core.grpc.GPreCommitMessage parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.pbft.tendermint.core.grpc.GPreCommitMessage parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.pbft.tendermint.core.grpc.GPreCommitMessage parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.pbft.tendermint.core.grpc.GPreCommitMessage parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.pbft.tendermint.core.grpc.GPreCommitMessage parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.pbft.tendermint.core.grpc.GPreCommitMessage parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.pbft.tendermint.core.grpc.GPreCommitMessage prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code tendermint.GPreCommitMessage}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:tendermint.GPreCommitMessage)
      com.pbft.tendermint.core.grpc.GPreCommitMessageOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.pbft.tendermint.core.grpc.TendermintConsensusProto.internal_static_tendermint_GPreCommitMessage_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.pbft.tendermint.core.grpc.TendermintConsensusProto.internal_static_tendermint_GPreCommitMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.pbft.tendermint.core.grpc.GPreCommitMessage.class, com.pbft.tendermint.core.grpc.GPreCommitMessage.Builder.class);
    }

    // Construct using com.pbft.tendermint.core.grpc.GPreCommitMessage.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      height_ = 0;

      round_ = 0;

      hashValue_ = com.google.protobuf.ByteString.EMPTY;

      signature_ = com.google.protobuf.ByteString.EMPTY;

      nodeId_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.pbft.tendermint.core.grpc.TendermintConsensusProto.internal_static_tendermint_GPreCommitMessage_descriptor;
    }

    @java.lang.Override
    public com.pbft.tendermint.core.grpc.GPreCommitMessage getDefaultInstanceForType() {
      return com.pbft.tendermint.core.grpc.GPreCommitMessage.getDefaultInstance();
    }

    @java.lang.Override
    public com.pbft.tendermint.core.grpc.GPreCommitMessage build() {
      com.pbft.tendermint.core.grpc.GPreCommitMessage result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.pbft.tendermint.core.grpc.GPreCommitMessage buildPartial() {
      com.pbft.tendermint.core.grpc.GPreCommitMessage result = new com.pbft.tendermint.core.grpc.GPreCommitMessage(this);
      result.height_ = height_;
      result.round_ = round_;
      result.hashValue_ = hashValue_;
      result.signature_ = signature_;
      result.nodeId_ = nodeId_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.pbft.tendermint.core.grpc.GPreCommitMessage) {
        return mergeFrom((com.pbft.tendermint.core.grpc.GPreCommitMessage)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.pbft.tendermint.core.grpc.GPreCommitMessage other) {
      if (other == com.pbft.tendermint.core.grpc.GPreCommitMessage.getDefaultInstance()) return this;
      if (other.getHeight() != 0) {
        setHeight(other.getHeight());
      }
      if (other.getRound() != 0) {
        setRound(other.getRound());
      }
      if (other.getHashValue() != com.google.protobuf.ByteString.EMPTY) {
        setHashValue(other.getHashValue());
      }
      if (other.getSignature() != com.google.protobuf.ByteString.EMPTY) {
        setSignature(other.getSignature());
      }
      if (other.getNodeId() != 0) {
        setNodeId(other.getNodeId());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.pbft.tendermint.core.grpc.GPreCommitMessage parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.pbft.tendermint.core.grpc.GPreCommitMessage) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int height_ ;
    /**
     * <code>int32 height = 1;</code>
     * @return The height.
     */
    @java.lang.Override
    public int getHeight() {
      return height_;
    }
    /**
     * <code>int32 height = 1;</code>
     * @param value The height to set.
     * @return This builder for chaining.
     */
    public Builder setHeight(int value) {
      
      height_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 height = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearHeight() {
      
      height_ = 0;
      onChanged();
      return this;
    }

    private int round_ ;
    /**
     * <code>int32 round = 2;</code>
     * @return The round.
     */
    @java.lang.Override
    public int getRound() {
      return round_;
    }
    /**
     * <code>int32 round = 2;</code>
     * @param value The round to set.
     * @return This builder for chaining.
     */
    public Builder setRound(int value) {
      
      round_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 round = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearRound() {
      
      round_ = 0;
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString hashValue_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes hashValue = 3;</code>
     * @return The hashValue.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getHashValue() {
      return hashValue_;
    }
    /**
     * <code>bytes hashValue = 3;</code>
     * @param value The hashValue to set.
     * @return This builder for chaining.
     */
    public Builder setHashValue(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      hashValue_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bytes hashValue = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearHashValue() {
      
      hashValue_ = getDefaultInstance().getHashValue();
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString signature_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes signature = 4;</code>
     * @return The signature.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getSignature() {
      return signature_;
    }
    /**
     * <code>bytes signature = 4;</code>
     * @param value The signature to set.
     * @return This builder for chaining.
     */
    public Builder setSignature(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      signature_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bytes signature = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearSignature() {
      
      signature_ = getDefaultInstance().getSignature();
      onChanged();
      return this;
    }

    private int nodeId_ ;
    /**
     * <code>int32 nodeId = 5;</code>
     * @return The nodeId.
     */
    @java.lang.Override
    public int getNodeId() {
      return nodeId_;
    }
    /**
     * <code>int32 nodeId = 5;</code>
     * @param value The nodeId to set.
     * @return This builder for chaining.
     */
    public Builder setNodeId(int value) {
      
      nodeId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 nodeId = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearNodeId() {
      
      nodeId_ = 0;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:tendermint.GPreCommitMessage)
  }

  // @@protoc_insertion_point(class_scope:tendermint.GPreCommitMessage)
  private static final com.pbft.tendermint.core.grpc.GPreCommitMessage DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.pbft.tendermint.core.grpc.GPreCommitMessage();
  }

  public static com.pbft.tendermint.core.grpc.GPreCommitMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GPreCommitMessage>
      PARSER = new com.google.protobuf.AbstractParser<GPreCommitMessage>() {
    @java.lang.Override
    public GPreCommitMessage parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GPreCommitMessage(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GPreCommitMessage> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GPreCommitMessage> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.pbft.tendermint.core.grpc.GPreCommitMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

