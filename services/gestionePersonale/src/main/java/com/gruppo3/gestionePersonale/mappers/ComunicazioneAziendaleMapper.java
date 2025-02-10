package com.gruppo3.gestionePersonale.mappers;

import com.gruppo3.gestionePersonale.dto.ComunicazioneAziendaleRequest;
import com.gruppo3.gestionePersonale.entity.ComunicazioneAziendale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComunicazioneAziendaleMapper {

    public ComunicazioneAziendale toEntity(Long id, ComunicazioneAziendaleRequest request) {
        return ComunicazioneAziendale.builder()
                .testo(request.testo())
                .allegato_url(request.allegato_url())
                .dipendenteId(id)
                .build();
    }

}