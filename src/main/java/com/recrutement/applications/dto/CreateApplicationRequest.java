package com.recrutement.applications.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class CreateApplicationRequest {

    @NotNull
    private UUID candidateId;

    @NotNull
    private UUID jobOfferId;

    @NotNull
    private UUID cvDocumentId;

    private UUID motivationLetterDocumentId;


}
