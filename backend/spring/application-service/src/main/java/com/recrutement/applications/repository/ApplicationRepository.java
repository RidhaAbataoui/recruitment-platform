package com.recrutement.applications.repository;

import com.recrutement.applications.domain.Application;
import com.recrutement.applications.domain.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ApplicationRepository extends JpaRepository<Application, UUID>, JpaSpecificationExecutor<Application> {

    boolean existsByCandidateIdAndJobOfferId(UUID candidateId, UUID jobOfferId);


}
