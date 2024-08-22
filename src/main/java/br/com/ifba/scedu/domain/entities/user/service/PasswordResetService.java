package br.com.ifba.scedu.domain.entities.user.service;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.ifba.scedu.domain.entities.user.model.User;
import br.com.ifba.scedu.domain.entities.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class PasswordResetService {
    private final JavaMailSender mailSender;

    private final UserRepository userRepo;

    public void sendPasswordResetEmail(String email) {
        System.out.println(email);

        Optional<User> user = this.userRepo.findByEmail(email);

        if(user.isEmpty()) {
            throw new EntityNotFoundException("Email not in DB");
        }

        String token = UUID.randomUUID().toString();
        user.get().setPasswordResetToken(token);
        this.userRepo.save(user.get());

        String url = "http://localhost:8080/resetPassword?token=" + token;

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("SCRUM MANAGER PASSWORD RESET LINK");
            message.setText("Click the link to reset your password: " + url);

            mailSender.send(message);

        } catch(Exception e) {
            throw new RuntimeException("Error Sending Email");
        }
    }

    public void resetPassword(String token, String newPassword) {
        System.out.println("Searching for token: " + token);
        var user = this.userRepo.findByPasswordResetToken(token);

        if(user.isEmpty())
            throw new EntityNotFoundException("Token not valid");

        String encryptedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

        user.get().setPassword(encryptedPassword);
        user.get().setPasswordResetToken(null);

        this.userRepo.save(user.get());
    }
}
