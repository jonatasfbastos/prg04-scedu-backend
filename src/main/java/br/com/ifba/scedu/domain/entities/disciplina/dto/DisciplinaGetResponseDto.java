package br.com.ifba.scedu.domain.entities.disciplina.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisciplinaGetResponseDto {

    @JsonProperty(value = "nome")
    private String nome;

    @JsonProperty(value = "nomeAbreviado")
    private String nomeAbreviado;

    @JsonProperty(value = "baseCurricular")
    private String baseCurricular;

}
