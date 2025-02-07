package com.gruppo3.user_service.dto.request;

public record UpdateDipartimentoRequest(
        Long id,
        String nome,
        String descrizione
) {
}
