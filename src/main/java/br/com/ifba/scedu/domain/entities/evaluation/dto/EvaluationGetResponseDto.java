package br.com.ifba.scedu.domain.entities.evaluation.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluationGetResponseDto {

    @JsonProperty(value = "nome")
    private String name;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "date_evaluation")
    private Calendar date_evaluation;

    @JsonProperty(value = "note")
    private float note;

    @JsonProperty(value = "professor_id")
    private Long professorId;

    @JsonProperty(value = "professor_name")
    private String professorName;

    @JsonProperty(value = "turma_id")
    private Long turmaId;

    @JsonProperty(value = "turma_name")
    private String turmaName;

    @JsonProperty(value = "disciplina_id")
    private Long disciplinaId;

    @JsonProperty(value = "disciplina_name")
    private String disciplinaName;
}

