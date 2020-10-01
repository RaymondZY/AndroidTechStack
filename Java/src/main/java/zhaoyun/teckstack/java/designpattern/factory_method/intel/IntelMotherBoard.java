package zhaoyun.teckstack.java.designpattern.factory_method.intel;

import zhaoyun.teckstack.java.designpattern.factory_method.Cpu;
import zhaoyun.teckstack.java.designpattern.factory_method.MotherBoard;

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
