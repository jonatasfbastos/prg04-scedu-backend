package br.com.ifba.scedu.domain.entities.curso.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import br.com.ifba.scedu.domain.entities.curso.model.Curso;
import br.com.ifba.scedu.domain.entities.curso.repository.CursoRepository;
import br.com.ifba.scedu.domain.entities.turma.model.Turma;
import br.com.ifba.scedu.domain.entities.turma.repository.TurmaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CursoService {
 private final CursoRepository cursoRepository;
 private final TurmaRepository turmaRepository;
 
 public List<Curso> findAll(){
    return cursoRepository.findAll();
 }
 public Curso findByName(String name){
   return cursoRepository.findCursoByName(name).orElseThrow(() -> new EntityNotFoundException("Curso nao encontrado"));
 }
@Transactional
 public Curso save(Curso c){
   // i checked only the name because cannot there the same course
   if(cursoRepository.existsByName(c.getName())){
      throw new DataIntegrityViolationException("O curso já está cadastrado");
   }
   return cursoRepository.save(c);


  }
 
 @Transactional
 public void delete(Long id){
    cursoRepository.deleteById(id);
 }
 @Transactional
 public void update(Curso c, Long id){
   // if course exists, i get the return and update the fields
      if (cursoRepository.existsById(id)) {
      Curso cursoExistente = cursoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Curso não encontrado"));
      
      cursoExistente.setCode(c.getCode());
      cursoExistente.setName(c.getName());
      cursoExistente.setDescription(c.getDescription());
      cursoExistente.setCourseHours(c.getCourseHours());
      cursoExistente.setStatus(c.isStatus());
      cursoExistente.setMode(c.getMode());
      
      cursoRepository.save(cursoExistente);
      }
   }
      @Transactional
    public void addTurmaToCurso(Long cursoId, Turma turma) {
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado"));

        turma.setCurso(curso);
        curso.getTurmas().add(turma);

        turmaRepository.save(turma);
        cursoRepository.save(curso);
    }

    @Transactional
    public void removeTurmaFromCurso(Long cursoId, Long turmaId) {
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado"));

        Turma turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new EntityNotFoundException("Turma não encontrada"));

        if (turma.getCurso().equals(curso)) {
            curso.getTurmas().remove(turma);
            turma.setCurso(null);

            turmaRepository.save(turma);
            cursoRepository.save(curso);
        } else {
            throw new IllegalArgumentException("A turma não pertence ao curso especificado");
        }
    }

    public List<Turma> getTurmasByCurso(Long cursoId) {
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado"));

        return List.copyOf(curso.getTurmas());
    }
}
