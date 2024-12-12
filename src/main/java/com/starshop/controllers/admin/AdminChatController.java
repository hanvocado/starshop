package com.starshop.controllers.admin;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.starshop.configs.ChatQueue;
import com.starshop.models.ChatMessage;

@Controller
public class AdminChatController {
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
		
	@GetMapping("/admin/chat")
	public String index(Model model) {
		return "admin/chat";
	}	

	@MessageMapping("/admin/private-message") // /app/admin/private-message
	public void sendPrivateMessage(ChatMessage message) {
		message.setSender("StarShop");
		messagingTemplate.convertAndSendToUser(message.getRecipient(), "/queue/messages", message);
	}
}
