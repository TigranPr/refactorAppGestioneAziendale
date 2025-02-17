package com.gruppo3.gestionePersonale.services;

import com.gruppo3.gestionePersonale.dto.ComunicazioneAziendaleRequest;
import com.gruppo3.gestionePersonale.dto.EntityIdResponse;
import com.gruppo3.gestionePersonale.dto.UpdateComunicazioneAziendaleRequest;
import com.gruppo3.gestionePersonale.entity.ComunicazioneAziendale;
import com.gruppo3.gestionePersonale.exceptions.MyEntityNotFoundException;
import com.gruppo3.gestionePersonale.exceptions.MyIllegalException;
import com.gruppo3.gestionePersonale.kafka.ComunicazioneConfirmation;
import com.gruppo3.gestionePersonale.kafka.ComunicazioneProducer;
import com.gruppo3.gestionePersonale.mappers.ComunicazioneAziendaleMapper;
import com.gruppo3.gestionePersonale.repository.ComunicazioneAziendaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComunicazioneAziendaleService {
    @Autowired
    private ComunicazioneAziendaleRepository comunicazioneAziendaleRepository;
    @Autowired
    private ComunicazioneAziendaleMapper comunicazioneAziendaleMapper;
    @Autowired
    private ComunicazioneProducer comunicazioneProducer;

    public ComunicazioneAziendale getById(Long id) throws MyEntityNotFoundException {
        return comunicazioneAziendaleRepository
                .findById(id)
                .orElseThrow(
                        () -> new MyEntityNotFoundException("Le comunicazioni aziendali con id " + id + " non esistono"));
    }

    public List<ComunicazioneAziendale> getAll() {
        return comunicazioneAziendaleRepository.findAll();
    }

    public EntityIdResponse createComunicazioneAziendale(Long idDipendente, ComunicazioneAziendaleRequest request) {
        ComunicazioneAziendale comunicazioneAziendale = comunicazioneAziendaleRepository.save(comunicazioneAziendaleMapper.toEntity(idDipendente, request));
        comunicazioneProducer.sendConfermaComunicazione(ComunicazioneConfirmation
                .builder()
                        .id(comunicazioneAziendale.getId())
                        .testo(comunicazioneAziendale.getTesto())
                        .allegatoUrl(comunicazioneAziendale.getAllegato_url())
                        .idDipendente(comunicazioneAziendale.getDipendenteId())
                .build());
        return new EntityIdResponse(comunicazioneAziendale.getId());
    }

    public EntityIdResponse updateComunicazioneAziendale(Long id, UpdateComunicazioneAziendaleRequest response) {
        ComunicazioneAziendale comunicazioneAziendale = getById(id);
        if (response.testo() != null) comunicazioneAziendale.setTesto(response.testo());
        if (response.allegato_url() != null) comunicazioneAziendale.setAllegato_url(response.allegato_url());
        if (response.idDipendente() != null)
            comunicazioneAziendale.setDipendenteId(response.idDipendente());
        if (response.testo() == null && response.allegato_url() == null && response.idDipendente() == null)
            throw new MyIllegalException("Per fare un update devi almeno inserire un campo");
        return new EntityIdResponse(comunicazioneAziendaleRepository.save(comunicazioneAziendale).getId());
    }

    public void deleteById(Long id) {
        ComunicazioneAziendale comunicazioneAziendale = getById(id);
        comunicazioneAziendaleRepository.deleteById(comunicazioneAziendale.getId());
    }
}
