package com.example.crypto_forex_api.service;

import com.example.crypto_forex_api.dto.PriceData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class PriceFeedService {

    private static final Logger logger = LoggerFactory.getLogger(PriceFeedService.class);
    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public PriceFeedService(SimpMessagingTemplate messagingTemplate, ObjectMapper objectMapper) {
        this.messagingTemplate = messagingTemplate;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "crypto_forex_raw", groupId = "price-feed-group")
    public void listenForAllPrices(String message) {
        try {
            PriceData priceData = objectMapper.readValue(message, PriceData.class);
            messagingTemplate.convertAndSend("/topic/prices", priceData);
        } catch (Exception e) {
            logger.error("Ham fiyat mesajı parse edilirken hata oluştu.", e);
        }
    }
}
