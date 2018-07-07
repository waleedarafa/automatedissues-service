package com.hungerstation.automated_issues.repository;

import com.hungerstation.automated_issues.domain.AutomatedIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

/**
 * Created by Administrator on 19/06/2018.
 */
@Repository
public interface IAutomatedIssueRepository extends JpaRepository<AutomatedIssue,Long> {
    Optional<AutomatedIssue> findById(long id);
}
