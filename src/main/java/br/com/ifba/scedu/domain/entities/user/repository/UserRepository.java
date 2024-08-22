package br.com.ifba.scedu.domain.entities.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.ifba.scedu.domain.entities.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<User> findByName(String name);
    boolean existsByName(String name);
    Optional<User> findByPasswordResetToken(String resetToken);

    // Métodos que retornam UserDetails
    UserDetails getByEmail(String email);
    UserDetails getByName(String name);
}
