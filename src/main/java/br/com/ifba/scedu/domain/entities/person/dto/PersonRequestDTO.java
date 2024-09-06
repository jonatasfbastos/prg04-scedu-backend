package br.com.ifba.scedu.domain.entities.person.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonRequestDTO {
    private String name;
    private int age;
    private String cpf;
    private String street;
    private String area;
    private String state;
    private String city;
}
