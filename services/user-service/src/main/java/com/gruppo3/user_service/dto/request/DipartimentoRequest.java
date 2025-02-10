package com.gruppo3.user_service.dto.request;

import jakarta.persistence.Column;
import lombok.Builder;

@Builder
public record DipartimentoRequest(
    @Column(nullable = false, unique = true)
    String nome,
    @Column(nullable = false)
    String descrizione
) {
}
