package br.com.ifba.scedu.web.controllers.user;

import br.com.ifba.scedu.domain.entities.user.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifba.scedu.domain.entities.user.dto.EmailDTO;
import br.com.ifba.scedu.domain.entities.user.dto.NewPasswordDTO;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/resetPassword")
public class PasswordResetController {
    @Autowired
    private PasswordResetService service;

    @PostMapping
    public ResponseEntity<String> sendResetPasswordToken(@RequestBody EmailDTO email) {
        System.out.println("Received email: " + email.getEmail());

        this.service.sendResetPasswordToken(email.getEmail());
        return ResponseEntity.ok("Password request link sent, please check your email.");

    }

    @PatchMapping
    public ResponseEntity<String> createNewPassword(@RequestBody NewPasswordDTO data) {
        System.out.println("Received token: " + data.getToken());
        System.out.println("Received new password: " + data.getNewPassword());

        this.service.createNewPassword(data.getToken(), data.getNewPassword());
        return ResponseEntity.ok("Password updated");
    }
}
