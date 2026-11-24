package com.recrutement.applications.service;


import com.recrutement.applications.domain.Application;
import com.recrutement.applications.domain.ApplicationStatus;
import com.recrutement.applications.exception.ApplicationNotFoundException;
import com.recrutement.applications.exception.DuplicateApplicationException;
import com.recrutement.applications.exception.InvalidApplicationStatusTransitionException;
import com.recrutement.applications.repository.ApplicationRepository;
import com.recrutement.applications.specification.ApplicationSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository){
        this.applicationRepository = applicationRepository;
    }

    public Application createApplication(
            UUID candidateId,
            UUID jobOfferId,
            UUID cvDocumentId,
            UUID motivationLetterDocumentId
    ){
        boolean alreadyApplied = applicationRepository.existsByCandidateIdAndJobOfferId(candidateId,jobOfferId);

        if(alreadyApplied){
            throw new DuplicateApplicationException(
                    "Candidate has already applied to this job offer"
            );
        }
        Application application = new Application(
                candidateId,
                jobOfferId,
                cvDocumentId,
                motivationLetterDocumentId
        );

        return applicationRepository.save(application);
    }

    public Application getApplicationById(UUID id){
        return applicationRepository.findById(id).orElseThrow(
                ()-> new ApplicationNotFoundException(
                        "Application not found"
                )
        );
    }




    public List<Application> getApplications(
            UUID candidateId,
            ApplicationStatus status
    ){

        System.out.println("candidateId = " + candidateId);
        System.out.println("status = " + status);


        Specification<Application> spec =   (root, query, criteriaBuilder) ->
                criteriaBuilder.conjunction();

        if(candidateId!=null){
            spec = spec.and(
                    ApplicationSpecification.hasCandidateId(candidateId)
            );
        }
        if (status != null){
            spec =spec.and(
                    ApplicationSpecification.hasStatus(status)
            );
        }

        return applicationRepository.findAll(spec);
    }

    public Application updateStatus(
            UUID id,
            ApplicationStatus newStatus
    ){
        Application application = applicationRepository.findById(id).orElseThrow(
                ()-> new ApplicationNotFoundException(
                        "Application not found"
                )
        );
        if (!application.getStatus().canTransitionTo(newStatus)){
            throw new InvalidApplicationStatusTransitionException(
                    "cannot transition application from "+application.getStatus()+" to "+newStatus
            );
        }
        application.setStatus(newStatus);

        return applicationRepository.save(application);
    }
}
