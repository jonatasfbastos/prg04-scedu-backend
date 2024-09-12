package br.com.ifba.scedu.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifba.scedu.user.dto.EmailDTO;
import br.com.ifba.scedu.user.dto.NewPasswordDTO;
import br.com.ifba.scedu.user.service.PasswordResetService;

@RestController // Indica que esta classe é um controlador REST, onde cada método retorna um objeto que é serializado para JSON automaticamente.
@CrossOrigin(origins = "*") // Permite que qualquer origem faça requisições a este controlador (CORS).
@RequestMapping("/resetPassword") // Define o caminho base para todas as rotas neste controlador.
public class PasswordResetController {

    /*@Autowired // Injeta automaticamente uma instância do serviço de redefinição de senha.
    private PasswordResetService service;

    @PostMapping // Mapeia requisições HTTP POST para o caminho "/resetPassword".
    public ResponseEntity<String> sendResetPasswordToken(@RequestBody EmailDTO email) {
        System.out.println("Received email: " + email.getEmail());

        this.service.sendResetPasswordToken(email.getEmail());

        return ResponseEntity.ok("Password request link sent, please check your email.");
    }

    @PatchMapping // Mapeia requisições HTTP PATCH para o caminho "/resetPassword".
    public ResponseEntity<String> createNewPassword(@RequestBody NewPasswordDTO data) {
        System.out.println("Received token: " + data.getToken());
        System.out.println("Received new password: " + data.getNewPassword());

        this.service.createNewPassword(data.getToken(), data.getNewPassword());

        // Retorna uma resposta de sucesso indicando que a senha foi atualizada.
        return ResponseEntity.ok("Password updated");
    }*/
}
