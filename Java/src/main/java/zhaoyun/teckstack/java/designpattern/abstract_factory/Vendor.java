package zhaoyun.teckstack.java.designpattern.abstract_factory;

import zhaoyun.teckstack.java.designpattern.abstract_factory.amd.AmdFactory;
import zhaoyun.teckstack.java.designpattern.abstract_factory.intel.IntelFactory;

public class Vendor {

    public enum Type {
        INTEL,
        AMD
    }

    public void provide(Type type) {
        Factory factory;
        switch (type) {
            case INTEL:
                factory = new IntelFactory();
                factory.createMotherBoard().install(factory.createCpu());
                break;

            case AMD:
                factory = new AmdFactory();
                factory.createMotherBoard().install(factory.createCpu());
                break;
        }
    }
}
