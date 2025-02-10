package com.gruppo3.gestioneComunity.mappers;

import com.gruppo3.gestioneComunity.dto.response.LikeResponse;
import com.gruppo3.gestioneComunity.entity.Like;
import com.gruppo3.gestioneComunity.entity.News;
import com.gruppo3.gestioneComunity.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LikeMapper {

    @Autowired
    private Long dipendenteService;

    @Autowired
    private NewsService newsService;

    public Like toEntity(News news, Long dipendente) {
        return Like.builder()
                .news(news)
                .dipendente(dipendente)
                .build();
    }

    public LikeResponse toResponse(Like like) {
        return new LikeResponse(
                like.getId(),
                like.getNews().getId(),
                like.getDipendente().getId()
        );
    }

}
