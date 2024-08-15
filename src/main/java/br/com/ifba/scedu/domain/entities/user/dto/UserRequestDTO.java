package br.com.ifba.scedu.domain.entities.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequestDTO {
    @NotNull(message = "Campo 'login' não pode ser nulo!")
    @NotBlank(message = "Campo 'login' não pode estar vazio!")
    private String login;

    @NotNull(message = "Campo 'email' não pode ser nulo!")
    @Email(message = "E-mail inválido!")
    private String email;

    @NotNull(message = "Campo 'senha' não pode ser nulo!")
    @NotBlank(message = "Campo 'senha' não pode estar vazio!")
    private String password;
}
