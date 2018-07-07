package com.hungerstation.automated_issues.rider_triggers;
import com.hungerstation.automated_issues.trigger.executer.Executable;
import com.hungerstation.automated_issues.triggers.Rider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Waleed Arafa on 16/06/2018.
 */
@Component
public class PenalizedAssignmentDecline extends Rider implements Executable{
    private Logger logger = LoggerFactory.getLogger(PenalizedAssignmentDecline.class);
    @Override
    protected boolean triggered() {
        //TODO: use rider object to check
        return true;
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
        logger.info("===========PenalizedAssignmentDecline========");
    }
}
