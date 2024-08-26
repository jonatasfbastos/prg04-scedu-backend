package br.com.ifba.scedu.web.controllers.disciplina;

import br.com.ifba.scedu.domain.entities.disciplina.dto.DisciplinaGetResponseDto;
import br.com.ifba.scedu.domain.entities.disciplina.dto.DisciplinaPostRequestDto;
import br.com.ifba.scedu.domain.entities.disciplina.model.Disciplina;
import br.com.ifba.scedu.domain.entities.disciplina.service.DisciplinaService;
import br.com.ifba.scedu.infrastructure.util.ObjectMapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
@RequiredArgsConstructor
public class DisciplinaController {


    private final DisciplinaService disciplinaService;
    private ObjectMapperUtil objectMapperUtil;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll(){
        return ResponseEntity.status(HttpStatus.OK).
                body(objectMapperUtil.mapAll(
                        this.disciplinaService.findAll(),
                        DisciplinaGetResponseDto.class));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid DisciplinaPostRequestDto disciplinaPostRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(objectMapperUtil.map(disciplinaService.save(
                        (objectMapperUtil.map(disciplinaPostRequestDto, Disciplina.class))), DisciplinaGetResponseDto.class));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@RequestBody DisciplinaPostRequestDto disciplinaPostRequestDto){
        Disciplina disciplina = objectMapperUtil.map(disciplinaPostRequestDto, Disciplina.class);
        disciplinaService.update(disciplina);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        disciplinaService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
