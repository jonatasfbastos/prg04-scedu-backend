package br.com.ifba.scedu.domain.entities.turma.services;

import br.com.ifba.scedu.domain.entities.turma.DTO.TurmaDTO;
import br.com.ifba.scedu.domain.entities.turma.model.Turma;
import br.com.ifba.scedu.domain.entities.turma.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TurmaService {

    @Autowired
    TurmaRepository repository;

    @Transactional
    public TurmaDTO save(Turma turma) {
        if (turma == null) {
            throw new RuntimeException("Turma object cannot be null");
        }

        repository.save(turma);

        return new TurmaDTO(turma.getCodigo(), turma.getEscola(), turma.getNome(), turma.getSerie(), turma.getAnoLetivo(), turma.getNumeroSala(), turma.getTurno(), turma.getNumeroMaximoAlunos());
    }

    @Transactional(readOnly = true)
    public Page<Turma> findAll(Pageable pageable) {
        Page<Turma> turmas = this.repository.findAll(pageable);
        if (turmas.isEmpty()) {
            throw new RuntimeException("No turmas found");
        }
        return turmas;
    }

    @Transactional(readOnly = true)
    public TurmaDTO findById(Long id) {

        var turma = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No records found for this ID"));

        return new TurmaDTO(turma.getCodigo(), turma.getEscola(), turma.getNome(), turma.getSerie(), turma.getAnoLetivo(), turma.getNumeroSala(), turma.getTurno(), turma.getNumeroMaximoAlunos());
    }

    @Transactional
    public TurmaDTO update(TurmaDTO newTurma, Long id) {
        var currentTurma = repository.findById(id).orElseThrow(() -> new RuntimeException("No records found for this ID"));
        
        currentTurma.setEscola(newTurma.escola());
        currentTurma.setNome(newTurma.nome());
        currentTurma.setSerie(newTurma.serie());
        currentTurma.setAnoLetivo(newTurma.anoLetivo().intValue()); // Convertendo Integer para int
        currentTurma.setNumeroSala(newTurma.numeroSala().intValue()); // Convertendo Integer para int
        currentTurma.setTurno(newTurma.turno());
        currentTurma.setNumeroMaximoAlunos(newTurma.numeroMaximoAlunos());
       
        this.save(currentTurma);
       
        return new TurmaDTO(currentTurma.getCodigo(), currentTurma.getEscola(), currentTurma.getNome(), currentTurma.getSerie(), currentTurma.getAnoLetivo(), currentTurma.getNumeroSala(), currentTurma.getTurno(), currentTurma.getNumeroMaximoAlunos());
        
    }

    @Transactional
    public void delete(Long id) {
        
        Turma entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No records found for this ID"));
        repository.delete(entity);
    }

}