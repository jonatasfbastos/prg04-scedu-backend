package br.com.ifba.scedu.domain.entities.professor.repository;

import br.com.ifba.scedu.domain.entities.professor.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository  extends JpaRepository<Professor, Long> {

    boolean existsByEmail(String email);
}
