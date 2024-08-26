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

import br.com.ifba.scedu.domain.entities.curso.dto.CursoGetResponseDto;
import br.com.ifba.scedu.domain.entities.curso.dto.CursoRequestPostDto;
import br.com.ifba.scedu.domain.entities.curso.model.Curso;
import br.com.ifba.scedu.domain.entities.curso.service.CursoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;



@RestController
@RequestMapping(path = "/Cursos")
@AllArgsConstructor
public class CursoController {
    private final CursoService cursoService;

    @GetMapping(path = "/findall", produces = "application/json")
    public ResponseEntity<List<CursoGetResponseDto>> findAll(){
        List<CursoGetResponseDto> cursos = cursoService.findAll().stream().map(CursoGetResponseDto::new).toList();
      return ResponseEntity.status(HttpStatus.OK).body(cursos);

    }
    @GetMapping("/findcourse/{name}")
    public ResponseEntity<CursoGetResponseDto> getMethodName(@PathVariable String name) {
        Curso c = cursoService.findByName(name);
        CursoGetResponseDto reposta = new CursoGetResponseDto(c.getName(),c.getDescription(),c.getMode());
        return ResponseEntity.status(HttpStatus.OK).body(reposta);
    }
    

    @PostMapping(path = "/save", consumes="application/json")
    public ResponseEntity<CursoGetResponseDto> save(@RequestBody @Valid CursoRequestPostDto post){
        // i'm setting fields the course with my dto post
        Curso c = new Curso();
        c.setName(post.name());
        c.setCode(post.code());
        c.setDescription(post.description());
        c.setCourseHours(post.courseHours());
        c.setStatus(post.status());
        // here i get the return
        Curso cursoRetorno = cursoService.save(c);
        //i'm setting my response here in a new dto reponse
        CursoGetResponseDto resposta = new CursoGetResponseDto(
          cursoRetorno.getName(),cursoRetorno.getDescription(), cursoRetorno.getMode()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @DeleteMapping(path = "/delete/{id}", produces="application/json")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
       cursoService.delete(id);
       return ResponseEntity.status(HttpStatus.OK).body("Curso deletado com sucesso");
    }

    @PutMapping(path="/update/{id}", consumes="application/json")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Curso c){
      cursoService.update(c, id);
        return ResponseEntity.status(HttpStatus.OK).body("Curso Atualizado");
    }

}
