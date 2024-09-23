package br.com.ifba.scedu.gestaoterceirizado.service;

import br.com.ifba.scedu.gestaoterceirizado.entities.GestaoTerceirizado;
import br.com.ifba.scedu.gestaoterceirizado.entities.dtos.ResponseDTO;
import br.com.ifba.scedu.gestaoterceirizado.exception.GestaoTerceirizadoNotFoundException;
import br.com.ifba.scedu.gestaoterceirizado.entities.dtos.RequestDTO;
import br.com.ifba.scedu.gestaoterceirizado.repository.GestaoTerceirizadoRepository;
import br.com.ifba.scedu.infrastructure.util.ObjectMapperUtil;
import br.com.ifba.scedu.person.model.Person;
import br.com.ifba.scedu.person.repository.PersonRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Define que esta classe é um serviço, permitindo sua injeção em outras partes da aplicação
@AllArgsConstructor
public class GestaoTerceirizadoService {

    private GestaoTerceirizadoRepository terceirizadoRepository; // Repositório para acessar a base de dados de GestaoTerceirizado
    private PersonRepository personRepository; // Repositório para acessar a base de dados de Person
    private ObjectMapperUtil objectMapperUtil; // Repositório para acessar a base de dados de ObjectMapper

    // Cria e salva um novo registro de GestaoTerceirizado


    public ResponseDTO save(RequestDTO dto) {
        GestaoTerceirizado gestaoTerceirizado = objectMapperUtil.map(dto, GestaoTerceirizado.class);
        // Verifica se a pessoa existe pelo ID antes de salvar
        Person person = personRepository.findById(dto.getIdPerson())
                .orElseThrow(() -> new GestaoTerceirizadoNotFoundException("Terceirizado não encontrada com o ID: " + dto.getIdPerson()));
        gestaoTerceirizado.setPerson(person);

        GestaoTerceirizado savedEntity = terceirizadoRepository.save(gestaoTerceirizado);
        return objectMapperUtil.map(savedEntity, ResponseDTO.class);
    }

    // Atualiza um registro existente de GestaoTerceirizado


    public ResponseDTO update(Long id, RequestDTO dto) {
        GestaoTerceirizado gestaoTerceirizado = terceirizadoRepository.findById(id)
                .orElseThrow(() -> new GestaoTerceirizadoNotFoundException("GestaoTerceirizado não encontrado com o ID: " + id));

        // Atualiza apenas os campos permitidos
        gestaoTerceirizado.setPosition(dto.getPosition());
        gestaoTerceirizado.setEnterprise(dto.getEnterprise());
        gestaoTerceirizado.setDepartment(dto.getDepartment());
        gestaoTerceirizado.setStatus(dto.getStatus());
        gestaoTerceirizado.setObservations(dto.getObservations());

        GestaoTerceirizado updatedEntity = terceirizadoRepository.save(gestaoTerceirizado);
        return objectMapperUtil.map(updatedEntity, ResponseDTO.class);
    }

    // Retorna todos os registros de GestaoTerceirizado
    public List<GestaoTerceirizado> findAll() { //ok /ok
        return terceirizadoRepository.findAll();
    }

    // Retorna um registro de GestaoTerceirizado pelo ID

    public ResponseDTO findById(Long id) {

        GestaoTerceirizado gestaoTerceirizado = terceirizadoRepository.findById(id)
                .orElseThrow(() -> new GestaoTerceirizadoNotFoundException("Gestão de Terceirizado com ID " + id + " não encontrada"));

        // Mapeia a entidade para o DTO de visualização (ViewDTO)
        ResponseDTO dto = objectMapperUtil.map(gestaoTerceirizado, ResponseDTO.class);

        return dto;
    }

    // Deleta um registro de GestaoTerceirizado pelo ID
    public void delete(Long id) {
        // Verifica se o GestaoTerceirizado existe, lança uma exceção se não encontrado
        GestaoTerceirizado entity = terceirizadoRepository.findById(id)
                .orElseThrow(() -> new GestaoTerceirizadoNotFoundException("Nenhum registro encontrado para este ID"));

        // Deleta o registro do banco de dados
        terceirizadoRepository.delete(entity);
    }
}
