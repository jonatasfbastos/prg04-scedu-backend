package br.com.ifba.scedu.domain.entities.escola.service;

import br.com.ifba.scedu.domain.entities.escola.model.Escola;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface EscolaIService {

    Escola save(Escola escola);

    Escola update(Long id, Escola updatedEscola);

    String delete(Long id);

    Page<Escola> findAll(int page, int size);
}
