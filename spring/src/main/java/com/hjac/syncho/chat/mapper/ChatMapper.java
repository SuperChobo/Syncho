package com.hjac.syncho.chat.mapper;

import java.util.List;
import java.util.Map;

import com.hjac.syncho.chat.model.ChatRoom;

public interface ChatMapper {
	public List<ChatRoom> getAllChatRooms();
	public ChatRoom getChatRoomByRoomId(int roomId);
	public void createChatRoom(Map<String, String> params);
	public void updateChatRoom(Map<String, String> params);
	public void deleteChatRoom(int id);
}
