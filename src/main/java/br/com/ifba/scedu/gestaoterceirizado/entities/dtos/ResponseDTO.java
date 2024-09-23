package br.com.ifba.scedu.gestaoterceirizado.entities.dtos;

import br.com.ifba.scedu.gestaoterceirizado.entities.GestaoTerceirizado;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor // Gera um construtor com todos os atributos como parâmetros
@NoArgsConstructor  // Gera um construtor vazio
public class ResponseDTO {

    // ID do registro da entidade GestaoTerceirizado
    @NotNull(message = "Person ID is required.") // Validação: o campo não pode ser nulo
    private Long Id;

    // ID da pessoa associada ao terceirizado
    @NotNull(message = "Person ID is required.") // Validação: o campo não pode ser nulo
    private Long IdPerson;

    // Email da pessoa ou email de trabalho do terceirizado
    @NotBlank(message = "Email is required.") // Validação: o campo não pode ser vazio ou nulo
    @Email(message = "Email should be valid.") // Validação: o email deve estar em formato válido
    private String email;

    // Cargo ou posição ocupada pelo terceirizado
    @NotBlank(message = "Position is required.") // Validação: o campo não pode ser vazio ou nulo
    private String position;

    // Empresa onde o terceirizado trabalha
    @NotBlank(message = "Enterprise is required.") // Validação: o campo não pode ser vazio ou nulo
    private String enterprise;

    // Departamento onde o terceirizado está alocado
    @NotBlank(message = "Department is required.") // Validação: o campo não pode ser vazio ou nulo
    private String department;

    // Status do terceirizado (ativo ou inativo)
    @NotNull(message = "Status is required.") // Validação: o campo não pode ser nulo
    private Boolean status;

    // Observações adicionais sobre o terceirizado
    @NotBlank(message = "Observations is required.") // Validação: o campo não pode ser vazio ou nulo
    private String observations;
}
