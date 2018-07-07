package com.hungerstation.automated_issues.rider_triggers;

import com.hungerstation.automated_issues.trigger.Trigger;
import com.hungerstation.automated_issues.triggers.Rider;
import org.springframework.stereotype.Component;

/**
 * Created by Waleed Arafa on 16/06/2018.
 */
@Component
public class BalanceCloseToThreshold extends Rider {

    @Override
    protected boolean triggered() {
        //TODO: integrate with WalletService to check.
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
