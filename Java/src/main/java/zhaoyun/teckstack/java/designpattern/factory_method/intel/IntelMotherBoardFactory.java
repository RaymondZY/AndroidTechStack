package zhaoyun.teckstack.java.designpattern.factory_method.intel;

import zhaoyun.teckstack.java.designpattern.factory_method.MotherBoard;
import zhaoyun.teckstack.java.designpattern.factory_method.MotherBoardFactory;

public class IntelMotherBoardFactory implements MotherBoardFactory {

    @Override
    public MotherBoard create() {
        return new IntelMotherBoard();
    }
}
