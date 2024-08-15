package br.com.ifba.scedu.domain.entities.usuario.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ifba.scedu.domain.entities.usuario.dto.UserRequestDTO;
import br.com.ifba.scedu.domain.entities.usuario.model.User;
import br.com.ifba.scedu.domain.entities.usuario.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User save(UserRequestDTO data) {
        if(userRepository.existsByEmail(data.getEmail()))
            throw new IllegalArgumentException("Email already exists.");
        if(userRepository.existsByLogin(data.getLogin()))
            throw new IllegalArgumentException("Login already exists.");

            User newUser = User.fromDTOWithEncryptedPassword(data);

        return userRepository.save(newUser);
    }

    public User update(Long id, UserRequestDTO data) {
        return userRepository.findById(id).map(user -> {
            user.setLogin(data.getLogin());
            user.setEmail(data.getEmail());
            user.setPassword(data.getPassword());

            return userRepository.save(user);
        }).orElseThrow(() -> new IllegalArgumentException("User not found."));
    }

    public void delete(Long id) {
        if(!userRepository.existsById(id))
            throw new IllegalArgumentException("User not found.");

        userRepository.deleteById(id);
    }
}