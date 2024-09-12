package com.test.nginxtestbe.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class STOMPConfig implements WebSocketMessageBrokerConfigurer {
    @Value("${stomp.connection}")
    private String wsChatConnection;

    @Value("${stomp.subscribe}")
    private String subscribe;

    @Value("${stomp.publish}")
    private String publish;

    @Value("${cors.allowedOrigins}")
    private String allowedOrigins;


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(wsChatConnection).setAllowedOrigins(allowedOrigins)
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker(subscribe); //
        registry.setApplicationDestinationPrefixes(publish); // 구독한 곳에 메시지를 보낼 때
    }
}
