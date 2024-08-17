package br.com.ifba.scedu.domain.entities.gestaoTerceirizado.service;

import br.com.ifba.scedu.domain.entities.gestaoTerceirizado.dto.GestaoTerceirizadoCreateDTO;
import br.com.ifba.scedu.domain.entities.gestaoTerceirizado.dto.GestaoTerceirizadoUpdateDTO;
import br.com.ifba.scedu.domain.entities.gestaoTerceirizado.dto.GestaoTerceirizadoViewDTO;
import br.com.ifba.scedu.domain.entities.gestaoTerceirizado.exception.ResourceNotFoundException;
import br.com.ifba.scedu.domain.entities.gestaoTerceirizado.model.GestaoTerceirizado;
import br.com.ifba.scedu.domain.entities.gestaoTerceirizado.repository.GestaoTerceirizadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GestaoTerceirizadoService {

    @Autowired
    private GestaoTerceirizadoRepository repository;

    // Retorna uma lista de todos os registros de GestaoTerceirizado
    public List<GestaoTerceirizadoViewDTO> findAll() {
        return repository.findAll().stream()
                .map(this::convertToViewDTO)
                .collect(Collectors.toList());
    }

    // Busca um registro de GestaoTerceirizado pelo ID
    public GestaoTerceirizadoViewDTO findById(Long id) {
        GestaoTerceirizado terceirizado = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GestaoTerceirizado not found with id: " + id));
        return convertToViewDTO(terceirizado);
    }

    // Busca registros de GestaoTerceirizado pelo nome, ignorando maiúsculas e minúsculas
    public List<GestaoTerceirizadoViewDTO> findByName(String name) {
        List<GestaoTerceirizado> terceirizados = repository.findByName(name);
        return terceirizados.stream()
                .map(this::convertToViewDTO)
                .collect(Collectors.toList());
    }

    // Cria um novo registro de GestaoTerceirizado
    @Transactional
    public GestaoTerceirizadoViewDTO save(GestaoTerceirizadoCreateDTO saveDTO) {
        GestaoTerceirizado terceirizado = convertToEntity(saveDTO);
        GestaoTerceirizado saved = repository.save(terceirizado);
        return convertToViewDTO(saved);
    }

    // Atualiza um registro de GestaoTerceirizado existente pelo ID
    @Transactional
    public GestaoTerceirizadoViewDTO update(Long id, GestaoTerceirizadoUpdateDTO updateDTO) {
        GestaoTerceirizado existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GestaoTerceirizado not found with id: " + id));

        updateEntity(existing, updateDTO);
        GestaoTerceirizado updated = repository.save(existing);
        return convertToViewDTO(updated);
    }

    // Deleta um registro de GestaoTerceirizado pelo ID
    @Transactional
    public void delete(Long id) {
        GestaoTerceirizado existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GestaoTerceirizado not found with id: " + id));
        repository.delete(existing);
    }

    // Converte um GestaoTerceirizadoCreateDTO para uma entidade GestaoTerceirizado
    private GestaoTerceirizado convertToEntity(GestaoTerceirizadoCreateDTO dto) {
        GestaoTerceirizado entity = new GestaoTerceirizado();
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setRg(dto.getRg());
        entity.setPhone(dto.getPhone());
        entity.setAddress(dto.getAddress());
        entity.setEmail(dto.getEmail());
        entity.setPosition(dto.getPosition());
        entity.setEnterprise(dto.getEnterprise());
        entity.setDepartment(dto.getDepartment());
        entity.setStatus(dto.isStatus());
        entity.setObservations(dto.getObservations());
        return entity;
    }

    // Atualiza uma entidade GestaoTerceirizado existente com dados de um GestaoTerceirizadoUpdateDTO
    private void updateEntity(GestaoTerceirizado entity, GestaoTerceirizadoUpdateDTO dto) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setRg(dto.getRg());
        entity.setPhone(dto.getPhone());
        entity.setAddress(dto.getAddress());
        entity.setEmail(dto.getEmail());
        entity.setPosition(dto.getPosition());
        entity.setEnterprise(dto.getEnterprise());
        entity.setDepartment(dto.getDepartment());
        entity.setStatus(dto.isStatus());
        entity.setObservations(dto.getObservations());
    }

    // Converte uma entidade GestaoTerceirizado para um GestaoTerceirizadoViewDTO
    private GestaoTerceirizadoViewDTO convertToViewDTO(GestaoTerceirizado entity) {
        GestaoTerceirizadoViewDTO dto = new GestaoTerceirizadoViewDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCpf(entity.getCpf());
        dto.setRg(entity.getRg());
        dto.setPhone(entity.getPhone());
        dto.setAddress(entity.getAddress());
        dto.setEmail(entity.getEmail());
        dto.setPosition(entity.getPosition());
        dto.setEnterprise(entity.getEnterprise());
        dto.setDepartment(entity.getDepartment());
        dto.setStatus(entity.isStatus());
        dto.setObservations(entity.getObservations());
        return dto;
    }
}

