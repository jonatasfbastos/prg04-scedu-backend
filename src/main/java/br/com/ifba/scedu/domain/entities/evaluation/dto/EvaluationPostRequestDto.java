package br.com.ifba.scedu.domain.entities.evaluation.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluationPostRequestDto {

    @JsonProperty(value = "nome")
    @NotNull(message = "O nome é obrigatório!")
    @NotBlank(message = "O nome não pode ser vazio!")
    private String name;

    @JsonProperty(value = "descricao")
    @NotNull(message = "A descricao é obrigatória!")
    @NotBlank(message = "A descricao não pode ser vazia!")
    @Size(min = 20, max = 150, message = "A descricao precisa ter pelo menos 20 caracteres e no máximo 150!")
    private String description;

    @JsonProperty(value = "data_avaliacao")
    @NotNull(message = "A data da avaliacao é obrigatória!")
    private Calendar date_evaluation;

    @JsonProperty(value = "nota")
    @NotNull(message = "A nota é obrigatória!")
    private float note;

    @JsonProperty(value = "professor_id")
    @NotNull(message = "O ID do professor é obrigatório!")
    private Long professorId;

    @JsonProperty(value = "turma_id")
    @NotNull(message = "O ID da turma é obrigatório!")
    private Long turmaId;

    @JsonProperty(value = "disciplina_id")
    @NotNull(message = "O ID da disciplina é obrigatório!")
    private Long disciplinaId;
}
