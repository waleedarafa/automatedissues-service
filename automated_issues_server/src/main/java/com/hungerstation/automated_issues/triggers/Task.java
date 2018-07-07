package com.hungerstation.automated_issues.triggers;

import com.hungerstation.automated_issues.trigger.Trigger;
import com.hungerstation.automated_issues.trigger.enums.TriggerBaseEnum;

/**
 * Created by Waleed Arafa on 17/06/2018.
 */
public abstract class Task extends Trigger {

    private final TriggerBaseEnum related_to_type = TriggerBaseEnum.TASK;

    //TODO: define Task variable then create Setter & Getter .

    public TriggerBaseEnum getRelated_to_type() {
        return related_to_type;
    }
}
