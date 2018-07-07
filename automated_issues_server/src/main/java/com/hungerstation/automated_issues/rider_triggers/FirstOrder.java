package com.hungerstation.automated_issues.rider_triggers;

import com.hungerstation.automated_issues.trigger.Trigger;
import com.hungerstation.automated_issues.trigger.executer.Executable;
import com.hungerstation.automated_issues.triggers.Rider;
import org.springframework.stereotype.Component;

/**
 * Created by Waleed Arafa on 16/06/2018.
 */
@Component
public class FirstOrder extends Rider implements Executable {

    @Override
    protected boolean triggered() {
        //TODO: integrate with DeliveryOrderService to check
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
