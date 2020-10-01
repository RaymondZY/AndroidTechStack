package zhaoyun.teckstack.java.designpattern.factory_method;

public class Client {

    public static void main(String[] args) {
        new Vendor().provide(Vendor.CpuType.INTEL, Vendor.MotherBoardType.INTEL);
        new Vendor().provide(Vendor.CpuType.INTEL, Vendor.MotherBoardType.AMD);
    }
}
