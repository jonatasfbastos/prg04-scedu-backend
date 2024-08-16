package br.com.ifba.scedu.domain.entities.curso.dto;

import br.com.ifba.scedu.domain.entities.curso.model.Curso;

public record CursoGetResponseDto(String curso, String modalidade, String turno) {
    public CursoGetResponseDto(Curso c){
        this(c.getCurso(), c.getModalidade(), c.getTurno());
    }

}
