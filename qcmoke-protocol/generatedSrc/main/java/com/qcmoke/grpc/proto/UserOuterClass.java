// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: user.proto

package com.qcmoke.grpc.proto;

public final class UserOuterClass {
  private UserOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_UserData_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_UserData_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_UserRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_UserRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_UserResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_UserResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\nuser.proto\"N\n\010UserData\022\n\n\002id\030\001 \001(\005\022\014\n\004" +
      "name\030\002 \001(\t\022\013\n\003sex\030\003 \001(\t\022\013\n\003age\030\004 \001(\005\022\016\n\006" +
      "remark\030\005 \001(\t\"\031\n\013UserRequest\022\n\n\002id\030\001 \001(\005\"" +
      "!\n\014UserResponse\022\021\n\tisSuccess\030\001 \001(\0102T\n\004Us" +
      "er\022&\n\010SaveUser\022\t.UserData\032\r.UserResponse" +
      "\"\000\022$\n\007GetUser\022\014.UserRequest\032\t.UserData\"\000" +
      "B\031\n\025com.qcmoke.grpc.protoP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_UserData_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_UserData_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_UserData_descriptor,
        new java.lang.String[] { "Id", "Name", "Sex", "Age", "Remark", });
    internal_static_UserRequest_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_UserRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_UserRequest_descriptor,
        new java.lang.String[] { "Id", });
    internal_static_UserResponse_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_UserResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_UserResponse_descriptor,
        new java.lang.String[] { "IsSuccess", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}