package com.gruppo3.gestioneComunity.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titolo;
    @Column(nullable = false)
    private String testo;
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String image_url;
    @Column(nullable = false)
    private String allegato_url;
    @ManyToOne
    @JoinColumn(name = "id_publisher", referencedColumnName = "id")
    private Long idPublisher;
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
