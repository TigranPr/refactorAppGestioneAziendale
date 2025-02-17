package com.gruppo3.notifications.kafka.news;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record NewsConfirmation (
        Long id,
        String titolo,
        String testo,
        String image_url,
        String allegato_url,
        Long idPublisher,
        LocalDateTime createdAt,
        Long createdBy,
        LocalDateTime lastModifiedAt,
        Long lastModifiedBy
) {
}
