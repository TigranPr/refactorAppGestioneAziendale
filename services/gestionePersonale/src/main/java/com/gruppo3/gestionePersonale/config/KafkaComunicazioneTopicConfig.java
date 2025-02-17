package com.gruppo3.gestionePersonale.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaComunicazioneTopicConfig {

    public NewTopic comunicazioneTopic() {
        return TopicBuilder
                .name("comunicazione-topic")
                .build();
    }
}
