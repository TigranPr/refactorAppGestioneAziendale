package com.gruppo3.gestioneCurriculum.service;

import com.gruppo3.gestioneCurriculum.dto.EntityRespone;
import com.gruppo3.gestioneCurriculum.entity.Curriculum;
import com.gruppo3.gestioneCurriculum.exceptions.MyEntityNotFoundException;
import com.gruppo3.gestioneCurriculum.exceptions.MyIllegalException;
import com.gruppo3.gestioneCurriculum.repository.CurriculumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class CurriculumService {

    @Autowired
    private CurriculumRepository curriculumRepository;

    @Autowired
    private UtenteClient utenteClient;

    private String path = "refactorAppGestioneAziendale/assest";

    public Curriculum getById(Long id){
        return curriculumRepository.findById(id).orElseThrow(()-> new MyEntityNotFoundException("Curriculum con id " + id + " non trovato"));
    }

    public List<Curriculum> all(){
        return curriculumRepository.findAll();
    }

   public Curriculum getByUtenteId(Long idUtente) {
        return curriculumRepository.findByIdUtente(idUtente).orElseThrow(()-> new MyEntityNotFoundException("Utente con id: " + idUtente + " non trovato!"));
   }

    public EntityRespone upload(Long idUtente, MultipartFile file){
        var utente = utenteClient.getUtenteById(idUtente);
        if (!valideFile(file)) throw new MyIllegalException("Tipo di file non valido!");
        Curriculum curriculum;
        try {
            Path directoryPath = Paths.get(path);
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }
            String fileName = utente.id() + "_" + file.getOriginalFilename();
            Path filePath = directoryPath.resolve(fileName);
            curriculumRepository.findByIdUtente(utente.id()).ifPresent(cvEsistente -> {
                File oldFile = new File(cvEsistente.getPercorsoFile());
                if (oldFile.exists()) {
                    oldFile.delete();
                }
                curriculumRepository.delete(cvEsistente);
            });
            file.transferTo(filePath);
            curriculum = Curriculum
                    .builder()
                    .idUtente(utente.id())
                    .percorsoFile(filePath.toString())
                    .build();
        } catch (IOException e) {
            throw new MyIllegalException("Tipo di file non supportato: " + e);
        }
        return new EntityRespone(curriculumRepository.save(curriculum).getId());
    }

    public ResponseEntity<Resource> download(Long idUtente) {
        Curriculum curriculum = getById(idUtente);
        Path filePath = Paths.get(curriculum.getPercorsoFile());
        if (!Files.exists(filePath)) {
            throw new MyEntityNotFoundException("File non trovato nel server");
        }
        try {
            Resource resource = new UrlResource(filePath.toUri());
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filePath.getFileName() + "\"")
                    .body(resource);
        } catch (MalformedURLException e) {
            throw new MyIllegalException("Errore nel recupero del file: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        Curriculum cv = getById(id);
        new File(cv.getPercorsoFile()).delete();
        curriculumRepository.deleteById(id);
    }

    public boolean valideFile(MultipartFile file) {
        List<String> tipoFilePermessi = List.of("pdf", "docx");
        String fileName = file.getOriginalFilename();
        String tipoFileCaricato = "";
        if (fileName != null && fileName.contains(".")) {
            tipoFileCaricato = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        }
        return tipoFilePermessi.contains(tipoFileCaricato);
    }
}
