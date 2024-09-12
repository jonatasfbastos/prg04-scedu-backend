package br.com.ifba.scedu.professor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifba.scedu.professor.model.Professor;

/**
 * @author Matheus Mendes
 * Repositório responsável por gerenciar as operações de acesso a dados de professores.
 */
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    /**
     * @param email E-mail do professor a ser verificado.
     * @return True se o e-mail existir, false caso contrário.
     * @author Matheus Mendes
     * Verifica se existe um professor com o e-mail informado.
     */
    boolean existsByEmail(String email);
    boolean existsBySiape(String siape);

}
