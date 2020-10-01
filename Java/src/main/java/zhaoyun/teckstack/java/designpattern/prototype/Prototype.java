package zhaoyun.teckstack.java.designpattern.prototype;

import java.util.concurrent.TimeUnit;

public class Prototype {

    private String name;

    public void init() {
        timeConsumingOperation();
    }

    private void timeConsumingOperation() {
        System.out.println("timeConsumingOperation ..");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Prototype clone() {
        Prototype copy = new Prototype();
        copy.name = name;
        return copy;
    }
}
