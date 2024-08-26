package br.com.ifba.scedu.domain.entities.escola.service;

import br.com.ifba.scedu.domain.entities.escola.model.Escola;
import br.com.ifba.scedu.domain.entities.escola.repository.EscolaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EscolaService implements EscolaIService{

    EscolaRepository escolaRepository;

    @Override
    @Transactional
    public Escola save(Escola escola){
        return escolaRepository.save(escola);
    }

    @Override
    @Transactional
    public Escola update(Escola escola) {
        return escolaRepository.save(escola);
    }

    @Override
    @Transactional
    public String delete(Long id) {
        escolaRepository.deleteById(id);
        return "DELETED";
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Escola> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return escolaRepository.findAllPageable(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Escola> findById(Long id) {
        return escolaRepository.findById(id);
    }

}
