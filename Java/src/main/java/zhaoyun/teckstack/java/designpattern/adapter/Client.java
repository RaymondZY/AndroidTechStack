package zhaoyun.teckstack.java.designpattern.adapter;

public class Client {

    public static void main(String[] args) {
        System.out.println(new Adapter(new Adaptee()).currentTime());
    }
}
