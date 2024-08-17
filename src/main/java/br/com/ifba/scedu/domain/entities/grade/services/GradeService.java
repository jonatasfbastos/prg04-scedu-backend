/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.scedu.domain.entities.grade.services;

import br.com.ifba.scedu.domain.entities.grade.model.DTOs.GradeViewDTO;
import br.com.ifba.scedu.domain.entities.grade.model.Grade;
import br.com.ifba.scedu.domain.entities.grade.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lara
 */
@Service
public class GradeService {

    @Autowired
    GradeRepository repository;

    @Transactional
    public GradeViewDTO save(Grade grade) {
        if (grade == null) {
            throw new RuntimeException("Grade object cannot be null");
        }

        repository.save(grade);

        return new GradeViewDTO(grade.getCode(), grade.getName(), grade.getCurriculumCode());
    }

    @Transactional(readOnly = true)
    public Page<Grade> findAll(Pageable pageable) {
        Page<Grade> grades = this.repository.findAll(pageable);
        if (grades.isEmpty()) {
            throw new RuntimeException("No grades found");
        }
        return grades;
    }

    @Transactional(readOnly = true)
    public GradeViewDTO findById(Long id) {

        var grade = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No records found for this ID"));

        return new GradeViewDTO(grade.getCode(), grade.getName(), grade.getCurriculumCode());
    }

    @Transactional
    public GradeViewDTO update(GradeViewDTO newGrade, Long id) {
        var currentGrade = repository.findById(id).orElseThrow(() -> new RuntimeException("No records found for this ID"));
        
       currentGrade.setName(newGrade.name());
       currentGrade.setCurriculumCode(newGrade.curriculumCode());
       currentGrade.setCode(newGrade.code());
       
       this.save(currentGrade);
       
       return new GradeViewDTO(currentGrade.getCode(), currentGrade.getName(), currentGrade.getCurriculumCode());
        
    }

    @Transactional
    public void delete(Long id) {
        
        Grade entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No records found for this ID"));
        repository.delete(entity);
    }

}
