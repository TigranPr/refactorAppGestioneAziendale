package com.gruppo3.gestionePersonale.dto;

import lombok.Builder;

@Builder
public record UpdateComunicazioneAziendaleRequest(
        String testo,
        String allegato_url,
        Long idDipendente
) {
}