package br.com.ifba.scedu.domain.entities.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
public class UserUpdateDataDTO {
    @NotNull(message = "Campo 'nome' não pode ser nulo!")
    @NotBlank(message = "Campo 'nome' não pode estar vazio!")
    private String name;

    @NotNull(message = "Campo 'email' não pode ser nulo!")
    @Email(message = "E-mail inválido!")
    private String email;
}
