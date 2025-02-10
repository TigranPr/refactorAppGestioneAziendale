package com.gruppo3.user_service.dto.request;

import jakarta.validation.constraints.Past;
import java.time.LocalDate;

public record UpdateUtenteRequest(
        String nome,
        String cognome,
        String email,
        Long comune,
        @Past
        LocalDate dataDiNascita,
        String telefono,
        String avatar,
        String ruolo
) {
}
