package com.hjac.syncho.chat.service;

import java.util.List;
import java.util.Map;

import com.hjac.syncho.chat.model.ChatRoom;

public interface ChatService {
	public List<ChatRoom> getAllChatRooms();
	public ChatRoom getChatRoomByRoomId(int roomId);
	public void createChatRoom(Map<String, String> params);
	public void updateChatRoom(Map<String, String> params);
	public void deleteChatRoom(int id);
}
