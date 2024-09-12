package br.com.ifba.scedu.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ifba.scedu.person.model.Person;
import br.com.ifba.scedu.user.model.User;

public interface PersonRepository extends JpaRepository<Person, Long> {
    boolean existsByUser(User user);
    boolean existsByCpf(String cpf);
}
