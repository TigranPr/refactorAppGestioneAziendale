package com.gruppo3.user_service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record UtenteRequest(
        @NotBlank(message = "Il nome non può essere blank o null")
        String nome,
        @NotBlank(message = "Il cognome non può essere blank o null")
        String cognome,
        @Email(message = "Email non valida")
        String email,
        @NotBlank(message = "La password non può essere blank o null")
        String password,
        @NotNull(message = "Comune non puo essere null")
        Long comune,
        @Past
        LocalDate dataDiNascita,
        @NotBlank(message = "Il numero di telefono non può essere blank o null")
        String telefono,
        @NotBlank(message = "Problemi con l'Avatar")
        String avatar,
        @NotBlank(message = "Il ruolo non può essere blank o null")
        String ruolo,
        @NotNull
        Long idPosizioneLavorativa
) {
}
