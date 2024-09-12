package br.com.ifba.scedu.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
public class UserUpdateDataDTO {
    @NotNull(message = "Campo 'username' não pode ser nulo!")
    @NotBlank(message = "Campo 'username' não pode estar vazio!")
    // Campo que armazena o nome de usuario
    private String usernname;

    @NotNull(message = "Campo 'email' não pode ser nulo!")
    @Email(message = "E-mail inválido!")
    // Campo que armazena o endereço de e-mail
    private String email;
}
