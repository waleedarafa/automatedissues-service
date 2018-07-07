package com.hungerstation.automated_issues.trigger.enums;

/**
 * Created by Waleed Arafa on 16/06/2018.
 */
public enum TriggerTypeEnum {
    ActiveStateNotMovingForFiveMinutes(200),
    BalanceCloseToThreshold(201),
    FirstOrder(202),
    OfflineWithOrderInHand(203),
    OnBoarding(204),
    InactiveNewRider(205),
    StartedOrderWithPaymentAtPickup(206),
    PenalizedAssignmentDecline(207),

    ArrivedDeliveryDestinationWithoutUpdates(100),
    FoodPickedupNotEnrouteToCustomerYet(101),
    ReachingDeliveryDestinationLateness(102),
    ReachingPickupDestinationLateness(103),
    StartingPickupTaskLateness(104),
    StartingDeliveryTaskLateness(105),
    PotentialFraud(106);



    private int number;

    private TriggerTypeEnum(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public static TriggerTypeEnum forNumber(int number) {
        for (TriggerTypeEnum e : values()) {
            if (e.getNumber() == number) {
                return e;
            }
        }
        return null;
    }

}