package br.com.ifba.scedu.domain.entities.evaluation.repository;


import br.com.ifba.scedu.domain.entities.evaluation.model.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    // Método para buscar uma lista de avaliações por nome
   List<Evaluation> findByName(String name);



}
