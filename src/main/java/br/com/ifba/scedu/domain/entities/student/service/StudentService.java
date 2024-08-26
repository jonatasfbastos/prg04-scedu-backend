package br.com.ifba.scedu.domain.entities.student.service;

import br.com.ifba.scedu.domain.entities.student.model.Student;
import br.com.ifba.scedu.domain.entities.student.repository.StudentRepository;
import br.com.ifba.scedu.domain.entities.user.exceptions.other.UserEmailAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    @Transactional
    public Student save(Student entity) {

        if(studentRepository.existsByStudentCpf(entity.getStudentCpf()))
            throw new UserEmailAlreadyExistsException("Estudante já matriculado");

        return this.studentRepository.save(entity);
    }

    @Transactional
    public Student update(Long id, Student updatedStudent) {

        Student student = this.studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No records found for this ID"));

        updateStudentData(student, updatedStudent);

        return this.studentRepository.save(student);
    }

    @Transactional(readOnly = true)
    public Student findById(Long id) {
        return this.studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No records found for this ID"));
    }

    @Transactional(readOnly = true)
    public Page<Student> findAll(Pageable pageable) {
        return this.studentRepository.findAll(pageable);
    }

    private void updateStudentData(Student existingStudent, Student newStudent) {
        existingStudent.setCode(newStudent.getCode());
        existingStudent.setName(newStudent.getName());
        existingStudent.setSocialName(newStudent.getSocialName());
        existingStudent.setGender(newStudent.getGender());
        existingStudent.setGenderIdentity(newStudent.getGenderIdentity());
        existingStudent.setStudentRg(newStudent.getStudentRg());
        existingStudent.setStudentRgIssuingAuthority(newStudent.getStudentRgIssuingAuthority());
        existingStudent.setStudentRgIssueDate(newStudent.getStudentRgIssueDate());
        existingStudent.setStudentCpf(newStudent.getStudentCpf());
        existingStudent.setVoterRegistration(newStudent.getVoterRegistration());
        existingStudent.setBirthDate(newStudent.getBirthDate());
        existingStudent.setFatherName(newStudent.getFatherName());
        existingStudent.setMotherName(newStudent.getMotherName());
        existingStudent.setFatherRg(newStudent.getFatherRg());
        existingStudent.setFatherRgIssuingAuthority(newStudent.getFatherRgIssuingAuthority());
        existingStudent.setFatherRgIssueDate(newStudent.getFatherRgIssueDate());
        existingStudent.setFatherCpf(newStudent.getFatherCpf());
        existingStudent.setFatherProfession(newStudent.getFatherProfession());
        existingStudent.setMotherRg(newStudent.getMotherRg());
        existingStudent.setMotherRgIssuingAuthority(newStudent.getMotherRgIssuingAuthority());
        existingStudent.setMotherRgIssueDate(newStudent.getMotherRgIssueDate());
        existingStudent.setMotherCpf(newStudent.getMotherCpf());
        existingStudent.setMotherProfession(newStudent.getMotherProfession());
        existingStudent.setLegalGuardian(newStudent.getLegalGuardian());
        existingStudent.setStudentPhone(newStudent.getStudentPhone());
        existingStudent.setGuardianPhone(newStudent.getGuardianPhone());
        existingStudent.setZipCode(newStudent.getZipCode());
        existingStudent.setStreet(newStudent.getStreet());
        existingStudent.setNumber(newStudent.getNumber());
        existingStudent.setCity(newStudent.getCity());
        existingStudent.setNeighborhood(newStudent.getNeighborhood());
        existingStudent.setState(newStudent.getState());
        existingStudent.setLivingRegion(newStudent.getLivingRegion());
        existingStudent.setNationality(newStudent.getNationality());
        existingStudent.setBirthPlace(newStudent.getBirthPlace());
        existingStudent.setPreviousSchool(newStudent.getPreviousSchool());
        existingStudent.setDisability(newStudent.getDisability());
        existingStudent.setAllergies(newStudent.getAllergies());
        existingStudent.setRelevantMedicalConditions(newStudent.getRelevantMedicalConditions());
        existingStudent.setRegularMedications(newStudent.getRegularMedications());
    }


}
