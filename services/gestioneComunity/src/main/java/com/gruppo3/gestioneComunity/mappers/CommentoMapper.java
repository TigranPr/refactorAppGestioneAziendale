package com.gruppo3.gestioneComunity.mappers;

import com.gruppo3.gestioneComunity.dto.requests.CreateCommentoRequest;
import com.gruppo3.gestioneComunity.entity.Commento;
import com.gruppo3.gestioneComunity.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentoMapper {
    @Autowired
    private Long dipendenteService;
    @Autowired
    private NewsService newsService;

    public Commento fromCreateCommentoRequest(CreateCommentoRequest request) {
        return Commento.builder()
                .testo(request.testo())
                .news(newsService.getById(request.news().id()))
                .dipendente(dipendenteService.getById(request.dipendente().id()))
                .build();
    }
}
