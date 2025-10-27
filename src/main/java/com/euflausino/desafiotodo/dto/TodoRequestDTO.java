package com.euflausino.desafiotodo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.NonNull;

public record TodoRequestDTO(
        @NotBlank(message = "{nome.obrigatorio}")
        String nome,

        String descricao,

        @NotNull(message = "{prioridade.obrigatorio}")
        Integer prioridade

) {
}
