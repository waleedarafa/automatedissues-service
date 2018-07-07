package com.hungerstation.automated_issues.services;

import com.hungerstation.automated_issues.domain.AutomatedIssue;
import com.hungerstation.automated_issues.exceptions.InvalidTriggerException;
import com.hungerstation.automated_issues.trigger.Trigger;
import com.hungerstation.automated_issues.trigger.enums.TriggerBaseEnum;
import com.hungerstation.automated_issues.trigger.enums.TriggerTypeEnum;
import com.hungerstation.automated_issues.trigger.factory.TriggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Waleed Arafa on 22/06/2018.
 */
@Service
public class AsynchronousService {

    @Autowired
    TriggerFactory triggerFactory;

    @Autowired
    OperationsService operationsService;

    private Logger logger = LoggerFactory.getLogger(AsynchronousService.class);

    private final static List<TriggerTypeEnum> RIDER_CREATED_TRIGGERS_LIST;
    private final static List<TriggerTypeEnum> RIDER_UPDATED_TRIGGERS_LIST;
    private final static List<TriggerTypeEnum> TASK_CREATED_TRIGGERS_LIST;
    private final static List<TriggerTypeEnum> TASK_UPDATED_TRIGGERS_LIST;

    static {
        RIDER_CREATED_TRIGGERS_LIST = new ArrayList<>();
        RIDER_UPDATED_TRIGGERS_LIST = Arrays.asList(TriggerTypeEnum.OfflineWithOrderInHand, TriggerTypeEnum.OnBoarding,
                TriggerTypeEnum.InactiveNewRider, TriggerTypeEnum.PenalizedAssignmentDecline);
        TASK_CREATED_TRIGGERS_LIST = Arrays.asList(TriggerTypeEnum.FirstOrder,
                TriggerTypeEnum.StartedOrderWithPaymentAtPickup);
        TASK_UPDATED_TRIGGERS_LIST = Arrays.asList(TriggerTypeEnum.FoodPickedupNotEnrouteToCustomerYet,
                TriggerTypeEnum.ArrivedDeliveryDestinationWithoutUpdates, TriggerTypeEnum.PotentialFraud);
    }


    @Async
    public void riderCreatedProcess(String data) {

        //TODO:use RiderService to get Rider with rider_id given in data
        for (TriggerTypeEnum triggerTypeEnum : RIDER_CREATED_TRIGGERS_LIST) {
            //TODO: update this method to take more one parameter [Rider] variable.
            Trigger trigger = intializeRiderTrigger(triggerTypeEnum);
            if (trigger.can_trigger()) {
                create(triggerTypeEnum.getNumber(), Long.parseLong(data));
            }

        }

    }

    @Async
    public void riderUpdatedProcess(String data) {
        //TODO:use RiderService to get Rider with rider_id given in data
        for (TriggerTypeEnum triggerTypeEnum : RIDER_UPDATED_TRIGGERS_LIST) {
            //TODO: update this method to take more one parameter [Rider] variable.
            Trigger trigger = intializeRiderTrigger(triggerTypeEnum);
            if (trigger.can_trigger()) {
                create(triggerTypeEnum.getNumber(), Long.parseLong(data));
            }

        }
    }

    @Async
    public void taskCreatedProcess(String data) {

    }

    @Async
    public void taskUpdatedProcess(String data) {

    }

    private Trigger intializeRiderTrigger(TriggerTypeEnum triggerTypeEnum) {
        Trigger trigger = null;
        try {
            trigger = triggerFactory.getTrigger(triggerTypeEnum);
            //TODO:use trigger.setRider
        } catch (InvalidTriggerException e) {
            logger.error("intializeRiderTrigger:  " + e.toString());
        }
        return trigger;
    }

    private void create(Integer triggerKey, Long relatedToId) {

        AutomatedIssue automatedIssue = new AutomatedIssue();
        automatedIssue.setTriggerKey(triggerKey);
        automatedIssue.setRelatedToId(relatedToId);
        automatedIssue.setRelatedToType(TriggerBaseEnum.RIDER.getValue());
        automatedIssue.setStatus(AutomatedIssue.StatusEnum.OPEN);

        try {
            AutomatedIssue saved = operationsService.create(automatedIssue);
            if (saved == null) {
                logger.error("error while saving the issue in DB");
            }
        } catch (InvalidTriggerException e) {
            logger.error("create Method " + e.toString());
        }
    }

}
