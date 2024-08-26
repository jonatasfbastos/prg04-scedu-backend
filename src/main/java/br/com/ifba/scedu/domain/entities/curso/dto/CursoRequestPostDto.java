package br.com.ifba.scedu.domain.entities.curso.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CursoRequestPostDto(
    @NotBlank(message="O curso nao pode ser vazio")String name,
    @NotBlank(message="O codigo nao pode ser vazio")@Size(min=5, max=12, message = "Codigo entre 5 e 12 caracteres")
    String code,
    @NotBlank(message="A descricao nao pode ser vazia")String description,
    @NotNull(message="A carga horaria nao pode ser nula")@Min(value=10)Integer courseHours,
    @NotNull(message="O status nao pode ser nulo")boolean status,
    @NotBlank(message="A modalidade nao pode ser nula")String mode
) {

}
