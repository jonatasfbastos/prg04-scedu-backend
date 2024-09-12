package br.com.ifba.scedu.domain.entities.absences.dto;

import br.com.ifba.scedu.domain.entities.disciplina.model.Disciplina;
import br.com.ifba.scedu.domain.entities.student.model.Student;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbsencesCreateDto {
    @NotNull(message = "Estudante não pode ser nulo")
    private Student student;

    @NotNull(message = "Data da falta não pode ser nula")
    @PastOrPresent(message = "Data da falta deve estar no passado ou no presente")
    private LocalDate date;

    @NotNull(message = "Justificado não pode ser nulo")
    private Boolean justified;

    @NotNull(message = "Disciplina não pode ser nula")
    private Disciplina subject;
}

