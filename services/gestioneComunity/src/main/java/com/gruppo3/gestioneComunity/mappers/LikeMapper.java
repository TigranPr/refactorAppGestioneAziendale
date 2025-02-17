package com.gruppo3.gestioneComunity.mappers;

import com.gruppo3.gestioneComunity.dto.requests.LikeRequest;
import com.gruppo3.gestioneComunity.dto.response.LikeResponse;
import com.gruppo3.gestioneComunity.entity.Like;
import com.gruppo3.gestioneComunity.entity.News;
import com.gruppo3.gestioneComunity.services.NewsService;
import com.gruppo3.gestioneComunity.services.UtenteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LikeMapper {

    @Autowired
    private UtenteClient utenteClient;

    @Autowired
    private NewsService newsService;

    public Like toEntity(LikeRequest request) {
        News news = newsService.getById(request.NewsId());
        var dipendente = utenteClient.getUtenteById(request.IdDipendente());

        if (news == null) {
            throw new IllegalArgumentException("News con id " + request.NewsId() + " non trovata!");
        }

        if (dipendente == null) {
            throw new IllegalArgumentException("Dipendente con id " + request.IdDipendente() + " non trovato!");
        }

        return Like.builder()
                .news(news)
                .idDipendente(dipendente.id())
                .build();
    }

    public LikeResponse toResponse(Like like) {
        return new LikeResponse(
                like.getId(),
                like.getNews().getId(),
                like.getIdDipendente()
        );
    }

}
