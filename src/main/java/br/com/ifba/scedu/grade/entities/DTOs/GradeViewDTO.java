/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package br.com.ifba.scedu.grade.entities.DTOs;

import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) para a entidade 'Série'.
 * 
 * Este DTO é utilizado para transferir dados relacionados a uma 'Série' entre camadas da aplicação, como entre o controlador e o serviço.
 * Ele define os campos que podem ser enviados e recebidos via API, incluindo validações para garantir que os dados necessários sejam fornecidos.
 * 
 * @param id O identificador único da Série
 * @param code O código da Série (não nulo)
 * @param name O nome da Série (não nulo)
 * @param curriculumCode O código do currículo da Série (não nulo)
 * @param courseCode O código do curso associado à Série (não nulo)
 * 
 * @author lara
 */
public record GradeViewDTO(
        Long id, // Identificador único da Série
        @NotNull(message="Código da Série não pode ser nulo") 
        String code, // Código da Série, não pode ser nulo

        @NotNull(message="Nome da Série não pode ser nulo") 
        String name, // Nome da Série, não pode ser nulo

        @NotNull(message="Código do currículo da Série não pode ser nulo") 
        String curriculumCode, // Código do currículo da Série, não pode ser nulo

        @NotNull(message="Código do curso não pode ser nulo") 
        String courseCode) // Código do curso associado à Série, não pode ser nulo
{
}

