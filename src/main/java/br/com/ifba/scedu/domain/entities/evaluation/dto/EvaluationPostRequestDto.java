package br.com.ifba.scedu.domain.entities.evaluation.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;


@Data  // Gera automaticamente getters, setters, equals, hashCode e toString
@AllArgsConstructor  // Gera um construtor com todos os argumentos
@NoArgsConstructor  // Gera um construtor sem argumentos
public class EvaluationPostRequestDto {

    @JsonProperty(value = "nome")  // Mapeia o campo 'name' para o nome JSON 'nome'
    @NotNull(message = "O nome é obrigatório!")  // Validação: o campo não pode ser nulo
    @NotBlank(message = "O nome não pode ser vazio!")  // Validação: o campo não pode ser vazio ou conter apenas espaços
    private String name;

    @JsonProperty(value = "descricao")  // Mapeia o campo 'description' para o nome JSON 'descricao'
    @NotNull(message = "A descricao é obrigatória!")  // Validação: o campo não pode ser nulo
    @NotBlank(message = "A descricao não pode ser vazia!")  // Validação: o campo não pode ser vazio
    @Size(min = 20, max = 150, message = "A descricao precisa ter pelo menos 20 caracteres e no máximo 150!")  // Validação: tamanho mínimo e máximo da string
    private String description;

    @JsonProperty(value = "data_avaliacao")  // Mapeia o campo 'date_evaluation' para o nome JSON 'data_avaliacao'
    @NotNull(message = "A data da avaliacao é obrigatória!")  // Validação: o campo não pode ser nulo
    private Calendar date_evaluation;

    @JsonProperty(value = "nota")  // Mapeia o campo 'note' para o nome JSON 'nota'
    @NotNull(message = "A nota é obrigatória!")  // Validação: o campo não pode ser nulo
    private float note;



    @JsonProperty(value = "professor_nome")  // Mapeia o campo 'professorName' para o nome JSON 'professor_nome'
    @NotNull(message = "O nome do professor é obrigatório!")  // Validação: o campo não pode ser nulo
    @NotBlank(message = "O nome do professor não pode ser vazio!")  // Validação: o campo não pode ser vazio
    private String professorName;



    @JsonProperty(value = "turma_nome")  // Mapeia o campo 'turmaName' para o nome JSON 'turma_nome'
    @NotNull(message = "O nome da turma é obrigatório!")  // Validação: o campo não pode ser nulo
    @NotBlank(message = "O nome da turma não pode ser vazio!")  // Validação: o campo não pode ser vazio
    private String turmaName;

    @JsonProperty(value = "disciplina_nome")  // Mapeia o campo 'disciplinaName' para o nome JSON 'disciplina_nome'
    @NotNull(message = "O nome da disciplina é obrigatório!")  // Validação: o campo não pode ser nulo
    @NotBlank(message = "O nome da disciplina não pode ser vazio!")  // Validação: o campo não pode ser vazio
    private String disciplinaName;

    @JsonProperty(value = "professor_id")  // Mapeia o campo 'professorId' para o nome JSON 'professor_id'
    @NotNull(message = "O ID do professor é obrigatório!")  // Validação: o campo não pode ser nulo
    private Long professorId;

    @JsonProperty(value = "turma_id")  // Mapeia o campo 'turmaId' para o nome JSON 'turma_id'
    @NotNull(message = "O ID da turma é obrigatório!")  // Validação: o campo não pode ser nulo
    private Long turmaId;

    @JsonProperty(value = "disciplina_id")  // Mapeia o campo 'disciplinaId' para o nome JSON 'disciplina_id'
    @NotNull(message = "O ID da disciplina é obrigatório!")  // Validação: o campo não pode ser nulo
    private Long disciplinaId;

}


