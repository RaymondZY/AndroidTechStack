package zhaoyun.teckstack.java.designpattern.abstract_factory.amd;

import zhaoyun.teckstack.java.designpattern.abstract_factory.Cpu;
import zhaoyun.teckstack.java.designpattern.abstract_factory.Factory;
import zhaoyun.teckstack.java.designpattern.abstract_factory.MotherBoard;

public class AmdFactory implements Factory {

    @Override
    public Cpu createCpu() {
        return new AmdCpu();
    }

    @Override
    public MotherBoard createMotherBoard() {
        return new AmdMotherBoard();
    }
}
