package br.com.ifba.scedu.domain.entities.absences.dto;

import br.com.ifba.scedu.domain.entities.disciplina.model.Disciplina;
import br.com.ifba.scedu.domain.entities.student.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbsencesCreateDto {
    private Student student;
    private LocalDate date;
    private Boolean justified;
    private Disciplina subject;
}

