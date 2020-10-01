package zhaoyun.teckstack.java.designpattern.bridge.abstraction;

import zhaoyun.teckstack.java.designpattern.bridge.implementor.Messenger;

public class UrgentMessage extends Message {

    public UrgentMessage(Messenger messenger) {
        super(messenger);
    }

    @Override
    public void sendToUser(String message, String user) {
        System.out.println("This is urgent");
        super.sendToUser(message, user);
    }
}
