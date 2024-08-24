package br.com.ifba.scedu.domain.entities.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.ifba.scedu.domain.entities.user.exceptions.other.NoUsersToListException;
import br.com.ifba.scedu.domain.entities.user.exceptions.other.UserEmailAlreadyExistsException;
import br.com.ifba.scedu.domain.entities.user.exceptions.other.UserNotFoundByIdException;
import br.com.ifba.scedu.infrastructure.exception.GlobalExceptionHandler;

// Anotação @ControllerAdvice indica que esta classe trata exceções de todo o aplicativo ou de um grupo específico de controladores
@ControllerAdvice
public class UserExceptionHandler extends GlobalExceptionHandler {
    // Método sobrescreve o tratamento da exceção IllegalArgumentException definido na superclasse
    @Override
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        // Retorna uma resposta HTTP 400 (Bad Request) com a mensagem da exceção
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // Método sobrescreve o tratamento de exceções genéricas definido na superclasse
    @Override
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        // Retorna uma resposta HTTP 500 (Internal Server Error) com uma mensagem de erro genérica
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage());
    }

    // Método específico para tratar a exceção NoUsersToListException
    @ExceptionHandler(NoUsersToListException.class)
    private ResponseEntity<String> noUsersToListHandler(NoUsersToListException exception) {
        // Retorna uma resposta HTTP 204 (No Content) com a mensagem da exceção
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(exception.getMessage());
    }

    // Método específico para tratar a exceção UserEmailAlreadyExistsException
    @ExceptionHandler(UserEmailAlreadyExistsException.class)
    private ResponseEntity<String> userEmailAlreadyExistsHandler(UserEmailAlreadyExistsException exception) {
        // Retorna uma resposta HTTP 409 (Conflict) com a mensagem da exceção
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    // Método específico para tratar a exceção UserNotFoundByIdException
    @ExceptionHandler(UserNotFoundByIdException.class)
    private ResponseEntity<String> userIdNotFoundHandler(UserNotFoundByIdException exception) {
        // Retorna uma resposta HTTP 404 (Not Found) com a mensagem da exceção
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}