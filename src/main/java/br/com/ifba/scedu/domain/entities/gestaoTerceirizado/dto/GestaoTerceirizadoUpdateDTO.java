package br.com.ifba.scedu.domain.entities.gestaoTerceirizado.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GestaoTerceirizadoUpdateDTO {

    private String name;
    private String cpf;
    private String rg;
    private String phone;
    private String address;
    @Email
    private String email;
    private String position;
    private String enterprise;
    private String department;
    private boolean status;
    private String observations;
}
