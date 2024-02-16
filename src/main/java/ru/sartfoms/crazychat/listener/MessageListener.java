package ru.sartfoms.crazychat.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import ru.sartfoms.crazychat.sound.SoundPlayer;
import ru.sartfoms.crazychat.user.ChatUser;

@Component
public class MessageListener {
	private final SoundPlayer soundPlayer;
	private final ChatUser user;

	public MessageListener(SoundPlayer soundPlayer, ChatUser user) {
		this.soundPlayer = soundPlayer;
		this.user = user;
	}

	@Value("${soundplayer.enabled}")
	Boolean soundPlayerEnabled;

	@JmsListener(destination = "#{@room.topicName}", containerFactory = "topicConnectionFactory")
	public void consumeTopicOne(String message) {
		System.out.println(message);
		if (!user.getUserName().equals(message.split(" -> ")[0].trim())) {
			if (soundPlayerEnabled)
				soundPlayer.play();
		}
	}

}
