package br.com.ifba.scedu.domain.entities.user.exceptions.other;

public class UserEmailAlreadyExistsException extends RuntimeException {
    public UserEmailAlreadyExistsException(String message) {
        super(message);
    }
}
