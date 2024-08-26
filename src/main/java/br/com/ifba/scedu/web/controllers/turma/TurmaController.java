package br.com.ifba.scedu.web.controllers.turma;

import br.com.ifba.scedu.domain.entities.turma.services.TurmaService;
import br.com.ifba.scedu.domain.entities.turma.DTO.TurmaCreateDto;
import br.com.ifba.scedu.domain.entities.turma.DTO.TurmaResponseDto;
import br.com.ifba.scedu.domain.entities.turma.model.Turma;
import br.com.ifba.scedu.infrastructure.util.ObjectMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaService turmaService;
    private final ObjectMapperUtil objectMapperUtil;

    @PostMapping("/save")
    public ResponseEntity<TurmaResponseDto> save(@RequestBody TurmaCreateDto dto) {
        Turma turma = objectMapperUtil.map(dto, Turma.class);
        Turma savedTurma = this.turmaService.save(turma);
        TurmaResponseDto responseDto = objectMapperUtil.map(savedTurma, TurmaResponseDto.class);

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TurmaResponseDto> update(@PathVariable Long id, @RequestBody TurmaCreateDto dto) {
        Turma updatedTurma = objectMapperUtil.map(dto, Turma.class);
        Turma turma = this.turmaService.update(id, updatedTurma);
        TurmaResponseDto responseDto = objectMapperUtil.map(turma, TurmaResponseDto.class);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaResponseDto> findById(@PathVariable Long id) {
        Turma turma = this.turmaService.findById(id);
        TurmaResponseDto responseDto = objectMapperUtil.map(turma, TurmaResponseDto.class);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<Page<TurmaResponseDto>> findAll(Pageable pageable) {
        Page<Turma> turmasPage = turmaService.findAll(pageable);
        Page<TurmaResponseDto> turmasResponseDtos = turmasPage.map(turma -> objectMapperUtil.map(turma, TurmaResponseDto.class));
        return ResponseEntity.ok(turmasResponseDtos);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.turmaService.delete(id);
        return ResponseEntity.ok("Turma deleted");
    }
}