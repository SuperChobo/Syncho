package com.hjac.syncho.chat.controller;

import java.util.HashMap;
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
import com.hjac.syncho.chat.model.JoinedChatRoom;
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
		params.put("roomId", String.valueOf(id));
		try {
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
	
	@PostMapping("/join/{roomId}")
	public ResponseEntity<?> joinChatRoom(@RequestParam Map<String, String> params, @PathVariable int roomId) {
		params.put("roomId", String.valueOf(roomId));
		try {
			chatService.joinChatRoom(params);
			return ResponseEntity.ok("성공적으로 가입 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("잘못된 요청입니다.");
		}
	}
	
	@PostMapping("/quit/{roomId}")
	public ResponseEntity<?> quitChatRoom(@RequestParam Map<String, String> params, @PathVariable int roomId) {
		params.put("roomId", String.valueOf(roomId));
		try {
			chatService.quitChatRoom(params);
			return ResponseEntity.ok("성공적으로 퇴장하였습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("잘못된 요청입니다.");
		}
	}
	
	@GetMapping("/role/{roomId}")
	public ResponseEntity<?> getRole(@RequestParam Map<String, String> params, @PathVariable int roomId) {
		params.put("roomId", String.valueOf(roomId));
		try {
			Map<String, String> output = new HashMap<>();
			output.put("role", chatService.getRole(params));
			return ResponseEntity.ok(output);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("잘못된 요청입니다.");
		}
	}
	
	@PostMapping("/role/{roomId}")
	public ResponseEntity<?> updateRole(@RequestParam Map<String, String> params, @PathVariable int roomId) {
		params.put("roomId", String.valueOf(roomId));
		try {
			chatService.updateRole(params);
			return ResponseEntity.ok("성공적으로 업데이트 하였습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("잘못된 요청입니다.");
		}
	}
	
	@GetMapping("/join")
	public ResponseEntity<?> getAllJoinedChatRooms(@RequestParam Map<String, String> params) {
		try {
			List<JoinedChatRoom> rooms = chatService.getAllJoinedChatRooms(params);
			return ResponseEntity.ok(rooms);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("잘못된 요청입니다.");
		}
	}
	
	
	@PostMapping("/alarm/{roomId}/{value}")
	public ResponseEntity<?> updateAlarm(@RequestParam Map<String, String> params, @PathVariable int roomId, @PathVariable String value) {
		params.put("roomId", String.valueOf(roomId));
		params.put("value", value);
		
		try {
			chatService.updateAlarm(params);
			return ResponseEntity.ok("성공적으로 업데이트 하였습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("잘못된 요청입니다.");
		}
	}
	
	
	@PostMapping("/favorite/{roomId}/{value}")
	public ResponseEntity<?> updateFavorite(@RequestParam Map<String, String> params, @PathVariable int roomId, @PathVariable String value) {
		params.put("roomId", String.valueOf(roomId));
		params.put("value", value);
		
		try {
			chatService.updateFavorite(params);
			return ResponseEntity.ok("성공적으로 업데이트 하였습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("잘못된 요청입니다.");
		}
	}
}
