package br.com.ifba.scedu.domain.entities.curso.service;

import java.util.List;

import org.springframework.stereotype.Service;
import br.com.ifba.scedu.domain.entities.curso.exception.CursoSavedException;
import br.com.ifba.scedu.domain.entities.curso.model.Curso;
import br.com.ifba.scedu.domain.entities.curso.repository.CursoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CursoService {
 private final CursoRepository cursoRepository;
 
 public List<Curso> findAll(){
    return cursoRepository.findAll();
 }
@Transactional
 public Curso save(Curso c){
   Curso saved = cursoRepository.save(c);

   if(saved==null){
      throw new CursoSavedException();
   }
   return saved;

  }
 
 @Transactional
 public void delete(Long id){
    cursoRepository.deleteById(id);
 }
 @Transactional
 public void update(Curso c, Long id){
      if (cursoRepository.existsById(id)) {
      Curso cursoExistente = cursoRepository.findById(id).orElseThrow(() -> new RuntimeException("Curso não encontrado"));
      
      cursoExistente.setCurso(c.getCurso());
      cursoExistente.setModalidade(c.getModalidade());
      cursoExistente.setTurno(c.getTurno());
      
      cursoRepository.save(cursoExistente);
  } else {
      throw new RuntimeException("Curso não encontrado com o ID: " + id);
  }
 }
}
