package br.com.ifba.scedu.user.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewPasswordDTO {
    // Campo que armazena o token de login
    private String token;
    // Campo que armazena a senha
    private String newPassword;
}
