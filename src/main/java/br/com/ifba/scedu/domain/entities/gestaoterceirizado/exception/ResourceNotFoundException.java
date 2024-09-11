package br.com.ifba.scedu.domain.entities.gestaoterceirizado.exception;

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
