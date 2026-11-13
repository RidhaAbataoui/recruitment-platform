package com.recrutement.applications.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="applications")
public class Application {
    @Id
    private UUID id;

    @Column(name = "candidate_id", nullable = false)
    private UUID candidateId;

    @Column(name = "job_offer_id", nullable = false)
    private UUID jobOfferId;

    @Column(name = "cv_document_id")
    private UUID cvDocument_id;

    @Column(name = "status", nullable = false,length = 50)
    private String status;

    @Column(name ="motivation_letter")
    private String motivationLetter;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "update_at", nullable = false)
    private LocalDateTime updatedAt;
}
