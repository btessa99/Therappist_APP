package it.unipi.dii.dsmt.therappist.controller;

import com.google.gson.Gson;
import it.unipi.dii.dsmt.therappist.Utils.ConnectionUtils;
import it.unipi.dii.dsmt.therappist.dto.MessageDTO;
import it.unipi.dii.dsmt.therappist.event.MessageEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class MessageController extends TextWebSocketHandler implements ApplicationListener<ApplicationEvent> {

    private Gson gson = new Gson();
    private static Map<String, WebSocketSession> sessions = new HashMap<>();


    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {

            System.out.println( "New message " + message.getPayload());

            MessageDTO tosend = gson.fromJson(message.getPayload(),MessageDTO.class);

            ConnectionUtils.getConnection(tosend.getSender()).sendMessage(tosend);

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        synchronized (sessions) {
            sessions.put( session.getId(), session );
            try {
                session.sendMessage( new TextMessage( "Session started" ) );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

        synchronized (sessions) {
            sessions.remove( session.getId() );
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println( exception.getMessage() );
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        if(event instanceof MessageEvent){

            String message = gson.toJson(((MessageEvent) event).getNewMessage());
            try {
                if ( sessions != null && !sessions.isEmpty() ) {
                    synchronized (sessions) {
                        for ( WebSocketSession session : sessions.values() ) {
                            if ( session.isOpen() ) {
                                session.sendMessage( new TextMessage( message ) );
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        }



}
