package com.gruppo3.gestionePersonale.kafka;


import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class ComunicazioneProducer {
    @Autowired
    private KafkaTemplate<String, ComunicazioneConfirmation> kafkaTemplate;

    public void sendConfermaComunicazione(ComunicazioneConfirmation comunicazioneConfirmation) {
        Message<ComunicazioneConfirmation> message = MessageBuilder
                .withPayload(comunicazioneConfirmation)
                .setHeader(KafkaHeaders.TOPIC, "comunicazione-topic")
                .build();
        kafkaTemplate.send(message);
    }
}
