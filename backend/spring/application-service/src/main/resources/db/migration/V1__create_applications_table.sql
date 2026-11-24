CREATE TABLE applications(
    id UUID PRIMARY KEY ,

    candidate_id UUID NOT NULL ,
    jon_offer_id UUID NOT NULL ,
    cv_document_id UUID,

    status VARCHAR(50) NOT NULL ,
    created_at TIMESTAMP NOT NULL ,
    update_at TIMESTAMP NOT NULL ,

    CONSTRAINT uk_application_candidate_offer UNIQUE (candidate_id, jon_offer_id)

)