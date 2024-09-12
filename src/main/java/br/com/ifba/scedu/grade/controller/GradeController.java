/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.scedu.grade.controller;

import br.com.ifba.scedu.grade.entities.DTOs.GradeViewDTO;
import br.com.ifba.scedu.domain.entities.curso.entity.Curso;
import br.com.ifba.scedu.grade.entities.Grade; // Note que 'Grade' é a entidade que estamos chamando de 'série'
import br.com.ifba.scedu.grade.service.GradeService;
import br.com.ifba.scedu.infrastructure.util.ObjectMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador responsável pela manipulação das requisições relacionadas às 'Séries'.
 * 
 * Este controlador define os endpoints que permitem a criação, leitura, atualização e exclusão de objetos Série.
 * Ele utiliza o GradeService para realizar operações de negócios e o ObjectMapperUtil para conversões entre entidades e DTOs.
 * 
 * @author lara
 */
@RestController
@RequestMapping("/grades") // Mapeia a URL base para este controlador
@CrossOrigin(origins = "*") // Permite requisições de qualquer origem (para fins de desenvolvimento)
@RequiredArgsConstructor // Gera um construtor com argumentos para todos os campos finais
public class GradeController {

    private final GradeService gradeService; // Serviço para manipulação das Séries
    private final ObjectMapperUtil objectMapperUtil; // Utilitário para conversão entre entidades e DTOs

    /**
     * Endpoint para salvar uma nova Série.
     *
     * @param gradeDTO DTO contendo os dados da nova Série a ser salva
     * @return ResponseEntity contendo o DTO da Série salva e status HTTP 201 Created
     */
    @PostMapping
    public ResponseEntity<GradeViewDTO> save(@RequestBody GradeViewDTO gradeDTO) {
        // Cria uma nova instância de Série a partir do DTO
        Grade newGrade = new Grade();
        newGrade = objectMapperUtil.map(gradeDTO, Grade.class);

        // Verifica se o código do curso foi fornecido e associa o curso à Série
        if (gradeDTO.courseCode() != null) {
            Curso curso = new Curso();
            curso.setCode(gradeDTO.courseCode());
            newGrade.setCourse(curso);
        }

        // Salva a nova Série usando o GradeService
        Grade savedGrade = gradeService.save(newGrade);
        // Converte a Série salva em um DTO para a resposta
        GradeViewDTO response = objectMapperUtil.map(savedGrade, GradeViewDTO.class);

        // Retorna a Série salva com status HTTP 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Endpoint para buscar todas as Séries com paginação.
     *
     * @param pageable Parâmetros de paginação
     * @return ResponseEntity contendo uma página de DTOs de Séries e status HTTP 200 OK
     */
    @GetMapping
    public ResponseEntity<Page<GradeViewDTO>> findAll(Pageable pageable) {
        // Obtém uma página de Séries do serviço e a converte para uma página de DTOs
        Page<GradeViewDTO> grades = gradeService.findAll(pageable).map(
                grade -> objectMapperUtil.map(grade, GradeViewDTO.class)
        );

        // Retorna a página de DTOs com status HTTP 200 OK
        return ResponseEntity.ok(grades);
    }

    /**
     * Endpoint para buscar uma Série específica pelo seu ID.
     *
     * @param id ID da Série a ser buscada
     * @return ResponseEntity contendo o DTO da Série encontrada e status HTTP 200 OK
     */
    @GetMapping("/{id}")
    public ResponseEntity<GradeViewDTO> findById(@PathVariable Long id) {
        // Busca a Série pelo ID usando o GradeService
        Grade grade = gradeService.findById(id);
        // Converte a Série encontrada em um DTO para a resposta
        GradeViewDTO response = objectMapperUtil.map(grade, GradeViewDTO.class);

        // Retorna o DTO da Série com status HTTP 200 OK
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para atualizar uma Série existente.
     *
     * @param gradeDTO DTO contendo os dados atualizados da Série
     * @param id ID da Série a ser atualizada
     * @return ResponseEntity contendo o DTO da Série atualizada e status HTTP 200 OK
     */
    @PutMapping("/{id}")
    public ResponseEntity<GradeViewDTO> update(@RequestBody GradeViewDTO gradeDTO, @PathVariable Long id) {
        // Atualiza a Série usando o GradeService com base no DTO e no ID fornecido
        Grade updatedGrade = gradeService.update(gradeDTO, id);
        // Converte a Série atualizada em um DTO para a resposta
        GradeViewDTO response = objectMapperUtil.map(updatedGrade, GradeViewDTO.class);

        // Retorna o DTO da Série atualizada com status HTTP 200 OK
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para deletar uma Série existente pelo seu ID.
     *
     * @param id ID da Série a ser deletada
     * @return ResponseEntity com status HTTP 204 No Content após a exclusão
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // Deleta a Série pelo ID usando o GradeService
        gradeService.delete(id);
        // Retorna status HTTP 204 No Content indicando que a exclusão foi bem-sucedida
        return ResponseEntity.noContent().build();
    }
}

