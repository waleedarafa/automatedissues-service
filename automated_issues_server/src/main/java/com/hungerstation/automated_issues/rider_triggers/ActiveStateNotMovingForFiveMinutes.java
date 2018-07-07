package com.hungerstation.automated_issues.rider_triggers;

import com.hungerstation.automated_issues.trigger.Trigger;
import com.hungerstation.automated_issues.triggers.Rider;
import org.springframework.stereotype.Component;

/**
 * Created by Waleed Arafa on 16/06/2018.
 */
@Component
public class ActiveStateNotMovingForFiveMinutes extends Rider {

    @Override
    protected boolean triggered() {
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
