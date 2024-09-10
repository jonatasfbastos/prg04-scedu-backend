package br.com.ifba.scedu.domain.entities.user.model;

import java.util.Collection;
import java.util.List;

import jakarta.persistence.*;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.ifba.scedu.infrastructure.persistenceentity.PersistenceEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Anotações @Entity e @Table indicam que esta classe é uma entidade JPA
// e será mapeada para a tabela "users" no banco de dados.
@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Adiciona uma nova tabela no banco de dados para as classes que herdam de User
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends PersistenceEntity implements UserDetails {
    // Coluna "name" no banco de dados. O atributo "nullable = false" indica que este campo não pode ser nulo.
    @Column(name = "username", nullable = false)
    private String username;

    // Coluna "email" no banco de dados, também não pode ser nulo.
    @Column(name = "email", nullable = false)
    private String email;

    // Coluna "password" no banco de dados, que armazena a senha do usuário. Este campo é obrigatório.
    @Column(name = "password", nullable = false)
    private String password;

    // Coluna "passwordResetToken" que pode ser usada para armazenar um token temporário para redefinição de senha.
    @Column(name = "passwordResetToken")
    private String passwordResetToken;

    // Enumeração que indica o papel (role) do usuário, como ADMIN ou USER.
    private UserRoleEnum role;

    // Construtor que cria um objeto User a partir de um DTO de requisição.
    // Se o papel do usuário não for fornecido, ele assume o valor padrão de USER.
    public User(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole() != null ? user.getRole() : UserRoleEnum.USER;
    }

    // Método de fábrica (factory method) que cria um User a partir de um DTO,
    // criptografando a senha antes de definir o campo password.
    public static User fromDTOWithEncryptedPassword(User user) {
        user = new User(user);

        // BCrypt é uma classe usada para criptografar a senha do usuário.
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

        return user;
    }

    // Implementação do método getAuthorities() da interface UserDetails.
    // Retorna as autoridades (permissões) do usuário baseado em seu papel.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRoleEnum.ADMIN) 
            return List.of(
                new SimpleGrantedAuthority("ROLE_ADMIN"), 
                new SimpleGrantedAuthority("ROLE_USER")
            );
        else 
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    // Implementação do método getUsername() da interface UserDetails.
    // Retorna o nome do usuário.
    @Override
    public String getUsername() {
        return username;
    }

    // Os métodos abaixo são parte da interface UserDetails.
    // Eles controlam se a conta do usuário está expirada, bloqueada,
    // se as credenciais (senha) estão expiradas e se a conta está habilitada.

    @Override
    public boolean isAccountNonExpired() {
        return true; // Retorna true, indicando que a conta não está expirada.
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Retorna true, indicando que a conta não está bloqueada.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Retorna true, indicando que as credenciais não estão expiradas.
    }

    @Override
    public boolean isEnabled() {
        return true; // Retorna true, indicando que a conta está habilitada.
    }
}