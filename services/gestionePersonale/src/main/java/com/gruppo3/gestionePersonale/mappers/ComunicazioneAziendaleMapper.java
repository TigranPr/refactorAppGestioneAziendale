package com.gruppo3.gestionePersonale.mappers;

import com.gruppo3.gestionePersonale.dto.ComunicazioneAziendaleRequest;
import com.gruppo3.gestionePersonale.entity.ComunicazioneAziendale;
import com.gruppo3.gestionePersonale.services.UtenteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComunicazioneAziendaleMapper {
    @Autowired
    UtenteClient utenteClient;

    public ComunicazioneAziendale toEntity(Long id, ComunicazioneAziendaleRequest request) {
        var utente = utenteClient.getUtenteById(id);
        return ComunicazioneAziendale.builder()
                .testo(request.testo())
                .allegato_url(request.allegato_url())
                .dipendenteId(utente.idDipendente())
                .build();
    }

}