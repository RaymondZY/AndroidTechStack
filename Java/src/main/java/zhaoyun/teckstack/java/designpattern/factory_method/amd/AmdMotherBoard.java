package zhaoyun.teckstack.java.designpattern.factory_method.amd;

import zhaoyun.teckstack.java.designpattern.factory_method.Cpu;
import zhaoyun.teckstack.java.designpattern.factory_method.MotherBoard;

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
