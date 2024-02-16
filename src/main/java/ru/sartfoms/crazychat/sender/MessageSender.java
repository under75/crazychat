package ru.sartfoms.crazychat.sender;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import ru.sartfoms.crazychat.destination.ChatRoom;
import ru.sartfoms.crazychat.user.ChatUser;

@Component
public class MessageSender {
	private final JmsTemplate jmsTemplate;
	private final ChatUser user;
	private final ChatRoom room;

	public MessageSender(JmsTemplate jmsTemplate, ChatUser user, ChatRoom room) {
		this.jmsTemplate = jmsTemplate;
		this.user = user;
		this.room = room;
	}

	public void publishTopic(String message) {
		jmsTemplate.setPubSubDomain(true);
		message = user.getUserName() + " -> " + message;
		jmsTemplate.convertAndSend(room.getTopicName(), message);
	}

	public void reportLeavingChat() {
		jmsTemplate.setPubSubDomain(true);
		String message = user.getUserName() + " покинул чат";
		jmsTemplate.convertAndSend(room.getTopicName(), message);
	}

	public void reportEntryToChat() {
		jmsTemplate.setPubSubDomain(true);
		String message = user.getUserName() + " вошел в чат";
		this.jmsTemplate.convertAndSend(room.getTopicName(), message);
	}

}