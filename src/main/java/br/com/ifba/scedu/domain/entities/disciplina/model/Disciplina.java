package br.com.ifba.scedu.domain.entities.disciplina.model;


import br.com.ifba.scedu.infrastructure.persistenceentity.PersistenceEntity;
import br.com.ifba.scedu.professor.model.Professor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Entity
@Table(name = "disciplinas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Disciplina extends PersistenceEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nomeAbreviado;

    @Column(nullable = false)
    private String baseCurricular;

    @ManyToOne
    @JoinColumn(name = "professorId", nullable = false)  // Chave estrangeira para Professor
    private Professor professor;

}
