package br.com.ifba.scedu.gestaoterceirizado.controller;

import br.com.ifba.scedu.gestaoterceirizado.entities.dtos.RequestDTO;
import br.com.ifba.scedu.gestaoterceirizado.entities.dtos.ResponseDTO;
import br.com.ifba.scedu.gestaoterceirizado.entities.GestaoTerceirizado;
import br.com.ifba.scedu.gestaoterceirizado.exception.GestaoTerceirizadoNotFoundException;
import br.com.ifba.scedu.gestaoterceirizado.service.GestaoTerceirizadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*") // Permite o acesso ao controlador de qualquer origem (CORS)
@RestController // Indica que essa classe é um controlador REST
@RequestMapping("/gestaoTerceirizados") // Define a URL base para os endpoints deste controlador
public class GestaoTerceirizadoControllers {

    @Autowired
    private GestaoTerceirizadoService terceirizadoService; // Injeta o serviço responsável pelas operações da entidade

    // Criar um novo terceirizado
    @CrossOrigin(origins = "*") // Habilita CORS para esse endpoint
    @PostMapping // Define que esse método responde a requisições HTTP POST
    public ResponseEntity<GestaoTerceirizado> save(@Valid @RequestBody RequestDTO dto) {
        // Salva um novo terceirizado baseado nos dados do DTO de requisição
        GestaoTerceirizado terceirizado = terceirizadoService.save(dto);
        // Retorna a entidade criada com o status HTTP 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(terceirizado);
    }

    // Editar terceirizado por ID
    @CrossOrigin(origins = "*")
    @PutMapping("/{id}") // Define que esse método responde a requisições HTTP PUT, com um parâmetro de caminho (ID)
    public ResponseEntity<GestaoTerceirizado> update(@PathVariable Long id, @Valid @RequestBody RequestDTO dto) {
        // Atualiza o terceirizado com base no ID e nos dados do DTO
        GestaoTerceirizado updated = terceirizadoService.update(id, dto);
        // Retorna a entidade atualizada com status HTTP 200 (OK)
        return ResponseEntity.ok(updated);
    }

    // Apagar terceirizado por ID
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}") // Define que esse método responde a requisições HTTP DELETE
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            // Tenta deletar o terceirizado pelo ID
            terceirizadoService.delete(id);
            // Retorna status HTTP 204 (No Content) se a exclusão for bem-sucedida
            return ResponseEntity.noContent().build();
        } catch (GestaoTerceirizadoNotFoundException e) {
            // Se o ID não for encontrado, retorna status HTTP 404 (Not Found)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Buscar todos os terceirizados
    @CrossOrigin(origins = "*")
    @GetMapping("/findAll") // Define que esse método responde a requisições HTTP GET para buscar todos os registros
    public ResponseEntity<List<ResponseDTO>> getAll() {
        // Busca todos os terceirizados
        List<GestaoTerceirizado> terceirizados = terceirizadoService.findAll();
        // Converte a lista de entidades para DTOs de resposta
        List<ResponseDTO> viewDTOs = terceirizados.stream()
                .map(this::convertToViewDTO) // Converte cada entidade para DTO
                .collect(Collectors.toList());
        // Retorna a lista de DTOs com status HTTP 200 (OK)
        return new ResponseEntity<>(viewDTOs, HttpStatus.OK);
    }

    // Buscar terceirizado por ID
    @CrossOrigin(origins = "*")
    @GetMapping("/{id}") // Define que esse método responde a requisições HTTP GET com um parâmetro de caminho (ID)
    public ResponseEntity<ResponseDTO> findById(@PathVariable Long id) {
        // Busca o terceirizado pelo ID
        GestaoTerceirizado terceirizado = terceirizadoService.findById(id);
        // Retorna o DTO da entidade encontrada com status HTTP 200 (OK)
        return ResponseEntity.ok(convertToViewDTO(terceirizado));
    }

    // Método de conversão de entidade para DTO (usado nas respostas)
    private ResponseDTO convertToViewDTO(GestaoTerceirizado terceirizado) {
        // Cria um novo DTO de resposta e preenche com os dados da entidade
        ResponseDTO dto = new ResponseDTO();
        dto.setId(terceirizado.getId());
        dto.setIdPerson(terceirizado.getPerson().getId());
        dto.setName(terceirizado.getPerson().getName());
        dto.setCpf(terceirizado.getPerson().getCpf());
        dto.setAge(terceirizado.getPerson().getAge());
        dto.setArea(terceirizado.getPerson().getArea());
        dto.setCity(terceirizado.getPerson().getCity());
        dto.setState(terceirizado.getPerson().getState());
        dto.setStreet(terceirizado.getPerson().getStreet());
        dto.setPhone(terceirizado.getPhone());
        dto.setEmail(terceirizado.getEmail());
        dto.setPosition(terceirizado.getPosition());
        dto.setEnterprise(terceirizado.getEnterprise());
        dto.setDepartment(terceirizado.getDepartment());
        dto.setStatus(terceirizado.isStatus());
        dto.setObservations(terceirizado.getObservations());
        // Retorna o DTO preenchido
        return dto;
    }
}
