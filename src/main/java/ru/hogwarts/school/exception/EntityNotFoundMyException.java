package ru.hogwarts.school.exception;

public class EntityNotFoundMyException extends RuntimeException {
    public EntityNotFoundMyException (String massage) {
        super(massage);
    }
}
