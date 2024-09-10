package br.com.ifba.scedu.domain.entities.evaluation.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;


@Data  // Gera automaticamente getters, setters, equals, hashCode e toString
@AllArgsConstructor  // Gera um construtor com todos os argumentos
@NoArgsConstructor  // Gera um construtor sem argumentos
public class EvaluationGetResponseDto {

    @JsonProperty(value = "nome")  // Mapeia o campo 'name' para o nome JSON 'nome'
    private String name;

    @JsonProperty(value = "description")  // Mapeia o campo 'description' para o nome JSON 'description'
    private String description;

    @JsonProperty(value = "date_evaluation")  // Mapeia o campo 'date_evaluation' para o nome JSON 'date_evaluation'
    private Calendar date_evaluation;

    @JsonProperty(value = "note")  // Mapeia o campo 'note' para o nome JSON 'note'
    private float note;


    @JsonProperty(value = "professor_name")  // Mapeia o campo 'professorName' para o nome JSON 'professor_name'
    private String professorName;

    @JsonProperty(value = "turma_name")  // Mapeia o campo 'turmaName' para o nome JSON 'turma_name'
    private String turmaName;

    @JsonProperty(value = "professor_id")  // Mapeia o campo 'professorId' para o nome JSON 'professor_id'
    private Long professorId;

    @JsonProperty(value = "turma_id")  // Mapeia o campo 'turmaId' para o nome JSON 'turma_id'
    private Long turmaId;

    @JsonProperty(value = "disciplina_id")  // Mapeia o campo 'disciplinaId' para o nome JSON 'disciplina_id'
    private Long disciplinaId;

    @JsonProperty(value = "disciplina_name")  // Mapeia o campo 'disciplinaName' para o nome JSON 'disciplina_name'
    private String disciplinaName;
}


