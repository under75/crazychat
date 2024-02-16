package ru.sartfoms.crazychat;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

import ru.sartfoms.crazychat.destination.ChatRoom;
import ru.sartfoms.crazychat.sender.MessageSender;

@SpringBootApplication
@EnableJms
public class CrazychatApplication {
	private final MessageSender messageSender;
	private final ChatRoom room;

	public CrazychatApplication(MessageSender messageSender, ChatRoom room) {
		this.messageSender = messageSender;
		this.room = room;
	}

	public static void main(String[] args) {
		SpringApplication.run(CrazychatApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return (args) -> {
			Thread printingHook = new Thread(() -> messageSender.reportLeavingChat());
			Runtime.getRuntime().addShutdownHook(printingHook);
			try {
				new ProcessBuilder("cmd", "/c", "chcp 866").inheritIO().start().waitFor();
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				new ProcessBuilder("cmd", "/c", "Color 8E").inheritIO().start().waitFor();

				System.out.println("Добро пожаловать в комнату " + room.getTopicName());

				messageSender.reportEntryToChat();
				while (true) {
					BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "Cp866"));
					String msg = reader.readLine();
					if (!msg.isBlank())
						messageSender.publishTopic(msg);
				}
			} catch (Exception e) {
				System.exit(0);
			}
		};

	}

}
