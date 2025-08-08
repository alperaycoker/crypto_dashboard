package com.example.crypto_forex_api.service;

import com.example.crypto_forex_api.dto.AlertMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class AnomalyListenerService {

    private static final Logger logger = LoggerFactory.getLogger(AnomalyListenerService.class);
    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public AnomalyListenerService(SimpMessagingTemplate messagingTemplate, ObjectMapper objectMapper) {
        this.messagingTemplate = messagingTemplate;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "PRICE_SPIKE_ALERTS", groupId = "${spring.kafka.consumer.group-id}")
    public void listenForAnomalies(String message) {
        logger.info("Anomali mesajı alındı (raw string): {}", message);
        try {
            AlertMessage alert = objectMapper.readValue(message, AlertMessage.class);
            logger.info("Anomali objesi WebSocket'e gönderiliyor: {}", alert);
            messagingTemplate.convertAndSend("/topic/alerts", alert);
        } catch (Exception e) {
            logger.error("Kafka mesajı parse edilirken veya WebSocket'e gönderilirken hata oluştu.", e);
        }
    }
}
