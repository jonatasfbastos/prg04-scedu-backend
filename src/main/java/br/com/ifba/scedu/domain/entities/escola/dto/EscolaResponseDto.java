package br.com.ifba.scedu.domain.entities.escola.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EscolaResponseDto {

    private String nome;

    private String inep;

    private String localizacao;

    private String cep;

    private String bairro;

    private String logradouro;

    private String complemento;

    private String telefone;

    private String modalidade;

    private String nomeDiretor;

}
