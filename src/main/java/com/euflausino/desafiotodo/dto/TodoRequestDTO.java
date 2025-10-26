package com.euflausino.desafiotodo.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.NonNull;

public record TodoRequestDTO(
        @NotBlank
        String name,

        String descricao,

        @NonNull
        Integer prioridade

) {
}
