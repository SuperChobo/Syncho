package com.hjac.syncho.chat.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hjac.syncho.chat.mapper.ChatMapper;
import com.hjac.syncho.chat.model.ChatRoom;

@Service
public class ChatServiceImpl implements ChatService {
	ChatMapper chatMapper;
	
	public ChatServiceImpl(ChatMapper chatMapper) {
		this.chatMapper = chatMapper;
	}
	
	@Override
	public void createChatRoom(Map<String, String> params) {
		chatMapper.createChatRoom(params);
	}
	
	@Override
	public void updateChatRoom(Map<String, String> params) {
		chatMapper.updateChatRoom(params);
	}
	
	@Override
	public List<ChatRoom> getAllChatRooms() {
		System.out.println("hi");
		return chatMapper.getAllChatRooms();
	}
	
	@Override
	public ChatRoom getChatRoomByRoomId(int roomId) {
		return chatMapper.getChatRoomByRoomId(roomId);
	}
	
	@Override
	public void deleteChatRoom(int id) {
		chatMapper.deleteChatRoom(id);
	}
}
