package com.gruppo3.gestionePersonale.services;

import com.gruppo3.gestionePersonale.dto.EntityIdResponse;
import com.gruppo3.gestionePersonale.dto.TimbraturaRequest;
import com.gruppo3.gestionePersonale.entity.Timbratura;
import com.gruppo3.gestionePersonale.exceptions.MyEntityNotFoundException;
import com.gruppo3.gestionePersonale.mappers.TimbraturaMapper;
import com.gruppo3.gestionePersonale.repository.TimbraturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimbraturaService {
    @Autowired
    private TimbraturaRepository timbraturaRepository;
    @Autowired
    private TimbraturaMapper timbraturaMapper;

    public Timbratura getById(Long id) {
        return timbraturaRepository.findById(id).orElseThrow(() -> new MyEntityNotFoundException("la timbratura con id " + id + " non esiste"));
    }

    public List<Timbratura> findAll() {
        return timbraturaRepository.findAll();
    }

    public EntityIdResponse createTimbratura(TimbraturaRequest request) {
        Timbratura timbratura = timbraturaMapper.fromTimbraturaRequest(request);
        return EntityIdResponse.builder()
                .id(timbraturaRepository.save(timbratura).getId())
                .build();
    }

    public Timbratura updateTimbratura(Long idTimbratura, TimbraturaRequest request) {
        Timbratura timbratura = getById(idTimbratura);
        if (request.ingresso() != null) System.out.println("non puoi cambiare l'orario di ingresso della timbratura!");
        if (request.inizioPausa() != null && timbratura.getInizioPausaPranzo() == null)
            timbratura.setInizioPausaPranzo(request.inizioPausa());
        if (request.finePausa() != null && timbratura.getFinePausa() == null)
            timbratura.setFinePausa(request.finePausa());
        if (request.uscita() != null && timbratura.getUscita() == null) timbratura.setUscita(request.uscita());
        return timbraturaRepository.save(timbratura);
    }
}
