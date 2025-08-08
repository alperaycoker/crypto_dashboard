package com.example.crypto_forex_api.service;

import com.example.crypto_forex_api.dto.VolatilityData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class VolatilityService {

    private static final Logger logger = LoggerFactory.getLogger(VolatilityService.class);

    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public VolatilityService(SimpMessagingTemplate messagingTemplate, ObjectMapper objectMapper) {
        this.messagingTemplate = messagingTemplate;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "volatility_5min_table", groupId = "volatility-group")
    public void listenForVolatility(ConsumerRecord<String, String> record) {
        String key = record.key();
        String message = record.value();

        logger.info("Kafka Key (symbol): {}", key);
        logger.info("HAM JSON VERİSİ: {}", message);

        try {
            VolatilityData data = objectMapper.readValue(message, VolatilityData.class);

            if (key != null) {
                data.setSymbol(key);
            } else {
                logger.warn("Kafka mesajının key değeri null geldi.");
            }

            logger.info("Parse edilen veri: {}", data);

            // 🔄 En kritik değişiklik burada: JSON olarak gönder
            String jsonToSend = objectMapper.writeValueAsString(data);
            messagingTemplate.convertAndSend("/topic/volatility", jsonToSend);

        } catch (Exception e) {
            logger.error("HATA: JSON parse edilemedi", e);
        }
    }
}
