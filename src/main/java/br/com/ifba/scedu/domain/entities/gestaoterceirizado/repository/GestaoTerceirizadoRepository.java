package br.com.ifba.scedu.domain.entities.gestaoterceirizado.repository;

import br.com.ifba.scedu.domain.entities.gestaoterceirizado.model.GestaoTerceirizado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GestaoTerceirizadoRepository extends JpaRepository<GestaoTerceirizado, Long> {

}
