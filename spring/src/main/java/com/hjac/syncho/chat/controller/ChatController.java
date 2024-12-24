package com.hjac.syncho.chat.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hjac.syncho.chat.service.ChatService;
import com.hjac.syncho.chat.service.ChatServiceImpl;

@RestController
@RequestMapping("/chat")
public class ChatController {
	private ChatService chatService;
	private ChatController(ChatServiceImpl chatService) {
		this.chatService = chatService;
	}
	
	
}
