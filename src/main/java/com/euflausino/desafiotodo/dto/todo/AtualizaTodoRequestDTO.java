package com.euflausino.desafiotodo.dto.todo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record AtualizaTodoRequestDTO(

        String nome,

        String descricao,

        boolean realizado,

        @Min(0)
        @Max(5)
        Integer prioridade

) {
}
