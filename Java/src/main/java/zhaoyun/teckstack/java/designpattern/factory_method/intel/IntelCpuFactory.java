package zhaoyun.teckstack.java.designpattern.factory_method.intel;

import zhaoyun.teckstack.java.designpattern.factory_method.Cpu;
import zhaoyun.teckstack.java.designpattern.factory_method.CpuFactory;

public class IntelCpuFactory implements CpuFactory {

    @Override
    public Cpu create() {
        return new IntelCpu();
    }
}
