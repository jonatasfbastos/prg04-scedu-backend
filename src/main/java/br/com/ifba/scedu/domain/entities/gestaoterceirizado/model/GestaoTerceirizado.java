package br.com.ifba.scedu.domain.entities.gestaoterceirizado.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

import br.com.ifba.scedu.person.model.Person;

@Entity
@Table(name = "gestaoTerceirizado")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GestaoTerceirizado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person Person;

    @Column(name = "phone_terceirizado", nullable = false)
    private String phone;

    @Column(name = "email_terceirizado", nullable = false)
    @Email
    private String email;

    @Column(name = "position", nullable = false)
    private String position;

    @Column(name = "enterprise", nullable = false)
    private String enterprise;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "observations", nullable = false)
    private String observations;


    public Optional<GestaoTerceirizado> getTerceirizadoById(Long id) {
        return this.getTerceirizadoById(id);
    }
}
