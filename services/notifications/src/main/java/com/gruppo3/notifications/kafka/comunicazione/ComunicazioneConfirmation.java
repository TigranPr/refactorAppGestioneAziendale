package com.gruppo3.notifications.kafka.comunicazione;

import lombok.Builder;

@Builder
public record ComunicazioneConfirmation(
        Long id,
        String testo,
        String allegatoUrl,
        Long idDipendente
) {
}

