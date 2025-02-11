package com.gruppo3.gestioneComunity.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaNewsTopicConfig {
    public NewTopic newsTopic() {
        return TopicBuilder
                .name("news-topic")
                .build();
    }
}
