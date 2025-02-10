package com.gruppo3.user_service.mappers;

import com.gruppo3.user_service.dto.request.UtenteRequest;
import com.gruppo3.user_service.entity.Utente;
import com.gruppo3.user_service.enums.Ruolo;
import com.gruppo3.user_service.exception.MyIllegalException;
import com.gruppo3.user_service.services.ComuneService;
import com.gruppo3.user_service.services.PosizioneLavorativaService;
import org.springframework.beans.factory.annotation.Autowired;

public class UtenteMapper {
    @Autowired
    private ComuneService comuneService;
    @Autowired
    private PosizioneLavorativaService posizioneLavorativaService;

    public Utente utenteFromRequest(UtenteRequest request){
        Ruolo ruolo;
        try {
            ruolo = Ruolo.valueOf(request.ruolo().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new MyIllegalException("Ruolo inserito non valido!");
        }
        return Utente.builder()
                .nome(request.nome())
                .cognome(request.cognome())
                .email(request.email())
                .password(request.password())
                .dataDiNascita(request.dataDiNascita())
                .telefono(request.telefono())
                .avatar(request.avatar())
                .comune(comuneService.getById(request.comune()))
                .ruolo(ruolo)
                .posizioneLavorativa(posizioneLavorativaService.getById(request.idPosizioneLavorativa()))
                .build();
    }
}
