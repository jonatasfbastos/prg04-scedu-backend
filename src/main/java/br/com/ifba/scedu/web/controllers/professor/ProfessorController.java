package br.com.ifba.scedu.web.controllers.professor;

import br.com.ifba.scedu.domain.entities.professor.model.Professor;
import br.com.ifba.scedu.domain.entities.professor.service.ProfessorIService;
import br.com.ifba.scedu.domain.entities.user.dto.UserResponseDTO;
import br.com.ifba.scedu.infrastructure.util.ObjectMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorIService professorIService;
    private final ObjectMapperUtil objectMapperUtil;

    // Lista todos os professores
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<Professor> professores = professorIService.findAll();
        List<UserResponseDTO> responseDTO = objectMapperUtil.mapAll(professores, UserResponseDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    // Busca um professor por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        Professor professor = professorIService.findById(id);
        UserResponseDTO responseDTO = objectMapperUtil.map(professor, UserResponseDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    // Salva um novo professor
    @PostMapping
    public ResponseEntity<UserResponseDTO> save(@RequestBody Professor professor) {
        Professor savedProfessor = professorIService.save(professor);
        UserResponseDTO responseDTO = objectMapperUtil.map(savedProfessor, UserResponseDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // Atualiza um professor existente
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody Professor professor) {
        Professor updatedProfessor = professorIService.update(id, professor);
        UserResponseDTO responseDTO = objectMapperUtil.map(updatedProfessor, UserResponseDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    // Deleta um professor
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        professorIService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Professor deletado com sucesso!");
    }
}
