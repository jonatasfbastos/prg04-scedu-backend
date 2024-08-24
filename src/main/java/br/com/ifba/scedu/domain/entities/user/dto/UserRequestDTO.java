package br.com.ifba.scedu.domain.entities.user.dto;

import br.com.ifba.scedu.domain.entities.user.model.UserRoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequestDTO {
    @NotNull(message = "Campo 'nome' não pode ser nulo!")
    @NotBlank(message = "Campo 'nome' não pode estar vazio!")
    // Campo que armazena o nome do usuario
    private String name;

    @NotNull(message = "Campo 'email' não pode ser nulo!")
    @Email(message = "E-mail inválido!")
    // Campo que armazena o endereço de e-mail
    private String email;

    @NotNull(message = "Campo 'senha' não pode ser nulo!")
    @NotBlank(message = "Campo 'senha' não pode estar vazio!")
    // Campo que armazena a senha
    private String password;

    // Campo que armazena a role do usuario (USER/ADMIN)
    private UserRoleEnum role;
}
