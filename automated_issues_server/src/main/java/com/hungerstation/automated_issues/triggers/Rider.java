package com.hungerstation.automated_issues.triggers;

import com.hungerstation.automated_issues.domain.AutomatedIssue;
import com.hungerstation.automated_issues.repository.IAutomatedIssueRepository;
import com.hungerstation.automated_issues.trigger.Trigger;
import com.hungerstation.automated_issues.trigger.enums.TriggerBaseEnum;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Waleed Arafa on 17/06/2018.
 */
public abstract class Rider extends Trigger {

    private final TriggerBaseEnum related_to_type = TriggerBaseEnum.RIDER;

    //TODO: define Rider variable then create Setter & Getter .

    public TriggerBaseEnum getRelated_to_type() {
        return related_to_type;
    }
}
