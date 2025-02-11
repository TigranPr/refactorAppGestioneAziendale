package com.gruppo3.gestionePersonale.services;

import com.gruppo3.gestionePersonale.dto.UtenteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "${application.config.user-url}")
public interface UtenteClient {
    @GetMapping("/get/{id}")
    UtenteResponse getUtenteById(@PathVariable Long id);
}