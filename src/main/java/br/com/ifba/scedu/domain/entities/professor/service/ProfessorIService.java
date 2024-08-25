package br.com.ifba.scedu.domain.entities.professor.service;

import br.com.ifba.scedu.domain.entities.professor.model.Professor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProfessorIService {
    // Lista todos os professores
    List<Professor> findAll();

    // Busca um professor por ID
    Professor findById(Long id);

    // Salva um novo professor
    @Transactional
    Professor save(Professor professor);

    // Atualiza os dados de um professor existente
    @Transactional
    Professor update(Long id, Professor professor);

    // Deleta um professor pelo ID
    @Transactional
    void delete(Long id);
}
