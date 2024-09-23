package br.com.ifba.scedu.gestaoterceirizado.entities.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDTO {



    // O ID da pessoa associada ao terceirizado
    @NotNull(message = "Person ID is required.") // Validação: o campo não pode ser nulo
    private Long IdPerson;

    // O e-mail do terceirizado
    @NotBlank(message = "Email is required.") // Validação: o campo não pode ser vazio ou nulo
    @Email(message = "Email should be valid.") // Validação: o e-mail precisa ter um formato válido
    private String email; // Email pode ser de trabalho ou pessoal, dependendo da empresa

    // A posição ou cargo do terceirizado na empresa
    @NotBlank(message = "Position is required.") // Validação: o campo não pode ser vazio ou nulo
    private String position;

    // O nome da empresa em que o terceirizado trabalha
    @NotBlank(message = "Enterprise is required.") // Validação: o campo não pode ser vazio ou nulo
    private String enterprise;

    // O departamento onde o terceirizado está alocado
    @NotBlank(message = "Department is required.") // Validação: o campo não pode ser vazio ou nulo
    private String department;

    // O status do terceirizado (ativo ou inativo)
    @NotNull(message = "Status is required.") // Validação: o campo não pode ser nulo
    private Boolean status;

    // Observações adicionais sobre o terceirizado
    @NotBlank(message = "Observations is required.") // Validação: o campo não pode ser vazio ou nulo
    private String observations;

    // Método auxiliar para verificar o status
    public boolean isStatus() {
        return status;
    }
}
