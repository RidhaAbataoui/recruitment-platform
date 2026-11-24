ALTER TABLE applications
DROP COLUMN motivation_letter;

ALTER TABLE applications
    ADD COLUMN motivation_letter_document_id UUID;