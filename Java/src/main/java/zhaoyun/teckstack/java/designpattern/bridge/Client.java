package zhaoyun.teckstack.java.designpattern.bridge;

import zhaoyun.teckstack.java.designpattern.bridge.abstraction.LongMessage;
import zhaoyun.teckstack.java.designpattern.bridge.abstraction.Message;
import zhaoyun.teckstack.java.designpattern.bridge.abstraction.UrgentMessage;
import zhaoyun.teckstack.java.designpattern.bridge.implementor.EmailMessenger;
import zhaoyun.teckstack.java.designpattern.bridge.implementor.Messenger;
import zhaoyun.teckstack.java.designpattern.bridge.implementor.SmsMessenger;

public class Client {

    public static void main(String[] args) {
        Messenger smsMessenger = new SmsMessenger();
        Messenger emailMessenger = new EmailMessenger();

        Message message1 = new LongMessage(emailMessenger);
        message1.sendToUser("veryyyyyyyy longggggggggggg", "15801997092");

        Message message2 = new UrgentMessage(smsMessenger);
        message2.sendToUser("<html>hello</html>", "zhaoyun1224@gmai.com");
    }
}
