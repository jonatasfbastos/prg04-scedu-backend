package br.com.ifba.scedu.domain.entities.gestaoterceirizado.service;

import br.com.ifba.scedu.domain.entities.gestaoterceirizado.dto.RequestDTO;
import br.com.ifba.scedu.domain.entities.gestaoterceirizado.exception.ResourceNotFoundException;
import br.com.ifba.scedu.domain.entities.gestaoterceirizado.model.GestaoTerceirizado;
import br.com.ifba.scedu.domain.entities.gestaoterceirizado.repository.GestaoTerceirizadoRepository;
import br.com.ifba.scedu.domain.entities.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestaoTerceirizadoService {

    @Autowired
    private GestaoTerceirizadoRepository terceirizadoRepository;

    @Autowired
    private PersonRepository personRepository;

    public GestaoTerceirizado save(RequestDTO dto) {
        //pega os dados do dto e transforma e salva com entidade
        GestaoTerceirizado terceirizado = new GestaoTerceirizado();
        terceirizado.setPosition(dto.getPosition());
        terceirizado.setEnterprise(dto.getEnterprise());
        terceirizado.setDepartment(dto.getDepartment());
        terceirizado.setStatus(dto.isStatus());
        terceirizado.setEmail(dto.getEmail());
        terceirizado.setPhone(dto.getPhone());
        terceirizado.setObservations(dto.getObservations());

        // Salvando o terceirizado
        return terceirizadoRepository.save(terceirizado);
    }

    public GestaoTerceirizado update(Long id, RequestDTO dto) {

        GestaoTerceirizado terceirizado = terceirizadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Terceirizado not found"));

        //pega os dados do dto e transforma e salva(edita) com entidade
        terceirizado.setPosition(dto.getPosition());
        terceirizado.setEnterprise(dto.getEnterprise());
        terceirizado.setDepartment(dto.getDepartment());
        terceirizado.setStatus(dto.isStatus());
        terceirizado.setEmail(dto.getEmail());
        terceirizado.setPhone(dto.getPhone());
        terceirizado.setObservations(dto.getObservations());

        //salva os "alterações"
        return terceirizadoRepository.save(terceirizado);
    }

    //busca tudo
    public List<GestaoTerceirizado> findAll() {
        return terceirizadoRepository.findAll();
    }

    //busca por id
    public GestaoTerceirizado findById(Long id) {
        return terceirizadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Terceirizado not found"));
    }

    //apaga por id
    public void delete(Long id) {
        terceirizadoRepository.deleteById(id);
    }
}

