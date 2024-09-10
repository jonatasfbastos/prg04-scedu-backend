package br.com.ifba.scedu.domain.entities.evaluation.model;


import br.com.ifba.scedu.domain.entities.disciplina.model.Disciplina;
import br.com.ifba.scedu.domain.entities.professor.model.Professor;
import br.com.ifba.scedu.domain.entities.turma.model.Turma;
import br.com.ifba.scedu.infrastructure.persistenceentity.PersistenceEntity;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity  // Indica que esta classe é uma entidade JPA mapeada para uma tabela no banco de dados
@Data  // Gera automaticamente getters, setters, equals, hashCode e toString
@Builder  // Permite o uso do padrão de projeto Builder para criar instâncias da classe
@Table(name="Evaluation")  // Define o nome da tabela correspondente a esta entidade no banco de dados
@AllArgsConstructor  // Gera um construtor com todos os argumentos
@NoArgsConstructor  // Gera um construtor sem argumentos
@EqualsAndHashCode(callSuper = false)  // Define como o método equals e hashCode serão gerados, sem incluir a superclasse
public class Evaluation extends PersistenceEntity implements Serializable {

    @Column(name = "name", nullable = false)  // Define a coluna 'name' que não pode ser nula
    private String name;

    @Column(name = "description", nullable = false, unique = true)  // Define a coluna 'description' que é única e não pode ser nula
    private String description;

    @Column(name = "date_evaluation", nullable = false, unique = true)  // Define a coluna 'date_evaluation' que é única e não pode ser nula
    private Calendar date_evaluation;

    @Column(name = "note", nullable = false)  // Define a coluna 'note' que não pode ser nula
    private float note;

    @ManyToOne  // Define um relacionamento de muitos para um com a entidade 'Professor'
    @JoinColumn(name = "professor_id", nullable = false)  // Define a chave estrangeira 'professor_id' que não pode ser nula
    private Professor professor;

    @ManyToOne  // Define um relacionamento de muitos para um com a entidade 'Turma'
    @JoinColumn(name = "turma_id", nullable = false)  // Define a chave estrangeira 'turma_id' que não pode ser nula
    private Turma turma;

    @ManyToOne  // Define um relacionamento de muitos para um com a entidade 'Disciplina'
    @JoinColumn(name = "disciplina_id", nullable = false)  // Define a chave estrangeira 'disciplina_id' que não pode ser nula
    private Disciplina disciplina;
}


