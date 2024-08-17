package br.com.ifba.scedu.domain.entities.gestaoTerceirizado.exception;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super();
    }
    public ResourceNotFoundException(String massage) {
        super(massage);
    }
}
