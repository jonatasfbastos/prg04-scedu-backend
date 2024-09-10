package br.com.ifba.scedu.domain.entities.curso.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CursoDto {
    @NotBlank(message="O codigo nao pode ser vazio")@Size(min=5, max=12, message = "Codigo entre 5 e 12 caracteres")
    private String code;
    @NotBlank(message="O curso nao pode ser vazio")
    private String name;
    @NotBlank(message="A descricao nao pode ser vazia")
    private String description;
    
    @NotNull(message="A carga horaria nao pode ser nula")@Min(value=10)
    private Integer courseHours;
    @NotNull(message="O status nao pode ser nulo")
    private boolean status;
    @NotBlank(message="A modalidade nao pode ser nula")
    private String mode;

}
