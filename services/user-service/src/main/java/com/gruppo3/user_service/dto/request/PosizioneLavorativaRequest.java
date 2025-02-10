package com.gruppo3.user_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PosizioneLavorativaRequest (
        @NotBlank(message = "Il nome non può essere blank o null")
        String nome,
        @NotBlank(message = "La descrizione non può essere blank o null")
        String descrizione,
        @NotNull(message = "Il dipartimento associato non può essere null")
        Long idDipartimento
){
}
