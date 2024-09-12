package br.com.ifba.scedu.domain.entities.absences.exceptions;

public class InvalidRegistrationInformationException extends RuntimeException {
    public InvalidRegistrationInformationException(String message) {
        super(message);
    }
}
