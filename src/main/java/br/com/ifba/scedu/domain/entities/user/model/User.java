package br.com.ifba.scedu.domain.entities.user.model;

import java.util.Collection;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class User extends PersistenceEntity implements UserDetails {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "passwordResetToken")
    private String passwordResetToken;

    private UserRoleEnum role;

    public User(UserRequestDTO data) {
        this.name = data.getName();
        this.email = data.getEmail();
        this.password = data.getPassword();
        this.role = data.getRole() != null ? data.getRole() : UserRoleEnum.USER;
    }

    public static User fromDTOWithEncryptedPassword(UserRequestDTO data) {
        User user = new User(data);
        user.setPassword(BCrypt.hashpw(data.getPassword(), BCrypt.gensalt()));
        
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRoleEnum.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}