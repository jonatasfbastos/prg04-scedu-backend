package br.com.ifba.scedu.domain.entities.escola.repository;

import br.com.ifba.scedu.domain.entities.escola.model.Escola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EscolaRepository extends JpaRepository<Escola, Long> {

    Optional<Escola> findById(Long id);

}
