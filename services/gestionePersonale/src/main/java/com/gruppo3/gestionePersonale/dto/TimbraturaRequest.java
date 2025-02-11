package com.gruppo3.gestionePersonale.dto;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

public record TimbraturaRequest(
        LocalDateTime ingresso,
        LocalDateTime uscita,
        LocalDateTime inizioPausa,
        LocalDateTime finePausa,
        @NotEmpty
        Long idDipendente
) {
}
