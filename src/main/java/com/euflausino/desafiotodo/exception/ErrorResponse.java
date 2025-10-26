package com.euflausino.desafiotodo.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record ErrorResponse(

        String mensagem,
        int status,
        @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
        LocalDateTime timeStamp

) {
    public ErrorResponse(String message, int status) {
        this(message, status, LocalDateTime.now());
    }
}
