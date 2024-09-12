package br.com.ifba.scedu.domain.entities.curso.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.ifba.scedu.domain.entities.curso.entity.Curso;
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
 public void delete(String code){
    cursoRepository.deleteCursoByCode(code);
    
 }
 @Transactional
 public Curso update(Curso c, String code){
   // if course exists, i get the return and update the fields
      if (cursoRepository.existsByCode(code)) {
      Curso cursoExistente = cursoRepository.findCursoByCode(code).orElseThrow(() -> new EntityNotFoundException("Curso não encontrado"));
      c.setId(cursoExistente.getId());
      cursoRepository.save(c);
      }
      return c;
   }
    
}