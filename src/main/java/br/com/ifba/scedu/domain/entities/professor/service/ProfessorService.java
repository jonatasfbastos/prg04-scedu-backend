package br.com.ifba.scedu.domain.entities.professor.service;

import br.com.ifba.scedu.domain.entities.professor.model.Professor;
import br.com.ifba.scedu.domain.entities.professor.repository.ProfessorRepository;
import br.com.ifba.scedu.domain.entities.user.exceptions.other.UserEmailAlreadyExistsException;
import br.com.ifba.scedu.domain.entities.user.exceptions.other.UserNotFoundByIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static br.com.ifba.scedu.infrastructure.util.BeanUtilsHelper.getNullPropertyNames;

/**
 * @author Matheus Mendes
 * Serviço responsável por gerenciar as operações relacionadas a professores.
 * Este serviço fornece métodos para salvar, atualizar, deletar e buscar professores.
 */
@Service
@RequiredArgsConstructor
public class ProfessorService implements ProfessorIService {
    private final ProfessorRepository professorRepository;

    /**
     * @author Matheus Mendes
     * Retorna uma lista de todos os professores cadastrados no sistema.
     *
     * @return Uma lista de objetos do tipo Professor.
     */
    @Override
    public List<Professor> findAll() {
        // Utiliza o método findAll do repositório para buscar todos os professores
        return professorRepository.findAll();
    }

    /**
     * @author Matheus Mendes
     * Busca um professor por ID.
     *
     * @param id o ID do professor a ser buscado
     * @return o professor encontrado
     * @throws UserNotFoundByIdException se o professor não for encontrado pelo ID
     */
    @Override
    public Professor findById(Long id) {
        // Busca o professor no repositório pelo ID
        return professorRepository.findById(id)
                // Se o professor não for encontrado, lança uma exceção personalizada
                .orElseThrow(() -> new UserNotFoundByIdException("Professor não encontrado pelo id: " + id));
    }
    /**
     * @author Matheus Mendes
     * Salva um novo professor no banco de dados.
     * Verifica se o email do professor já está em uso e lança uma exceção personalizada caso positivo.
     * Criptografa a senha do professor antes de salvar.
     *
     * @param professor o objeto Professor a ser salvo
     * @return o professor salvo
     * @throws UserEmailAlreadyExistsException se o email do professor já estiver em uso
     */
    @Override
    @Transactional
    public Professor save(Professor professor) {
        // Verifica se o email do professor já está em uso
        if (professorRepository.existsByEmail(professor.getEmail())) {
            // Lança uma exceção personalizada caso o email já esteja em uso
            throw new UserEmailAlreadyExistsException("Email já existente.");
        }

        // Criptografa a senha do professor antes de salvar
        professor.setPassword(BCrypt.hashpw(professor.getPassword(), BCrypt.gensalt()));

        // Salva o professor no banco de dados
        return professorRepository.save(professor);
    }

    /**
     * @author Matheus Mendes
     * Atualiza os dados de um professor existente.
     *
     * @param id o ID do professor a ser atualizado
     * @param professor o objeto Professor com os novos dados
     * @return o professor atualizado
     * @throws UserEmailAlreadyExistsException se o email do professor já estiver em uso
     */
    @Override
    @Transactional
    public Professor update(Long id, Professor professor) {
        // Busca o professor existente pelo ID
        Professor existingProfessor = findById(id);

        // Verifica se o email do professor já está em uso e é diferente do email atual
        if (professor.getEmail() != null && professorRepository.existsByEmail(professor.getEmail()) && !existingProfessor.getEmail().equals(professor.getEmail())) {
            throw new UserEmailAlreadyExistsException("Email já existente.");
        }
        // Copia as propriedades do objeto professor para o professor existente, ignorando propriedades nulas
        BeanUtils.copyProperties(professor, existingProfessor, getNullPropertyNames(professor));

        return professorRepository.save(existingProfessor);
    }
    /**
     * @author Matheus Mendes
     * Deleta um professor pelo ID.
     *
     * @param id o ID do professor a ser deletado
     * @throws UserNotFoundByIdException se o professor não for encontrado pelo ID
     */
    @Override
    @Transactional
    public void delete(Long id) {
        // Verifica se o professor existe no banco de dados
        if (!professorRepository.existsById(id)) {
            // Lança uma exceção personalizada caso o professor não seja encontrado
            throw new UserNotFoundByIdException("Professor não encontrado pelo id: " + id);
        }
        // Deleta o professor do banco de dados
        professorRepository.deleteById(id);
    }}
