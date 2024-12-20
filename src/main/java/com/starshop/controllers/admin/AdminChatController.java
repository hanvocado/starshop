package com.starshop.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.starshop.models.ChatMessage;

@Controller
public class AdminChatController {
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
		
	@GetMapping("/admin/chat")
	public String index() {
		return "admin/chat";
	}	

	@MessageMapping("/admin/private-message") 
	public void sendPrivateMessage(ChatMessage message) {
		message.setSender("StarShop");
		messagingTemplate.convertAndSendToUser(message.getRecipient(), "/queue/messages", message);
	}
}
