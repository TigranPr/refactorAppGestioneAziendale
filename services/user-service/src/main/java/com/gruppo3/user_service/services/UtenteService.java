package com.gruppo3.user_service.services;

import com.gruppo3.user_service.dto.request.UpdateUtenteRequest;
import com.gruppo3.user_service.dto.request.UtenteRequest;
import com.gruppo3.user_service.dto.response.EntityIdResponse;
import com.gruppo3.user_service.entity.Utente;
import com.gruppo3.user_service.enums.Ruolo;
import com.gruppo3.user_service.exception.MyEntityNotFoundException;
import com.gruppo3.user_service.exception.MyIllegalException;
import com.gruppo3.user_service.mappers.UtenteMapper;
import com.gruppo3.user_service.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService {
    @Autowired
    private ComuneService comuneService;
    @Autowired
    private PosizioneLavorativaService posizioneLavorativaService;
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private UtenteMapper utenteMapper;

    public Utente getById(Long id){
        return utenteRepository.findById(id)
                .orElseThrow(()-> new MyEntityNotFoundException("Utente con id %d non trovato"));
    }

    public List<Utente> getAll(){
        return utenteRepository.findAll();
    }

    public EntityIdResponse createUtente(UtenteRequest request){
        Utente utente = utenteRepository.save(utenteMapper.utenteFromRequest(request));
        return new EntityIdResponse(utente.getId());
    }

    public EntityIdResponse updateDipendente(Long id, UpdateUtenteRequest request) {
        Utente utente = getById(id);
        Ruolo ruolo;
        try {
            ruolo = Ruolo.valueOf(request.ruolo());
        } catch (IllegalArgumentException e) {
            throw new MyIllegalException("Ruolo inserito non valido!");
        }
        if (request.nome() != null) utente.setNome(request.nome());
        if (request.cognome() != null) utente.setCognome(request.cognome());
        if (request.email() != null) utente.setEmail(request.email());
        if (request.comune() != null) utente.setComune(comuneService.getById(request.comune()));
        if (request.dataDiNascita() != null) utente.setDataDiNascita(request.dataDiNascita());
        if (request.telefono() != null) utente.setTelefono(request.telefono());
        if (request.avatar() != null) utente.setAvatar(request.avatar());
        if (request.ruolo() != null) utente.setRuolo(ruolo);
        return new EntityIdResponse(utenteRepository.save(utente).getId());
    }

    public void deleteUtente(Long id){
        utenteRepository.deleteById(id);
    }

    public Utente getByRegistrationToken(String token) {
        return utenteRepository
                .findByRegistrationToken(token)
                .orElseThrow(() -> new MyEntityNotFoundException("utente con token " + token + " non trovato"));
    }

    public void insertUtente(Utente utente) {
        utenteRepository.save(utente);
    }
}
