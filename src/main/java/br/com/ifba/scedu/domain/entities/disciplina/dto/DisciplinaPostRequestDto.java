package br.com.ifba.scedu.domain.entities.disciplina.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisciplinaPostRequestDto{

    @JsonProperty(value = "nome")
    @NotNull(message = "nome é obrigatório!")
    @NotBlank(message = "nome não pode ser vazio!")
    private String nome;

    @JsonProperty(value = "nomeAbreviado")
    private String nomeAbreviado;

    @JsonProperty(value = "baseCurricular")
    @NotNull(message = "Base curricular é obrigatório!")
    @NotBlank(message = "Base curricular não pode ser vazio!")
    private String baseCurricular;

}


