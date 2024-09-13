package br.com.ifba.scedu.gestaoterceirizado.service;

import br.com.ifba.scedu.gestaoterceirizado.entities.GestaoTerceirizado;
import br.com.ifba.scedu.gestaoterceirizado.exception.GestaoTerceirizadoNotFoundException;
import br.com.ifba.scedu.gestaoterceirizado.entities.dtos.RequestDTO;

import java.util.List;

/**
 * Interface para o serviço de gerenciamento de terceirizados.
 */
public interface GestaoTerceirizadosIService {
    /**
     * Cria um novo terceirizado.
     *
     * @param dto Dados do novo terceirizado.
     * @return O terceirizado criado.
     */
    GestaoTerceirizado save(RequestDTO dto);

    /**
     * Atualiza um terceirizado existente.
     *
     * @param id  ID do terceirizado a ser atualizado.
     * @param dto Dados atualizados do terceirizado.
     * @return O terceirizado atualizado.
     * @throws GestaoTerceirizadoNotFoundException Se o terceirizado não for encontrado.
     */
    GestaoTerceirizado update(Long id, RequestDTO dto) throws GestaoTerceirizadoNotFoundException;

    /**
     * Obtém todos os terceirizados.
     *
     * @return Lista de terceirizados.
     */
    List<GestaoTerceirizado> findAll();

    /**
     * Obtém um terceirizado pelo ID.
     *
     * @param id ID do terceirizado.
     * @return O terceirizado correspondente ao ID.
     * @throws GestaoTerceirizadoNotFoundException Se o terceirizado não for encontrado.
     */
    GestaoTerceirizado findById(Long id) throws GestaoTerceirizadoNotFoundException;

    /**
     * Deleta um terceirizado pelo ID.
     *
     * @param id ID do terceirizado a ser deletado.
     * @throws GestaoTerceirizadoNotFoundException Se o terceirizado não for encontrado.
     */
    void delete(Long id) throws GestaoTerceirizadoNotFoundException;
}
