package br.com.ifba.scedu.domain.entities.user.service;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.ifba.scedu.domain.entities.user.dto.UserLoginDTO;
import br.com.ifba.scedu.domain.entities.user.dto.UserRequestDTO;
import br.com.ifba.scedu.domain.entities.user.exceptions.other.AuthenticationException;
import br.com.ifba.scedu.domain.entities.user.exceptions.other.NoUsersToListException;
import br.com.ifba.scedu.domain.entities.user.exceptions.other.UserEmailAlreadyExistsException;
import br.com.ifba.scedu.domain.entities.user.exceptions.other.UserNotFoundByIdException;
import br.com.ifba.scedu.domain.entities.user.model.User;
import br.com.ifba.scedu.domain.entities.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll() {
        List<User> allUsers = userRepository.findAll();

        if(allUsers.isEmpty())
            throw new NoUsersToListException("There are no users to list.");

        return allUsers;
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundByIdException("Usuário de Id: " + id + " não foi encontrado!"));
    }

    public Optional<User> login(UserLoginDTO data) { 
        Optional<User> userOptional = userRepository.findByEmail(data.getEmail()); 

        if(userOptional.isPresent()) { 
            User user = userOptional.get(); 
            if(!BCrypt.checkpw(data.getPassword(), user.getPassword())) 
                throw new AuthenticationException("Senha incorreta");
        } else
            throw new AuthenticationException("Usuário não encontrado"); 

        return userOptional;
    }

    @Transactional
    public User save(UserRequestDTO data) {
        if(userRepository.existsByEmail(data.getEmail()))
            throw new UserEmailAlreadyExistsException("Email already exists.");

        User newUser = User.fromDTOWithEncryptedPassword(data);

        return userRepository.save(newUser);
    }

    @Transactional
    public User update(Long id, UserRequestDTO data) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundByIdException("User not found."));

        if(userRepository.existsByEmail(data.getEmail()) && !existingUser.getEmail().equals(data.getEmail()))
            throw new UserEmailAlreadyExistsException("Email already exists.");
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
            throw new UserNotFoundByIdException("User not found.");

        userRepository.deleteById(id);
    }
}