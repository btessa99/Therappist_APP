package it.unipi.dii.dsmt.therappist.component;

import org.springframework.stereotype.Component;
import java.util.concurrent.CountDownLatch;

@Component
public class MessageReciever {
    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message){
        System.out.println(message);
        latch.countDown();
    }

    public CountDownLatch getLatch(){
        return latch;
    }

}
