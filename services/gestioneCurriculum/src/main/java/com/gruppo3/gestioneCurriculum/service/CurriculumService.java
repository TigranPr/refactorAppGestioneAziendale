package com.gruppo3.gestioneCurriculum.service;

import com.gruppo3.gestioneCurriculum.dto.EntityRespone;
import com.gruppo3.gestioneCurriculum.entity.Curriculum;
import com.gruppo3.gestioneCurriculum.exceptions.MyEntityNotFoundException;
import com.gruppo3.gestioneCurriculum.exceptions.MyIllegalException;
import com.gruppo3.gestioneCurriculum.repository.CurriculumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class CurriculumService {
    @Autowired
    private CurriculumRepository curriculumRepository;
    @Autowired
    private UtenteClient utenteClient;

    public Curriculum getById(Long id){
        return curriculumRepository.findById(id).orElseThrow(()-> new MyEntityNotFoundException("Curriculum con id " + id + " non trovato"));
    }

    public List<Curriculum> all(){
        return curriculumRepository.findAll();
    }

    public EntityRespone upload(Long idUtente, MultipartFile file){
        var utente = utenteClient.getUtenteById(idUtente);
        Curriculum curriculum;
        try {
            curriculum = Curriculum
                    .builder()
                    .idUtente(utente.id())
                    .percorsoFile(Arrays.toString(file.getBytes()))
                    .build();
        } catch (IOException e) {
            throw new MyIllegalException("Tipo di file non supportato: " + e);
        }
        return new EntityRespone(curriculumRepository.save(curriculum).getId());
    }

    public void delete(Long id){
        curriculumRepository.deleteById(id);
    }
}
