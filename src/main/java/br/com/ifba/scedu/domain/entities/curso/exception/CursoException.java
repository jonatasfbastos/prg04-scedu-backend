package br.com.ifba.scedu.domain.entities.curso.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CursoException {
    // handler generico para capturar excecoes de classes runtime
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handlerGenericException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
