package com.gruppo3.user_service.services;

import com.gruppo3.user_service.dto.request.DipartimentoRequest;
import com.gruppo3.user_service.dto.response.EntityIdResponse;
import com.gruppo3.user_service.entity.Dipartimento;
import com.gruppo3.user_service.exception.MyEntityNotFoundException;
import com.gruppo3.user_service.mappers.DipartimentoMapper;
import com.gruppo3.user_service.repository.DipartimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DipartimentoService {
    @Autowired
    private DipartimentoMapper dipartimentoMapper;
    @Autowired
    private DipartimentoRepository dipartimentoRepository;

    public Dipartimento getById(Long id){
        return dipartimentoRepository.findById(id)
                .orElseThrow(()-> new MyEntityNotFoundException("il dipartimento con id %d non esiste"));
    }

    public List<Dipartimento> getAll(){
        return dipartimentoRepository.findAll();
    }

    public EntityIdResponse createDipartimento(DipartimentoRequest request){
        Dipartimento dipartimento = dipartimentoRepository.save(dipartimentoMapper.dipartimentoFromRequest(request));
        return new EntityIdResponse(dipartimento.getId());
    }

    public EntityIdResponse updateDipartimento(Long id, DipartimentoRequest request) {
        Dipartimento dipartimento = dipartimentoRepository.findById(id)
                .orElseThrow(() -> new MyEntityNotFoundException("Dipartimento con ID " + id + " non trovato"));
        if (request.nome() != null) dipartimento.setNome(request.nome());
        if (request.descrizione() != null) dipartimento.setDescrizione(request.descrizione());
        return new EntityIdResponse(dipartimentoRepository.save(dipartimento).getId());
    }

    public void deleteDipartimento(Long id){
        dipartimentoRepository.deleteById(id);
    }
}
