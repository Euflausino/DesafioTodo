package com.euflausino.desafiotodo.dto;

public record AtualizaTodoRequestDTO(

        String nome,

        String descricao,

        boolean realizado,

        Integer prioridade

) {
}
