package br.com.ifba.scedu.domain.entities.professor.model;

import br.com.ifba.scedu.domain.entities.disciplina.model.Disciplina;
import br.com.ifba.scedu.domain.entities.user.model.User;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author Matheus Mendes
 * Classe que representa um professor no sistema.
 * Herda da classe User e adiciona atributos e métodos específicos para professores.
 */
@Entity
@Table(name = "professores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Professor extends User {

    /**
     * Siape (identificador único) do professor.
     * Deve ser único na tabela.
     */
    @Column(name = "siape", unique = true)
    private String siape;


    /**
     * Departamento ao qual o professor está vinculado.
     */
    @Column(name = "departamento")
    private String departamento;

    /**
     * Carga horária do professor.
     */
    @Column(name = "carga_horaria")
    private Integer cargaHoraria;

    //@OneToMany(mappedBy = "professor") estava dando erro nessa linha então deixei comentando para poder testar minha parte
    @ManyToOne
    private Disciplina disciplina;


}
