package com.gruppo3.gestioneComunity.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCommentoRequest(
        @NotBlank(message = "Il testo non può essere blank o null")
        String testo,
        @NotNull(message = "News non puo essere null")
        EntityIdRequest news,
        @NotNull(message = "Dipendente non puo essere null")
        EntityIdRequest dipendente
) {
}
