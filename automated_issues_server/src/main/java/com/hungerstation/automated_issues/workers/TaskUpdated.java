package com.hungerstation.automated_issues.workers;

import com.hungerstation.automated_issues.services.AsynchronousService;
import com.hungerstation.automated_issues.trigger.executer.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Waleed Arafa on 22/06/2018.
 */
@Component
public class TaskUpdated implements Worker {

    @Autowired
    AsynchronousService asynchronousService;

    @Override
    public void executeAsynchronously(String data) {

        asynchronousService.taskUpdatedProcess(data);

    }
}
