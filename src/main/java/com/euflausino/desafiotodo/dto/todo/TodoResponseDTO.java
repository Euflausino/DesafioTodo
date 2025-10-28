package com.euflausino.desafiotodo.dto.todo;

public record TodoResponseDTO(

        Long id,

        String nome,

        String descricao,

        boolean realizado,

        Integer prioridade

) {
}
