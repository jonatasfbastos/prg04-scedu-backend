package br.com.ifba.scedu.domain.entities.escola.dto;


import br.com.ifba.scedu.domain.entities.grade.model.DTOs.GradeViewDTO;
import br.com.ifba.scedu.domain.entities.student.dto.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EscolaResponseDto {

    private Long id;

    private String nome;

    private String inep;

    private String localizacao;

    private String cep;

    private String bairro;

    private String logradouro;

    private String complemento;

    private String telefone;

    private String modalidade;

    private String nomeDiretor;
/*
    private List<GradeViewDTO> series;

    private List<StudentDTO> alunos;
 */
}
