package zhaoyun.teckstack.java.designpattern.factory_method.amd;

import zhaoyun.teckstack.java.designpattern.factory_method.Cpu;
import zhaoyun.teckstack.java.designpattern.factory_method.CpuFactory;

public class AmdCpuFactory implements CpuFactory {

    @Override
    public Cpu create() {
        return new AmdCpu();
    }
}
