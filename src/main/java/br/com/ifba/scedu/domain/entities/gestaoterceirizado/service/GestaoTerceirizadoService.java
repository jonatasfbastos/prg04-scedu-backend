package br.com.ifba.scedu.domain.entities.gestaoterceirizado.service;

import br.com.ifba.scedu.domain.entities.gestaoterceirizado.dto.RequestDTO;
import br.com.ifba.scedu.domain.entities.gestaoterceirizado.dto.ResponseDTO;
import br.com.ifba.scedu.domain.entities.gestaoterceirizado.exception.ResourceNotFoundException;
import br.com.ifba.scedu.domain.entities.gestaoterceirizado.model.GestaoTerceirizado;
import br.com.ifba.scedu.domain.entities.gestaoterceirizado.repository.GestaoTerceirizadoRepository;
import br.com.ifba.scedu.person.model.Person;
import br.com.ifba.scedu.person.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GestaoTerceirizadoService {

    @Autowired
    private GestaoTerceirizadoRepository terceirizadoRepository;

    @Autowired
    private PersonRepository personRepository;

    public GestaoTerceirizado save(RequestDTO dto) {
        GestaoTerceirizado terceirizado = new GestaoTerceirizado();

        // Busca a pessoa pelo ID e associa ao terceirizado
        Person person = personRepository.findById(dto.getIdPerson())
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada com o ID: " + dto.getIdPerson()));

        terceirizado.setPerson(person);
        terceirizado.setPhone(dto.getPhone());
        terceirizado.setEmail(dto.getEmail());
        terceirizado.setPosition(dto.getPosition());
        terceirizado.setEnterprise(dto.getEnterprise());
        terceirizado.setDepartment(dto.getDepartment());
        terceirizado.setStatus(dto.isStatus());
        terceirizado.setObservations(dto.getObservations());

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

