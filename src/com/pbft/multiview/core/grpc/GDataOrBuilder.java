// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: multiview_consensus_protocol.proto

package com.pbft.multiview.core.grpc;

public interface GDataOrBuilder extends
    // @@protoc_insertion_point(interface_extends:multiview.GData)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>uint64 timestamp = 1;</code>
   * @return The timestamp.
   */
  long getTimestamp();

  /**
   * <code>string value = 2;</code>
   * @return The value.
   */
  java.lang.String getValue();
  /**
   * <code>string value = 2;</code>
   * @return The bytes for value.
   */
  com.google.protobuf.ByteString
      getValueBytes();
}
