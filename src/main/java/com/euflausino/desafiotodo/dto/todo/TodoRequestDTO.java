package com.euflausino.desafiotodo.dto.todo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TodoRequestDTO(

        @NotBlank(message = "{nome.obrigatorio}")
        String nome,

        String descricao,

        @NotNull(message = "{prioridade.obrigatorio}")
        Integer prioridade

) {
}
