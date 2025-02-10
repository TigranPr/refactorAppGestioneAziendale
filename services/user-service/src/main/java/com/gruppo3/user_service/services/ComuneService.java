package com.gruppo3.user_service.services;

import com.gruppo3.user_service.dto.request.ComuneRequest;
import com.gruppo3.user_service.dto.response.EntityIdResponse;
import com.gruppo3.user_service.entity.Comune;
import com.gruppo3.user_service.exception.MyEntityNotFoundException;
import com.gruppo3.user_service.mappers.ComuneMapper;
import com.gruppo3.user_service.repository.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComuneService {
    @Autowired
    private ComuneMapper comuneMapper;
    @Autowired
    private ComuneRepository comuneRepository;

    public Comune getById(Long id){
        return comuneRepository.findById(id)
                .orElseThrow(()-> new MyEntityNotFoundException("Il ccomune con id %d non esiste"));
    }
    public List<Comune> getAll(){
        return comuneRepository.findAll();
    }

    public EntityIdResponse createComune(ComuneRequest request){
        Comune comune = comuneRepository.save(comuneMapper.fromComuneRequest(request));
        return new EntityIdResponse(comune.getId());
    }
}
