package com.gruppo3.gestioneComunity.mappers;

import com.gruppo3.gestioneComunity.dto.requests.CreateNewsRequest;
import com.gruppo3.gestioneComunity.entity.News;
import com.gruppo3.gestioneComunity.exceptions.MyEntityNotFoundException;
import com.gruppo3.gestioneComunity.services.UtenteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsMapper {

    @Autowired
    private UtenteClient utenteClient;

    public News fromCreateNewsRequest(CreateNewsRequest request)
            throws MyEntityNotFoundException {
        Long utente = utenteClient.getUtenteById(request.idPublisher()).id();

        return News.builder()
                .titolo(request.titolo())
                .testo(request.testo())
                .image_url(request.image_url())
                .allegato_url(request.allegato())
                .idPublisher(utente)
                .build();
    }
}
