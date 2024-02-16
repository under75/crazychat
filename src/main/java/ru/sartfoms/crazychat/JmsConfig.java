package ru.sartfoms.crazychat;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import jakarta.jms.ConnectionFactory;
import ru.sartfoms.crazychat.destination.ChatRoom;

@Configuration
public class JmsConfig {

    @Bean
    public JmsListenerContainerFactory<?> topicConnectionFactory(@Qualifier("jmsConnectionFactory") ConnectionFactory connectionFactory,
                                                                 DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }
    
    @Bean
    public ChatRoom room() {
        return new ChatRoom();
    }
}