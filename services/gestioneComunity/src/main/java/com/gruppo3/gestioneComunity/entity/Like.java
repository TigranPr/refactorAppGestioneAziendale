package com.gruppo3.gestioneComunity.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "likes")
@EntityListeners(AuditingEntityListener.class)
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idDipendente;

    @ManyToOne
    @JoinColumn(name = "id_news", nullable = false)
    private News news;
}
