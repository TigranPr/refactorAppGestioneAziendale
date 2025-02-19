package com.gruppo3.user_service.services;

import com.gruppo3.user_service.dto.request.LoginRequest;
import com.gruppo3.user_service.dto.request.UtenteRequest;
import com.gruppo3.user_service.dto.response.TokenResponse;
import com.gruppo3.user_service.entity.Utente;
import com.gruppo3.user_service.enums.Ruolo;
import com.gruppo3.user_service.exception.MyEntityNotFoundException;
import com.gruppo3.user_service.repository.UtenteRepository;
import com.gruppo3.user_service.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ComuneService comuneService;
    @Autowired
    private PosizioneLavorativaService posizioneLavorativaService;

    public TokenResponse register(UtenteRequest registerRequest) {
        if (utenteRepository.existsByEmail(registerRequest.email())) {
            throw new RuntimeException("Email già registrata");
        }
        if (registerRequest.password() == null || registerRequest.password().isBlank()) {
            throw new RuntimeException("La password non può essere nulla o vuota");
        }
        Ruolo ruolo;
        try {
            ruolo = Ruolo.valueOf(registerRequest.ruolo().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Ruolo non valido: " + registerRequest.ruolo());
        }
        Utente utente = Utente.builder()
                .nome(registerRequest.nome())
                .cognome(registerRequest.cognome())
                .email(registerRequest.email())
                .password(passwordEncoder.encode(registerRequest.password()))
                .dataDiNascita(registerRequest.dataDiNascita())
                .telefono(registerRequest.telefono())
                .avatar(registerRequest.avatar())
                .comune(comuneService.getById(registerRequest.comune()))
                .ruolo(ruolo)
                .posizioneLavorativa(posizioneLavorativaService.getById(registerRequest.idPosizioneLavorativa()))
                .build();
        utente = utenteRepository.save(utente);
        String token = jwtUtil.generateToken(utente.getEmail());
        return new TokenResponse(token);
    }

    public TokenResponse login(LoginRequest loginRequest) {
        Utente utente = utenteRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new MyEntityNotFoundException("Utente non trovato"));

        if (!passwordEncoder.matches(loginRequest.password(), utente.getPassword())) {
            throw new RuntimeException("Credenziali non valide");
        }
        String token = jwtUtil.generateToken(utente.getEmail());
        return new TokenResponse(token);
    }
}
