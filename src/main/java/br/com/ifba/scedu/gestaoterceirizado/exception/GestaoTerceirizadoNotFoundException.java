package br.com.ifba.scedu.gestaoterceirizado.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GestaoTerceirizadoNotFoundException extends RuntimeException{

    public GestaoTerceirizadoNotFoundException(){
        super();
    }
    public GestaoTerceirizadoNotFoundException(String massage) {
        super(massage);
    }
}
