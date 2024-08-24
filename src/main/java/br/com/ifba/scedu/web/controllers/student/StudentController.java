package br.com.ifba.scedu.web.controllers.student;

import br.com.ifba.scedu.domain.entities.student.dto.StudentDTO;
import br.com.ifba.scedu.domain.entities.student.dto.StudentPageDTO;
import br.com.ifba.scedu.domain.entities.student.model.Student;
import br.com.ifba.scedu.domain.entities.student.service.StudentService;
import br.com.ifba.scedu.infrastructure.util.ObjectMapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/students")
@CrossOrigin("*")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final ObjectMapperUtil objectMapperUtil;

    @PostMapping
    public ResponseEntity<StudentDTO> save(@RequestBody @Valid StudentDTO dto) {
        Student student = objectMapperUtil.map(dto, Student.class);
        Student savedStudent = this.studentService.save(student);
        StudentDTO responseDTO = objectMapperUtil.map(student, StudentDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<StudentDTO> update(@PathVariable Long id, @RequestBody @Valid StudentDTO dto) {
        Student student = objectMapperUtil.map(dto, Student.class);
        Student updatedStudent = this.studentService.update(id, student);
        StudentDTO responseDTO = objectMapperUtil.map(student, StudentDTO.class);

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StudentDTO> findById(@PathVariable Long id) {
        Student student = this.studentService.findById(id);
        StudentDTO response = objectMapperUtil.map(student, StudentDTO.class);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<StudentPageDTO>> findAll(Pageable pageable) {
        Page<Student> studentsPage = this.studentService.findAll(pageable);
        Page<StudentPageDTO> studentsPageDTO = studentsPage.map(student -> objectMapperUtil.map(student, StudentPageDTO.class));

        return ResponseEntity.ok(studentsPageDTO);
    }

}
