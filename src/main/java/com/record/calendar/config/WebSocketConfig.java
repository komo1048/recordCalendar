package com.record.calendar.config;

import com.record.calendar.calendarService.WebSocketChat;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@RequiredArgsConstructor
@Component
public class WebSocketConfig implements WebSocketConfigurer {

    private final WebSocketChat webSocketChat;

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketChat, "/chat")
                .setAllowedOrigins("http://localhost:8083")
                .withSockJS();
    }
}

