package com.hungerstation.automated_issues.task_triggers;

import com.hungerstation.automated_issues.trigger.Trigger;
import com.hungerstation.automated_issues.trigger.executer.Executable;
import com.hungerstation.automated_issues.triggers.Task;
import org.springframework.stereotype.Component;

/**
 * Created by Waleed Arafa on 16/06/2018.
 */
@Component
public class ReachingDeliveryDestinationLateness extends Task implements Executable{
    @Override
    protected boolean triggered() {
        //TODO: use task object to check.
        return false;
    }

    @Override
    protected boolean enabled() {
        return true;
    }

    @Override
    protected boolean already_triggered() {
        return false;
    }

    @Override
    public void execute() {
        //TODO: integrate with ZendeskTicketService to create ticket.
    }
}
