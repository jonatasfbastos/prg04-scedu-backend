package br.com.ifba.scedu.domain.entities.user.dto;

// Campo que armazena o endereço de e-mail e a senha de usuário 
public record LoginRequestDTO(String email, String password) {
    
}
