package br.com.ifba.scedu.domain.entities.professor.dto;

import br.com.ifba.scedu.domain.entities.user.model.UserRoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorRequestDTO{

    @NotNull(message = "Campo 'nome' não pode ser nulo!")
    @NotBlank(message = "Campo 'nome' não pode estar em branco!")
    private String name;

    @NotNull(message = "Campo 'email' não pode ser nulo!")
    @NotBlank(message = "Campo 'email' não pode estar em branco!")
    private String email;

    @NotNull(message = "Campo 'password' não pode ser nulo!")
    @NotBlank(message = "Campo 'password' não pode estar em branco!")
    private String password;

    @NotNull(message = "Campo 'siape' não pode ser nulo!")
    @NotBlank(message = "Campo 'siape' não pode estar em branco!")
    private String siape;

    @NotNull(message = "Campo 'departamento' não pode ser nulo!")
    @NotBlank(message = "Campo 'departamento' não pode estar em branco!")
    private String departamento;


    private Integer cargaHoraria;

    private UserRoleEnum role;
}
