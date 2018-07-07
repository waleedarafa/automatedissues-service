package com.hungerstation.automated_issues.services;

import com.hungerstation.automated_issues.domain.AutomatedIssue;
import com.hungerstation.automated_issues.exceptions.InvalidTriggerException;
import com.hungerstation.automated_issues.repository.IAutomatedIssueRepository;
import com.hungerstation.automated_issues.services.grpc.AutomatedIssuesService;
import com.hungerstation.automated_issues.trigger.Trigger;
import com.hungerstation.automated_issues.trigger.enums.TriggerTypeEnum;
import com.hungerstation.automated_issues.trigger.executer.Executable;
import com.hungerstation.automated_issues.trigger.factory.TriggerFactory;
import io.grpc.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created by Waleed Arafa on 22/06/2018.
 */

@Service
@Transactional
public class OperationsService {

    @Autowired
    IAutomatedIssueRepository automatedIssueRepository;
    @Autowired
    TriggerFactory triggerFactory;
    private Logger logger = LoggerFactory.getLogger(OperationsService.class);

    public AutomatedIssue create(AutomatedIssue savedIssue) throws InvalidTriggerException {

        Trigger trigger;
        trigger = triggerFactory.getTrigger(TriggerTypeEnum.forNumber(savedIssue.getTriggerKey()));
        AutomatedIssue savedAutomatedIssue;
        savedAutomatedIssue = automatedIssueRepository.save(savedIssue);

        if (savedAutomatedIssue != null) {
            if (trigger instanceof Executable)
                ((Executable) trigger).execute();
        }

        return savedAutomatedIssue;
    }

    public Optional<AutomatedIssue> fetch(long id) {
        return automatedIssueRepository.findById(id);
    }

    public Page<AutomatedIssue> filter(AutomatedIssue issueCriteria, int page, int perPage) {
        return automatedIssueRepository.findAll(Example.of(issueCriteria), new PageRequest(page - 1, perPage, Sort.Direction.ASC, "id"));
    }
}
