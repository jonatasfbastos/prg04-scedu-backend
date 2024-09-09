package br.com.ifba.scedu.domain.entities.student.model;

import br.com.ifba.scedu.domain.entities.absences.model.Absences;
import br.com.ifba.scedu.domain.entities.escola.model.Escola;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "social_name", nullable = true)
    private String socialName;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "gender_identity", nullable = true)
    private String genderIdentity;

    @Column(name = "student_rg", nullable = false)
    private String studentRg;

    @Column(name = "student_rg_issuing_authority", nullable = false)
    private String studentRgIssuingAuthority;

    @Column(name = "student_rg_issue_date", nullable = false)
    private Date studentRgIssueDate;

    @Column(name = "student_cpf", nullable = false)
    private String studentCpf;

    @Column(name = "voter_registration", nullable = true)
    private String voterRegistration;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @Column(name = "father_name", nullable = true)
    private String fatherName;

    @Column(name = "mother_name", nullable = false)
    private String motherName;

    @Column(name = "father_rg", nullable = true)
    private String fatherRg;

    @Column(name = "father_rg_issuing_authority", nullable = true)
    private String fatherRgIssuingAuthority;

    @Column(name = "father_rg_issue_date", nullable = true)
    private Date fatherRgIssueDate;

    @Column(name = "father_cpf", nullable = true)
    private String fatherCpf;

    @Column(name = "father_profession", nullable = true)
    private String fatherProfession;

    @Column(name = "mother_rg", nullable = false)
    private String motherRg;

    @Column(name = "mother_rg_issuing_authority", nullable = false)
    private String motherRgIssuingAuthority;

    @Column(name = "mother_rg_issue_date", nullable = false)
    private Date motherRgIssueDate;

    @Column(name = "mother_cpf", nullable = false)
    private String motherCpf;

    @Column(name = "mother_profession", nullable = false)
    private String motherProfession;

    @Column(name = "legal_guardian", nullable = true)
    private String legalGuardian;

    @Column(name = "student_phone", nullable = false)
    private String studentPhone;

    @Column(name = "guardian_phone", nullable = false)
    private String guardianPhone;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "neighborhood", nullable = false)
    private String neighborhood;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "living_region", nullable = false)
    private String livingRegion;

    @Column(name = "nationality", nullable = false)
    private String nationality;

    @Column(name = "birth_place", nullable = false)
    private String birthPlace;

    @Column(name = "previous_school", nullable = false)
    private String previousSchool;

    @Column(name = "disability", nullable = true)
    private String disability;

    @Column(name = "allergies", nullable = true)
    private String allergies;

    @Column(name = "relevant_medical_conditions", nullable = true)
    private String relevantMedicalConditions;

    @Column(name = "regular_medications", nullable = true)
    private String regularMedications;

    @OneToMany(mappedBy = "student")
    private List<Absences> absences = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "escola_id")
    private Escola school;


}
