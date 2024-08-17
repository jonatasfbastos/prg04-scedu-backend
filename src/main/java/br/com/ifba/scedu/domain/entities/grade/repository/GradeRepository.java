/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.scedu.domain.entities.grade.repository;

import br.com.ifba.scedu.domain.entities.grade.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lara
 */

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    
}
