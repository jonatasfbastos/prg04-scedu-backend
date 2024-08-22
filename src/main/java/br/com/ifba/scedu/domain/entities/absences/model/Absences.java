package br.com.ifba.scedu.domain.entities.absences.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Absences")
public class Absences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column( name = "student", nullable = false )
    private String student;

    @Column( name = "date", nullable = false )
    private LocalDate date;

    @Column( name = "justified", nullable = false )
    private Boolean justified;

    @Column( name = "subject", nullable = false )
    private String subject;
}
