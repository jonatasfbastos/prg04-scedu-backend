package br.com.ifba.scedu.domain.entities.absences.controller;

import br.com.ifba.scedu.domain.entities.absences.dto.AbsencesCreateDto;
import br.com.ifba.scedu.domain.entities.absences.dto.AbsencesResponseDto;
import br.com.ifba.scedu.domain.entities.absences.model.Absences;
import br.com.ifba.scedu.domain.entities.absences.service.AbsencesService;
import br.com.ifba.scedu.infrastructure.util.ObjectMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/absences")
public class AbsencesController {

    private final AbsencesService absencesService;
    private final ObjectMapperUtil objectMapperUtil;

    @PostMapping("/save")
    public ResponseEntity<AbsencesResponseDto> save(@RequestBody AbsencesCreateDto dto) {
        Absences absence = objectMapperUtil.map(dto, Absences.class);
        Absences savedAbsence = this.absencesService.save(absence);
        AbsencesResponseDto responseDto = objectMapperUtil.map(savedAbsence, AbsencesResponseDto.class);

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AbsencesResponseDto> update(@PathVariable Long id, @RequestBody AbsencesCreateDto dto) {
        Absences updatedAbsence = objectMapperUtil.map(dto, Absences.class);
        Absences absence = this.absencesService.update(id, updatedAbsence);
        AbsencesResponseDto responseDto = objectMapperUtil.map(absence, AbsencesResponseDto.class);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AbsencesResponseDto> findById(@PathVariable Long id) {
        Absences absence = this.absencesService.findById(id);
        AbsencesResponseDto responseDto = objectMapperUtil.map(absence, AbsencesResponseDto.class);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<Page<AbsencesResponseDto>> findAll(Pageable pageable) {
        Page<Absences> absencesPage = absencesService.findAll(pageable);
        Page<AbsencesResponseDto> absencesResponseDtos = absencesPage.map(absence -> objectMapperUtil.map(absence, AbsencesResponseDto.class));
        return ResponseEntity.ok(absencesResponseDtos);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.absencesService.delete(id);
        return ResponseEntity.ok("Absence deleted");
    }
}

