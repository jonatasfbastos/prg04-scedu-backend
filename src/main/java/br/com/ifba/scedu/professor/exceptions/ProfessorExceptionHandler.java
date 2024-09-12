package br.com.ifba.scedu.professor.exceptions;

import br.com.ifba.scedu.infrastructure.exception.StandardError;
import br.com.ifba.scedu.professor.exceptions.other.ProfessorAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
@Order(1)
public class ProfessorExceptionHandler {

    @ExceptionHandler(ProfessorAlreadyExistsException.class)
    public ResponseEntity<StandardError> handleProfessorAlreadyExists(ProfessorAlreadyExistsException exception, HttpServletRequest request) {
        // Cria o objeto de erro padrão
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setError("Conflict");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());

        // Retorna uma resposta HTTP 409 (Conflict) com o objeto de erro
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> handleGenericException(Exception exception, HttpServletRequest request) {
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setError("Internal Server Error");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}

