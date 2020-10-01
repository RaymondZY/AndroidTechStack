package zhaoyun.teckstack.java.designpattern.decorate;

public class Client {

    public static void main(String[] args) {
        Component concreteComponent = new ConcreteComponent();
        Decorator decorator = new Decorator(concreteComponent);
        decorator.operation();
    }
}
