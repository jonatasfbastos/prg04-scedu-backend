package br.com.ifba.scedu.domain.entities.absences.service;

import br.com.ifba.scedu.domain.entities.absences.exceptions.EntityNotFoundException;
import br.com.ifba.scedu.domain.entities.absences.exceptions.InvalidRegistrationInformationException;
import br.com.ifba.scedu.domain.entities.absences.model.Absences;
import br.com.ifba.scedu.domain.entities.absences.repository.AbsencesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class AbsencesService {

    private final AbsencesRepository absencesRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public Absences save(Absences abscence){
        if(abscence.getStudent().getId() == null || abscence.getSubject().getId() == null){
            throw new InvalidRegistrationInformationException("Invalid registration credentials (Student or Subject)");
        }

        log.info("Saving abscence: {}", abscence);
        return this.absencesRepository.save(abscence);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Absences update(Long id, Absences updatedAbsence){
        Absences existingAbsence = this.findById(id);
        if(updatedAbsence.getStudent().getId() == null || updatedAbsence.getSubject().getId() == null){
            throw new InvalidRegistrationInformationException("Invalid registration credentials (Student or Subject)");
        }

        existingAbsence.setDate(updatedAbsence.getDate());
        existingAbsence.setStudent(updatedAbsence.getStudent());
        existingAbsence.setSubject(updatedAbsence.getSubject());
        existingAbsence.setJustified(updatedAbsence.getJustified());

        log.info("Updating task id={} : {}", id, existingAbsence);
        return this.absencesRepository.save(existingAbsence);
    }

    @Transactional(readOnly = true)
    public Absences findById(Long id){
        return this.absencesRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Task with id= %s not found", id))
        );
    }

    @Transactional(readOnly = true)
    public Page<Absences> findAll(Pageable pageable) {
        return this.absencesRepository.findAll(pageable);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id){
        Absences task = this.findById(id);
        this.absencesRepository.delete(task);
        log.info("Deleting task id= {} : {}", id, task);
    }
}
