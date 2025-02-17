package com.gruppo3.gestionePersonale.kafka;

import lombok.Builder;

@Builder
public record ComunicazioneConfirmation(
        Long id,
        String testo,
        String allegatoUrl,
        Long idDipendente
) {
}
