-- liquibase formatted sql

-- changeset alexust11:1
CREATE INDEX students_name_index ON student (name);

-- changeset alexust11:2
CREATE INDEX faculty_name_and_color_index ON faculty (name,color);
