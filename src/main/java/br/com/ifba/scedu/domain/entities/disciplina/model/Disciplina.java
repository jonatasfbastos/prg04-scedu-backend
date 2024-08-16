package br.com.ifba.scedu.domain.entities.disciplina.model;

import br.com.ifba.scedu.infrastructure.persistenceentity.PersistenceEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "disciplinas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Disciplina extends PersistenceEntity implements Serializable {

    private String name;
    private String nomeAbreviado;
    private String baseCurricular;
}
