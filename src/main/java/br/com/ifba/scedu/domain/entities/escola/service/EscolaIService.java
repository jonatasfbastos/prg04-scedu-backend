package br.com.ifba.scedu.domain.entities.escola.service;

import br.com.ifba.scedu.domain.entities.escola.model.Escola;

import java.util.List;
import java.util.Optional;

public interface EscolaIService {

    Escola save(Escola escola);

    Escola update(Escola escola);

    String delete(Long id);

    List<Escola> findAll();

    Optional<Escola> findById(Long id);

}
