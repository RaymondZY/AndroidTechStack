package zhaoyun.teckstack.java.designpattern.prototype;

public class Client {

    public static void main(String[] args) {
        System.out.println("create prototype");
        Prototype prototype = new Prototype();
        prototype.init();
        System.out.println("prototype created " + prototype);

        System.out.println("create copy1");
        Prototype copy1 = prototype.clone();
        System.out.println("copy1 created " + copy1);

        System.out.println("create copy2");
        Prototype copy2 = prototype.clone();
        System.out.println("copy2 created " + copy2);
    }
}
