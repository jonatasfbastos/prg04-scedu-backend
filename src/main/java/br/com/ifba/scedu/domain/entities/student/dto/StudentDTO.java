package br.com.ifba.scedu.domain.entities.student.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDTO {

    private Long id;

    @NotBlank(message = "Código é obrigatório")
    private String code;

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    private String name;

    private String socialName; // Opcional, sem validação

    @NotBlank(message = "Gênero é obrigatório")
    private String gender;

    private String genderIdentity; // Opcional, sem validação

    @NotBlank(message = "RG do aluno é obrigatório")
    private String studentRg;

    @NotBlank(message = "Órgão expedidor do RG do aluno é obrigatório")
    private String studentRgIssuingAuthority;

    @NotNull(message = "Data de emissão do RG do aluno é obrigatória")
    @PastOrPresent(message = "Data de emissão do RG do aluno deve estar no passado ou presente")
    private Date studentRgIssueDate;

    @NotBlank(message = "CPF do aluno é obrigatório")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF deve seguir o padrão XXX.XXX.XXX-XX")
    private String studentCpf;

    private String voterRegistration; // Opcional, sem validação

    @NotNull(message = "Data de nascimento é obrigatória")
    @Past(message = "Data de nascimento deve estar no passado")
    private Date birthDate;

    @Size(max = 100, message = "Nome do pai deve ter no máximo 100 caracteres")
    private String fatherName;

    @Size(max = 100, message = "Nome da mãe deve ter no máximo 100 caracteres")
    @NotBlank(message = "Nome da mãe é obrigatório")
    private String motherName;

    @Size(max = 20, message = "RG do pai deve ter no máximo 20 caracteres")
    private String fatherRg;

    @Size(max = 50, message = "Órgão expedidor deve ter no máximo 50 caracteres")
    private String fatherRgIssuingAuthority;

    @PastOrPresent(message = "Data de emissão do RG do pai deve estar no passado ou presente")
    private Date fatherRgIssueDate;

    //@Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF deve seguir o padrão XXX.XXX.XXX-XX")
    private String fatherCpf;

    private String fatherProfession; // Opcional, sem validação

    @Size(max = 20, message = "RG da mãe deve ter no máximo 20 caracteres")
    @NotBlank(message = "O RG da mãe é obrigatório")
    private String motherRg;

    @Size(max = 50, message = "Órgão expedidor deve ter no máximo 50 caracteres")
    @NotBlank(message = "O Órgão Expedidor do RG da mãe é Obrigatório")
    private String motherRgIssuingAuthority;

    @PastOrPresent(message = "Data de emissão do RG da mãe deve estar no passado ou presente")
    @NotNull(message = "A data de emissão do RG da mãe é obrigatório")
    private Date motherRgIssueDate;

    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF deve seguir o padrão XXX.XXX.XXX-XX")
    @NotBlank(message = "O CPF da mãe é obrigatório")
    private String motherCpf;

    private String motherProfession; // Opcional, sem validação

    private String legalGuardian; // Opcional, sem validação

    @NotBlank(message = "Telefone do aluno é obrigatório")
    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Telefone do aluno deve seguir o padrão (XX) XXXX-XXXX ou (XX) XXXXX-XXXX")
    private String studentPhone;

    @NotBlank(message = "Telefone do responsável é obrigatório")
    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Telefone do responsável deve seguir o padrão (XX) XXXX-XXXX ou (XX) XXXXX-XXXX")
    private String guardianPhone;

    @NotBlank(message = "CEP é obrigatório")
    private String zipCode;

    @NotBlank(message = "Rua é obrigatória")
    @Size(max = 80, message = "Rua deve ter no máximo 80 caracteres")
    private String street;

    @NotBlank(message = "Número da casa é obrigatório")
    @Size(max = 10, message = "Número da casa deve ter no máximo 10 caracteres")
    private String number;

    @NotBlank(message = "Cidade é obrigatória")
    @Size(max = 100, message = "Cidade deve ter no máximo 100 caracteres")
    private String city;

    @NotBlank(message = "Bairro é obrigatório")
    @Size(max = 100, message = "Bairro deve ter no máximo 100 caracteres")
    private String neighborhood;

    @NotBlank(message = "Estado é obrigatório")
    @Size(max = 2, message = "Estado deve ser o código de 2 letras")
    private String state;

    @NotBlank(message = "Região de moradia é obrigatória")
    private String livingRegion; // "Rural" ou "Urbana"

    @NotBlank(message = "Nacionalidade é obrigatória")
    private String nationality;

    @NotBlank(message = "Naturalidade é obrigatória")
    private String birthPlace;

    @NotBlank(message = "A escola anterior do aluno é obrigatória")
    private String previousSchool;

    private String disability; // Opcional, sem validação

    private String allergies; // Opcional, sem validação

    private String relevantMedicalConditions; // Opcional, sem validação

    private String regularMedications; // Opcional, sem validação

}
