package com.gruppo3.user_service.controllers;

import com.gruppo3.user_service.dto.request.LoginRequest;
import com.gruppo3.user_service.dto.request.UtenteRequest;
import com.gruppo3.user_service.dto.response.AuthenticationResponse;
import com.gruppo3.user_service.dto.response.GenericResponse;
import com.gruppo3.user_service.dto.response.TokenResponse;
import com.gruppo3.user_service.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utenti/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public TokenResponse register(@RequestBody UtenteRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @GetMapping("/conferma/{token}")
    public ResponseEntity<GenericResponse> confirmRegistration(@PathVariable String token) {
        return new ResponseEntity<>(authService.confirmRegistration(token), HttpStatus.CREATED);
    }

}
