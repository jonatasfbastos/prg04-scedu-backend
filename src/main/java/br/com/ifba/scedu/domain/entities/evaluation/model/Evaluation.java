package br.com.ifba.scedu.domain.entities.evaluation.model;


import br.com.ifba.scedu.infrastructure.persistenceentity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

}

