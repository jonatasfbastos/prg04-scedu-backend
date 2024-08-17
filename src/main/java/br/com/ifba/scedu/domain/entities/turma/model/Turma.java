package br.com.ifba.scedu.domain.entities.turma.model;

import br.com.ifba.scedu.infrastructure.persistenceentity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Turmas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Turma extends PersistenceEntity {
    
    @Column(nullable = false, unique = true)
    private String codigo;
    
    @Column(nullable = false)
    private String escola;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String serie;
    
    @Column(nullable = false)
    private Integer anoLetivo;
    
    @Column(nullable = false)
    private Integer numeroSala;
    
    @Column(nullable = false)
    private String turno;
    
    @Column(nullable = false)
    private Integer numeroMaximoAlunos;
}
