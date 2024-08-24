package br.com.ifba.scedu.domain.entities.student.exceptions;

public class StudentAlreadyExistsException extends RuntimeException {
    public StudentAlreadyExistsException(String message) {
        super(message);
    }
}
