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
import br.com.ifba.scedu.domain.entities.curso.entity.Curso;
import br.com.ifba.scedu.domain.entities.curso.service.CursoService;
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
    public ResponseEntity<CursoDto> save(@RequestBody @Valid CursoDto post){
        Curso c = mapper.map(post, Curso.class);
        Curso cursoRetorno = cursoService.save(c);
        CursoDto dtoResposta = mapper.map(cursoRetorno, CursoDto.class);// mapeando retorno
       
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResposta);
    }

    @DeleteMapping(path = "/delete/{code}", produces="application/json")
    public ResponseEntity<String> delete(@PathVariable String code){
       cursoService.delete(code);// pegando curso deletado para retorno
       return ResponseEntity.status(HttpStatus.OK).body("curso deletado");
    }

    @PutMapping(path="/update/{code}", consumes="application/json")
    public ResponseEntity<CursoDto> update(@PathVariable String code, @RequestBody @Valid CursoDto cursoUpdate){
        Curso c = mapper.map(cursoUpdate, Curso.class);
      Curso retorno = cursoService.update(c, code);
      CursoDto reposta = mapper.map(retorno, CursoDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(reposta);
    }

}
