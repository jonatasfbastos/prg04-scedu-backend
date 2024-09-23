package br.com.ifba.scedu.gestaoterceirizado.controller;

import br.com.ifba.scedu.gestaoterceirizado.entities.dtos.RequestDTO;
import br.com.ifba.scedu.gestaoterceirizado.entities.dtos.ResponseDTO;
import br.com.ifba.scedu.gestaoterceirizado.entities.GestaoTerceirizado;
import br.com.ifba.scedu.gestaoterceirizado.service.GestaoTerceirizadoService;
import br.com.ifba.scedu.infrastructure.util.ObjectMapperUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*") // Permite o acesso ao controlador de qualquer origem (CORS)
@RestController // Indica que essa classe é um controlador REST
@AllArgsConstructor
@RequestMapping("/gestaoTerceirizados") // Define a URL base para os endpoints deste controlador
public class GestaoTerceirizadoControllers {

    private ObjectMapperUtil objectMapperUtil;
    private GestaoTerceirizadoService terceirizadoService; // Injeta o serviço responsável pelas operações da entidade

    // Criar um novo terceirizado
    @PostMapping("/save") // Define que esse método responde a requisições HTTP POST
    public ResponseEntity<ResponseDTO> save(@RequestBody RequestDTO dto) {
        ResponseDTO createdGestaoTerceirizado = terceirizadoService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGestaoTerceirizado);
    }

    // Editar terceirizado por ID
    @PutMapping("/{id}") // Define que esse método responde a requisições HTTP UPDATE
    public ResponseEntity<ResponseDTO> update(@PathVariable Long id, @RequestBody RequestDTO dto) {
        ResponseDTO updatedGestaoTerceirizado = terceirizadoService.update(id, dto); //faz o update no tercerizado com esse id
        return ResponseEntity.ok(updatedGestaoTerceirizado);
    }

    // Apagar terceirizado por ID
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")// Define que esse método responde a requisições HTTP DELETE
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        terceirizadoService.delete(id); //delete terceirizados com esse id
        return ResponseEntity.noContent().build();
    }

    // Buscar todos os terceirizados
    @CrossOrigin(origins = "*")
    @GetMapping("/findAll")// Define que esse método responde a requisições HTTP GET para buscar todos os registros
    public ResponseEntity<List<ResponseDTO>> findAll() {
        // Busca todos os terceirizados
        List<GestaoTerceirizado> terceirizados = terceirizadoService.findAll();
        // Converte a lista de entidades para DTOs de resposta
        List<ResponseDTO> viewDTOs = terceirizados.stream()
                .map(terceirizado1 -> objectMapperUtil.map(terceirizado1, ResponseDTO.class))
                .collect(Collectors.toList());
        // Retorna a lista de DTOs com status HTTP 200 (OK)
        return new ResponseEntity<>(viewDTOs, HttpStatus.OK);
    }

    // Buscar terceirizado por ID
    @GetMapping("/{id}") // Define que esse método responde a requisições HTTP GET para buscar por um id registros
    public ResponseEntity<ResponseDTO> findById(@PathVariable Long id) {
        ResponseDTO gestaoTerceirizado = terceirizadoService.findById(id);
        return ResponseEntity.ok(gestaoTerceirizado);
    }
}
