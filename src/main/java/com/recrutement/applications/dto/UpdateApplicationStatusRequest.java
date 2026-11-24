package com.recrutement.applications.dto;


import com.recrutement.applications.domain.ApplicationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateApplicationStatusRequest {
    @NotNull
    private ApplicationStatus status;
}
