package br.com.ifba.scedu.domain.entities.evaluation.repository;


import br.com.ifba.scedu.domain.entities.evaluation.model.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    public List<Evaluation> findByName(String name);

    public Optional<Evaluation> findById(Long id);

}
