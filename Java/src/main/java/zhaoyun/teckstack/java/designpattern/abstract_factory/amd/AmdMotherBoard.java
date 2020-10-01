package zhaoyun.teckstack.java.designpattern.abstract_factory.amd;

import zhaoyun.teckstack.java.designpattern.abstract_factory.Cpu;
import zhaoyun.teckstack.java.designpattern.abstract_factory.MotherBoard;

public class AmdMotherBoard implements MotherBoard {

    @Override
    public String getName() {
        return "AmdMotherBoard";
    }

    @Override
    public void install(Cpu cpu) {
        if (!(cpu instanceof AmdCpu)) {
            System.out.println("This won't work");
        }
        System.out.println(getName() + " : " + cpu.getName() + " installed");
    }
}
