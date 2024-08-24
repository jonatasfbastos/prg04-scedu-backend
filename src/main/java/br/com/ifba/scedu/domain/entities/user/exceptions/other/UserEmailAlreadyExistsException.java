package br.com.ifba.scedu.domain.entities.user.exceptions.other;

// Classe que representa uma exceção personalizada caso já exista um e-mail de usuário cadastrado
public class UserEmailAlreadyExistsException extends RuntimeException {
    public UserEmailAlreadyExistsException(String message) {
        super(message);
    }
}
