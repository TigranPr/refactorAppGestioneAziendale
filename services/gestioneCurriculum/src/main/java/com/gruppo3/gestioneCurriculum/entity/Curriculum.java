package com.gruppo3.gestioneCurriculum.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "curriculum")
@EntityListeners(AuditingEntityListener.class)
public class Curriculum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idUtente;
    @Column(name = "percorso_file")
    private String percorsoFile;
}
