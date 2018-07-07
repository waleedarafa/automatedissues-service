package com.hungerstation.grpc;

import com.hungerstation.automated_issues.grpc.services.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;


@Service
public class AutomatedIssuesServiceGrpcClient {


    ManagedChannel managedChannel;

    private Logger logger = LoggerFactory.getLogger(AutomatedIssuesServiceGrpcClient.class);

    private AutomatedIssuesServiceGrpc.AutomatedIssuesServiceBlockingStub AutomatedIssueServiceBlockingStub;
    private SubscribersServiceGrpc.SubscribersServiceBlockingStub subscribersServiceBlockingStub;

    public void create() {
        AutomatedIssue.Builder builder = AutomatedIssue.newBuilder();
        builder.setTriggerKey(500)
                .setStatus(StatusEnum.OPEN)
                .setRelatedToType("Task")
                .setRelatedToId(5);
        AutomatedIssue request = builder.build();
        logger.debug("Request " + request);

        AutomatedIssue response;
        try {
            response = AutomatedIssueServiceBlockingStub.create(request);
        } catch (StatusRuntimeException e) {
            logger.error("Create operation failed: " + e.getStatus());
            return;
        }
        logger.debug("Response " + response);
    }

    public void fetch() {
        Id.Builder builder = Id.newBuilder();
        builder.setId(3);
        Id request = builder.build();
        logger.debug("Request " + request);

        AutomatedIssue response;
        try {
            response = AutomatedIssueServiceBlockingStub.fetch(request);
        } catch (StatusRuntimeException e) {
            logger.error("fetch operation failed: " + e.getStatus());
            return;
        }
        logger.debug("Response " + response);
    }

    public void filter() {
        SearchCriteria.Builder criteriaBuilder = SearchCriteria.newBuilder();

        criteriaBuilder.putCriteria("status", "1")
                .putCriteria("related_to_type", "Rider")
                .setPage(1);
        SearchCriteria request = criteriaBuilder.build();

        logger.debug("Request " + request);

        AutomatedIssues response;
        try {
            response = AutomatedIssueServiceBlockingStub.filter(request);
        } catch (StatusRuntimeException e) {
            logger.error("filter operation failed: " + e.getStatus());
            return;
        }
        logger.debug("Response " + response.getAutomatedIssueList());
    }

    public void publishEvent() {
        Event.Builder builder = Event.newBuilder();
        builder.setName("rider_updated")
                .setData("32");
        Event request = builder.build();
        logger.debug("Request " + request);

        Empty response;
        try {
            response = subscribersServiceBlockingStub.publishEvent(request);
        } catch (StatusRuntimeException e) {
            logger.error("publishEvent operation failed: " + e.getStatus());
            return;
        }
        logger.debug("Response " + response);
    }

    @PostConstruct
    private void initializeClient() {

        managedChannel = ManagedChannelBuilder.forAddress("localhost", 7565)
                .usePlaintext(true)
                .build();

        AutomatedIssueServiceBlockingStub = AutomatedIssuesServiceGrpc.newBlockingStub(managedChannel);
        subscribersServiceBlockingStub = SubscribersServiceGrpc.newBlockingStub(managedChannel);

    }

    public void shutdown() throws InterruptedException {
        managedChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

}
