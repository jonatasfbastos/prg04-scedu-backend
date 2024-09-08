package br.com.ifba.scedu.domain.entities.curso.model;

import java.util.Set;

import br.com.ifba.scedu.domain.entities.turma.model.Turma;
import br.com.ifba.scedu.domain.entities.disciplina.model.Disciplina;
import br.com.ifba.scedu.infrastructure.persistenceentity.PersistenceEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="Cursos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Curso extends PersistenceEntity{
    @Column(name = "codigo")
    private String code;

    @Column(name = "nome")
    private String name;

    @Column(name = "descricao")
    private String description;

    @Column(name = "carga-horaria")
    private Integer courseHours;

    @Column(name = "status")
    private boolean status;

    @Column(name="Modalidade")
    private String mode;

     @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Turma> turmas;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Disciplina> disciplinas;
    
}
