package br.com.ifba.scedu.web.controllers.gestaoTercerizado;

import br.com.ifba.scedu.domain.entities.gestaoTerceirizado.dto.GestaoTerceirizadoCreateDTO;
import br.com.ifba.scedu.domain.entities.gestaoTerceirizado.dto.GestaoTerceirizadoUpdateDTO;
import br.com.ifba.scedu.domain.entities.gestaoTerceirizado.dto.GestaoTerceirizadoViewDTO;
import br.com.ifba.scedu.domain.entities.gestaoTerceirizado.model.GestaoTerceirizado;
import br.com.ifba.scedu.domain.entities.gestaoTerceirizado.service.GestaoTerceirizadoService;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/gestaoTerceirizados")
public class GestaoTerceirizadoController {

    @Autowired
    private GestaoTerceirizadoService service;

    //lista todos os tercerizados
    @GetMapping
    public List<GestaoTerceirizadoViewDTO> findAll() {
        return service.findAll().stream()
                .map(this::convertToViewDTO)
                .collect(Collectors.toList());
    }

    //lista o tercerizado por id
    @GetMapping("/id/{id}")
    public ResponseEntity<GestaoTerceirizadoViewDTO> findById(@PathVariable Long id) {
        GestaoTerceirizado entity = service.findById(id);
        GestaoTerceirizadoViewDTO dto = convertToViewDTO(entity);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //lista o tercerizado pelo nome
    @GetMapping("/name/{name}")
    public ResponseEntity<List<GestaoTerceirizadoViewDTO>> findByName(@PathVariable String name) {
        List<GestaoTerceirizado> entities = service.findByName(name);
        List<GestaoTerceirizadoViewDTO> dtoList = entities.stream()
                .map(this::convertToViewDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    //salva(cria) o tercerizados
    @PostMapping("/save")
    public ResponseEntity<GestaoTerceirizadoViewDTO> save(@RequestBody GestaoTerceirizadoCreateDTO saveDTO) {
        GestaoTerceirizado entity = convertToEntity(saveDTO);
        GestaoTerceirizado savedEntity = service.save(entity);
        GestaoTerceirizadoViewDTO dto = convertToViewDTO(savedEntity);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //lista edita o tercerizado pelo id
    @PutMapping("/edit/{id}")
    public ResponseEntity<GestaoTerceirizadoViewDTO> update(@PathVariable Long id, @RequestBody GestaoTerceirizadoUpdateDTO updateDTO) {
        GestaoTerceirizado entity = service.findById(id);
        updateEntity(entity, updateDTO);
        GestaoTerceirizado updatedEntity = service.update(entity);
        GestaoTerceirizadoViewDTO dto = convertToViewDTO(updatedEntity);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //deletar tercerizado pelo id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Métodos de conversão do DTO criar
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

    // Métodos de conversão do DTO editar
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

    // Métodos de conversão do DTO visualizar
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
