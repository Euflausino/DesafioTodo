package com.euflausino.desafiotodo.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(

        @NotBlank
        String login,
        @NotBlank
        String senha
) {
}
