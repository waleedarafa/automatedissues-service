package com.hungerstation.automated_issues.services.grpc;

import com.hungerstation.automated_issues.exceptions.InvalidEventException;
import com.hungerstation.automated_issues.grpc.services.Empty;
import com.hungerstation.automated_issues.trigger.enums.WorkersEnum;
import com.hungerstation.automated_issues.trigger.executer.Worker;
import com.hungerstation.automated_issues.trigger.factory.WorkerFactory;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Waleed Arafa on 23/06/2018.
 */
@GRpcService
public class SubscribersService extends com.hungerstation.automated_issues.grpc.services.SubscribersServiceGrpc.SubscribersServiceImplBase {
    private Logger logger = LoggerFactory.getLogger(SubscribersService.class);

    @Autowired
    WorkerFactory workerFactory;

    @Override
    public void publishEvent(com.hungerstation.automated_issues.grpc.services.Event request, StreamObserver<Empty> responseObserver) {
        logger.debug("Request " + request);
        Worker worker;
        try {
            worker = workerFactory.getWorker(WorkersEnum.forValue(request.getName()));
        } catch (InvalidEventException e) {
            logger.error("publishEvent Method " + e.toString());
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription(String.format("Event with name [%s] is Invalid", request.getName()))
                    .asRuntimeException());
            return;
        }
        worker.executeAsynchronously(request.getData());

        Empty empty = Empty.newBuilder().build();

        logger.debug("Response " + empty);

        responseObserver.onNext(empty);
        responseObserver.onCompleted();
    }
}

