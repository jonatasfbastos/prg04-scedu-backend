package br.com.ifba.scedu.web.controllers.turma;

import br.com.ifba.scedu.domain.entities.turma.DTO.TurmaDTO;
import br.com.ifba.scedu.domain.entities.turma.model.Turma;
import br.com.ifba.scedu.domain.entities.turma.services.TurmaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @PostMapping
public ResponseEntity<TurmaDTO> create(@RequestBody TurmaDTO turmaDTO) {
    Turma newTurma = new Turma();
    newTurma.setCodigo(turmaDTO.codigo());
    newTurma.setEscola(turmaDTO.escola());
    newTurma.setNome(turmaDTO.nome());
    newTurma.setSerie(turmaDTO.serie());
    newTurma.setAnoLetivo(turmaDTO.anoLetivo().intValue()); // Convertendo Integer para int
    newTurma.setNumeroSala(turmaDTO.numeroSala().intValue()); // Convertendo Integer para int
    newTurma.setTurno(turmaDTO.turno());
    newTurma.setNumeroMaximoAlunos(turmaDTO.numeroMaximoAlunos());
    
    this.turmaService.save(newTurma);
    return ResponseEntity.status(HttpStatus.CREATED).body(turmaDTO);
}

    @GetMapping("/{id}")
    public ResponseEntity<TurmaDTO> getById(@PathVariable Long id) {
        var turma = turmaService.findById(id);
        return ResponseEntity.ok(turma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaDTO> update(@PathVariable Long id, @RequestBody TurmaDTO turmaDTO) {
        var updatedTurma = turmaService.update(turmaDTO, id);
        return ResponseEntity.ok(updatedTurma);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        turmaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<TurmaDTO>> getAll(Pageable pageable) {
        var turmas = turmaService.findAll(pageable);
        return ResponseEntity.ok(turmas.map(TurmaDTO::new));
    }
}