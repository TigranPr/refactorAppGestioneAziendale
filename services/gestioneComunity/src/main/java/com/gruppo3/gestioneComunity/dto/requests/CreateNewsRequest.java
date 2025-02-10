package com.gruppo3.gestioneComunity.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateNewsRequest (
        @NotBlank(message = "il titolo non può essere vuoto")
        String titolo,
        @NotBlank(message = "il testo non può essere vuoto")
        String testo,
        @NotNull
        String image_url,
        @NotNull
        String allegato,
        @NotNull
        Long idPublisher
){
}