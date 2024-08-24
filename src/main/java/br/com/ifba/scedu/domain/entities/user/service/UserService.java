package br.com.ifba.scedu.domain.entities.user.service;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ifba.scedu.domain.entities.user.dto.UserLoginDTO;
import br.com.ifba.scedu.domain.entities.user.dto.UserRequestDTO;
import br.com.ifba.scedu.domain.entities.user.dto.UserUpdateDataDTO;
import br.com.ifba.scedu.domain.entities.user.exceptions.other.AuthenticationException;
import br.com.ifba.scedu.domain.entities.user.exceptions.other.NoUsersToListException;
import br.com.ifba.scedu.domain.entities.user.exceptions.other.UserEmailAlreadyExistsException;
import br.com.ifba.scedu.domain.entities.user.exceptions.other.UserNotFoundByIdException;
import br.com.ifba.scedu.domain.entities.user.model.User;
import br.com.ifba.scedu.domain.entities.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor  // Gera um construtor com os campos finais obrigatórios (neste caso, userRepository)
public class UserService {
    private final UserRepository userRepository;  // Injeção de dependência para o repositório de usuários

    // Método para buscar todos os usuários do banco de dados
    public List<User> findAll() {
        List<User> allUsers = userRepository.findAll();

        // Se não houver usuários, lança uma exceção personalizada
        if(allUsers.isEmpty())
            throw new NoUsersToListException("There are no users to list.");

        return allUsers;
    }

    // Método para buscar um usuário por ID
    public User findById(Long id) {
        // Busca o usuário e, se não encontrado, lança uma exceção personalizada
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundByIdException("Usuário de Id: " + id + " não foi encontrado!"));
    }

    // Método para realizar o login do usuário
    public Optional<User> login(UserLoginDTO data) { 
        // Busca o usuário pelo email
        Optional<User> userOptional = userRepository.findByEmail(data.getEmail()); 

        // Verifica se o usuário foi encontrado e se a senha está correta
        if(userOptional.isPresent()) { 
            User user = userOptional.get(); 
            if(!BCrypt.checkpw(data.getPassword(), user.getPassword())) 
                throw new AuthenticationException("Senha incorreta");
        } else
            throw new AuthenticationException("Usuário não encontrado"); 

        return userOptional;
    }

    // Método para salvar um novo usuário no banco de dados
    @Transactional
    public User save(UserRequestDTO data) {
        // Verifica se o email já está registrado
        if(userRepository.existsByEmail(data.getEmail()))
            throw new UserEmailAlreadyExistsException("Email already exists.");

        // Cria um novo usuário com a senha criptografada
        User newUser = User.fromDTOWithEncryptedPassword(data);

        // Salva o novo usuário no banco de dados
        return userRepository.save(newUser);
    }

    // Método para atualizar os dados de um usuário existente
    @Transactional
    public User update(Long id, UserUpdateDataDTO data) {
        // Busca o usuário pelo ID ou lança uma exceção se não for encontrado
        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundByIdException("User not found."));

        // Verifica se o novo email já está registrado por outro usuário
        if(userRepository.existsByEmail(data.getEmail()) && !existingUser.getEmail().equals(data.getEmail()))
            throw new UserEmailAlreadyExistsException("Email already exists.");
        // Verifica se o novo nome já está registrado por outro usuário
        else if(userRepository.existsByName(data.getName()) && !existingUser.getName().equals(data.getName()))
            throw new IllegalArgumentException("Name already exists.");

        // Atualiza o nome e o email do usuário
        existingUser.setName(data.getName());
        existingUser.setEmail(data.getEmail());

        // Salva as alterações no banco de dados
        return userRepository.save(existingUser);
    }

    // Método para deletar um usuário pelo ID
    public void delete(Long id) {
        // Verifica se o usuário existe no banco de dados
        if(!userRepository.existsById(id))
            throw new UserNotFoundByIdException("User not found.");

        // Deleta o usuário do banco de dados
        userRepository.deleteById(id);
    }
}