package br.com.ifba.scedu.domain.entities.curso.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CursoSavedException.class)
    private ResponseEntity<String> userNotFoundHandler(CursoSavedException exception){
      return ResponseEntity.status(HttpStatus.CONFLICT).body("O curso já existe");
    }
}
