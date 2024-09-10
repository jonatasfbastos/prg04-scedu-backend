package br.com.ifba.scedu.domain.entities.evaluation.controller;



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

import java.util.List;


@RestController  // Indica que esta classe é um controlador REST
@RequestMapping("/evaluation")  // Define a rota base para todos os endpoints deste controlador
@CrossOrigin("*")  // Permite requisições de qualquer origem (CORS)
@RequiredArgsConstructor  // Gera o construtor para inicializar as dependências finais
public class EvaluationController {

    private final EvaluationIService evaluationService;  // Serviço responsável pelas operações de avaliação
    private final ObjectMapperUtil objectMapperUtil;  // Utilitário para mapeamento de entidades para DTOs e vice-versa

    @GetMapping(path = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<EvaluationGetResponseDto>> findAll(Pageable pageable) {
        // Retorna todas as avaliações paginadas, mapeadas para o DTO
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.evaluationService.findAll(pageable).map(c -> objectMapperUtil
                        .map(c, EvaluationGetResponseDto.class)));
    }

    @GetMapping(path = "/findByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EvaluationGetResponseDto>> findByName(@PathVariable String name) {
        // Busca avaliações pelo nome e as mapeia para o DTO
        List<Evaluation> evaluations = this.evaluationService.findByName(name);
        List<EvaluationGetResponseDto> responseDto  = objectMapperUtil.mapAll(evaluations, EvaluationGetResponseDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/evaluation/{id}")
    public ResponseEntity<EvaluationGetResponseDto> findById(@PathVariable Long id) {
        // Busca uma avaliação pelo ID e a mapeia para o DTO
        Evaluation evaluation = evaluationService.findById(id);
        EvaluationGetResponseDto responseDto = objectMapperUtil.map(evaluation, EvaluationGetResponseDto.class);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EvaluationGetResponseDto> save(@RequestBody @Valid EvaluationPostRequestDto evaluationPostRequestDto) {
        // Mapeia o DTO para a entidade e salva a nova avaliação
        Evaluation evaluation = objectMapperUtil.map(evaluationPostRequestDto, Evaluation.class);

        // Salva a avaliação associando professor, turma e disciplina
        Evaluation savedevaluation = evaluationService.save(
                evaluation,
                evaluationPostRequestDto.getProfessorId(),
                evaluationPostRequestDto.getTurmaId(),
                evaluationPostRequestDto.getDisciplinaId()
        );

        // Retorna a avaliação salva mapeada para o DTO
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(objectMapperUtil.map(savedevaluation, EvaluationGetResponseDto.class));
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@RequestBody @Valid EvaluationPostRequestDto evaluationPostRequestDto) {
        // Mapeia o DTO para a entidade e atualiza a avaliação existente
        Evaluation evaluation = objectMapperUtil.map(evaluationPostRequestDto, Evaluation.class);

        // Atualiza a avaliação associando professor, turma e disciplina
        evaluationService.update(
                evaluation,
                evaluationPostRequestDto.getProfessorId(),
                evaluationPostRequestDto.getTurmaId(),
                evaluationPostRequestDto.getDisciplinaId()
        );

        // Retorna uma resposta sem conteúdo (no content) após a atualização
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        // Deleta a avaliação pelo ID e retorna a resposta
        return ResponseEntity.status(HttpStatus.OK)
                .body(evaluationService.delete(id));
    }
}

