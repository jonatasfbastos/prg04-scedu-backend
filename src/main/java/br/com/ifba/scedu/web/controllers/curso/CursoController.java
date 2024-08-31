package br.com.ifba.scedu.web.controllers.curso;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifba.scedu.domain.entities.curso.dto.CursoDto;
import br.com.ifba.scedu.domain.entities.curso.model.Curso;
import br.com.ifba.scedu.domain.entities.curso.service.CursoService;
import br.com.ifba.scedu.domain.entities.turma.DTO.TurmaCreateDto;
import br.com.ifba.scedu.domain.entities.turma.model.Turma;
import br.com.ifba.scedu.infrastructure.util.ObjectMapperUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;



@RestController
@RequestMapping(path = "/Cursos")
@AllArgsConstructor
public class CursoController {
    private final CursoService cursoService;
    private final ObjectMapperUtil mapper;// object to convert class


    @GetMapping(path = "/findall", produces = "application/json")
    public ResponseEntity<List<CursoDto>> findAll(){
    
      return ResponseEntity.status(HttpStatus.OK).body(mapper.mapAll(cursoService.findAll(), CursoDto.class));

    }
    @GetMapping("/findcourse/{name}")
    public ResponseEntity<CursoDto> findCursoByName(@PathVariable String name) {
        Curso c = cursoService.findByName(name);
        CursoDto resposta = mapper.map(c, CursoDto.class);
        
        
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }
    

    @PostMapping(path = "/save", consumes="application/json")
    public ResponseEntity<Curso> save(@RequestBody @Valid CursoDto post){
        Curso c = mapper.map(post, Curso.class);
    
        Curso cursoRetorno = cursoService.save(c);
       
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoRetorno);
    }

    @DeleteMapping(path = "/delete/{id}", produces="application/json")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
       cursoService.delete(id);
       return ResponseEntity.status(HttpStatus.OK).body("Curso deletado com sucesso");
    }

    @PutMapping(path="/update/{id}", consumes="application/json")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody @Valid CursoDto cursoUpdate){
        Curso c = mapper.map(cursoUpdate, Curso.class);
      cursoService.update(c, id);
        return ResponseEntity.status(HttpStatus.OK).body("Curso Atualizado");
    }
      @PostMapping("/{cursoId}/turmas")
    public ResponseEntity<Void> addTurmaToCurso(@PathVariable Long cursoId, @RequestBody TurmaCreateDto turmaDto) {
        Turma turma = mapper.map(turmaDto, Turma.class);
      cursoService.addTurmaToCurso(cursoId, turma);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{cursoId}/turmas/{turmaId}")
    public ResponseEntity<Void> removeTurmaFromCurso(@PathVariable Long cursoId, @PathVariable Long turmaId) {
        cursoService.removeTurmaFromCurso(cursoId, turmaId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{cursoId}/turmas")
    public ResponseEntity<List<Turma>> getTurmasByCurso(@PathVariable Long cursoId) {
        List<Turma> turmas = cursoService.getTurmasByCurso(cursoId);
        return ResponseEntity.ok(turmas);
    }

}
