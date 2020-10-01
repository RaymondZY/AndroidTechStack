package zhaoyun.teckstack.java.designpattern.abstract_factory.intel;

import zhaoyun.teckstack.java.designpattern.abstract_factory.Cpu;
import zhaoyun.teckstack.java.designpattern.abstract_factory.MotherBoard;

public class IntelMotherBoard implements MotherBoard {

    @Override
    public String getName() {
        return "IntelMotherBoard";
    }

    @Override
    public void install(Cpu cpu) {
        if (!(cpu instanceof IntelCpu)) {
            System.out.println("This won't work");
        }
        System.out.println(getName() + " : " + cpu.getName() + " installed");
    }
}
