package br.com.ifba.scedu.domain.entities.turma.model;


import br.com.ifba.scedu.domain.entities.curso.model.Curso;
import br.com.ifba.scedu.infrastructure.persistenceentity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;
    
    @Column(nullable = false)
    private String escola;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String serie;
    
    @Column(nullable = false)
    private String anoLetivo;
    
    @Column(nullable = false)
    private String numeroSala;
    
    @Column(nullable = false)
    private String turno;
    
    @Column(nullable = false)
    private int numeroMaximoAlunos;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;
}
