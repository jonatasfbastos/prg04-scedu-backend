package br.com.ifba.scedu.domain.entities.user.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewPasswordDTO {
    private String newPassword;
}
