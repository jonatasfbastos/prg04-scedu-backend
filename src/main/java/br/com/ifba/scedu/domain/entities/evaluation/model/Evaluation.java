package br.com.ifba.scedu.domain.entities.evaluation.model;


import br.com.ifba.scedu.domain.entities.disciplina.model.Disciplina;
import br.com.ifba.scedu.domain.entities.professor.model.Professor;
import br.com.ifba.scedu.domain.entities.turma.model.Turma;
import br.com.ifba.scedu.infrastructure.persistenceentity.PersistenceEntity;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Data
@Builder
@Table(name="Avaliacao")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Evaluation extends PersistenceEntity implements Serializable {

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "description",nullable = false, unique = true)
    private String description;

    @Column(name = "date_evaluation",nullable = false, unique = true)
    private Calendar date_evaluation;

    @Column(name = "note",nullable = false)
    private float note;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;


    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = false)
    private Turma turma;

    @ManyToOne
    @JoinColumn(name = "disciplina_id", nullable = false)
    private Disciplina disciplina;

}

