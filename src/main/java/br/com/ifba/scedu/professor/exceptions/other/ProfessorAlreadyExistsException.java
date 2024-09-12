package br.com.ifba.scedu.professor.exceptions.other;

public class ProfessorAlreadyExistsException extends RuntimeException {

    public ProfessorAlreadyExistsException(String message) {
        super(message);
    }
}
