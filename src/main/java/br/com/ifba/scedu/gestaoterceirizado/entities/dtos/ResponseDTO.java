package br.com.ifba.scedu.gestaoterceirizado.entities.dtos;

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

    // Número de telefone do terceirizado
    @NotBlank(message = "Phone is required.") // Validação: o campo não pode ser vazio ou nulo
    private String phone;

    // Nome da pessoa associada ao terceirizado
    @NotBlank(message = "Name is required.") // Validação: o campo não pode ser vazio ou nulo
    private String name;

    // CPF da pessoa associada ao terceirizado
    @NotBlank(message = "CPF is required.") // Validação: o campo não pode ser vazio ou nulo
    private String cpf;

    // Idade da pessoa associada ao terceirizado
    @NotBlank(message = "Age is required.") // Validação: o campo não pode ser vazio ou nulo
    private Integer age;

    // Rua onde a pessoa associada ao terceirizado reside
    @NotBlank(message = "Street is required.") // Validação: o campo não pode ser vazio ou nulo
    private String street;

    // Bairro ou área onde a pessoa associada ao terceirizado reside
    @NotBlank(message = "Area is required.") // Validação: o campo não pode ser vazio ou nulo
    private String area;

    // Cidade onde a pessoa associada ao terceirizado reside
    @NotBlank(message = "City is required.") // Validação: o campo não pode ser vazio ou nulo
    private String city;

    // Estado onde a pessoa associada ao terceirizado reside
    @NotBlank(message = "State is required.") // Validação: o campo não pode ser vazio ou nulo
    private String state;

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
