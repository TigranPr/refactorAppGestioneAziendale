package com.gruppo3.gestioneComunity.mappers;

import com.gruppo3.gestioneComunity.dto.requests.CreateCommentoRequest;
import com.gruppo3.gestioneComunity.dto.response.UtenteResponse;
import com.gruppo3.gestioneComunity.entity.Commento;
import com.gruppo3.gestioneComunity.services.NewsService;
import com.gruppo3.gestioneComunity.services.UtenteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentoMapper {
    @Autowired
    private UtenteClient utenteClient;
    @Autowired
    private NewsService newsService;

    public Commento fromCreateCommentoRequest(CreateCommentoRequest request) {
        var utente = utenteClient.getUtenteById(request.IdDipendente());
        // Creazione dell'oggetto Commento basata sulla request
        return Commento.builder()
                .testo(request.testo()) // assegna il testo del commento
                .news(newsService.getById(request.news().id())) // associa la news
                .idDipendente(utente.id()) // associa il dipendente
                .build();
    }

}
