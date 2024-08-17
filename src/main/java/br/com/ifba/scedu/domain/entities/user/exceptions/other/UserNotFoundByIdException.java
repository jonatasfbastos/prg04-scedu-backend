package br.com.ifba.scedu.domain.entities.user.exceptions.other;

public class UserNotFoundByIdException extends RuntimeException {
    public UserNotFoundByIdException(String message) {
        super(message);
    }
}
