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

    private final EvaluationRepository evaluationRepository; // Repositório para persistência de avaliações
    private final ProfessorRepository professorRepository; // Repositório para persistência de professores
    private final TurmaRepository turmaRepository; // Repositório para persistência de turmas
    private final DisciplinaRepository disciplinaRepository; // Repositório para persistência de disciplinas

    /**
     * Obtém todas as avaliações com paginação.
     * @param pageable Informações de paginação
     * @return Uma página de avaliações
     */
    public Page<Evaluation> findAll(Pageable pageable) {
        return evaluationRepository.findAll(pageable);
    }

    /**
     * Busca avaliações pelo nome.
     * @param name Nome da avaliação
     * @return Lista de avaliações com o nome especificado
     */
    public List<Evaluation> findByName(String name) {
        return evaluationRepository.findByName(name);
    }

    /**
     * Busca uma avaliação pelo ID.
     * @param id ID da avaliação
     * @return Avaliação correspondente ao ID
     * @throws RuntimeException Se a avaliação não for encontrada
     */
    public Evaluation findById(Long id) {
        return evaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recurso não encontrado!"));
    }

    /**
     * Salva uma nova avaliação, associando-a aos professores, turmas e disciplinas com base nos nomes fornecidos.
     * @param evaluation Dados da avaliação a ser salva
     * @param professorId Nome do professor
     * @param turmaId Nome da turma
     * @param disciplinaId Nome da disciplina
     * @return Avaliação salva
     */
    @Transactional
    public Evaluation save(Evaluation evaluation, Long professorId, Long turmaId, Long disciplinaId) {
        // Obtém o professor associado pelo ID. Lança uma exceção se não for encontrado
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado!"));

        // Obtém a turma associada pelo ID. Lança uma exceção se não for encontrada
        Turma turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada!"));

        // Obtém a disciplina associada pelo ID. Lança uma exceção se não for encontrada
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada!"));

        // Associa o professor, turma e disciplina à avaliação
        evaluation.setProfessor(professor);
        evaluation.setTurma(turma);
        evaluation.setDisciplina(disciplina);

        // Salva a avaliação e retorna a instância salva
        return evaluationRepository.save(evaluation);
    }

    // Atualiza uma avaliação existente com novos dados de professor, turma e disciplina. Transacional garante atomicidade.
    @Transactional
    public void update(Evaluation evaluation, Long professorId, Long turmaId, Long disciplinaId) {
        // Obtém o professor associado pelo ID. Lança uma exceção se não for encontrado
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado!"));

        // Obtém a turma associada pelo ID. Lança uma exceção se não for encontrada
        Turma turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada!"));

        // Obtém a disciplina associada pelo ID. Lança uma exceção se não for encontrada
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada!"));

        // Atualiza a avaliação com o novo professor, turma e disciplina
        evaluation.setProfessor(professor);
        evaluation.setTurma(turma);
        evaluation.setDisciplina(disciplina);

        // Salva a avaliação atualizada
        evaluationRepository.save(evaluation);
    }

    /**
     * Exclui uma avaliação pelo ID.
     * @param id ID da avaliação a ser excluída
     * @return Mensagem de sucesso
     */
    public Map<String, String> delete(Long id) {
        evaluationRepository.deleteById(id); // Exclui a avaliação pelo ID
        Map<String, String> response = new HashMap<>();
        response.put("message", "Avaliação deletada com sucesso"); // Mensagem de sucesso
        return response;
    }
}




