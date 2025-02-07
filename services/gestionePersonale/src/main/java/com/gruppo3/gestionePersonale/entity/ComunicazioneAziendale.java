package com.gruppo3.gestionePersonale.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comunicazione_aziendale")
@EntityListeners(AuditingEntityListener.class)
public class ComunicazioneAziendale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String testo;
    private String allegato_url;
    @ManyToOne
    @JoinColumn(name = "id_publisher", referencedColumnName = "id")
    private Long dipendenteId;
}
