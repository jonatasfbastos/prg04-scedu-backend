package br.com.ifba.scedu.domain.entities.curso.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import br.com.ifba.scedu.domain.entities.curso.model.Curso;
import br.com.ifba.scedu.domain.entities.curso.repository.CursoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CursoService {
 private final CursoRepository cursoRepository;
 
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
}
