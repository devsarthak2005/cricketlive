package com.myproject.cricketlivescore.config;

import com.myproject.cricketlivescore.websocket.LiveScoreHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer, WebSocketConfigurer {


    private final LiveScoreHandler liveScoreHandler;

    public WebSocketConfig(LiveScoreHandler liveScoreHandler) {
        this.liveScoreHandler = liveScoreHandler;
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/live-score");
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(liveScoreHandler, "/live-score").setAllowedOrigins("*");
    }
}
