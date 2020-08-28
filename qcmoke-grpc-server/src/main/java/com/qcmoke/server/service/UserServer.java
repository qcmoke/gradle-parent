package com.qcmoke.server.service;

import com.qcmoke.grpc.proto.UserData;
import com.qcmoke.grpc.proto.UserGrpc;
import com.qcmoke.grpc.proto.UserRequest;
import com.qcmoke.grpc.proto.UserResponse;
import io.grpc.stub.StreamObserver;
import net.devh.springboot.autoconfigure.grpc.server.GrpcService;

@GrpcService(UserGrpc.class)
public class UserServer extends UserGrpc.UserImplBase {
    @Override
    public void saveUser(UserData request, StreamObserver<UserResponse> responseObserver) {
        super.saveUser(request, responseObserver);
    }

    @Override
    public void getUser(UserRequest request, StreamObserver<UserData> responseObserver) {
        int id = request.getId();
        UserData userData = UserData.newBuilder().setId(id).setName("qcmoke").setAge(24).setRemark("A good boy !").build();
        responseObserver.onNext(userData);
        responseObserver.onCompleted();
    }
}
