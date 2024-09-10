package br.com.ifba.scedu.domain.entities.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.ifba.scedu.domain.entities.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Método para encontrar um usuário pelo email. 
    // Retorna um Optional<User>, o que permite lidar com a possibilidade de não encontrar o usuário.
    Optional<User> findByEmail(String email);

    // Verifica se existe um usuário no banco de dados com o email especificado.
    // Retorna um booleano: true se existir, false caso contrário.
    boolean existsByEmail(String email);

    // Método para encontrar um usuário pelo nome.
    // Também retorna um Optional<User>.
    Optional<User> findByUsername(String username);

    // Verifica se existe um usuário no banco de dados com o nome especificado.
    // Retorna true se existir, false caso contrário.
    boolean existsByUsername(String username);

    // Método para encontrar um usuário pelo token de redefinição de senha.
    // O token é usado em processos de recuperação de senha.
    Optional<User> findByPasswordResetToken(String resetToken);

    // Método adicional para obter UserDetails (interface usada pelo Spring Security)
    // a partir do email do usuário.
    UserDetails getByEmail(String email);
}