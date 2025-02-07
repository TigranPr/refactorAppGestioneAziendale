package com.gruppo3.user_service.dto.request;

import jakarta.persistence.Column;

public record ComuneRequest (
        @Column(nullable = false, unique = true)
        String nome,
        @Column(nullable = false)
        String regione
){
}
