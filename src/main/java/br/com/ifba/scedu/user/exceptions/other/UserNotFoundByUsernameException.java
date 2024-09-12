package br.com.ifba.scedu.user.exceptions.other;

// Classe que representa uma exceção personalizada para o erro de usuário não encontrado pelo seu username
public class UserNotFoundByUsernameException extends RuntimeException {
    public UserNotFoundByUsernameException(String message) {
        super(message);
    }
}
