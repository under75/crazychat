package ru.sartfoms.crazychat.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ChatUser {
	private String userName;

	public ChatUser(@Value("${chatuser.name}") String userName, @Value("${user.name}") String anonymous) {
		if (userName.trim().isEmpty())
			this.userName = anonymous;
		else 
			this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
