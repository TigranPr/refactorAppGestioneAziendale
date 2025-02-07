package com.gruppo3.user_service.dto.request;

public record UpdatePosizioneLavorativaRequest(
        Long id,
        String nome,
        String descrizione,
        Long idDipartimento
) {
}
