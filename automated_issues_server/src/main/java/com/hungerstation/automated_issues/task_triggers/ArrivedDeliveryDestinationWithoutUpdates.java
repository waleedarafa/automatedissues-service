package com.hungerstation.automated_issues.task_triggers;

import com.hungerstation.automated_issues.trigger.Trigger;
import com.hungerstation.automated_issues.triggers.Task;
import org.springframework.stereotype.Component;

/**
 * Created by Waleed Arafa on 16/06/2018.
 */
@Component
public class ArrivedDeliveryDestinationWithoutUpdates extends Task {
    @Override
    protected boolean triggered() {
        //TODO: integrate with TaskService and DeliveryOrderService to check.
        return false;
    }

    @Override
    protected boolean enabled() {
        return false;
    }

    @Override
    protected boolean already_triggered() {
        return false;
    }

}
