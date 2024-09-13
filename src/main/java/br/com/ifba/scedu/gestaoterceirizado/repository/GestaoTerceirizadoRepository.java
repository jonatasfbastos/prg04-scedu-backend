package br.com.ifba.scedu.gestaoterceirizado.repository;

import br.com.ifba.scedu.gestaoterceirizado.entities.GestaoTerceirizado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GestaoTerceirizadoRepository extends JpaRepository<GestaoTerceirizado, Long> {

}
