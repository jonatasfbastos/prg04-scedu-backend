package br.com.ifba.scedu.professor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorResponseDTO {

        @JsonProperty(value = "id")
        private Long id;

        @JsonProperty(value = "name")
        private String name;

        @JsonProperty(value = "siape")
        private String siape;

        @JsonProperty(value = "departamento")
        private String departamento;

        @JsonProperty(value = "cargaHoraria")
        private Integer cargaHoraria;

}
