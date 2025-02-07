package com.gruppo3.user_service.mappers;

import com.gruppo3.user_service.dto.request.ComuneRequest;
import com.gruppo3.user_service.entity.Comune;
import org.springframework.stereotype.Service;

@Service
public class ComuneMapper {

    public Comune fromComuneRequest(ComuneRequest request){
        return Comune.builder()
                .nome(request.nome())
                .regione(request.regione())
                .build();
    }
}
