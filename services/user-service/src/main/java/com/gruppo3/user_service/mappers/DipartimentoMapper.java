package com.gruppo3.user_service.mappers;

import com.gruppo3.user_service.dto.request.DipartimentoRequest;
import com.gruppo3.user_service.entity.Dipartimento;
import org.springframework.stereotype.Service;

@Service
public class DipartimentoMapper {

    public Dipartimento dipartimentoFromRequest(DipartimentoRequest request){
        return Dipartimento.builder()
                .nome(request.nome())
                .descrizione(request.descrizione())
                .build();
    }
}
