package br.com.ifba.scedu.domain.entities.user.exceptions.other;

public class NoUsersToListException extends RuntimeException {
    public NoUsersToListException(String message) {
        super(message);
    }
}
