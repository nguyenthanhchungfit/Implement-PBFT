// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tendermint_consensus_protocol.proto

package com.pbft.tendermint.core.grpc;

public interface GPreCommitMessageOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tendermint.GPreCommitMessage)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 height = 1;</code>
   * @return The height.
   */
  int getHeight();

  /**
   * <code>int32 round = 2;</code>
   * @return The round.
   */
  int getRound();

  /**
   * <code>bytes hashValue = 3;</code>
   * @return The hashValue.
   */
  com.google.protobuf.ByteString getHashValue();

  /**
   * <code>bytes signature = 4;</code>
   * @return The signature.
   */
  com.google.protobuf.ByteString getSignature();

  /**
   * <code>int32 nodeId = 5;</code>
   * @return The nodeId.
   */
  int getNodeId();
}
