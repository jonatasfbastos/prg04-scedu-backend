package br.com.ifba.scedu.domain.entities.gestaoTerceirizado.repository;

import br.com.ifba.scedu.domain.entities.gestaoTerceirizado.model.GestaoTerceirizado;
import br.com.ifba.scedu.domain.entities.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GestaoTerceirizadoRepository extends JpaRepository<GestaoTerceirizado, Long> {

    // Busca registros de GestaoTerceirizado cujo nome contém a string fornecida, ignorando maiúsculas e minúsculas
    List<GestaoTerceirizado> findByName(String name);
}
