package br.com.ifba.scedu.domain.entities.evaluation.service;

import br.com.ifba.scedu.domain.entities.evaluation.model.Evaluation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface EvaluationIService {

    public Page<Evaluation> findAll(Pageable pageable);
    public List<Evaluation> findByName(String name);
    public Evaluation findById(Long id);
    public Evaluation save(Evaluation avaliacao, Long professorId, Long turmaId, Long disciplinaId);
    public void update(Evaluation avaliacao, Long professorId, Long turmaId, Long disciplinaId) ;
    public Map<String, String> delete(Long id);

}
