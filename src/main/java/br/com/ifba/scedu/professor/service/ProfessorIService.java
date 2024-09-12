package br.com.ifba.scedu.professor.service;

import org.springframework.transaction.annotation.Transactional;

import br.com.ifba.scedu.professor.model.Professor;

import java.util.List;

/**
 * @author Matheus Mendes
 * Interface de serviço para gerenciamento de professores.
 */
public interface ProfessorIService {

    /**
     * @author Matheus Mendes
     * Retorna uma lista de todos os professores cadastrados no sistema.
     *
     * @return Uma lista de objetos do tipo Professor.
     */
    List<Professor> findAll();

    /**
     * @author Matheus Mendes
     * Busca um professor por ID.
     *
     * @param id o ID do professor a ser buscado
     * @return o professor encontrado
     */
    Professor findById(Long id);

    /**
     * @author Matheus Mendes
     * Salva um novo professor no banco de dados.
     *
     * @param professor o objeto Professor a ser salvo
     * @return o professor salvo
     */
    @Transactional
    Professor save(Professor professor);

    /**
     * @author Matheus Mendes
     * Atualiza os dados de um professor existente.
     *
     * @param id o ID do professor a ser atualizado
     * @param professor o objeto Professor com os novos dados
     * @return o professor atualizado
     */
    @Transactional
    Professor update(Long id, Professor professor);

    /**
     * @author Matheus Mendes
     * Deleta um professor pelo ID.
     *
     * @param id o ID do professor a ser deletado
     */
    @Transactional
    void delete(Long id);
}
