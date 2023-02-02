
ALTER TABLE student
    ADD CONSTRAINT age_constraint CHECK (age >= 16);

ALTER TABLE student
    ALTER COLUMN name SET NOT NULL,
    ADD CONSTRAINT name_unique UNIQUE (name);

ALTER TABLE student
    ADD CONSTRAINT name_faculty_unique UNIQUE (name, faculty_id);

ALTER TABLE student
    ALTER COLUMN age SET DEFAULT 20;