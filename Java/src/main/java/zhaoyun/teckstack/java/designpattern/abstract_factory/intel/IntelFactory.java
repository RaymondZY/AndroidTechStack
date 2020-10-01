package zhaoyun.teckstack.java.designpattern.abstract_factory.intel;

import zhaoyun.teckstack.java.designpattern.abstract_factory.Cpu;
import zhaoyun.teckstack.java.designpattern.abstract_factory.Factory;
import zhaoyun.teckstack.java.designpattern.abstract_factory.MotherBoard;

public class IntelFactory implements Factory {

    @Override
    public Cpu createCpu() {
        return new IntelCpu();
    }

    @Override
    public MotherBoard createMotherBoard() {
        return new IntelMotherBoard();
    }
}
