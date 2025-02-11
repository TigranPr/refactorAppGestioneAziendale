package com.gruppo3.gestioneComunity.services;

import com.gruppo3.gestioneComunity.dto.requests.LikeRequest;
import com.gruppo3.gestioneComunity.dto.response.LikeResponse;
import com.gruppo3.gestioneComunity.entity.Like;
import com.gruppo3.gestioneComunity.entity.News;
import com.gruppo3.gestioneComunity.exceptions.MyEntityNotFoundException;
import com.gruppo3.gestioneComunity.mappers.LikeMapper;
import com.gruppo3.gestioneComunity.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private NewsService newsService;
    @Autowired
    private UtenteClient utenteClient;
    @Autowired
    private LikeMapper likeMapper;

    public LikeResponse createLike(LikeRequest request) {
        // Recupero News e Dipendente
        News news = newsService.getById(request.NewsId());
        var dipendente = utenteClient.getUtenteById(request.IdDipendente());

        // Verifico se ci sta il duplicato del like
        if (likeRepository.existsByNewsAndDipendente(news, utenteClient.getUtenteById(request.IdDipendente()).id())) {
            throw new IllegalArgumentException(
                    "Il dipendente con ID " + dipendente + " ha già messo like alla news con ID " + news.getId()
            );
        }

        // Creazione del like
        Like like = likeMapper.toEntity(request);
        likeRepository.save(like);

        // Ritorna la risposta del Like creato
        return likeMapper.toResponse(like);
    }

    public Like getById(Long id) {
        return likeRepository.findById(id)
                .orElseThrow(() -> new MyEntityNotFoundException("Like non trovato con id: " + id));
    }

    public List<Like> getAll() {
        return likeRepository.findAll();
    }

    public void deleteById(Long id) {
        if (!likeRepository.existsById(id)) {
            throw new MyEntityNotFoundException("Like con id " + id + " non trovato!"
            );
        }
        likeRepository.deleteById(id);
    }


}
