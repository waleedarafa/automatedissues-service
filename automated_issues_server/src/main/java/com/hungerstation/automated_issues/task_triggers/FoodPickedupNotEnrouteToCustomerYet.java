package com.hungerstation.automated_issues.task_triggers;

import com.hungerstation.automated_issues.trigger.Trigger;
import com.hungerstation.automated_issues.trigger.executer.Executable;
import com.hungerstation.automated_issues.triggers.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Waleed Arafa on 16/06/2018.
 */
@Component
public class FoodPickedupNotEnrouteToCustomerYet extends Task implements Executable{
    private Logger logger = LoggerFactory.getLogger(FoodPickedupNotEnrouteToCustomerYet.class);
    @Override
    protected boolean triggered() {
        //TODO: integrate with TaskService to check.
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
        logger.info("==========Action has been taken=========");

    }
}
