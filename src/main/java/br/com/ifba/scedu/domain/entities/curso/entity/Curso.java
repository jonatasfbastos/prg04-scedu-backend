package br.com.ifba.scedu.domain.entities.curso.entity;


import java.io.Serializable;
import java.util.List;

import br.com.ifba.scedu.grade.entities.Grade;
import br.com.ifba.scedu.infrastructure.persistenceentity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

public class Curso extends PersistenceEntity implements Serializable{
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

    @OneToMany// relação um para muitos com serie
    private List<Grade> grade;

}
