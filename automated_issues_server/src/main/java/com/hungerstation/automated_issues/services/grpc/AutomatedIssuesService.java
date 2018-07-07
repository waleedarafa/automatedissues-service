package com.hungerstation.automated_issues.services.grpc;

import com.hungerstation.automated_issues.domain.AutomatedIssue;
import com.hungerstation.automated_issues.exceptions.InvalidTriggerException;
import com.hungerstation.automated_issues.grpc.services.StatusEnum;
import com.hungerstation.automated_issues.services.OperationsService;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.*;

import java.util.Optional;

/**
 * Created by Waleed Arafa on 16/06/2018.
 */
@GRpcService
public class AutomatedIssuesService extends
        com.hungerstation.automated_issues.grpc.services.AutomatedIssuesServiceGrpc.AutomatedIssuesServiceImplBase {

    @Autowired
    OperationsService operationsService;

    private Logger logger = LoggerFactory.getLogger(AutomatedIssuesService.class);

    @Override
    public void create(com.hungerstation.automated_issues.grpc.services.AutomatedIssue request, StreamObserver<com.hungerstation.automated_issues.grpc.services.AutomatedIssue> responseObserver) {
        logger.debug("Request " + request);

        if (request.getRelatedToId() <= 0) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription(String.format("related_to_id must be greater than zero"))
                    .asRuntimeException());
            return;
        }

        if (request.getRelatedToType() == null || request.getRelatedToType().isEmpty()) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription(String.format("related_to_type must be specified"))
                    .asRuntimeException());
            return;
        }

        AutomatedIssue automatedIssue = new AutomatedIssue();
        automatedIssue.setTriggerKey(request.getTriggerKey());
        automatedIssue.setStatus(AutomatedIssue.StatusEnum.forNumber(request.getStatus().getNumber()));
        automatedIssue.setRelatedToType(request.getRelatedToType());
        automatedIssue.setRelatedToId(request.getRelatedToId());

        AutomatedIssue savedAutomatedIssue;
        try {
            savedAutomatedIssue = operationsService.create(automatedIssue);
            if (savedAutomatedIssue == null) {
                responseObserver.onError(Status.ABORTED
                        .withDescription(String.format("error while saving the issue in DB"))
                        .asRuntimeException());
                return;
            }
        } catch (InvalidTriggerException e) {
            logger.error("create Method " + e.toString());
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription(String.format("Trigger with key [%s] is Invalid", request.getTriggerKey()))
                    .asRuntimeException());
            return;
        }

        com.hungerstation.automated_issues.grpc.services.AutomatedIssue AutomatedIssueResponse = createDTO(savedAutomatedIssue);

        logger.debug("Response " + AutomatedIssueResponse);

        responseObserver.onNext(AutomatedIssueResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void fetch(com.hungerstation.automated_issues.grpc.services.Id request,
                      io.grpc.stub.StreamObserver<com.hungerstation.automated_issues.grpc.services.AutomatedIssue> responseObserver) {

        Optional<AutomatedIssue> automatedIssue = operationsService.fetch(request.getId());

        if (automatedIssue.isPresent()) {
            responseObserver.onNext(createDTO(automatedIssue.get()));
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(Status.UNAVAILABLE
                    .withDescription(String.format("automatedIssue with id [%s] is unavailable",
                            request.getId()))
                    .asRuntimeException());
        }


    }


    @Override
    public void filter(com.hungerstation.automated_issues.grpc.services.SearchCriteria request,
                       io.grpc.stub.StreamObserver<com.hungerstation.automated_issues.grpc.services.AutomatedIssues> responseObserver) {
        if (request.getPage() <= 0) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription(String.format("Page number must starts from 1"))
                    .asRuntimeException());
            return;
        }
        if (request.getPerPage() < 0) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription(String.format("PerPage number must be greater than zero"))
                    .asRuntimeException());
            return;
        }

        // create issue to be as a criteria
        AutomatedIssue automatedIssue = new AutomatedIssue();
        if (request.getCriteriaMap().containsKey("trigger_key")) {
            try {
                automatedIssue.setTriggerKey(Integer.parseInt(request.getCriteriaMap().get("trigger_key")));
            } catch (NumberFormatException nfe) {
                responseObserver.onError(Status.INVALID_ARGUMENT
                        .withDescription(String.format("trigger_key must be number"))
                        .asRuntimeException());
                return;
            }
        }
        if (request.getCriteriaMap().containsKey("related_to_id")) {
            try {
                automatedIssue.setRelatedToId(Long.parseLong(request.getCriteriaMap().get("related_to_id")));
            } catch (NumberFormatException nfe) {
                responseObserver.onError(Status.INVALID_ARGUMENT
                        .withDescription(String.format("related_to_id must be number"))
                        .asRuntimeException());
                return;
            }
        }

        if (request.getCriteriaMap().containsKey("related_to_type")) {
            automatedIssue.setRelatedToType(request.getCriteriaMap().get("related_to_type"));
        }

        if (request.getCriteriaMap().containsKey("status")) {
            try {
                automatedIssue.setStatus(AutomatedIssue.StatusEnum.forNumber(Integer.parseInt(request.getCriteriaMap().get("status"))));
            } catch (NumberFormatException nfe) {
                responseObserver.onError(Status.INVALID_ARGUMENT
                        .withDescription(String.format("status must be equal 0 or 1"))
                        .asRuntimeException());
                return;
            }

        }
        //default value is always 50
        int perPage = request.getPerPage() == 0 ? 50 : request.getPerPage();
        responseObserver.onNext(createListDTO(operationsService.filter(automatedIssue, request.getPage(), perPage)));
        responseObserver.onCompleted();
    }

    private com.hungerstation.automated_issues.grpc.services.AutomatedIssue createDTO(AutomatedIssue automatedIssue) {
        return com.hungerstation.automated_issues.grpc.services.AutomatedIssue.newBuilder()
                .setTriggerKey(automatedIssue.getTriggerKey())
                .setStatus(StatusEnum.forNumber(automatedIssue.getStatus().getNumber()))
                .setRelatedToType(automatedIssue.getRelatedToType())
                .setRelatedToId(automatedIssue.getRelatedToId())
                .build();
    }

    private com.hungerstation.automated_issues.grpc.services.AutomatedIssues createListDTO(Page<AutomatedIssue> issues) {
        com.hungerstation.automated_issues.grpc.services.AutomatedIssues.Builder issuesBuilder = com.hungerstation.automated_issues.grpc.services.AutomatedIssues.newBuilder();
        for (AutomatedIssue issue : issues) {
            issuesBuilder.addAutomatedIssue(createDTO(issue));
        }
        return issuesBuilder.build();
    }


}
