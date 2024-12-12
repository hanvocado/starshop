package com.starshop.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic", "/queue");
		// Incoming client messages are routed to @MessageMapping methods if the destination starts with /app.
		config.setApplicationDestinationPrefixes("/app");
		// a prefix for messages directed to specific users.
		config.setUserDestinationPrefix("/user");	
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// WebSocket endpoint that clients connect to
		// Enables SockJS fallback for browsers that do not support WebSocket.
		registry.addEndpoint("/chat").withSockJS();
	}
}
