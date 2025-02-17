package com.gruppo3.gestioneComunity.mappers;

import com.gruppo3.gestioneComunity.dto.requests.CreateNewsRequest;
import com.gruppo3.gestioneComunity.entity.News;
import com.gruppo3.gestioneComunity.exceptions.MyEntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsMapper {

    @Autowired
    private Long dipendenteRepository;

    public News fromCreateNewsRequest(CreateNewsRequest request) throws MyEntityNotFoundException {
        Long dipendente = dipendenteRepository
                .findById(request.idPublisher())
                .orElseThrow(() -> new IllegalArgumentException("Dipartimento con ID : " + request.idPublisher() +
                        " non trovato"));

        return News.builder()
                .titolo(request.titolo())
                .testo(request.testo())
                .image_url(request.image_url())
                .allegato_url(request.allegato())
                .idPublisher(dipendente)
                .build();
    }
}
