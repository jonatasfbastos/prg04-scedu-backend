/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.scedu.grade.entities;

import br.com.ifba.scedu.domain.entities.curso.entity.Curso;
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

/**
 * Entidade que representa uma 'Série' no sistema.
 * 
 * A 'Série' é uma unidade acadêmica associada a um 'Curso'. Esta entidade mapeia a tabela 'grades' no banco de dados.
 * 
 * A anotação @Entity indica que esta classe é uma entidade JPA, e @Table define o nome da tabela correspondente no banco de dados.
 * Os atributos desta classe correspondem às colunas da tabela 'grades'.
 * 
 * @author lara
 */
@Entity
@Table(name = "grades") // Nome da tabela no banco de dados
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática de IDs
    @Column(name = "id", updatable = false, nullable = false) // Define a coluna como não atualizável e não nula
    private Long id;

    @Column(name = "code", nullable = false) // Define a coluna 'code' como não nula
    private String code;

    @Column(name = "name", nullable = false) // Define a coluna 'name' como não nula
    private String name;

    @Column(name = "curriculum_code", nullable = false) // Define a coluna 'curriculum_code' como não nula
    private String curriculumCode;

    @ManyToOne // Define o relacionamento Many-to-One com a entidade Curso
    @JoinColumn(name = "curso_id") // Nome da coluna que armazena o ID do Curso associado
    private Curso course; // Curso associado à Série

}

