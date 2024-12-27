package com.hjac.syncho.chat.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hjac.syncho.chat.mapper.ChatMapper;
import com.hjac.syncho.chat.model.ChatRoom;
import com.hjac.syncho.chat.model.JoinedChatRoom;
import com.hjac.syncho.chat.model.Message;

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
	
	@Override
	public void joinChatRoom(Map<String, String> params) {
		chatMapper.joinChatRoom(params);
	}
	
	@Override
	public void quitChatRoom(Map<String, String> params) {
		chatMapper.quitChatRoom(params);
	}
	
	@Override
	public String getRole(Map<String, String> params) {
		return chatMapper.getRole(params);
	}
	
	@Override
	public void updateRole(Map<String, String> params) {
		chatMapper.updateRole(params);
	}
	
	@Override
	public void updateAlarm(Map<String, String> params) {
		chatMapper.updateAlarm(params);
	}
	
	@Override
	public void updateFavorite(Map<String, String> params) {
		chatMapper.updateFavorite(params);
	}
	
	@Override
	public List<JoinedChatRoom> getAllJoinedChatRooms(Map<String, String> params) {
		return chatMapper.getAllJoinedChatRooms(params);
	}
	
	@Override
	public void createChatting(Map<String, String> params) {
		chatMapper.createChatting(params);
	}
	
	@Override
	public List<Message> getChattingsByRoomId(int roomId) {
		return chatMapper.getChattingsByRoomId(roomId);
	}
	
	@Override
	public void deleteChatting(int messageId) {
		chatMapper.deleteChatting(messageId);
	}
		
}
