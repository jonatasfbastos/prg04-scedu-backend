package br.com.ifba.scedu.web.controllers.gestaoTercerizado;

import br.com.ifba.scedu.domain.entities.gestaoTerceirizado.dto.GestaoTerceirizadoCreateDTO;
import br.com.ifba.scedu.domain.entities.gestaoTerceirizado.dto.GestaoTerceirizadoUpdateDTO;
import br.com.ifba.scedu.domain.entities.gestaoTerceirizado.dto.GestaoTerceirizadoViewDTO;
import br.com.ifba.scedu.domain.entities.gestaoTerceirizado.service.GestaoTerceirizadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gestaoTerceirizados")
public class GestaoTerceirizadoController {

    @Autowired
    private GestaoTerceirizadoService service;

    // Retorna todos os registros de GestaoTerceirizado
    @GetMapping
    public List<GestaoTerceirizadoViewDTO> findAll() {
        return service.findAll();
    }

    // Retorna um registro específico de GestaoTerceirizado pelo ID
    @GetMapping("/id/{id}")
    public ResponseEntity<GestaoTerceirizadoViewDTO> findById(@PathVariable Long id) {
        GestaoTerceirizadoViewDTO dto = service.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // Busca registros de GestaoTerceirizado pelo nome (case insensitive)
    @GetMapping("/name/{name}")
    public ResponseEntity<List<GestaoTerceirizadoViewDTO>> findByName(@PathVariable String name) {
        List<GestaoTerceirizadoViewDTO> dtoList = service.findByName(name);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    // Cria um novo registro de GestaoTerceirizado
    @PostMapping("/save")
    public ResponseEntity<GestaoTerceirizadoViewDTO> save(@RequestBody GestaoTerceirizadoCreateDTO saveDTO) {
        GestaoTerceirizadoViewDTO dto = service.save(saveDTO);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // Atualiza um registro existente de GestaoTerceirizado pelo ID
    @PutMapping("/edit/{id}")
    public ResponseEntity<GestaoTerceirizadoViewDTO> update(@PathVariable Long id, @RequestBody GestaoTerceirizadoUpdateDTO updateDTO) {
        GestaoTerceirizadoViewDTO dto = service.update(id, updateDTO);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // Deleta um registro específico de GestaoTerceirizado pelo ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
