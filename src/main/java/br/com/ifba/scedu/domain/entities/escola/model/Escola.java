package br.com.ifba.scedu.domain.entities.escola.model;

import br.com.ifba.scedu.infrastructure.persistenceentity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "escolas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Escola extends PersistenceEntity {

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "inep", nullable = false)
    private String inep;

    @Column(name = "localizacao", nullable = false)
    private String localizacao;

    @Column(name = "cep", nullable = false)
    private String cep;

    @Column(name = "bairro", nullable = false)
    private String bairro;

    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Column(name = "complemento", nullable = false)
    private String complemento;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Column(name = "modalidade", nullable = false)
    private String modalidade;

    @Column(name = "nomeDiretor", nullable = false)
    private String nomeDiretor;



/*Esta funcionalidade deve permitir a gestão das escolas.
 As funções disponíveis são: cadastrar, alterar, remover, pesquisar e listar escolas.
No cadastro deve-se informar o nome da escola, código do INEP, localização, CEP,
 bairro, logradouro, complemento, telefone, modalidade e nome do diretor,
 lista de séries, lista de alunos.*/
}
