package com.hungerstation.automated_issues.services;

import com.hungerstation.automated_issues.exceptions.InvalidEventException;
import com.hungerstation.automated_issues.trigger.enums.WorkersEnum;
import com.hungerstation.automated_issues.trigger.executer.Worker;
import com.hungerstation.automated_issues.trigger.factory.WorkerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Waleed Arafa on 22/06/2018.
 */
@Service
public class WorkersService {

    @Autowired
    WorkerFactory workerFactory;

    private Logger logger = LoggerFactory.getLogger(WorkersService.class);

    public void publishEvent(String name, String data) throws InvalidEventException {
        logger.debug("name: " + name + ", data: " + data);

        Worker worker;
        worker = workerFactory.getWorker(WorkersEnum.forValue(name));

        worker.executeAsynchronously(data);


    }
}
