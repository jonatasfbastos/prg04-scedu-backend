package br.com.ifba.scedu.user.exceptions.other;

// Classe que representa uma exceção personalizada para erros de autenticação de usuário
public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }
}
