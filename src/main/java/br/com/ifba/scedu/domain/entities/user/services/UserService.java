package br.com.ifba.scedu.domain.entities.user.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ifba.scedu.domain.entities.user.dto.UserRequestDTO;
import br.com.ifba.scedu.domain.entities.user.model.User;
import br.com.ifba.scedu.domain.entities.user.repositories.UserRepository;
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

    @Transactional
    public User save(UserRequestDTO data) {
        if(userRepository.existsByEmail(data.getEmail()))
            throw new IllegalArgumentException("Email already exists.");
        if(userRepository.existsByName(data.getName()))
            throw new IllegalArgumentException("Name already exists.");

            User newUser = User.fromDTOWithEncryptedPassword(data);

        return userRepository.save(newUser);
    }

    @Transactional
    public User update(Long id, UserRequestDTO data) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        if(userRepository.existsByEmail(data.getEmail()) && !existingUser.getEmail().equals(data.getEmail()))
            throw new IllegalArgumentException("Email already exists.");
        else if(userRepository.existsByName(data.getName()) && !existingUser.getName().equals(data.getName()))
            throw new IllegalArgumentException("Name already exists.");

        existingUser.setName(data.getName());
        existingUser.setEmail(data.getEmail());

        if (data.getPassword() != null && !data.getPassword().isEmpty())
            existingUser.setPassword(BCrypt.hashpw(data.getPassword(), BCrypt.gensalt()));

        return userRepository.save(existingUser);
    }

    public void delete(Long id) {
        if(!userRepository.existsById(id))
            throw new IllegalArgumentException("User not found.");

        userRepository.deleteById(id);
    }
}