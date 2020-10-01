package zhaoyun.teckstack.java.designpattern.adapter;

public class Adapter implements Target {

    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public String currentTime() {
        return "current time is " + adaptee.currentTime();
    }
}
