package zhaoyun.teckstack.java.designpattern.bridge.implementor;

public class EmailMessenger implements Messenger {

    @Override
    public void sendToUser(String message, String user) {
        System.out.println("Send email " + message + " to " + user);
    }
}
