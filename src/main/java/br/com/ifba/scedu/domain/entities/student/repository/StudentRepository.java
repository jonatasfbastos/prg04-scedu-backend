package br.com.ifba.scedu.domain.entities.student.repository;

import br.com.ifba.scedu.domain.entities.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByStudentCpf(String cpf);

}
