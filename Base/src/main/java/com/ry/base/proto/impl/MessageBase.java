// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: messageBase.proto

package com.ry.base.proto.impl;

public final class MessageBase {
  private MessageBase() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_ry_base_proto_impl_Message_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_ry_base_proto_impl_Message_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_ry_base_proto_impl_MessageHeader_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_ry_base_proto_impl_MessageHeader_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_ry_base_proto_impl_MessageBody_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_ry_base_proto_impl_MessageBody_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\021messageBase.proto\022\026com.ry.base.proto.i" +
      "mpl\032\021messageType.proto\032\021messageList.prot" +
      "o\"y\n\007Message\0228\n\tmsgHeader\030\001 \001(\0132%.com.ry" +
      ".base.proto.impl.MessageHeader\0224\n\007msgBod" +
      "y\030\002 \001(\0132#.com.ry.base.proto.impl.Message" +
      "Body\"\247\001\n\rMessageHeader\0220\n\005msgId\030\001 \001(\0162!." +
      "com.ry.base.proto.impl.MessageID\0224\n\007msgT" +
      "ype\030\002 \001(\0162#.com.ry.base.proto.impl.Messa" +
      "geType\022\017\n\007version\030\003 \001(\t\022\016\n\006source\030\004 \001(\t\022" +
      "\r\n\005token\030\005 \001(\t\"\274\001\n\013MessageBody\022\022\n\nresult" +
      "Code\030\001 \001(\005\022\021\n\tresultMsg\030\002 \001(\t\0228\n\010loginRe" +
      "q\030\003 \001(\0132$.com.ry.base.proto.impl.LoginRe" +
      "questH\000\022@\n\014appStatusReq\030\004 \001(\0132(.com.ry.b" +
      "ase.proto.impl.AppStatusRequestH\000B\n\n\010dat" +
      "aBodyB\002P\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.ry.base.proto.impl.MessageTypeOuterClass.getDescriptor(),
          com.ry.base.proto.impl.MessageList.getDescriptor(),
        });
    internal_static_com_ry_base_proto_impl_Message_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_ry_base_proto_impl_Message_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_ry_base_proto_impl_Message_descriptor,
        new java.lang.String[] { "MsgHeader", "MsgBody", });
    internal_static_com_ry_base_proto_impl_MessageHeader_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_ry_base_proto_impl_MessageHeader_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_ry_base_proto_impl_MessageHeader_descriptor,
        new java.lang.String[] { "MsgId", "MsgType", "Version", "Source", "Token", });
    internal_static_com_ry_base_proto_impl_MessageBody_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_com_ry_base_proto_impl_MessageBody_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_ry_base_proto_impl_MessageBody_descriptor,
        new java.lang.String[] { "ResultCode", "ResultMsg", "LoginReq", "AppStatusReq", "DataBody", });
    com.ry.base.proto.impl.MessageTypeOuterClass.getDescriptor();
    com.ry.base.proto.impl.MessageList.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
