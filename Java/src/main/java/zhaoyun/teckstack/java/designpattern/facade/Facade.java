package zhaoyun.teckstack.java.designpattern.facade;

public class Facade {

    private SubSystemA subSystemA;
    private SubSystemB subSystemB;
    private SubSystemC subSystemC;

    public Facade() {
        subSystemA = new SubSystemA();
        subSystemB = new SubSystemB();
        subSystemC = new SubSystemC();
    }

    public void methodA() {
        subSystemA.methodA();
    }

    public void methodB() {
        subSystemB.methodB();
    }

    public void methodC() {
        subSystemC.methodC();
    }
}
