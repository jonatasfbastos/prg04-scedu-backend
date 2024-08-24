package br.com.ifba.scedu.domain.entities.user.exceptions.other;

// Classe que representa uma exceção personalizada caso não seja possível listar usuários
public class NoUsersToListException extends RuntimeException {
    public NoUsersToListException(String message) {
        super(message);
    }
}
