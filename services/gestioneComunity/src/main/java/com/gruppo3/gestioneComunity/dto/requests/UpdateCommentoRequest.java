package com.gruppo3.gestioneComunity.dto.requests;

public record UpdateCommentoRequest(
        String testo,
        EntityIdRequest news,
        EntityIdRequest dipendente
) {
}

