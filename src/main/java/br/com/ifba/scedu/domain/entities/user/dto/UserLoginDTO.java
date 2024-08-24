package br.com.ifba.scedu.domain.entities.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
public class UserLoginDTO {
    @Email(message = "E-mail inválido!")
    @NotNull(message = "O campo 'email' não pode ser nulo!")
    // Campo que armazena o endereço de e-mail
    private String email;

    @NotNull(message = "O campo 'senha' não pode ser nulo!")
    @NotBlank(message = "O campo 'senha' não pode ser vazio!")
    // Campo que armazena a senha
    private String password;
}
