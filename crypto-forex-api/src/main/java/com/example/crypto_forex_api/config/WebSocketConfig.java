package com.example.crypto_forex_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // Mesaj broker yapılandırması
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic"); // frontend /topic/ ile dinler
        registry.setApplicationDestinationPrefixes("/app"); // frontend /app/ ile gönderir
    }

    // STOMP endpoint yapılandırması
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/ws-alerts") // SockJS'nin bağlanacağı endpoint
                .setAllowedOriginPatterns("*") // CORS için tüm kaynaklara izin veriyoruz
                .withSockJS(); // SockJS fallback desteği
    }
}
