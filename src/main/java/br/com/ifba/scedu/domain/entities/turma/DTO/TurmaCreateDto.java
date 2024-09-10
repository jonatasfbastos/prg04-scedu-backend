package br.com.ifba.scedu.domain.entities.turma.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurmaCreateDto {
    private String codigo;
    private String escola;
    private String nome;
    private String serie;
    private Integer anoLetivo;
    private Integer numeroSala;
    private String turno;
    private Integer numeroMaximoAlunos;
}
