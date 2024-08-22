package br.com.ifba.scedu.domain.entities.absences.repository;

import br.com.ifba.scedu.domain.entities.absences.model.Absences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsencesRepository extends JpaRepository<Absences, Long> {

}
