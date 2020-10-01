package zhaoyun.teckstack.java.designpattern.decorate;

public class ConcreteComponent implements Component {

    @Override
    public void operation() {
        System.out.println("concrete operation");
    }
}
