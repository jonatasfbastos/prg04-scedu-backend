package br.com.ifba.scedu.domain.entities.turma.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurmaResponseDto {
    private Long id;
    private String codigo;
    private String escola;
    private String nome;
    private String serie;
    private String anoLetivo;
    private String numeroSala;
    private String turno;
    private String numeroMaximoAlunos;
}