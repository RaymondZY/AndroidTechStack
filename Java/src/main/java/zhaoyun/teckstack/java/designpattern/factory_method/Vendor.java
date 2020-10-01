package zhaoyun.teckstack.java.designpattern.factory_method;

import zhaoyun.teckstack.java.designpattern.factory_method.amd.AmdCpuFactory;
import zhaoyun.teckstack.java.designpattern.factory_method.amd.AmdMotherBoardFactory;
import zhaoyun.teckstack.java.designpattern.factory_method.intel.IntelCpuFactory;
import zhaoyun.teckstack.java.designpattern.factory_method.intel.IntelMotherBoardFactory;

public class Vendor {

    public enum CpuType {
        INTEL,
        AMD
    }

    public enum MotherBoardType {
        INTEL,
        AMD
    }

    public void provide(CpuType cpuType, MotherBoardType motherBoardType) {
        Cpu cpu = null;
        switch (cpuType) {
            case INTEL:
                cpu = new IntelCpuFactory().create();
                break;
            case AMD:
                cpu = new AmdCpuFactory().create();
                break;
        }

        MotherBoard motherBoard = null;
        switch (motherBoardType) {
            case INTEL:
                motherBoard = new IntelMotherBoardFactory().create();
                break;
            case AMD:
                motherBoard = new AmdMotherBoardFactory().create();
                break;
        }

        motherBoard.install(cpu);
    }
}
