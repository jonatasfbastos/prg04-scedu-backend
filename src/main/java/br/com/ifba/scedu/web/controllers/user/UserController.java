package br.com.ifba.scedu.web.controllers.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifba.scedu.domain.entities.user.dto.UserRequestDTO;
import br.com.ifba.scedu.domain.entities.user.dto.UserResponseDTO;
import br.com.ifba.scedu.domain.entities.user.exceptions.other.UserNotFoundByIdException;
import br.com.ifba.scedu.domain.entities.user.model.User;
import br.com.ifba.scedu.domain.entities.user.service.UserService;
import br.com.ifba.scedu.infrastructure.util.ObjectMapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/*
    TO DO: 
    Criar CRUD (findAll, findById, save, update, delete) // OK
    Adicionar DTOs // OK
    Adicionar ObjectMapperUtil (Mapeamento de classes) // OK
    Adicionar anotações de transações e validação de campos // OK
    Modificar atributos da entidade e nos DTOs (+nome, -login) // OK
    Criar exceptions personalizadas // OK
    Implementar segurança com Spring Security
*/ 

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ObjectMapperUtil objectMapperUtil;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(objectMapperUtil.mapAll(this.userService.findAll(), UserResponseDTO.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> findById(@PathVariable Long id) {
        try {
            User user = userService.findById(id);
            UserResponseDTO userResponseDTO = objectMapperUtil.map(user, UserResponseDTO.class);
            
            return ResponseEntity.status(HttpStatus.OK).body("User found: " + userResponseDTO.getName());
        } catch (UserNotFoundByIdException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found by id: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> save(@RequestBody @Valid UserRequestDTO data) {
        User createdUser = userService.save(data);
        UserResponseDTO responseDTO = objectMapperUtil.map(createdUser, UserResponseDTO.class);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody @Valid UserRequestDTO data) {
        User updatedUser = userService.update(id, data);
        UserResponseDTO responseDTO = objectMapperUtil.map(updatedUser, UserResponseDTO.class);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        userService.delete(id);
        
        return ResponseEntity.status(HttpStatus.OK).body("User with id: " + id + " was sucessfully deleted!");
    }
}