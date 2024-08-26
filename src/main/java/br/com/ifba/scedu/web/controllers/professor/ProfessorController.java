package br.com.ifba.scedu.web.controllers.professor;

import br.com.ifba.scedu.domain.entities.professor.dto.ProfessorResponseDTO;
import br.com.ifba.scedu.domain.entities.professor.model.Professor;
import br.com.ifba.scedu.domain.entities.professor.service.ProfessorIService;
import br.com.ifba.scedu.domain.entities.user.dto.UserResponseDTO;
import br.com.ifba.scedu.infrastructure.util.ObjectMapperUtil;
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
     * @param professor O objeto Professor a ser salvo.
     * @return Uma resposta HTTP com o professor salvo e status CREATED (201).
     */
    @PostMapping
    public ResponseEntity<UserResponseDTO> save(@RequestBody Professor professor) {
        // Chama o serviço para salvar o professor
        Professor savedProfessor = professorIService.save(professor);

        // Converte o professor salvo para um objeto UserResponseDTO
        UserResponseDTO responseDTO = objectMapperUtil.map(savedProfessor, UserResponseDTO.class);

        // Retorna a resposta HTTP com o professor salvo e status CREATED (201)
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    /**
     * @author Matheus Mendes
     * Atualiza um professor existente no sistema.
     *
     * @param id O ID do professor a ser atualizado.
     * @param professor O objeto Professor com as informações atualizadas.
     * @return Uma resposta HTTP com o professor atualizado e status OK (200).
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Professor professor) {
        // Chama o serviço para atualizar o professor
        this.professorIService.update(id, professor);

        // Retorna a resposta HTTP com o professor atualizado e status OK (200)
        return ResponseEntity.status(HttpStatus.OK).body("Atualização realizada com sucesso!");
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
