package br.com.ifba.scedu.domain.entities.user.exceptions.other;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }
}
