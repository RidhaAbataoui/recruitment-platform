package com.recrutement.applications.controller;

import com.recrutement.applications.domain.Application;
import com.recrutement.applications.domain.ApplicationStatus;
import com.recrutement.applications.dto.ApplicationResponse;
import com.recrutement.applications.dto.CreateApplicationRequest;
import com.recrutement.applications.dto.UpdateApplicationStatusRequest;
import com.recrutement.applications.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController{
    private final ApplicationService applicationsService;

    public ApplicationController(ApplicationService applicationService){
        this.applicationsService = applicationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationResponse createdApplication(
          @Valid @RequestBody CreateApplicationRequest request
    ){
       Application application = applicationsService.createApplication(
               request.getCandidateId(),
               request.getJobOfferId(),
               request.getCvDocumentId(),
               request.getMotivationLetterDocumentId()
       );
       return new ApplicationResponse(
               application.getId(),
               application.getCandidateId(),
               application.getJobOfferId(),
               application.getCvDocument_id(),
               application.getMotivationLetterDocumentId(),
               application.getStatus(),
               application.getCreatedAt(),
               application.getUpdatedAt()
       );
    }


    @GetMapping("/{id}")
    public ApplicationResponse getApplicationById(
            @RequestParam UUID id

    ){
        Application application = applicationsService.getApplicationById(id);

        return new ApplicationResponse(
                application.getId(),
                application.getCandidateId(),
                application.getJobOfferId(),
                application.getCvDocument_id(),
                application.getMotivationLetterDocumentId(),
                application.getStatus(),
                application.getCreatedAt(),
                application.getUpdatedAt()
        );
    }

    @GetMapping
    public List<ApplicationResponse> getApplications(
            @RequestParam(required = false) UUID candidateId,
            @RequestParam(required = false)ApplicationStatus status
            ){

        return applicationsService.getApplications(candidateId,status).stream()
                .map(application -> new ApplicationResponse(
                        application.getId(),
                        application.getCandidateId(),
                        application.getJobOfferId(),
                        application.getCvDocument_id(),
                        application.getMotivationLetterDocumentId(),
                        application.getStatus(),
                        application.getCreatedAt(),
                        application.getUpdatedAt()
                )).toList();
    }

    @PatchMapping("/{id}/status")
    public ApplicationResponse updateStatus(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateApplicationStatusRequest request
            ){
        Application application = applicationsService.updateStatus(
                id,
                request.getStatus()
        );
        return new ApplicationResponse(
                application.getId(),
                application.getCandidateId(),
                application.getJobOfferId(),
                application.getCvDocument_id(),
                application.getMotivationLetterDocumentId(),
                application.getStatus(),
                application.getCreatedAt(),
                application.getUpdatedAt()
        );
    }


}
