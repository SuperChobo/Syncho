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

import com.hjac.syncho.chat.model.ChatRoom;
import com.hjac.syncho.chat.service.ChatService;
import com.hjac.syncho.chat.service.ChatServiceImpl;

@RestController
@RequestMapping("/chatroom")
public class ChatRoomController {
	private ChatService chatService;	
	private ChatRoomController(ChatServiceImpl chatService) {
		this.chatService = chatService;
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAllChatRooms() {
		try {
			return ResponseEntity.ok(chatService.getAllChatRooms());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body("내부 오류가 발생하였습니다.");
		}
	}
	
	@GetMapping("/room/{id}")
	public ResponseEntity<?> getChatRoomByRoomId(@PathVariable int id) {
		try {
			ChatRoom chatRoom = chatService.getChatRoomByRoomId(id);
			if(chatRoom != null) {
				return ResponseEntity.ok(chatRoom);
			} else {
				return ResponseEntity.noContent().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body("내부 오류가 발생하였습니다.");
		}
	}
	
//	@GetMapping("/user/{id}")
//	public ResponseEntity<?> getChatRoomByUserId(@PathVariable int id) {
//		return null;
//	}
	
	@PostMapping("/")
	public ResponseEntity<?> createChatRoom(@RequestParam Map<String, String> params) {
		try {
			chatService.createChatRoom(params);
			return ResponseEntity.ok("성공적으로 추가되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("잘못된 요청입니다.");
		}
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<?> updateChatRoom(@RequestParam Map<String, String> params, @PathVariable int id) {
		try {
			params.put("roomId", String.valueOf(id));
			chatService.updateChatRoom(params);
			return ResponseEntity.ok("성공적으로 수정되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("잘못된 요청입니다.");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteChatRoom(@PathVariable int id) {
		try {
			chatService.deleteChatRoom(id);
			return ResponseEntity.ok("성공적으로 제거되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("잘못된 요청입니다.");
		}
	}
}
