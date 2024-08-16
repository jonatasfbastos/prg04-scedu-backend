package br.com.ifba.scedu.domain.entities.curso.exception;

public class CursoSavedException extends RuntimeException {
    public CursoSavedException(){
        super();
    }
    
    public CursoSavedException(String message){
        super(message);
    }

}
