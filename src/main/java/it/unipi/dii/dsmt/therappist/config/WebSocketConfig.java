package it.unipi.dii.dsmt.therappist.config;


import it.unipi.dii.dsmt.therappist.controller.MessageController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

// path : /chat_page/chat/username
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        registry.addHandler(getHandler(),"/chat-page/chat").setAllowedOrigins("*").withSockJS();
    }

    @Bean
    public WebSocketHandler getHandler(){ return new MessageController();}
}
