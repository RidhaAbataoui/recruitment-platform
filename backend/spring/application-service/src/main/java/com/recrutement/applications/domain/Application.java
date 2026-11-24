package com.recrutement.applications.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name="applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Setter
    @Column(name = "candidate_id", nullable = false)
    private UUID candidateId;

    @Setter
    @Column(name = "job_offer_id", nullable = false)
    private UUID jobOfferId;

    @Setter
    @Column(name = "cv_document_id")
    private UUID cvDocument_id;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false,length = 50)
    private ApplicationStatus status;

    @Setter
    @Column(name ="motivation_letter_document_id")
    private UUID motivationLetterDocumentId;


    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "update_at", nullable = false)
    private LocalDateTime updatedAt;


    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate(){
        updatedAt = LocalDateTime.now();
    }

    public Application( UUID candidateId, UUID jobOfferId, UUID cvDocument_id, UUID motivationLetter) {
        this.candidateId = candidateId;
        this.jobOfferId = jobOfferId;
        this.cvDocument_id = cvDocument_id;
        this.status = ApplicationStatus.SUBMITTED;
        this.motivationLetterDocumentId = motivationLetter;

    }
}
