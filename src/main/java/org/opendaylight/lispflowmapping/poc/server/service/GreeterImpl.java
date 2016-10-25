package org.opendaylight.lispflowmapping.poc.server.service;

import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;

/**
 * Created by sheikahm on 10/24/16.
 */
public class GreeterImpl extends GreeterGrpc.GreeterImplBase {
    private static final Logger logger = Logger.getLogger(GreeterImpl.class.getName());

    int served = 0;

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder()
                .setMessage("Hello client " + req.getName())
                .build();
        served++;

        if(served % 10000 == 0) {
            logger.info("Completed " + served/10000 + " rounds");
        }
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
