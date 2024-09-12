package br.com.ifba.scedu.professor.controller;

import br.com.ifba.scedu.infrastructure.util.ObjectMapperUtil;
import br.com.ifba.scedu.professor.dto.ProfessorRequestDTO;
import br.com.ifba.scedu.professor.dto.ProfessorResponseDTO;
import br.com.ifba.scedu.professor.model.Professor;
import br.com.ifba.scedu.professor.service.ProfessorIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Matheus Mendes
 * Controlador responsável por gerenciar as requisições relacionadas a professores.
 * Este controlador fornece endpoints para buscar, salvar, atualizar e deletar professores.
 */
@RestController
@RequestMapping("/professor")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorIService professorIService;
    private final ObjectMapperUtil objectMapperUtil;

    /**
     * @author Matheus Mendes
     * Busca por todos os professores cadastrados no sistema.
     *
     * @return Uma lista de objetos do tipo UserResponseDTO, que representam os professores.
     */
    @GetMapping
    public ResponseEntity<List<ProfessorResponseDTO>> findAll() {
        // Busca todos os professores cadastrados no sistema
        List<Professor> professores = professorIService.findAll();

        // Converte a lista de professores para uma lista de objetos UserResponseDTO
        List<ProfessorResponseDTO> responseDTO = objectMapperUtil.mapAll(professores, ProfessorResponseDTO.class);

        // Retorna a lista de professores com status HTTP OK (200)
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }


    /**
     * @author Matheus Mendes
     * Busca um professor pelo ID.
     *
     * @param id O ID do professor a ser buscado.
     * @return Uma resposta HTTP com o professor encontrado, ou um erro se não for encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> findById(@PathVariable Long id) {
        // Chama o serviço para buscar o professor pelo ID
        Professor professor = professorIService.findById(id);

        // Converte o professor encontrado para um objeto UserResponseDTO
        ProfessorResponseDTO responseDTO = objectMapperUtil.map(professor, ProfessorResponseDTO.class);

        // Retorna a resposta HTTP com o professor encontrado e status OK (200)
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    /**
     * @author Matheus Mendes
     * Salva um novo professor no sistema.
     *
     * @param professorRequestDTO O objeto Professor a ser salvo.
     * @return Uma resposta HTTP com o professor salvo e status CREATED (201).
     */
    @PostMapping
    public ResponseEntity<ProfessorResponseDTO> save(@RequestBody @Valid ProfessorRequestDTO professorRequestDTO) {

        Professor professor = objectMapperUtil.map(professorRequestDTO, Professor.class);
        Professor savedProfessor = professorIService.save(professor);
        ProfessorResponseDTO responseDTO = objectMapperUtil.map(savedProfessor, ProfessorResponseDTO.class);

        // Retorna a resposta HTTP com o professor salvo e status CREATED (201)
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    /**
     * @author Matheus Mendes
     * Atualiza um professor existente no sistema.
     *
     * @param id O ID do professor a ser atualizado.
     * @param professorRequestDTO O objeto Professor com as informações atualizadas.
     * @return Uma resposta HTTP com o professor atualizado e status OK (200).
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> update(@PathVariable Long id, @RequestBody @Valid ProfessorRequestDTO professorRequestDTO) {

        Professor professor = objectMapperUtil.map(professorRequestDTO, Professor.class);
        Professor savedProfessor = this.professorIService.update(id, professor);
        ProfessorResponseDTO professorResponseDTO = objectMapperUtil.map(savedProfessor, ProfessorResponseDTO.class);

        // Retorna a resposta HTTP com o professor atualizado e status OK (200)
        return ResponseEntity.status(HttpStatus.OK).body(professorResponseDTO);
    }

    /**
     * @author Matheus Mendes
     * Deleta um professor pelo ID.
     *
     * @param id o ID do professor a ser deletado
     * @return uma resposta HTTP com uma mensagem de sucesso
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        // Chama o serviço para deletar o professor pelo ID
        professorIService.delete(id);

        // Retorna a resposta HTTP com uma mensagem de sucesso
        return ResponseEntity.status(HttpStatus.OK).body("Professor deletado com sucesso!");
    }

}
