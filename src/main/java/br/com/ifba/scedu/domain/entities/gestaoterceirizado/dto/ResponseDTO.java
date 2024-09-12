package br.com.ifba.scedu.domain.entities.gestaoterceirizado.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    @NotNull(message = "Person ID is required.")
    private Long Id;

    @NotNull(message = "Person ID is required.")
    private Long IdPerson; //id da pessoa associada

    @NotBlank(message = "Phone is required.")
    private String phone;

    @NotBlank(message = "name is required.")
    private String name;

    @NotBlank(message = "cpf is required.")
    private String cpf;

    @NotBlank(message = "Email is required.")
    @Email(message = "Email should be valid.")
    private String email; //email de trabalho

    @NotBlank(message = "Position is required.")
    private String position;

    @NotBlank(message = "Enterprise is required.")
    private String enterprise;

    @NotBlank(message = "Department is required.")
    private String department;

    @NotNull(message = "Status is required.")
    private Boolean status;

    @NotBlank(message = "Observations is required.")
    private String observations;

}
