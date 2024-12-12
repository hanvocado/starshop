package com.starshop.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.starshop.models.ChatMessage;
import com.starshop.services.ChatQueue;

@Controller
public class ChatController {
	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@Autowired
	private ChatQueue chatQueue;

	@GetMapping("/chat")
	public String index(Model model) {
		String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();

		model.addAttribute("currentUser", currentUsername);
		return "chat";
	}	

	@MessageMapping("/private-message") // /app/private-message
	public void sendPrivateMessage(ChatMessage message) {
		// Send message to a specific user's queue /user/{username}/queue/messages
		messagingTemplate.convertAndSendToUser(message.getRecipient(), "/queue/messages", message);
	}
}
