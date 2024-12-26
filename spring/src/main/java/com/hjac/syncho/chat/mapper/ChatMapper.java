package com.hjac.syncho.chat.mapper;

import java.util.List;
import java.util.Map;

import com.hjac.syncho.chat.model.ChatRoom;
import com.hjac.syncho.chat.model.JoinedChatRoom;
import com.hjac.syncho.chat.model.Message;

public interface ChatMapper {
	// 채팅방 CRUD
	public List<ChatRoom> getAllChatRooms();
	public ChatRoom getChatRoomByRoomId(int roomId);
	public void createChatRoom(Map<String, String> params);
	public void updateChatRoom(Map<String, String> params);
	public void deleteChatRoom(int id);
	
	// 유저 - 채팅방
	public void joinChatRoom(Map<String, String> params);
	public void quitChatRoom(Map<String, String> params);
	public String getRole(Map<String, String> params);
	public void updateRole(Map<String, String> params);
	public void updateAlarm(Map<String, String> params);
	public void updateFavorite(Map<String, String> params);
	public List<JoinedChatRoom> getAllJoinedChatRooms(Map<String, String> params);

	// 메세지
	public void createChatting(Map<String, String> params);
	public List<Message> getChattingsByRoomId(int roomId);
	public void deleteChatting(int messageId);
}
