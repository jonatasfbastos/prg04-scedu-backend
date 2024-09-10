package br.com.ifba.scedu.domain.entities.person.service;

import org.springframework.stereotype.Service;

import br.com.ifba.scedu.domain.entities.person.model.Person;
import br.com.ifba.scedu.domain.entities.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public Person save(Person person) {
        // Verificando se o usuário já tem uma pessoa associada
        if(personRepository.existsByUser(person.getUser()))
            throw new RuntimeException("This token already has a user with a person related to!");
        else if(personRepository.existsByCpf(person.getCpf()))
            throw new RuntimeException("This CPF already exists!");

        return personRepository.save(person);
    }

    public Person update(Long id, Person person) {
        Person existingPerson = findById(id);
        existingPerson.setName(person.getName());
        existingPerson.setCpf(person.getCpf());
        existingPerson.setStreet(person.getStreet());
        existingPerson.setArea(person.getArea());
        existingPerson.setState(person.getState());
        existingPerson.setCity(person.getCity());
        return personRepository.save(existingPerson);
    }

    public Person findById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found!"));
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public void delete(Long id) {
        Person person = findById(id);
        personRepository.delete(person);
    }
}