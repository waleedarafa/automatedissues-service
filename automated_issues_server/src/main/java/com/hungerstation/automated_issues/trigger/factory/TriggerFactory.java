package com.hungerstation.automated_issues.trigger.factory;

import com.hungerstation.automated_issues.exceptions.InvalidTriggerException;
import com.hungerstation.automated_issues.rider_triggers.*;
import com.hungerstation.automated_issues.task_triggers.*;
import com.hungerstation.automated_issues.trigger.Trigger;
import com.hungerstation.automated_issues.trigger.enums.TriggerTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by Waleed Arafa on 16/06/2018.
 */
@Component
public class TriggerFactory {

    @Autowired
    private ApplicationContext applicationContext;

    public Trigger getTrigger(TriggerTypeEnum triggerType) throws InvalidTriggerException {
        if (triggerType == null)
            throw new InvalidTriggerException();
        Trigger trigger = null;
        switch (triggerType) {
            case ActiveStateNotMovingForFiveMinutes:
                trigger = applicationContext.getBean(ActiveStateNotMovingForFiveMinutes.class);
                break;
            case BalanceCloseToThreshold:
                trigger = applicationContext.getBean(BalanceCloseToThreshold.class);
                break;
            case FirstOrder:
                trigger = applicationContext.getBean(FirstOrder.class);
                break;
            case OfflineWithOrderInHand:
                trigger = applicationContext.getBean(OfflineWithOrderInHand.class);
                break;
            case OnBoarding:
                trigger = applicationContext.getBean(OnBoarding.class);
                break;
            case InactiveNewRider:
                trigger = applicationContext.getBean(InactiveNewRider.class);
                break;
            case StartedOrderWithPaymentAtPickup:
                trigger = applicationContext.getBean(StartedOrderWithPaymentAtPickup.class);
                break;
            case PenalizedAssignmentDecline:
                trigger = applicationContext.getBean(PenalizedAssignmentDecline.class);
                break;
            case ArrivedDeliveryDestinationWithoutUpdates:
                trigger = applicationContext.getBean(ArrivedDeliveryDestinationWithoutUpdates.class);
                break;
            case FoodPickedupNotEnrouteToCustomerYet:
                trigger = applicationContext.getBean(FoodPickedupNotEnrouteToCustomerYet.class);
                break;
            case ReachingDeliveryDestinationLateness:
                trigger = applicationContext.getBean(ReachingDeliveryDestinationLateness.class);
                break;
            case ReachingPickupDestinationLateness:
                trigger = applicationContext.getBean(ReachingPickupDestinationLateness.class);
                break;
            case StartingPickupTaskLateness:
                trigger = applicationContext.getBean(StartingPickupTaskLateness.class);
                break;
            case StartingDeliveryTaskLateness:
                trigger = applicationContext.getBean(StartingDeliveryTaskLateness.class);
                break;
            case PotentialFraud:
                trigger = applicationContext.getBean(PotentialFraud.class);
                break;
            default:
                break;
        }
        return trigger;
    }
}
