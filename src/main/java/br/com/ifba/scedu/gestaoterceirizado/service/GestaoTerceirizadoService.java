package br.com.ifba.scedu.gestaoterceirizado.service;

import br.com.ifba.scedu.gestaoterceirizado.entities.GestaoTerceirizado;
import br.com.ifba.scedu.gestaoterceirizado.exception.GestaoTerceirizadoNotFoundException;
import br.com.ifba.scedu.gestaoterceirizado.entities.dtos.RequestDTO;
import br.com.ifba.scedu.gestaoterceirizado.repository.GestaoTerceirizadoRepository;
import br.com.ifba.scedu.person.model.Person;
import br.com.ifba.scedu.person.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Define que esta classe é um serviço, permitindo sua injeção em outras partes da aplicação
public class GestaoTerceirizadoService {

    @Autowired
    private GestaoTerceirizadoRepository terceirizadoRepository; // Repositório para acessar a base de dados de GestaoTerceirizado

    @Autowired
    private PersonRepository personRepository; // Repositório para acessar a base de dados de Person

    // Cria e salva um novo registro de GestaoTerceirizado
    public GestaoTerceirizado save(RequestDTO dto) {
        GestaoTerceirizado terceirizado = new GestaoTerceirizado(); // Cria uma nova instância de GestaoTerceirizado

        // Busca a pessoa associada ao ID fornecido e lança uma exceção se não encontrada
        Person person = personRepository.findById(dto.getIdPerson())
                .orElseThrow(() -> new GestaoTerceirizadoNotFoundException("Pessoa não encontrada com o ID: " + dto.getIdPerson()));

        // Define os valores no objeto GestaoTerceirizado com base nos dados do DTO
        terceirizado.setPerson(person);
        terceirizado.setPhone(dto.getPhone());
        terceirizado.setEmail(dto.getEmail());
        terceirizado.setPosition(dto.getPosition());
        terceirizado.setEnterprise(dto.getEnterprise());
        terceirizado.setDepartment(dto.getDepartment());
        terceirizado.setStatus(dto.isStatus());
        terceirizado.setObservations(dto.getObservations());

        // Salva o objeto GestaoTerceirizado no banco de dados e retorna o resultado
        return terceirizadoRepository.save(terceirizado);
    }

    // Atualiza um registro existente de GestaoTerceirizado
    public GestaoTerceirizado update(Long id, RequestDTO dto) {
        // Busca o GestaoTerceirizado pelo ID e lança uma exceção se não encontrado
        GestaoTerceirizado terceirizado = terceirizadoRepository.findById(id)
                .orElseThrow(() -> new GestaoTerceirizadoNotFoundException("Terceirizado not found"));

        // Atualiza os campos do GestaoTerceirizado com os dados do DTO
        terceirizado.setPosition(dto.getPosition());
        terceirizado.setEnterprise(dto.getEnterprise());
        terceirizado.setDepartment(dto.getDepartment());
        terceirizado.setStatus(dto.isStatus());
        terceirizado.setEmail(dto.getEmail());
        terceirizado.setPhone(dto.getPhone());
        terceirizado.setObservations(dto.getObservations());

        // Salva as alterações e retorna o objeto atualizado
        return terceirizadoRepository.save(terceirizado);
    }

    // Retorna todos os registros de GestaoTerceirizado
    public List<GestaoTerceirizado> findAll() {
        return terceirizadoRepository.findAll();
    }

    // Retorna um registro de GestaoTerceirizado pelo ID
    public GestaoTerceirizado findById(Long id) {
        // Busca o GestaoTerceirizado pelo ID e lança uma exceção se não encontrado
        return terceirizadoRepository.findById(id)
                .orElseThrow(() -> new GestaoTerceirizadoNotFoundException("Terceirizado not found"));
    }

    // Deleta um registro de GestaoTerceirizado pelo ID
    public void delete(Long id) {
        // Verifica se o GestaoTerceirizado existe, lança uma exceção se não encontrado
        GestaoTerceirizado entity = terceirizadoRepository.findById(id)
                .orElseThrow(() -> new GestaoTerceirizadoNotFoundException("Nenhum registro encontrado para este ID"));

        // Deleta o registro do banco de dados
        terceirizadoRepository.delete(entity);
    }
}
