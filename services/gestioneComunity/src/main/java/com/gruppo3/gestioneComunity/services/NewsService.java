package com.gruppo3.gestioneComunity.services;

import com.gruppo3.gestioneComunity.dto.requests.CreateNewsRequest;
import com.gruppo3.gestioneComunity.dto.response.NewsResponse;
import com.gruppo3.gestioneComunity.entity.News;
import com.gruppo3.gestioneComunity.mappers.NewsMapper;
import com.gruppo3.gestioneComunity.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private NewsMapper newsMapper;

    public News getById(Long id) throws MyEntityNotFoundException {
        return newsRepository.findById(id).orElseThrow(() -> new MyEntityNotFoundException
                ("la news con id " + id + " non esiste"));
    }

    public List<News> getAll() {
        return newsRepository.findAll();
    }

    public EntityIdResponse createNews(CreateNewsRequest request) {
        News news = newsMapper.fromCreateNewsRequest(request);
        return EntityIdResponse.
                builder()
                .id(newsRepository.save(news).getId())
                .build();
    }

    public EntityIdResponse updateNews(Long id, NewsResponse response) {
        News news = getById(id);
        news.setTitolo(response.titolo());
        news.setTesto(response.testo());
        news.setAllegato_url(response.allegato());
        news.setImage_url(response.image_url());
        return new EntityIdResponse(newsRepository.save(news).getId());
    }

    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }
}
