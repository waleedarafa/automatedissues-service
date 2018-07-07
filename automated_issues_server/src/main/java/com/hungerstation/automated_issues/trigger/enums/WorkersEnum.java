package com.hungerstation.automated_issues.trigger.enums;

/**
 * Created by Waleed Arafa on 22/06/2018.
 */
public enum WorkersEnum {
    RIDERCREATED("rider_created"),
    RIDERUPDATED("rider_updated"),
    TASKCREATED("task_created"),
    TASKUPDATED("task_updated");

    String value;

    private WorkersEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static WorkersEnum forValue(String value) {
        for (WorkersEnum e : values()) {
            if (e.getValue().equals(value)) {
                return e;
            }
        }
        return null;
    }


}
