package com.recrutement.applications.dto;
import com.recrutement.applications.domain.ApplicationStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class ApplicationResponse {
    private final UUID id;
    private final UUID candidateId;
    private final UUID jobOfferId;
    private final UUID cvDocumentId;
    private final UUID motivationLetterDocumentId;
    private final ApplicationStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ApplicationResponse(
            UUID id,
            UUID candidateId,
            UUID jobOfferId,
            UUID cvDocumentId,
            UUID motivationLetterDocumentId,
            ApplicationStatus status,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ){
        this.id = id;
        this.candidateId = candidateId;
        this.jobOfferId = jobOfferId;
        this.cvDocumentId = cvDocumentId;
        this.motivationLetterDocumentId = motivationLetterDocumentId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
