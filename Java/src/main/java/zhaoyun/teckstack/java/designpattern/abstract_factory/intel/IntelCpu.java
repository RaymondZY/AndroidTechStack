package zhaoyun.teckstack.java.designpattern.abstract_factory.intel;

import zhaoyun.teckstack.java.designpattern.abstract_factory.Cpu;

public class IntelCpu implements Cpu {

    @Override
    public String getName() {
        return "IntelCpu";
    }
}
