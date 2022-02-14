package it.unipi.dii.dsmt.therappist.erlangConnection;

import com.ericsson.otp.erlang.*;
import it.unipi.dii.dsmt.therappist.dto.MessageDTO;
import it.unipi.dii.dsmt.therappist.dto.UserDTO;

import java.io.IOException;
import java.util.ArrayList;
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

    public Future addToExecutorAndGetFuture(Callable task){
        return myExecutor.submit(task);
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

    public ArrayList<MessageDTO> initialize(UserDTO user) {
        prepareGateway(user.getUsername());
        Callable<ArrayList<MessageDTO>> toRunLogin = new LogTask(user);
        return (ArrayList<MessageDTO>)addToExecutor(toRunLogin);
    }

    private class LogTask implements Callable<ArrayList<MessageDTO>> {
        private UserDTO me;
        private final OtpMbox mbox;

        LogTask(UserDTO u){
            me=u;
            mbox = userNode.createMbox();
        }

        @Override
        public ArrayList<MessageDTO> call() throws Exception {
            OtpErlangAtom log = new OtpErlangAtom("init");
            OtpErlangPid pid = mbox.self();
            OtpErlangString username = new OtpErlangString(me.getUsername());
            OtpErlangString myNodeName = new OtpErlangString(myName);
            OtpErlangTuple tuple = new OtpErlangTuple(new OtpErlangObject[]{receiveMessagesMailbox.self(), username, myNodeName});
            OtpErlangTuple reqMessage = new OtpErlangTuple(new OtpErlangObject[]{pid, log, tuple});
            mbox.send(serverRegisteredName, serverNodeName, reqMessage);

//            OtpErlangAtom response = (OtpErlangAtom) mbox.receive();
//            return response.booleanValue();
        }
    }
}
