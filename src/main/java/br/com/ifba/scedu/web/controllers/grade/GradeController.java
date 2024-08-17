/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.scedu.web.controllers.grade;

import br.com.ifba.scedu.domain.entities.grade.model.DTOs.GradeViewDTO;
import br.com.ifba.scedu.domain.entities.grade.model.Grade;
import br.com.ifba.scedu.domain.entities.grade.services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author lara
 */
@RestController
@RequestMapping("/grades")
public class GradeController {

    private final GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PostMapping
    public ResponseEntity<GradeViewDTO> save(@RequestBody GradeViewDTO gradeDTO) {
        Grade newGrade = new Grade();
        newGrade.setCode(gradeDTO.code());
        newGrade.setCurriculumCode(gradeDTO.curriculumCode());
        newGrade.setName(gradeDTO.name());
        
        this.gradeService.save(newGrade);
        return ResponseEntity.status(HttpStatus.CREATED).body(gradeDTO);
    }

    @GetMapping
    public ResponseEntity<Page<GradeViewDTO>> findAll(Pageable pageable) {
        Page<GradeViewDTO> grades = gradeService.findAll(pageable).map(grade -> 
            new GradeViewDTO(grade.getCode(), grade.getName(), grade.getCurriculumCode()));
        return ResponseEntity.ok(grades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GradeViewDTO> findById(@PathVariable Long id) {
        GradeViewDTO grade = gradeService.findById(id);
        return ResponseEntity.ok(grade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GradeViewDTO> update(@RequestBody GradeViewDTO gradeDTO, @PathVariable Long id) {
        GradeViewDTO updatedGrade = gradeService.update(gradeDTO, id);
        return ResponseEntity.ok(updatedGrade);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        gradeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

