package com.hungerstation.automated_issues.trigger;

/**
 * Created by Waleed Arafa on 16/06/2018.
 */
public abstract class Trigger {

    protected abstract boolean triggered();

    protected abstract boolean enabled();

    protected abstract boolean already_triggered();

    public final boolean can_trigger() {
        return (triggered() && enabled() && !already_triggered());
    }
}
