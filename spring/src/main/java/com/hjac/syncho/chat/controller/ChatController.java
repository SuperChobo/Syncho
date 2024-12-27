package com.hjac.syncho.chat.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hjac.syncho.chat.model.Message;
import com.hjac.syncho.chat.service.ChatService;
import com.hjac.syncho.chat.service.ChatServiceImpl;

@RestController
@RequestMapping("/chat")
public class ChatController {
	private ChatService chatService;
	private ChatController(ChatServiceImpl chatService) {
		this.chatService = chatService;
	}
	
	@PostMapping("/room/{roomId}")
	public ResponseEntity<?> createChattings(@RequestParam Map<String, String> params, @PathVariable int roomId) {
		params.put("roomId", String.valueOf(roomId));
		try {
			chatService.createChatting(params);
			return ResponseEntity.ok("성공적으로 메시지를 보냈습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("잘못된 요청입니다.");
		}
	}
	
	@GetMapping("/room/{id}")
	public ResponseEntity<?> getChattingsByRoomId(@PathVariable int id) {
		try {
			List<Message> messages = chatService.getChattingsByRoomId(id);
			return ResponseEntity.ok(messages);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("잘못된 요청입니다.");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteChatting(@PathVariable int id) {
		try {
			chatService.deleteChatting(id);
			return ResponseEntity.ok("성공적으로 삭제 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("잘못된 요청입니다.");
		}
	}
}
