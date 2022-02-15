package it.unipi.dii.dsmt.therappist.event;

import it.unipi.dii.dsmt.therappist.dto.MessageDTO;
import org.springframework.context.ApplicationEvent;

public class MessageEvent extends ApplicationEvent {

    private MessageDTO newMessage;

    public MessageEvent(Object source, MessageDTO newMessage) {
        super(source);
        this.newMessage = newMessage;
    }

    public MessageDTO getNewMessage() {
        return this.newMessage;
    }
}
