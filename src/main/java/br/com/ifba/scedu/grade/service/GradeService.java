/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.scedu.grade.service;

import br.com.ifba.scedu.domain.entities.curso.model.Curso;
import br.com.ifba.scedu.domain.entities.curso.repository.CursoRepository;
import br.com.ifba.scedu.domain.entities.grade.exceptions.GradeNotFoundException;
import br.com.ifba.scedu.domain.entities.grade.exceptions.NullGradeException;
import br.com.ifba.scedu.grade.entities.DTOs.GradeViewDTO;
import br.com.ifba.scedu.grade.entities.Grade; // Note que 'Grade' é a entidade que estamos chamando de 'série'
import br.com.ifba.scedu.grade.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Serviço responsável pela manipulação das operações relacionadas às 'Séries'.
 * 
 * Este serviço lida com a lógica de negócios para criar, atualizar, buscar e excluir objetos Série.
 * Ele utiliza repositórios para interagir com o banco de dados e também lida com a validação e associação de entidades relacionadas.
 * 
 * @author lara
 */
@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository repository; // Repositório para manipulação das Séries
    private final CursoRepository cursoRepository; // Repositório para manipulação dos Cursos

    /**
     * Salva uma nova Série no banco de dados.
     *
     * @param grade Objeto Série a ser salvo
     * @return A Série salva
     * @throws NullGradeException Se o objeto Série fornecido for nulo
     * @throws RuntimeException Se o curso associado à Série não for encontrado
     */
    @Transactional
    public Grade save(Grade grade) {
        if (grade == null) {
            throw new NullGradeException("Série não pode ser nula");
        }

        // Verifica se o curso associado à Série existe e o associa à Série
        if (grade.getCourse() != null) {
            Curso curso = cursoRepository.findCursoByCode(grade.getCourse().getCode())
                    .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
            grade.setCourse(curso);
        }

        return repository.save(grade);
    }

    /**
     * Busca todas as Séries com paginação.
     *
     * @param pageable Parâmetros de paginação
     * @return Uma página de Séries
     * @throws GradeNotFoundException Se nenhuma Série for encontrada
     */
    @Transactional(readOnly = true)
    public Page<Grade> findAll(Pageable pageable) {
        Page<Grade> grades = this.repository.findAll(pageable);
        if (grades.isEmpty()) {
            throw new GradeNotFoundException("Nenhuma série encontrada");
        }
        return grades;
    }

    /**
     * Busca uma Série específica pelo seu ID.
     *
     * @param id ID da Série a ser buscada
     * @return A Série encontrada
     * @throws GradeNotFoundException Se nenhuma Série for encontrada com o ID fornecido
     */
    @Transactional(readOnly = true)
    public Grade findById(Long id) {
        var grade = repository.findById(id)
                .orElseThrow(() -> new GradeNotFoundException("Nenhum registro encontrado para este ID"));

        return grade;
    }

    /**
     * Atualiza uma Série existente com os dados fornecidos.
     *
     * @param newGrade DTO contendo os dados atualizados da Série
     * @param id ID da Série a ser atualizada
     * @return A Série atualizada
     * @throws GradeNotFoundException Se nenhuma Série for encontrada com o ID fornecido
     * @throws RuntimeException Se o curso associado à Série não for encontrado
     */
    @Transactional
    public Grade update(GradeViewDTO newGrade, Long id) {
        var currentGrade = repository.findById(id)
                .orElseThrow(() -> new GradeNotFoundException("Nenhum registro encontrado para este ID"));
        
        // Atualiza os dados da Série com base no DTO fornecido
        currentGrade.setName(newGrade.name());
        currentGrade.setCurriculumCode(newGrade.curriculumCode());
        currentGrade.setCode(newGrade.code());

        // Atualiza o curso associado se necessário
        if (newGrade.courseCode() != null) {
            Curso curso = cursoRepository.findCursoByCode(newGrade.courseCode())
                    .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
            currentGrade.setCourse(curso);
        }

        return repository.save(currentGrade);
    }

    /**
     * Deleta uma Série existente pelo seu ID.
     *
     * @param id ID da Série a ser deletada
     * @throws GradeNotFoundException Se nenhuma Série for encontrada com o ID fornecido
     */
    @Transactional
    public void delete(Long id) {
        Grade entity = repository.findById(id)
                .orElseThrow(() -> new GradeNotFoundException("Nenhum registro encontrado para este ID"));
        repository.delete(entity);
    }
}

