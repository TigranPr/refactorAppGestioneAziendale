package com.gruppo3.user_service.entity;

import com.gruppo3.user_service.enums.Ruolo;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "utente")
@EntityListeners(AuditingEntityListener.class)
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private LocalDate dataDiNascita;
    @Column(nullable = false)
    private String telefono;
    @Column(nullable = false)
    private String avatar;
    @Column(nullable = false)
    private Ruolo ruolo;
    @Column(name = "token_di_registrazione")
    private String registrationToken;
    @ManyToOne(optional = false)
    @JoinColumn(name = "comune_di_nascita", referencedColumnName = "nome")
    private Comune comune;
    @ManyToOne
    @JoinColumn(name = "id_posizione_lavorativa", referencedColumnName = "id")
    private PosizioneLavorativa posizioneLavorativa;
    @Column(name = "last_login")
    private LocalDateTime lastLogin;
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @CreatedBy
    @Column(name = "created_by")
    private Long createdBy;
    @LastModifiedDate
    @Column(name = "last_modified_at")
    private LocalDateTime lastModifiedAt;
    @LastModifiedBy
    @Column(name = "last_modified_by")
    private Long lastModifiedBy;
}