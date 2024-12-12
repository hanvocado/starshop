package com.starshop.configs;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.starshop.entities.User;

@Service
public class ChatQueue {
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	private List<String> store = new LinkedList<>();	
	
	@EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = accessor.getUser().getName();
        if (username != null && !username.contains("admin")) {
            this.store.add(username);
        }
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = accessor.getUser().getName();
        if (username != null && !username.contains("admin")) {
        	this.store.remove(username);
        }
    }
    
    @Scheduled(fixedRate = 5000)
    public void broadcastActiveUsers() {
    	messagingTemplate.convertAndSend("/topic/active-users", this.store);
    }
}
