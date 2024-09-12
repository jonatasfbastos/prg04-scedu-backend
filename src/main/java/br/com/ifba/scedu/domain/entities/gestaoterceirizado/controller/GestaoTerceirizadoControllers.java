package br.com.ifba.scedu.domain.entities.gestaoterceirizado.controller;

import br.com.ifba.scedu.domain.entities.gestaoterceirizado.dto.RequestDTO;
import br.com.ifba.scedu.domain.entities.gestaoterceirizado.dto.ResponseDTO;
import br.com.ifba.scedu.domain.entities.gestaoterceirizado.model.GestaoTerceirizado;
import br.com.ifba.scedu.domain.entities.gestaoterceirizado.service.GestaoTerceirizadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/gestaoTerceirizados")
public class GestaoTerceirizadoControllers {

    @Autowired
    private GestaoTerceirizadoService terceirizadoService;

    // criar o novo terceirizado
    @PostMapping
    public ResponseEntity<GestaoTerceirizado> save(@Valid @RequestBody RequestDTO dto) {
        GestaoTerceirizado terceirizado = terceirizadoService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(terceirizado);
    }

    // editar por id
    @PutMapping("/{id}")
    public ResponseEntity<GestaoTerceirizado> update(@PathVariable Long id, @Valid @RequestBody RequestDTO dto) {
        GestaoTerceirizado updated = terceirizadoService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    //apagar por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<GestaoTerceirizado> terceirizado = terceirizadoService.findById(id).getTerceirizadoById(id);
        if (terceirizado.isPresent()) {
            terceirizadoService.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //buscar todos
    @GetMapping("/findAll")
    public ResponseEntity<List<ResponseDTO>> getAll() {
        List<GestaoTerceirizado> terceirizados = terceirizadoService.findAll();
        List<ResponseDTO> viewDTOs = terceirizados.stream()
                .map(this::convertToViewDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(viewDTOs, HttpStatus.OK);
    }

    //buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable Long id) {
        GestaoTerceirizado terceirizado = terceirizadoService.findById(id);
        return ResponseEntity.ok(convertToViewDTO(terceirizado));
    }

    // Método de conversão no Controller DTO
    private ResponseDTO convertToViewDTO(GestaoTerceirizado terceirizado) {
        ResponseDTO dto = new ResponseDTO();
        dto.setId(terceirizado.getId());
        dto.setIdPerson(terceirizado.getPerson().getId());
        dto.setName(terceirizado.getPerson().getName());
        dto.setCpf(terceirizado.getPerson().getCpf());
        dto.setPhone(terceirizado.getPhone());
        dto.setEmail(terceirizado.getEmail());
        dto.setPosition(terceirizado.getPosition());
        dto.setEnterprise(terceirizado.getEnterprise());
        dto.setDepartment(terceirizado.getDepartment());
        dto.setStatus(terceirizado.isStatus());
        dto.setObservations(terceirizado.getObservations());
        return dto;
    }
}

