package br.com.ifba.scedu.domain.entities.escola.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EscolaRequestDto {

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 100, message = "O nome não pode ter mais de 100 caracteres.")
    private String nome;

    @NotBlank(message = "O código do INEP é obrigatório.")
    @Size(max = 12, message = "O código do INEP não pode ter mais de 8 caracteres.")
    private String inep;

    @NotBlank(message = "A localização é obrigatória.")
    private String localizacao;

    @NotBlank(message = "O CEP é obrigatório.")
    @Size(max = 9, message = "O CEP não pode ter mais de 9 caracteres.")
    private String cep;

    @NotBlank(message = "O bairro é obrigatório.")
    private String bairro;

    @NotBlank(message = "O logradouro é obrigatório.")
    private String logradouro;

    private String complemento;//Opcional, sem validacao

    @NotBlank(message = "O telefone é obrigatório.")
    private String telefone;

    @NotBlank(message = "A modalidade é obrigatória.")
    private String modalidade;

    @NotBlank(message = "O nome do diretor é obrigatório.")
    private String nomeDiretor;

}
