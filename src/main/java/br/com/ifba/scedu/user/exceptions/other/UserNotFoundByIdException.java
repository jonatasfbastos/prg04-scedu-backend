package br.com.ifba.scedu.user.exceptions.other;

// Classe que representa uma exceção personalizada para o erro de usuário não encontrado pelo seu ID
public class UserNotFoundByIdException extends RuntimeException {
    public UserNotFoundByIdException(String message) {
        super(message);
    }
}
