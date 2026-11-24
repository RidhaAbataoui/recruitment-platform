package com.recrutement.applications.domain;

import java.util.Map;
import java.util.Set;

public enum ApplicationStatus {
    SUBMITTED,
    UNDER_REVIEW,
    PRESELECTED,
    INTERVIEW_SCHEDULED,
    INTERVIEW_COMPLETED,
    ACCEPTED,
    REJECTED;

    private static final Map<ApplicationStatus, Set<ApplicationStatus>>
            ALLOWED_TRANSITIONS = Map.of(
                SUBMITTED,
                Set.of(UNDER_REVIEW,REJECTED),

                UNDER_REVIEW,
                Set.of(PRESELECTED, REJECTED),

                PRESELECTED,
                Set.of(INTERVIEW_SCHEDULED, REJECTED),

                INTERVIEW_SCHEDULED,
                Set.of(INTERVIEW_COMPLETED),

                INTERVIEW_COMPLETED,
                Set.of(ACCEPTED,REJECTED),

                ACCEPTED,
                Set.of(),

                REJECTED,
                Set.of()
            );

    public boolean canTransitionTo(ApplicationStatus newStatus){
        return ALLOWED_TRANSITIONS.getOrDefault(this, Set.of()).contains(newStatus);
    }
}
