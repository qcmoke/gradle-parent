package com.qcmoke.client.service;

import com.qcmoke.client.vo.UserDataVo;
import com.qcmoke.grpc.proto.UserData;
import com.qcmoke.grpc.proto.UserGrpc;
import com.qcmoke.grpc.proto.UserRequest;
import com.qcmoke.utils.ProtobufUtil;
import io.grpc.Channel;
import net.devh.springboot.autoconfigure.grpc.client.GrpcClient;
import org.springframework.stereotype.Service;

//@Service
//public class UserClient {
//
//    @GrpcClient("qcmoke-grpc-server")
//    private UserGrpc.UserBlockingStub stub;
//
//    public UserDataVo getUser() {
//        UserRequest userRequest = UserRequest.newBuilder().setId(1).build();
//        UserData userData = stub.getUser(userRequest);
//        return ProtobufUtil.toBean(userData, UserDataVo.class);
//    }
//}


@Service
public class UserClient extends UserGrpc.UserImplBase {

    @GrpcClient("qcmoke-grpc-server")
    private Channel channel;

    public UserDataVo getUser() {
        UserGrpc.UserBlockingStub stub = UserGrpc.newBlockingStub(channel);
        UserRequest userRequest = UserRequest.newBuilder().setId(1).build();
        UserData userData = stub.getUser(userRequest);
        return ProtobufUtil.toBean(userData, UserDataVo.class);
    }
}

