package zhaoyun.teckstack.java.designpattern.decorate;

public class Decorator implements Component {

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        System.out.println("Before operation");
        component.operation();
    }
}
