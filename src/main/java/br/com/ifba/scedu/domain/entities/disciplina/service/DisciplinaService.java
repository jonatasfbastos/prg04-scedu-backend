package br.com.ifba.scedu.domain.entities.disciplina.service;

import br.com.ifba.scedu.domain.entities.disciplina.model.Disciplina;
import br.com.ifba.scedu.domain.entities.disciplina.repository.DisciplinaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;

    public List<Disciplina> findAll() {
        return disciplinaRepository.findAll();
    }

    public Disciplina findById(Long id) {
        return disciplinaRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Recurso não encontrado!"));
    }

    public List<Disciplina> findByName(String name) {
        return disciplinaRepository.findByName(name);
    }

    @Transactional
    public Disciplina save(Disciplina disciplina){
        return disciplinaRepository.save(disciplina);
    }

    @Transactional
    public void delete(Long id) {
        Disciplina disciplina = findById(id);
        disciplinaRepository.delete(disciplina);
    }

    @Transactional
    public Disciplina update(Disciplina disciplina) {

        return disciplinaRepository.save(disciplina);
    }

}
