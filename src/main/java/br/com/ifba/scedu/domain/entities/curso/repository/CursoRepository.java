package br.com.ifba.scedu.domain.entities.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifba.scedu.domain.entities.curso.model.Curso;


@Repository
public interface CursoRepository extends JpaRepository<Curso, Long>{
  boolean existsByCurso(String curso);

}
