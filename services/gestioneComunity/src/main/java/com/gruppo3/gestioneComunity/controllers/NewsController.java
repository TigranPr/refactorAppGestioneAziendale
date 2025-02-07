package com.gruppo3.gestioneComunity.controllers;

import com.gruppo3.gestioneComunity.dto.requests.CreateNewsRequest;
import com.gruppo3.gestioneComunity.dto.response.NewsResponse;
import com.gruppo3.gestioneComunity.entity.News;
import com.gruppo3.gestioneComunity.services.NewsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping("/get/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable Long id) throws MyEntityNotFoundException {
        return new ResponseEntity<>(newsService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<News>> getAll() {
        return new ResponseEntity<>(newsService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<EntityIdResponse> createNews(@RequestBody @Valid CreateNewsRequest request) {
        return new ResponseEntity<>(newsService.createNews(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EntityIdResponse> updateNews(@PathVariable Long id, @RequestBody @Valid NewsResponse response)
            throws MyEntityNotFoundException {
        return new ResponseEntity<>(newsService.updateNews(id, response), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) throws MyEntityNotFoundException {
        newsService.deleteById(id);
    }
}
