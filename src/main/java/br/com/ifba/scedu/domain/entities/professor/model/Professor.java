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

@Entity
@Table(name = "professores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Professor extends User {

    @Column(name = "siape", unique = true)
    private String siape;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "carga_horaria")
    private Integer cargaHoraria;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_PROFESSOR"));
    }

}
