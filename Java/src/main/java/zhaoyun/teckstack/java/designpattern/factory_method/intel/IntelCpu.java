package zhaoyun.teckstack.java.designpattern.factory_method.intel;

import zhaoyun.teckstack.java.designpattern.factory_method.Cpu;

public class IntelCpu implements Cpu {

    @Override
    public String getName() {
        return "IntelCpu";
    }
}
