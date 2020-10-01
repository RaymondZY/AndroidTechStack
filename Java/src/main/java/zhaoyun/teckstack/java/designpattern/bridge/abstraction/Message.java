package zhaoyun.teckstack.java.designpattern.bridge.abstraction;

import zhaoyun.teckstack.java.designpattern.bridge.implementor.Messenger;

public class Message {

    private Messenger messenger;

    public Message(Messenger messenger) {
        this.messenger = messenger;
    }

    public void sendToUser(String message, String user) {
        messenger.sendToUser(message, user);
    }
}
