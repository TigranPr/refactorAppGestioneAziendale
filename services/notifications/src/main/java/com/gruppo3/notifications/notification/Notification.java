package com.gruppo3.notifications.notification;

import com.gruppo3.notifications.kafka.comunicazione.ComunicazioneConfirmation;
import com.gruppo3.notifications.kafka.news.NewsConfirmation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Notification {
    @Id
    private String id;
    private LocalDateTime notificationTime;
    private NewsConfirmation newsConfirmation;
    private ComunicazioneConfirmation comunicazioneConfirmation;
    private NotificationType notificationType;
}