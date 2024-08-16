package br.com.ifba.scedu.domain.entities.curso.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.ifba.scedu.infrastructure.persistenceentity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    
    @JsonProperty(value="curso")
    @NotNull(message=" O curso eh obrigatorio")
    @NotBlank(message="O campo curso não pode ser vazio")
    @Column(name="curso", nullable = false)
    private String curso;
    
    @NotNull(message="A modalidade eh obrigatoria")
    @NotBlank(message="O campo curso nao pode ser vazio")
    @Column(name="modalidade", nullable = false)
    private String modalidade;
    
    
    @NotNull(message="A modalidade eh obrigatoria")
    @NotBlank(message="O campo curso nao pode ser vazio")
    @Column(name="turno", nullable=false)
    private String turno;

}
