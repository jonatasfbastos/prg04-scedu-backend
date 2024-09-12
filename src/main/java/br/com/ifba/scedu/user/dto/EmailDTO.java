package br.com.ifba.scedu.user.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {
    // Campo que armazena o endereço de e-mail
    private String email;
}
