/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.scedu.domain.entities.grade.exceptions;

import br.com.ifba.scedu.infrastructure.exception.GlobalExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author lara
 */
@ControllerAdvice
public class GradesExceptionHandler extends GlobalExceptionHandler {

    @ExceptionHandler(GradeNotFoundException.class)
    public ResponseEntity<String> gradeNotFoundHandler(GradeNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
    
    @ExceptionHandler(NullGradeException.class)
    public ResponseEntity<String> nullGradeExceptionHandler(NullGradeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
