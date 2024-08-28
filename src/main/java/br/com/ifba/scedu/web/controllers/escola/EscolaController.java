package br.com.ifba.scedu.web.controllers.escola;

import br.com.ifba.scedu.domain.entities.escola.dto.EscolaRequestDto;
import br.com.ifba.scedu.domain.entities.escola.dto.EscolaResponseDto;
import br.com.ifba.scedu.domain.entities.escola.model.Escola;
import br.com.ifba.scedu.domain.entities.escola.service.EscolaService;
import br.com.ifba.scedu.domain.entities.student.dto.StudentDTO;
import br.com.ifba.scedu.domain.entities.student.model.Student;
import br.com.ifba.scedu.infrastructure.util.ObjectMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/escola")
@CrossOrigin("*")
@RequiredArgsConstructor
public class EscolaController {
    private final EscolaService escolaService;
    private final ObjectMapperUtil objectMapperUtil;

    @PostMapping(path = "/save", consumes = "application/json")
    public ResponseEntity<EscolaResponseDto> save(@RequestBody EscolaRequestDto escolaRequestDto) {

        Escola escola = objectMapperUtil.map(escolaRequestDto, Escola.class);
        Escola savedEscola = this.escolaService.save(escola);
        EscolaResponseDto responseDto = objectMapperUtil.map(savedEscola, EscolaResponseDto.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping(path = "/update/{id}", consumes = "application/json")
    public ResponseEntity<EscolaResponseDto> update(@PathVariable Long id, @RequestBody EscolaRequestDto escolaRequestDto) {

        Escola escola = objectMapperUtil.map(escolaRequestDto, Escola.class);
        Escola updatedEscola = this.escolaService.update(id, escola);
        EscolaResponseDto responseDto = objectMapperUtil.map(escola, EscolaResponseDto.class);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping(path = "/findall", produces = "application/json")
    public ResponseEntity<?> findAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size){

        Page<Escola> escolaPage = this.escolaService.findAll(page, size);
        Page<EscolaResponseDto> escolaDto = escolaPage.map(escola -> objectMapperUtil.map(escola, EscolaResponseDto.class));

        return ResponseEntity.status(HttpStatus.OK).body(escolaDto);
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<EscolaResponseDto> findById(@PathVariable Long id) {
        Escola escola = this.escolaService.findById(id);
        EscolaResponseDto escolaDto = objectMapperUtil.map(escola, EscolaResponseDto.class);

        return ResponseEntity.ok(escolaDto);
    }

    @DeleteMapping(path = "/delete/{id}", produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        escolaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
