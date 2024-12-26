package com.hjac.syncho.chat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JoinedChatRoom extends ChatRoom {
	String joinedAt;
	String role;
	int alarm;
	int favorite;
}
