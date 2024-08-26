package br.com.ifba.scedu.domain.entities.turma.services;

import br.com.ifba.scedu.domain.entities.turma.model.Turma;
import br.com.ifba.scedu.domain.entities.turma.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public Turma save(Turma turma) {
        if (turma.getEscola().isBlank() || turma.getNome().isBlank()) {
            throw new RuntimeException("Invalid registration credentials (Escola or Nome)");
        }

        log.info("Saving turma: {}", turma);
        return this.turmaRepository.save(turma);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Turma update(Long id, Turma updatedTurma) {
        Turma existingTurma = this.findById(id);
        if (updatedTurma.getEscola().isBlank() || updatedTurma.getNome().isBlank()) {
            throw new RuntimeException("Invalid registration credentials (Escola or Nome)");
        }

        existingTurma.setEscola(updatedTurma.getEscola());
        existingTurma.setNome(updatedTurma.getNome());
        existingTurma.setSerie(updatedTurma.getSerie());
        existingTurma.setAnoLetivo(updatedTurma.getAnoLetivo());
        existingTurma.setNumeroSala(updatedTurma.getNumeroSala());
        existingTurma.setTurno(updatedTurma.getTurno());
        existingTurma.setNumeroMaximoAlunos(updatedTurma.getNumeroMaximoAlunos());

        log.info("Updating turma id={} : {}", id, existingTurma);
        return this.turmaRepository.save(existingTurma);
    }

    @Transactional(readOnly = true)
    public Turma findById(Long id) {
        return this.turmaRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Turma with id= %s not found", id))
        );
    }

    @Transactional(readOnly = true)
    public Page<Turma> findAll(Pageable pageable) {
        return this.turmaRepository.findAll(pageable);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        Turma turma = this.findById(id);
        this.turmaRepository.delete(turma);
        log.info("Deleting turma id= {} : {}", id, turma);
    }
}