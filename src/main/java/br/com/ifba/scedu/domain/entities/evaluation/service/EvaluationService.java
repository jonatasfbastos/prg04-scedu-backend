package br.com.ifba.scedu.domain.entities.evaluation.service;



import br.com.ifba.scedu.domain.entities.disciplina.model.Disciplina;
import br.com.ifba.scedu.domain.entities.evaluation.model.Evaluation;
import br.com.ifba.scedu.domain.entities.professor.model.Professor;
import br.com.ifba.scedu.domain.entities.turma.model.Turma;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.com.ifba.scedu.domain.entities.evaluation.repository.EvaluationRepository;
import br.com.ifba.scedu.domain.entities.disciplina.repository.DisciplinaRepository;
import br.com.ifba.scedu.domain.entities.professor.repository.ProfessorRepository;
import br.com.ifba.scedu.domain.entities.turma.repository.TurmaRepository;

@Service
@RequiredArgsConstructor
public class EvaluationService implements EvaluationIService {

    private final EvaluationRepository evaluationRepository;
    private final ProfessorRepository professorRepository;
    private final TurmaRepository turmaRepository;
    private final DisciplinaRepository disciplinaRepository;

    public Page<Evaluation> findAll(Pageable pageable) {
        return evaluationRepository.findAll(pageable);
    }

    public List<Evaluation> findByName(String name) {
        return evaluationRepository.findByName(name);
    }

    public Evaluation findById(Long id) {
        return evaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recurso não encontrado!"));
    }

    @Transactional
    public Evaluation save(Evaluation evaluation, Long professorId, Long turmaId, Long disciplinaId) {
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado!"));

        Turma turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada!"));

        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada!"));

        evaluation.setProfessor(professor);
        evaluation.setTurma(turma);
        evaluation.setDisciplina(disciplina);

        return evaluationRepository.save(evaluation);
    }

    @Transactional
    public void update(Evaluation evaluation, Long professorId, Long turmaId, Long disciplinaId) {
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado!"));
        Turma turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada!"));
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada!"));

        evaluation.setProfessor(professor);
        evaluation.setTurma(turma);
        evaluation.setDisciplina(disciplina);

        evaluationRepository.save(evaluation);
    }

    public Map<String, String> delete(Long id) {
        evaluationRepository.deleteById(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Avaliação deletada com sucesso");
        return response;
    }
}


