package com.gruppo3.gestioneComunity.services;

import com.gruppo3.gestioneComunity.dto.requests.CreateCommentoRequest;
import com.gruppo3.gestioneComunity.dto.requests.UpdateCommentoRequest;
import com.gruppo3.gestioneComunity.dto.response.EntityIdResponse;
import com.gruppo3.gestioneComunity.entity.Commento;
import com.gruppo3.gestioneComunity.exceptions.MyEntityNotFoundException;
import com.gruppo3.gestioneComunity.exceptions.MyIllegalException;
import com.gruppo3.gestioneComunity.mappers.CommentoMapper;
import com.gruppo3.gestioneComunity.repository.CommentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentoService {
    @Autowired
    private CommentoRepository commentoRepository;
    @Autowired
    private CommentoMapper commentoMapper;
    @Autowired
    private NewsService newsService;
    @Autowired
    private UtenteClient utenteClient;

    public Commento getById(Long id) {
        return commentoRepository.findById(id)
                .orElseThrow(() -> new MyEntityNotFoundException("Commento con id: " + id + " non trovato!"));
    }

    public List<Commento> getAll() {
        return commentoRepository.findAll();
    }

    public void deleteCommento(Long id) {
        Commento commento = getById(id);
        commentoRepository.deleteById(commento.getId());
    }

    public EntityIdResponse createCommento(CreateCommentoRequest request) {
        Commento commento = commentoRepository.save(commentoMapper.fromCreateCommentoRequest(request));
        return new EntityIdResponse(commento.getId());
    }

    public EntityIdResponse updateCommento(Long id, UpdateCommentoRequest request) {
        Commento commento = getById(id);
        if (request.testo() != null) commento.setTesto(request.testo());
        if (request.news() != null) commento.setNews(newsService.getById(request.news().id()));
        if (request.dipendente() != null) commento.setIdDipendente(utenteClient.getUtenteById(request.dipendente().id()).id());
        if (request.testo() == null && request.news() == null && request.dipendente() == null)
            throw new MyIllegalException("Per fare un update devi almeno inserire un campo");
        return new EntityIdResponse(commentoRepository.save(commento).getId());
    }
}
