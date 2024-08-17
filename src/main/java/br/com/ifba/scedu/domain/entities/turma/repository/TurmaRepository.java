package br.com.ifba.scedu.domain.entities.turma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.ifba.scedu.domain.entities.turma.model.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
    
}
