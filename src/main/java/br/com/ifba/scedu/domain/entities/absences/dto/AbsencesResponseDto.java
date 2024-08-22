package br.com.ifba.scedu.domain.entities.absences.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbsencesResponseDto {
    private Long id;
    private String student;
    private LocalDate date;
    private Boolean justified;
    private String subject;
}
