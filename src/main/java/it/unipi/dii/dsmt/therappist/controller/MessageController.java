package it.unipi.dii.dsmt.therappist.controller;

import com.google.gson.Gson;
import it.unipi.dii.dsmt.therappist.Utils.ConnectionUtils;
import it.unipi.dii.dsmt.therappist.dto.MessageDTO;
import it.unipi.dii.dsmt.therappist.event.MessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class MessageController extends TextWebSocketHandler implements ApplicationListener<ApplicationEvent> {

    private Gson gson = new Gson();
    private static ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, String> sessionUsers = new ConcurrentHashMap<>();

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;


    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {

        System.out.println( "New message " + message.getPayload());

        MessageDTO tosend = gson.fromJson(message.getPayload(),MessageDTO.class);
        if(tosend.getText().equals("My username is " + tosend.getSender())){
            sessionUsers.put(tosend.getSender(), session.getId());
            return;
        }
//
        ConnectionUtils.getConnection(tosend.getSender()).sendMessage(tosend);

        applicationEventPublisher.publishEvent(new MessageEvent(this, tosend));

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        System.out.println("Connection established with WebSocket " + session.getId());

            sessions.put( session.getId(), session );

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

        for(String key: sessionUsers.keySet()){
            if(sessionUsers.get(key).equals(session.getId())) {
                sessionUsers.remove(key);
                System.out.println("Session with " + key + " correctly closed");
                break;
            }
        }
        sessions.remove( session.getId() );

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println( exception.getMessage() );
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        if(event instanceof MessageEvent){
            MessageDTO message = ((MessageEvent) event).getNewMessage();

            String messageText = gson.toJson(message);
            try {
                if(sessionUsers.containsKey(message.getReceiver())){
                    WebSocketSession session = sessions.get(sessionUsers.get(message.getReceiver()));
                    session.sendMessage(new TextMessage(messageText));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        }



}
