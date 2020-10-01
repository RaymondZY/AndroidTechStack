package zhaoyun.teckstack.java.designpattern.bridge.abstraction;

import zhaoyun.teckstack.java.designpattern.bridge.implementor.Messenger;

public class LongMessage extends Message {

    public LongMessage(Messenger messenger) {
        super(messenger);
    }

    @Override
    public void sendToUser(String message, String user) {
        System.out.println("This is very long");
        super.sendToUser(message, user);
    }
}
