package br.com.ifba.scedu.domain.entities.student.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentPageDTO {

    private String name;
    private String code;
    private String studentCpf;
    private String studentRg;

}
