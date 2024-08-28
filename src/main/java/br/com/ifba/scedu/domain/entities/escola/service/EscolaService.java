package br.com.ifba.scedu.domain.entities.escola.service;

import br.com.ifba.scedu.domain.entities.escola.model.Escola;
import br.com.ifba.scedu.domain.entities.escola.repository.EscolaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EscolaService implements EscolaIService{


    private final EscolaRepository escolaRepository;

    @Override
    @Transactional
    public Escola save(Escola escola){
        return this.escolaRepository.save(escola);
    }

    @Override
    @Transactional
    public Escola update(Long id, Escola updatedEscola) {
        Escola escolaExistente = this.escolaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Escola não encontrado"));

        escolaExistente.setNome(updatedEscola.getNome());
        escolaExistente.setInep(updatedEscola.getInep());
        escolaExistente.setLocalizacao(updatedEscola.getLocalizacao());
        escolaExistente.setCep(updatedEscola.getCep());
        escolaExistente.setBairro(updatedEscola.getBairro());
        escolaExistente.setLogradouro(updatedEscola.getLogradouro());
        escolaExistente.setComplemento(updatedEscola.getComplemento());
        escolaExistente.setTelefone(updatedEscola.getTelefone());
        escolaExistente.setModalidade(updatedEscola.getModalidade());
        escolaExistente.setNomeDiretor(updatedEscola.getNomeDiretor());


        return this.escolaRepository.save(escolaExistente);
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

    @Transactional(readOnly = true)
    public Escola findById(Long id) {
        return this.escolaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID nao encontrado"));
    }
}
