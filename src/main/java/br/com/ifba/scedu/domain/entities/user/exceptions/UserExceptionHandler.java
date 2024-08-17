package br.com.ifba.scedu.domain.entities.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.ifba.scedu.domain.entities.user.exceptions.other.NoUsersToListException;
import br.com.ifba.scedu.domain.entities.user.exceptions.other.UserEmailAlreadyExistsException;
import br.com.ifba.scedu.domain.entities.user.exceptions.other.UserNotFoundByIdException;
import br.com.ifba.scedu.infrastructure.exception.GlobalExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler extends GlobalExceptionHandler {
    // Métodos de exceção específicos para User

    @Override // Chamando método da superclasse 
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @Override // Chamando método da superclasse 
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage());
    }

    @ExceptionHandler(NoUsersToListException.class)
    private ResponseEntity<String> noUsersToListHandler(NoUsersToListException exception) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(exception.getMessage());
    }

    @ExceptionHandler(UserEmailAlreadyExistsException.class)
    private ResponseEntity<String> userEmailAlreadyExistsHandler(UserEmailAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundByIdException.class)
    private ResponseEntity<String> userIdNotFoundHandler(UserNotFoundByIdException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
