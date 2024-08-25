package br.com.ifba.scedu.domain.entities.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // Método para salvar um novo usuário no banco de dados
    @Transactional
    public User save(User user) {
        // Verifica se o email já está registrado
        if(userRepository.existsByEmail(user.getEmail()))
            throw new UserEmailAlreadyExistsException("Email already exists.");

        // Cria um novo usuário com a senha criptografada
        User newUser = User.fromDTOWithEncryptedPassword(user);

        // Salva o novo usuário no banco de dados
        return userRepository.save(newUser);
    }

    // Método para atualizar os dados de um usuário existente
    @Transactional
    public User update(Long id, User user) {
        // Busca o usuário pelo ID ou lança uma exceção se não for encontrado
        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundByIdException("User not found."));

        // Verifica se o novo email já está registrado por outro usuário
        if(userRepository.existsByEmail(user.getEmail()) && !existingUser.getEmail().equals(user.getEmail()))
            throw new UserEmailAlreadyExistsException("Email already exists.");
        // Verifica se o novo nome já está registrado por outro usuário
        else if(userRepository.existsByName(user.getName()) && !existingUser.getName().equals(user.getName()))
            throw new IllegalArgumentException("Name already exists.");

        // Atualiza o nome e o email do usuário
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());

        // Salva as alterações no banco de dados
        return userRepository.save(existingUser);
    }

    // Método para deletar um usuário pelo ID
    @Transactional
    public void delete(Long id) {
        // Verifica se o usuário existe no banco de dados
        if(!userRepository.existsById(id))
            throw new UserNotFoundByIdException("User not found.");

        // Deleta o usuário do banco de dados
        userRepository.deleteById(id);
    }
}