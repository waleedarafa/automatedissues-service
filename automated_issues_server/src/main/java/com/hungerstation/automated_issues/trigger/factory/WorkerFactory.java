package com.hungerstation.automated_issues.trigger.factory;

import com.hungerstation.automated_issues.exceptions.InvalidEventException;
import com.hungerstation.automated_issues.trigger.enums.WorkersEnum;
import com.hungerstation.automated_issues.trigger.executer.Worker;
import com.hungerstation.automated_issues.workers.RiderCreated;
import com.hungerstation.automated_issues.workers.RiderUpdated;
import com.hungerstation.automated_issues.workers.TaskCreated;
import com.hungerstation.automated_issues.workers.TaskUpdated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by Waleed Arafa on 22/06/2018.
 */
@Component
public class WorkerFactory {

    @Autowired
    private ApplicationContext applicationContext;

    public Worker getWorker(WorkersEnum workerEnum) throws InvalidEventException {
        if (workerEnum == null)
            throw new InvalidEventException();
        Worker worker = null;
        switch (workerEnum) {
            case RIDERCREATED:
                worker = applicationContext.getBean(RiderCreated.class);
                break;
            case RIDERUPDATED:
                worker = applicationContext.getBean(RiderUpdated.class);
                break;
            case TASKCREATED:
                worker = applicationContext.getBean(TaskCreated.class);
                break;
            case TASKUPDATED:
                worker = applicationContext.getBean(TaskUpdated.class);
                break;
            default:
                break;
        }
        return worker;
    }
}
