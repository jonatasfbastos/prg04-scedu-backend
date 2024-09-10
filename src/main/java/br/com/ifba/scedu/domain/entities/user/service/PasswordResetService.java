package br.com.ifba.scedu.domain.entities.user.service;

import br.com.ifba.scedu.domain.entities.user.model.User;
import br.com.ifba.scedu.domain.entities.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor  // Gera um construtor com os campos finais obrigatórios (neste caso, mailSender e userRepo)
public class PasswordResetService {
    /*private final JavaMailSender mailSender;  // Injeção de dependência para envio de emails
    private final UserRepository userRepo;    // Injeção de dependência para o repositório de usuários

    // Método para enviar um token de redefinição de senha para o email fornecido
    public void sendResetPasswordToken(String email) {
        System.out.println(email);

        // Busca o usuário pelo email fornecido
        Optional<User> user = this.userRepo.findByEmail(email);

        // Se o usuário não for encontrado, lança uma exceção
        if(user.isEmpty()) {
            throw new EntityNotFoundException("Email not in DB");
        }

        // Gera um token aleatório para redefinição de senha
        String token = UUID.randomUUID().toString();
        user.get().setPasswordResetToken(token);  // Define o token no usuário
        this.userRepo.save(user.get());  // Salva o usuário com o token atualizado

        // URL para o link de redefinição de senha
        String url = "http://localhost:8080/resetPassword?token=" + token;

        try {
            // Criação da mensagem de email para enviar o token de redefinição
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);  // Define o destinatário do email
            message.setSubject("SCEDU - Token de redefinição de senha");  // Assunto do email
            message.setText("Clique no link ou copie o código no fim da URL: " + url);  // Corpo do email com a URL

            mailSender.send(message);  // Envia o email

        } catch(Exception e) {
            // Em caso de falha no envio do email, lança uma exceção
            throw new RuntimeException("Error Sending Email");
        }
    }

    // Método para criar uma nova senha a partir do token fornecido
    public void createNewPassword(String token, String newPassword) {
        System.out.println("Searching for token: " + token);

        // Busca o usuário pelo token de redefinição de senha
        var user = this.userRepo.findByPasswordResetToken(token);

        // Se o token não for encontrado, lança uma exceção
        if(user.isEmpty())
            throw new EntityNotFoundException("Token not valid");

        // Criptografa a nova senha fornecida
        String encryptedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

        // Atualiza a senha do usuário e remove o token de redefinição de senha
        user.get().setPassword(encryptedPassword);
        user.get().setPasswordResetToken(null);

        // Salva o usuário com a nova senha e token removido
        this.userRepo.save(user.get());
    }*/
}