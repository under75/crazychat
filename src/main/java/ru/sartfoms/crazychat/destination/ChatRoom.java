package ru.sartfoms.crazychat.destination;

import org.springframework.beans.factory.annotation.Value;

public class ChatRoom {
	@Value("${topic.name}")
	private String topicName;

	public String getTopicName() {
		return topicName.trim().isEmpty() ? "CrazyChat" : topicName;
	}

}
