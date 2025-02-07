package com.gruppo3.user_service.services;

import com.gruppo3.user_service.dto.request.PosizioneLavorativaRequest;
import com.gruppo3.user_service.dto.response.EntityIdResponse;
import com.gruppo3.user_service.dto.request.UpdatePosizioneLavorativaRequest;
import com.gruppo3.user_service.entity.PosizioneLavorativa;
import com.gruppo3.user_service.exception.MyEntityNotFoundException;
import com.gruppo3.user_service.mappers.PosizioneLavorativaMapper;
import com.gruppo3.user_service.repository.PosizioneLavorativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PosizioneLavorativaService {
    @Autowired
    private PosizioneLavorativaMapper posizioneLavorativaMapper;
    @Autowired
    private PosizioneLavorativaRepository posizioneLavorativaRepository;

    public PosizioneLavorativa getById(Long id){
        return posizioneLavorativaRepository.findById(id)
                .orElseThrow(()-> new MyEntityNotFoundException("la posizione lavorativa con id d% non esiste"));
    }

    public List<PosizioneLavorativa> getAll(){
        return posizioneLavorativaRepository.findAll();
    }

    public EntityIdResponse createPosizione(PosizioneLavorativaRequest request){
        PosizioneLavorativa posizioneLavorativa = posizioneLavorativaRepository.save(posizioneLavorativaMapper.fromPozisioneRequest(request));
        return new EntityIdResponse(posizioneLavorativa.getId());
    }

    public EntityIdResponse updatePosizione(Long id, UpdatePosizioneLavorativaRequest response){
        PosizioneLavorativa posizioneLavorativa = posizioneLavorativaRepository.findById(id)
                .orElseThrow(()-> new MyEntityNotFoundException("la posizione lavorativa con id d% non esiste"));
        if(response.nome() != null) posizioneLavorativa.setNome(response.nome());
        if(response.descrizione() != null) posizioneLavorativa.setDescrizione(response.descrizione());
        return new EntityIdResponse(posizioneLavorativaRepository.save(posizioneLavorativa).getId());
    }

    public void deletePosizione(Long id){
        posizioneLavorativaRepository.deleteById(id);
    }
}
