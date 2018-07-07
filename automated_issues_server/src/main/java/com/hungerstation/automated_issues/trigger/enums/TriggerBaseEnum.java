package com.hungerstation.automated_issues.trigger.enums;

/**
 * Created by Waleed Arafa on 17/06/2018.
 */
public enum TriggerBaseEnum {
    RIDER("Rider"), TASK("Task");

    String value;

    private TriggerBaseEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
