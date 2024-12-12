package com.starshop.models;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.starshop.entities.User;

import lombok.*;

@Data
@AllArgsConstructor
public class ChatMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	private String sender;
	
	private String recipient;
	
	private String content;
	
	private LocalDateTime timestamp;
}
