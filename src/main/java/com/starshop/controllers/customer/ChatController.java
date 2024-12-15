package com.starshop.controllers.customer;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.starshop.configs.ChatQueue;
import com.starshop.models.ChatMessage;

@Controller
public class ChatController {
	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@GetMapping("/chat")
	public String index(Model model) {
		String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("currentUser", currentUsername);
		return "customer/chat";
	}	

	@MessageMapping("/private-message") // /app/private-message
	public void sendPrivateMessage(ChatMessage message) {
		messagingTemplate.convertAndSendToUser(message.getRecipient(), "/queue/messages", message);
	}
}
