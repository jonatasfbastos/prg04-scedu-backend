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
}
