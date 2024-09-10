package br.com.ifba.scedu.domain.entities.turma.DTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurmaCreateDto {
    @NotBlank(message = "Código da turma é obrigatório")
    private String codigo;
    
    @NotBlank(message = "Escola é obrigatória")
    private String escola;
    
    @NotBlank(message = "Nome da turma é obrigatório")
    private String nome;
    
    @NotBlank(message = "Série é obrigatória")
    private String serie;
    
    @NotNull(message = "Ano letivo é obrigatório")
    @Positive(message = "Ano letivo deve ser um número positivo")
    private Integer anoLetivo;
    
    @NotNull(message = "Número da sala é obrigatório")
    @Positive(message = "Número da sala deve ser um número positivo")
    private Integer numeroSala;
    
    @NotBlank(message = "Turno é obrigatório")
    private String turno;
    
    @NotNull(message = "Número máximo de alunos é obrigatório")
    @Positive(message = "Número máximo de alunos deve ser um número positivo")
    private Integer numeroMaximoAlunos;
}
