package br.com.ifba.scedu.domain.entities.professor.service;

import br.com.ifba.scedu.domain.entities.professor.model.Professor;
import br.com.ifba.scedu.domain.entities.professor.repository.ProfessorRepository;
import br.com.ifba.scedu.domain.entities.user.exceptions.other.UserEmailAlreadyExistsException;
import br.com.ifba.scedu.domain.entities.user.exceptions.other.UserNotFoundByIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService implements ProfessorIService {
    private final ProfessorRepository professorRepository;

    // Lista todos os professores
    @Override
    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    // Busca um professor por ID
    @Override
    public Professor findById(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundByIdException("Professor não encontrado pelo id: " + id));
    }

    // Salva um novo professor
    @Override
    @Transactional
    public Professor save(Professor professor) {
        // Verifica se o email já está em uso
        if (professorRepository.existsByEmail(professor.getEmail())) {
            throw new UserEmailAlreadyExistsException("Email já existente.");
        }
        // Criptografa a senha e salva
        professor.setPassword(BCrypt.hashpw(professor.getPassword(), BCrypt.gensalt()));
        return professorRepository.save(professor);
    }

    // Atualiza os dados de um professor existente
    @Override
    @Transactional
    public Professor update(Long id, Professor professor) {
        Professor existingProfessor = findById(id);

        if (professorRepository.existsByEmail(professor.getEmail()) && !existingProfessor.getEmail().equals(professor.getEmail())) {
            throw new UserEmailAlreadyExistsException("Email já existente.");
        }

        existingProfessor.setName(professor.getName());
        existingProfessor.setEmail(professor.getEmail());
        existingProfessor.setSiape(professor.getSiape());
        existingProfessor.setDepartamento(professor.getDepartamento());
        existingProfessor.setCargaHoraria(professor.getCargaHoraria());

        return professorRepository.save(existingProfessor);
    }

    // Deleta um professor pelo ID
    @Override
    @Transactional
    public void delete(Long id) {
        if (!professorRepository.existsById(id)) {
            throw new UserNotFoundByIdException("Professor não encontrado pelo id: " + id);
        }
        professorRepository.deleteById(id);
    }
}
