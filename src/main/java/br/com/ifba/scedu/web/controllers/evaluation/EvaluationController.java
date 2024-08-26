package br.com.ifba.scedu.web.controllers.evaluation;



import br.com.ifba.scedu.domain.entities.evaluation.dto.EvaluationGetResponseDto;
import br.com.ifba.scedu.domain.entities.evaluation.dto.EvaluationPostRequestDto;
import br.com.ifba.scedu.domain.entities.evaluation.model.Evaluation;
import br.com.ifba.scedu.domain.entities.evaluation.service.EvaluationIService;
import br.com.ifba.scedu.infrastructure.util.ObjectMapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/evaluation")
@CrossOrigin("*")
@RequiredArgsConstructor
public class EvaluationController {

    private final EvaluationIService evaluationService;
    private final ObjectMapperUtil objectMapperUtil;

    @GetMapping(path = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<EvaluationGetResponseDto>>findAll(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.evaluationService.findAll(pageable).map(c -> objectMapperUtil.
                        map(c, EvaluationGetResponseDto.class)));
    }

    @GetMapping(path = "/findByName", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>findByName(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.mapAll(this.evaluationService.findByName(findByName().toString()),
                        EvaluationGetResponseDto.class));
    }

    @GetMapping("/avaliacao/{id}")
    public ResponseEntity<EvaluationGetResponseDto> findById(@PathVariable Long id) {
        Evaluation avaliacao = evaluationService.findById(id);
            EvaluationGetResponseDto responseDto = objectMapperUtil.map(avaliacao, EvaluationGetResponseDto.class);
            return ResponseEntity.ok(responseDto);
        }


    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EvaluationGetResponseDto> save(@RequestBody @Valid EvaluationPostRequestDto avaliacaoPostRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(objectMapperUtil.map(evaluationService.save((objectMapperUtil.map(avaliacaoPostRequestDto, Evaluation.class))),
                        EvaluationGetResponseDto.class));
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@RequestBody @Valid EvaluationPostRequestDto avaliacaoPostRequestDto) {
        Evaluation avaliacao = objectMapperUtil.map(avaliacaoPostRequestDto, Evaluation.class);
        evaluationService.update(avaliacao);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(evaluationService.delete(id));
    }
}
