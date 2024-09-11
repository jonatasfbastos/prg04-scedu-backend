/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.scedu.domain.entities.grade.exceptions;

/**
 *
 * @author lara
 */
public class NullGradeException extends RuntimeException{

    public NullGradeException(String message) {
        super(message);
    }
    
}
