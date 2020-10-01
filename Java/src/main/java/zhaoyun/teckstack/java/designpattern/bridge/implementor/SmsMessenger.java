package zhaoyun.teckstack.java.designpattern.bridge.implementor;

public class SmsMessenger implements Messenger {

    @Override
    public void sendToUser(String message, String user) {
        System.out.println("Send sms " + message + " to " + user);
    }
}
