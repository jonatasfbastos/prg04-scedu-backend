package br.com.ifba.scedu.domain.entities.gestaoTerceirizado.service;

import br.com.ifba.scedu.domain.entities.gestaoTerceirizado.exception.ResourceNotFoundException;
import br.com.ifba.scedu.domain.entities.gestaoTerceirizado.model.GestaoTerceirizado;
import br.com.ifba.scedu.domain.entities.gestaoTerceirizado.repository.GestaoTerceirizadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestaoTerceirizadoService {

    @Autowired
    private GestaoTerceirizadoRepository repository;

    //buscar todos os tercerizados
    public List<GestaoTerceirizado> findAll() {
        return repository.findAll();
    }

    //buscar o tercerizados pelo id
    public GestaoTerceirizado findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GestaoTerceirizado not found with id: " + id));
    }

    //buscar o tercerizados pelo nome
    public List<GestaoTerceirizado> findByName(String name) {
        return repository.findByName(name);
    }

    //cria(salva) um novo tercerizados
    @Transactional
    public GestaoTerceirizado save(GestaoTerceirizado entity) {
        return repository.save(entity);
    }

    //edita(atualiza) um tercerizados pelo id
    @Transactional
    public GestaoTerceirizado update(GestaoTerceirizado entity) {
        return repository.save(entity);
    }

    //deletar um tercerizados pelo id
    @Transactional
    public void delete(Long id) {
        GestaoTerceirizado existing = findById(id);
        repository.delete(existing);
    }
}

