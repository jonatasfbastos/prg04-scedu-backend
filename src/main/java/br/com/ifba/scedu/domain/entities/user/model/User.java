package br.com.ifba.scedu.domain.entities.user.model;

import org.mindrot.jbcrypt.BCrypt;

import br.com.ifba.scedu.domain.entities.user.dto.UserRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    public User(UserRequestDTO data) {
        this.name = data.getName();
        this.email = data.getEmail();
        this.password = data.getPassword();
    }

    public static User fromDTOWithEncryptedPassword(UserRequestDTO data) {
        User user = new User(data);
        user.setPassword(BCrypt.hashpw(data.getPassword(), BCrypt.gensalt()));
        
        return user;
    }
}