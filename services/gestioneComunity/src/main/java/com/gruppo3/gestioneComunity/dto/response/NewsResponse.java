package com.gruppo3.gestioneComunity.dto.response;

import lombok.Builder;

@Builder
public record NewsResponse(
        Long id,
        String titolo,
        String testo,
        String image_url,
        String allegato,
        Long idPublisher
) {
}
