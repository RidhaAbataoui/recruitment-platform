package com.recrutement.applications.specification;

import com.recrutement.applications.domain.Application;
import com.recrutement.applications.domain.ApplicationStatus;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class ApplicationSpecification {

    public static Specification<Application> hasCandidateId(UUID candidateId){
        return (root, query, criteriaBuilder)->
                criteriaBuilder.equal(
                        root.get("candidateId"),
                        candidateId
                );
    }

    public static Specification<Application> hasStatus(ApplicationStatus status){
        return (root, query, criteriaBuilder)->
                criteriaBuilder.equal(
                        root.get("status"),
                        status
                );
    }
}
