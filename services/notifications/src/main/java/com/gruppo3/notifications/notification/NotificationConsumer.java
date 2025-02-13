package com.gruppo3.notifications.notification;

import com.gruppo3.notifications.kafka.comunicazione.ComunicazioneConfirmation;
import com.gruppo3.notifications.kafka.news.NewsConfirmation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationConsumer {
    @Autowired
    private NotificationRepository notificationRepository;

    @KafkaListener(topics = "news-topic", groupId = "notification-group")
    public void consumeNewsNotification (NewsConfirmation newsConfirmation) {
        notificationRepository.save(
                Notification.builder()
                        .notificationType(NotificationType.NEWS_NOTIFICATION)
                        .notificationTime(LocalDateTime.now())
                        .newsConfirmation(newsConfirmation)
                        .build()
        );
        /*TODO inviare la email di conferma*/
        System.out.println("comunica?!");
    }

    @KafkaListener(topics = "comunicazione-topic", groupId = "notification-group")
    public void consumeComunicazioneNotification (ComunicazioneConfirmation comunicazioneConfirmation) {
        notificationRepository.save(
                Notification.builder()
                        .notificationType(NotificationType.COMUNICAZIONE_NOTIFICATION)
                        .notificationTime(LocalDateTime.now())
                        .comunicazioneConfirmation(comunicazioneConfirmation)
                        .build()
        );
        /*TODO inviare la email di conferma*/
        System.out.println("comunica?!");
    }
}

