package br.com.ifba.scedu.domain.entities.evaluation.service;

import br.com.ifba.scedu.domain.entities.evaluation.model.Evaluation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface EvaluationIService {

    // Retorna uma página de avaliações com base na paginação fornecida
    Page<Evaluation> findAll(Pageable pageable);

    // Retorna uma lista de avaliações filtradas pelo nome
    List<Evaluation> findByName(String name);

    // Retorna uma avaliação específica com base no ID
    Evaluation findById(Long id);

    // Salva uma nova avaliação associando-a a um professor, turma e disciplina
    Evaluation save(Evaluation evaluation, Long professorId, Long turmaId, Long disciplinaId);

    // Atualiza uma avaliação existente com novos dados de professor, turma e disciplina
    void update(Evaluation evaluation, Long professorId, Long turmaId, Long disciplinaId);

    // Exclui uma avaliação com base no ID e retorna um mapa com informações sobre a exclusão
    Map<String, String> delete(Long id);

}

