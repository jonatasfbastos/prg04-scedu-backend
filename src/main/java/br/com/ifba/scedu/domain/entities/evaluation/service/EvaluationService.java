package br.com.ifba.scedu.domain.entities.evaluation.service;



import br.com.ifba.scedu.domain.entities.evaluation.model.Evaluation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.com.ifba.scedu.domain.entities.evaluation.repository.EvaluationRepository;

@Service
@RequiredArgsConstructor
public class EvaluationService implements EvaluationIService {

    private final EvaluationRepository avaliacaoRepository;

    public Page<Evaluation> findAll(Pageable pageable) {

        return avaliacaoRepository.findAll(pageable);
    }

    public List<Evaluation> findByName(String name) {

        return avaliacaoRepository.findByName(name);
    }

    public Evaluation findById(Long id) {
        return avaliacaoRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Recurso não encontrado!"));
    }

    @Transactional
    public Evaluation save(Evaluation avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }

    @Transactional
    public void update(Evaluation avaliacao) {
        avaliacaoRepository.save(avaliacao);
    }

    public Map<String, String> delete(Long id) {
        avaliacaoRepository.deleteById(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuario deletado com sucesso");
        return response;
    }
}

