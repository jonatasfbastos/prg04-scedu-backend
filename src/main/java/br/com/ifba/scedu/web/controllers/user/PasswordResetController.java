package br.com.ifba.scedu.web.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifba.scedu.domain.entities.user.dto.EmailDTO;
import br.com.ifba.scedu.domain.entities.user.dto.NewPasswordDTO;
import br.com.ifba.scedu.domain.entities.user.service.PasswordResetService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/resetPassword")
public class PasswordResetController {
    @Autowired
    private PasswordResetService service;

    @PostMapping
    public ResponseEntity<String> sendResetPasswordEmail(@RequestBody EmailDTO email) {
        System.out.println("Received email: " + email.getEmail());

        this.service.sendPasswordResetEmail(email.getEmail());
        return ResponseEntity.ok("Password request link sent, please check your email.");

    }

    @PatchMapping
    public ResponseEntity<String> sendResetPasswordEmail(@RequestParam String token, @RequestBody NewPasswordDTO newPassword) {
        System.out.println("Received token: " + token);
        System.out.println("Received new password: " + newPassword.getNewPassword());

        this.service.resetPassword(token, newPassword.getNewPassword());
        return ResponseEntity.ok("Password updated");
    }
}
