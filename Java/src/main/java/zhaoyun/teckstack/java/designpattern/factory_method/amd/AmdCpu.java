package zhaoyun.teckstack.java.designpattern.factory_method.amd;

import zhaoyun.teckstack.java.designpattern.factory_method.Cpu;

public class AmdCpu implements Cpu {

    @Override
    public String getName() {
        return "AmdCpu";
    }
}
