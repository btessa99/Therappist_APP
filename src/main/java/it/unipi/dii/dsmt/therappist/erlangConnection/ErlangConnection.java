package it.unipi.dii.dsmt.therappist.erlangConnection;

import com.ericsson.otp.erlang.*;
import it.unipi.dii.dsmt.therappist.dto.MessageDTO;
import it.unipi.dii.dsmt.therappist.dto.UserDTO;
import it.unipi.dii.dsmt.therappist.event.MessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.*;

public class ErlangConnection {
    private final String serverNodeName = "therappist_server@localhost"; //configuration parameter
    private String serverRegisteredName = "therappist_server"; //configuration parameter
    private String applicationCookie = "therappist"; //configuration parameter
    private String userNodeName = "therappist_spring@localhost";
    private String myName;
    private OtpMbox receiveMessagesMailbox;
    private OtpNode userNode;
    private ExecutorService myExecutor;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    protected void prepareGateway(String username){
        try {
            myName = username + "_" + userNodeName;
            if (userNode != null)
                userNode.close();
            userNode = new OtpNode(myName, applicationCookie);
            if(myExecutor!=null)
                myExecutor.shutdown();
            myExecutor= Executors.newCachedThreadPool();
            if(receiveMessagesMailbox!=null)
                receiveMessagesMailbox.close();
            receiveMessagesMailbox = userNode.createMbox(username + "_mailbox");
            receiveMessagesMailbox.registerName(receiveMessagesMailbox.getName());
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public Object addToExecutor(Callable task){
        try {
            return myExecutor.submit(task).get();
        } catch (InterruptedException e) {
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void stopExecutor(){
        myName=null;
        if(receiveMessagesMailbox!=null){
            receiveMessagesMailbox.close();
            receiveMessagesMailbox=null;
        }
        if(userNode!=null){
            userNode.close();
            userNode=null;
        }

        myExecutor.shutdown();
        try {
            if (!myExecutor.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                myExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            myExecutor.shutdownNow();
        }
    }

    public boolean sendMessage(MessageDTO m, int timeout) {
        Callable<Boolean> toRun = new SendTask(m, timeout);
        return (boolean) (Boolean)addToExecutor(toRun);
    }

    private class SendTask implements Callable<Boolean> {
        private MessageDTO toSend;
        private final OtpMbox mbox;
        private final int timeout;

        SendTask(MessageDTO m, int timeout){
            toSend=m;
            mbox = userNode.createMbox();
            this.timeout = timeout;
        }

        @Override
        public Boolean call() throws Exception {
            OtpErlangAtom message = new OtpErlangAtom("message");
            OtpErlangLong timestamp = new OtpErlangLong(toSend.getTimestamp());
            OtpErlangString sender = new OtpErlangString(toSend.getSender());
            OtpErlangString receiver = new OtpErlangString(toSend.getReceiver());
            OtpErlangString text = new OtpErlangString(toSend.getText());
            OtpErlangTuple messageInfo= new OtpErlangTuple(new OtpErlangObject[]{timestamp, sender, receiver, text});
            OtpErlangTuple reqMsg = new OtpErlangTuple(new OtpErlangObject[] {mbox.self(), message, messageInfo});
            mbox.send(serverRegisteredName, serverNodeName, reqMsg);

            OtpErlangObject ack = mbox.receive(timeout);
            if(ack==null) //timeout expired
                return false;

            OtpErlangAtom ackAtom = (OtpErlangAtom) ack;
            return ackAtom.atomValue().equals("ack");
        }
    }

    public ArrayList<MessageDTO> initialize(UserDTO user, String chatter) {
        prepareGateway(user.getUsername());
        Callable<ArrayList<MessageDTO>> toRunLogin = new LogTask(user, chatter);
        return (ArrayList<MessageDTO>)addToExecutor(toRunLogin);
    }

    private class LogTask implements Callable<ArrayList<MessageDTO>> {
        private UserDTO me;
        private final OtpMbox mbox;
        private String chatter;

        LogTask(UserDTO u, String chatter){
            me=u;
            mbox = userNode.createMbox();
            this.chatter = chatter;
        }

        @Override
        public ArrayList<MessageDTO> call() throws Exception {
            OtpErlangAtom log = new OtpErlangAtom("init");
            OtpErlangPid pid = mbox.self();
            OtpErlangString username = new OtpErlangString(me.getUsername());
            OtpErlangString chatterErl = new OtpErlangString(chatter);
            OtpErlangString myNodeName = new OtpErlangString(myName);
            OtpErlangTuple tuple = new OtpErlangTuple(new OtpErlangObject[]{receiveMessagesMailbox.self(), username, chatterErl, myNodeName});
            OtpErlangTuple reqMessage = new OtpErlangTuple(new OtpErlangObject[]{pid, log, tuple});
            mbox.send(serverRegisteredName, serverNodeName, reqMessage);

            OtpErlangList response = (OtpErlangList)mbox.receive();
            ArrayList<MessageDTO> chat = new ArrayList<>();
            for(OtpErlangObject o: response){
                OtpErlangTuple message = (OtpErlangTuple) o;
                String sender = ((OtpErlangString)message.elementAt(0)).stringValue();
                String receiver = ((OtpErlangString)message.elementAt(1)).stringValue();
                String text = ((OtpErlangString)message.elementAt(2)).stringValue();
                long timestamp = ((OtpErlangLong)message.elementAt(3)).longValue();
                MessageDTO toInsert = new MessageDTO(sender, receiver, text, timestamp);
                chat.add(toInsert);
            }
            if(chat.size() > 0)
                chat.sort(new Comparator<MessageDTO>() {
                    @Override
                    public int compare(MessageDTO m1, MessageDTO m2) {
                        return (int)(m1.getTimestamp() - m2.getTimestamp());
                    }
                });
            return chat;
        }
    }

    public boolean logout(UserDTO user){
        boolean result = (Boolean)addToExecutor(new LogoutTask(user));
        stopExecutor();
        return result;
    }

    private class LogoutTask implements Callable<Boolean>{
        private UserDTO me;
        private final OtpMbox mbox;

        LogoutTask(UserDTO u){
            me=u;
            mbox = userNode.createMbox();
        }

        @Override
        public Boolean call() throws Exception {
            OtpErlangPid pid = mbox.self();
            OtpErlangAtom logout = new OtpErlangAtom("logout");

            OtpErlangString username = new OtpErlangString(me.getUsername());
            OtpErlangTuple reqMessage = new OtpErlangTuple(new OtpErlangObject[]{pid, logout, username});
            mbox.send(serverRegisteredName, serverNodeName, reqMessage);

            OtpErlangAtom response = (OtpErlangAtom) mbox.receive();
            return response.booleanValue();
        }
    }

    public void receiveMessage() {
        try {
            OtpErlangTuple incomingMessage;
            while(!Thread.currentThread().isInterrupted()){
                incomingMessage = (OtpErlangTuple) receiveMessagesMailbox.receive(5000);
                if(incomingMessage!=null){
                    String sender = ((OtpErlangString)incomingMessage.elementAt(1)).stringValue();
                    String receiver = ((OtpErlangString)incomingMessage.elementAt(2)).stringValue();
                    String text = ((OtpErlangString)incomingMessage.elementAt(3)).stringValue();
                    long timestamp = ((OtpErlangLong)incomingMessage.elementAt(4)).longValue();
                    applicationEventPublisher.publishEvent(new MessageEvent(this, new MessageDTO(sender, receiver, text, timestamp)));
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
