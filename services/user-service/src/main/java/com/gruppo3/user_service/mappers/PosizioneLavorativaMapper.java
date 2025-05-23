package com.gruppo3.user_service.mappers;

import com.gruppo3.user_service.dto.request.PosizioneLavorativaRequest;
import com.gruppo3.user_service.entity.PosizioneLavorativa;
import com.gruppo3.user_service.services.DipartimentoService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PosizioneLavorativaMapper {

    @Autowired
    private DipartimentoService dipartimentoService;

    public PosizioneLavorativa fromPozisioneRequest(PosizioneLavorativaRequest request){
        return PosizioneLavorativa.builder()
                .nome(request.nome())
                .descrizione(request.descrizione())
                .idDipartimento(dipartimentoService.getById(request.idDipartimento()))
                .build();
    }
}
