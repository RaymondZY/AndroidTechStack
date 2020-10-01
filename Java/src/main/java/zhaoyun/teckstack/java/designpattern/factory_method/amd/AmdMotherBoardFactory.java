package zhaoyun.teckstack.java.designpattern.factory_method.amd;

import zhaoyun.teckstack.java.designpattern.factory_method.MotherBoard;
import zhaoyun.teckstack.java.designpattern.factory_method.MotherBoardFactory;

public class AmdMotherBoardFactory implements MotherBoardFactory {

    @Override
    public MotherBoard create() {
        return new AmdMotherBoard();
    }
}
