package com.hjac.syncho.chat.service;

import java.util.List;
import java.util.Map;

import com.hjac.syncho.chat.model.ChatRoom;
import com.hjac.syncho.chat.model.JoinedChatRoom;

public interface ChatService {
	public List<ChatRoom> getAllChatRooms();
	public ChatRoom getChatRoomByRoomId(int roomId);
	public void createChatRoom(Map<String, String> params);
	public void updateChatRoom(Map<String, String> params);
	public void deleteChatRoom(int id);
	
	public void joinChatRoom(Map<String, String> params);
	public void quitChatRoom(Map<String, String> params);
	public String getRole(Map<String, String> params);
	public void updateRole(Map<String, String> params);
	public void updateAlarm(Map<String, String> params);
	public void updateFavorite(Map<String, String> params);
	public List<JoinedChatRoom> getAllJoinedChatRooms(Map<String, String> params);
}
