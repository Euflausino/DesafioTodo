package com.euflausino.desafiotodo.dto;

public record TodoResponseDTO(

        Long id,

        String nome,

        String desc,

        boolean realizado,

        Integer prioridade

) {
}
