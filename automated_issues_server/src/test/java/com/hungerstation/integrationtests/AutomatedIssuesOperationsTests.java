package com.hungerstation.integrationtests;

import com.hungerstation.automated_issues.domain.AutomatedIssue;
import com.hungerstation.automated_issues.exceptions.InvalidTriggerException;
import com.hungerstation.automated_issues.services.OperationsService;
import com.hungerstation.automated_issues.trigger.enums.TriggerBaseEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AutomatedIssuesOperationsTests {

    @Autowired
    OperationsService operationsService;


    @Test
    public void testCreateOperationForAutomatedIssue() throws InvalidTriggerException {
        AutomatedIssue automatedIssue = new AutomatedIssue();
        automatedIssue.setTriggerKey(204);
        automatedIssue.setRelatedToId(1L);
        automatedIssue.setRelatedToType(TriggerBaseEnum.RIDER.getValue());
        automatedIssue.setStatus(AutomatedIssue.StatusEnum.OPEN);

        AutomatedIssue savedIssue = operationsService.create(automatedIssue);

        Assert.assertNotNull(savedIssue);


    }

    @Test(expected = InvalidTriggerException.class)
    public void testCreateOperationwithInvalidTrigger() throws InvalidTriggerException {
        AutomatedIssue automatedIssue = new AutomatedIssue();
        automatedIssue.setTriggerKey(55555);
        automatedIssue.setRelatedToId(1L);
        automatedIssue.setRelatedToType(TriggerBaseEnum.RIDER.getValue());
        automatedIssue.setStatus(AutomatedIssue.StatusEnum.OPEN);

        AutomatedIssue savedIssue = operationsService.create(automatedIssue);

    }

    @Test
    public void testFetchById() throws InvalidTriggerException {
        AutomatedIssue automatedIssue = new AutomatedIssue();
        automatedIssue.setTriggerKey(204);
        automatedIssue.setRelatedToId(1L);
        automatedIssue.setRelatedToType(TriggerBaseEnum.RIDER.getValue());
        automatedIssue.setStatus(AutomatedIssue.StatusEnum.OPEN);

        AutomatedIssue savedIssue = operationsService.create(automatedIssue);

        Optional<AutomatedIssue> foundIssue = operationsService.fetch(savedIssue.getId());

        Assert.assertEquals(foundIssue.get(), savedIssue);


    }


    @Test
    public void testFilterForGivenCriteria() throws InvalidTriggerException {
        AutomatedIssue automatedIssue = new AutomatedIssue();
        automatedIssue.setTriggerKey(204);
        automatedIssue.setRelatedToId(1L);
        automatedIssue.setRelatedToType(TriggerBaseEnum.RIDER.getValue());
        automatedIssue.setStatus(AutomatedIssue.StatusEnum.OPEN);

        AutomatedIssue savedIssue = operationsService.create(automatedIssue);

        AutomatedIssue criteria = new AutomatedIssue();
        criteria.setTriggerKey(204);
        criteria.setStatus(AutomatedIssue.StatusEnum.OPEN);

        Page<AutomatedIssue> issues = operationsService.filter(criteria, 1, 50);

        Assert.assertTrue(issues.getTotalElements() > 0);
    }


}
