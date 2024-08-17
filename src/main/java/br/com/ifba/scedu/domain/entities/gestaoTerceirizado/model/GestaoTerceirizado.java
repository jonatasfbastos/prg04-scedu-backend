package br.com.ifba.scedu.domain.entities.gestaoTerceirizado.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "rg", nullable = false)
    private String rg;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "email", nullable = false)
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
}
