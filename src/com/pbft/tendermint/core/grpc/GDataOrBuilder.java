// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tendermint_consensus_protocol.proto

package com.pbft.tendermint.core.grpc;

public interface GDataOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tendermint.GData)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>uint64 timestamp = 1;</code>
   * @return The timestamp.
   */
  long getTimestamp();

  /**
   * <code>string data = 2;</code>
   * @return The data.
   */
  java.lang.String getData();
  /**
   * <code>string data = 2;</code>
   * @return The bytes for data.
   */
  com.google.protobuf.ByteString
      getDataBytes();
}
