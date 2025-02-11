package com.gruppo3.gestioneComunity.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class NewsProducer {
    @Autowired
    private KafkaTemplate<String, NewsConfirmation> kafkaTemplate;

    public void sendConfermaNews(NewsConfirmation newsConfirmation) {
        Message<NewsConfirmation> message = MessageBuilder
                .withPayload(newsConfirmation)
                .setHeader(KafkaHeaders.TOPIC, "news-topic")
                .build();
        kafkaTemplate.send(message);
    }
}
