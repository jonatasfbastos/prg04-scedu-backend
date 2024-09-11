/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.scedu.grade.repository;

import br.com.ifba.scedu.grade.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para a entidade 'Série'.
 * 
 * Esta interface estende JpaRepository, fornecendo métodos prontos para realizar operações CRUD básicas
 * e consultas personalizadas na tabela 'grades' do banco de dados.
 * 
 * JpaRepository fornece métodos como save(), findById(), findAll(), deleteById() e muitos outros
 * sem a necessidade de implementação adicional.
 * 
 * A anotação @Repository indica que esta interface é um repositório Spring, o que permite a injeção de dependência
 * e a integração com o contexto do Spring.
 * 
 * @author lara
 */
@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    // Métodos para manipulação da entidade 'Série' são fornecidos pelo JpaRepository.
}

