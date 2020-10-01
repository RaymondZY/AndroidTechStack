package zhaoyun.teckstack.java.designpattern.abstract_factory;

public class Client {

    public static void main(String[] args) {
        new Vendor().provide(Vendor.Type.INTEL);
        new Vendor().provide(Vendor.Type.AMD);
    }
}
