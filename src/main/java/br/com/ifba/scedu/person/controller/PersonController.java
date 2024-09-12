package br.com.ifba.scedu.person.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.com.ifba.scedu.infrastructure.util.ObjectMapperUtil;
import br.com.ifba.scedu.person.dto.PersonRequestDTO;
import br.com.ifba.scedu.person.dto.PersonResponseDTO;
import br.com.ifba.scedu.person.model.Person;
import br.com.ifba.scedu.person.service.PersonService;
import br.com.ifba.scedu.user.model.User;
import br.com.ifba.scedu.user.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/* TO DO
 * 
 * Fazer as requisições pro CRUD (conferir se a pessoa está se relacionando com o User)
 * Adicionar exceptions de Person
 * Commitar o CRUD
 * 
 */

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;
    private final UserService userService;
    private final ObjectMapperUtil objectMapperUtil;

    @PostMapping
    public ResponseEntity<PersonResponseDTO> save(@RequestBody PersonRequestDTO personRequestDTO) {
        // Obtendo o username (ou email) do usuário logado a partir do SecurityContext
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Tentando buscar o usuário pelo username
        User user = userService.findByUsername(username);
        
        // Verificação: se o usuário não existir, lança uma exceção
        if (user == null) {
            // Lançar uma exceção personalizada ou retornar uma resposta de erro
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado. Não é possível criar uma pessoa sem um usuário associado.");
        }

        // Criando a pessoa e associando o User sem precisar passar o userId
        Person person = objectMapperUtil.map(personRequestDTO, Person.class);
        person.setUser(user);  // Associando o usuário à pessoa

        // Salvando a pessoa
        Person savedPerson = personService.save(person);

        // Convertendo para DTO de resposta
        PersonResponseDTO responseDTO = objectMapperUtil.map(savedPerson, PersonResponseDTO.class);

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResponseDTO> update(@PathVariable Long id, @RequestBody PersonRequestDTO personRequestDTO) {
        // Mapeando o DTO para a entidade
        Person person = objectMapperUtil.map(personRequestDTO, Person.class);
        
        // Atualizando a pessoa no serviço
        Person updatedPerson = personService.update(id, person);
        
        // Mapeando a entidade para o DTO de resposta
        PersonResponseDTO responseDTO = objectMapperUtil.map(updatedPerson, PersonResponseDTO.class);
        
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponseDTO> findById(@PathVariable Long id) {
        // Buscando a pessoa no serviço
        Person person = personService.findById(id);
        
        // Mapeando a entidade para o DTO de resposta
        PersonResponseDTO responseDTO = objectMapperUtil.map(person, PersonResponseDTO.class);
        
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<PersonResponseDTO>> findAll() {
        // Buscando todas as pessoas no serviço
        List<Person> people = personService.findAll();
        
        // Mapeando a lista de entidades para uma lista de DTOs de resposta
        List<PersonResponseDTO> responseDTOs = people.stream()
                .map(person -> objectMapperUtil.map(person, PersonResponseDTO.class))
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responseDTOs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // Deletando a pessoa no serviço
        personService.delete(id);
        
        return ResponseEntity.noContent().build();
    }
}
