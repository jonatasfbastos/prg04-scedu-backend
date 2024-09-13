package br.com.ifba.scedu.gestaoterceirizado.exception;

import br.com.ifba.scedu.infrastructure.exception.GlobalExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GestaoTerceirizadoExceptionHandler extends GlobalExceptionHandler {

    @ExceptionHandler(GestaoTerceirizadoNotFoundException.class)
    public ResponseEntity<String> gestaoTerceirizadoNotFoundException(GestaoTerceirizadoNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
    @ExceptionHandler(NullGestaoTerceirizadoException.class)
    public ResponseEntity<String> nullGestaoTerceirizadoException(NullGestaoTerceirizadoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
