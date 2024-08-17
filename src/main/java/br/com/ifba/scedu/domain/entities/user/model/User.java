package br.com.ifba.scedu.domain.entities.user.model;

import org.mindrot.jbcrypt.BCrypt;

import br.com.ifba.scedu.domain.entities.user.dto.UserRequestDTO;
import br.com.ifba.scedu.infrastructure.persistenceentity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class User extends PersistenceEntity {
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