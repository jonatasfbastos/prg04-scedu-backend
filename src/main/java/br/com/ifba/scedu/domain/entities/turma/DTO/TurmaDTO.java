package br.com.ifba.scedu.domain.entities.turma.DTO;

import br.com.ifba.scedu.domain.entities.turma.model.Turma;

public record TurmaDTO(String codigo, String escola, String nome, String serie, Integer anoLetivo, Integer numeroSala, String turno, int numeroMaximoAlunos) {
    public TurmaDTO(Turma turma) {
        this(turma.getCodigo(), turma.getEscola(), turma.getNome(), turma.getSerie(), turma.getAnoLetivo(), turma.getNumeroSala(), turma.getTurno(), turma.getNumeroMaximoAlunos());
    }
}