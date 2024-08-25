package br.com.ifba.scedu.domain.entities.professor.model;

import br.com.ifba.scedu.domain.entities.user.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

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

    /**
     * Retorna as autoridades (permissões) do professor.
     * Neste caso, o professor é considerado com a permissão "ROLE_PROFESSOR".
     * @return uma lista contendo a permissão "ROLE_PROFESSOR".
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_PROFESSOR"));
    }
}
