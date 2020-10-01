package zhaoyun.teckstack.java.designpattern.abstract_factory.amd;

import zhaoyun.teckstack.java.designpattern.abstract_factory.Cpu;

public class AmdCpu implements Cpu {

    @Override
    public String getName() {
        return "AmdCpu";
    }
}
