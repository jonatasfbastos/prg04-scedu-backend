package br.com.ifba.scedu.domain.entities.disciplina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifba.scedu.domain.entities.disciplina.model.Disciplina;

import java.util.List;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    public List<Disciplina> findByName(String name);

}
