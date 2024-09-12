/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.scedu.grade.exceptions;

import br.com.ifba.scedu.infrastructure.exception.GlobalExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Manipulador de exceções para as exceções específicas da 'Série'.
 * 
 * Este manipulador de exceções captura e trata exceções lançadas durante o processamento das solicitações relacionadas às 'Séries'.
 * Utiliza as anotações @ControllerAdvice e @ExceptionHandler para definir como as exceções devem ser tratadas e quais respostas devem ser retornadas ao cliente.
 * 
 * @author lara
 */
@ControllerAdvice // Anotação que define esta classe como um manipulador global de exceções
public class GradesExceptionHandler extends GlobalExceptionHandler {

    /**
     * Trata exceções do tipo GradeNotFoundException.
     * 
     * Quando uma exceção GradeNotFoundException é lançada, este método é acionado para gerar uma resposta apropriada.
     * Retorna uma resposta com o status HTTP 404 (Not Found) e a mensagem da exceção.
     *
     * @param exception A exceção lançada quando uma série não é encontrada
     * @return A resposta com o status 404 e a mensagem da exceção
     */
    @ExceptionHandler(GradeNotFoundException.class)
    public ResponseEntity<String> gradeNotFoundHandler(GradeNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
    
    /**
     * Trata exceções do tipo NullGradeException.
     * 
     * Quando uma exceção NullGradeException é lançada, este método é acionado para gerar uma resposta apropriada.
     * Retorna uma resposta com o status HTTP 400 (Bad Request) e a mensagem da exceção.
     *
     * @param ex A exceção lançada quando um objeto série nulo é encontrado
     * @return A resposta com o status 400 e a mensagem da exceção
     */
    @ExceptionHandler(NullGradeException.class)
    public ResponseEntity<String> nullGradeExceptionHandler(NullGradeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}

