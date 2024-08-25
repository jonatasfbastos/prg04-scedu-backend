package br.com.ifba.scedu.web.controllers.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifba.scedu.domain.entities.user.dto.LoginRequestDTO;
import br.com.ifba.scedu.domain.entities.user.dto.LoginResponseDTO;
import br.com.ifba.scedu.domain.entities.user.dto.UserRequestDTO;
import br.com.ifba.scedu.domain.entities.user.dto.UserResponseDTO;
import br.com.ifba.scedu.domain.entities.user.dto.UserUpdateDataDTO;
import br.com.ifba.scedu.domain.entities.user.exceptions.other.UserNotFoundByIdException;
import br.com.ifba.scedu.domain.entities.user.model.User;
import br.com.ifba.scedu.domain.entities.user.service.UserService;
import br.com.ifba.scedu.infrastructure.config.TokenService;
import br.com.ifba.scedu.infrastructure.util.ObjectMapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController // Indica que esta classe é um controlador REST, onde cada método retorna um objeto que é serializado para JSON automaticamente.
@RequestMapping("/user") // Define o caminho base para todas as rotas neste controlador.
@CrossOrigin(origins = "*") // Permite que qualquer origem faça requisições a este controlador (CORS).
@RequiredArgsConstructor // Gera um construtor com os campos final automaticamente (do Lombok).
public class UserController {

    // Declaração dos serviços e utilitários usados no controlador, todos injetados via construtor.
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final ObjectMapperUtil objectMapperUtil;
    private final TokenService tokenService;

    @GetMapping // Mapeia requisições HTTP GET para o caminho "/user"
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.mapAll(this.userService.findAll(), UserResponseDTO.class));
    }

    @GetMapping("/{id}") // Mapeia requisições HTTP GET para o caminho "/user/{id}"
    public ResponseEntity<String> findById(@PathVariable Long id) {
        try {
            User user = userService.findById(id);
            UserResponseDTO userResponseDTO = objectMapperUtil.map(user, UserResponseDTO.class);
            
            return ResponseEntity.status(HttpStatus.OK).body("User found: " + userResponseDTO.getName());
        } catch (UserNotFoundByIdException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found by id: " + id);
        }
    }

    // Faz a validação de login do usuário no próprio Controller
    @PostMapping("/auth/login") // Mapeia requisições HTTP POST para "/user/auth/login"
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/auth") // Mapeia requisições HTTP POST para "/user/auth"
    public ResponseEntity<UserResponseDTO> save(@RequestBody @Valid UserRequestDTO data) {
        User createUser = objectMapperUtil.map(data, User.class);
        User user = userService.save(createUser);
        UserResponseDTO responseDTO = objectMapperUtil.map(user, UserResponseDTO.class);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}") // Mapeia requisições HTTP PUT para "/user/{id}"
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody @Valid UserUpdateDataDTO data) {
        User updatedUser = objectMapperUtil.map(data, User.class);
        User user = userService.update(id, updatedUser);
        UserResponseDTO responseDTO = objectMapperUtil.map(user, UserResponseDTO.class);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}") // Mapeia requisições HTTP DELETE para "/user/{id}"
    public ResponseEntity<String> delete(@PathVariable Long id) {
        userService.delete(id);
        
        return ResponseEntity.status(HttpStatus.OK).body("User with id: " + id + " was successfully deleted!");
    }
}