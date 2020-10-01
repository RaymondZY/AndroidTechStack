package zhaoyun.teckstack.java.designpattern.composite;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {

    private List<Component> childList;
    private String name;

    public Composite(String name) {
        childList = new ArrayList<>();
        this.name = name;
    }

    @Override
    public void add(Component component) {
        childList.add(component);
    }

    @Override
    public void remove(Component component) {
        childList.remove(component);
    }

    @Override
    public Component getChildAt(int index) {
        return childList.get(index);
    }

    @Override
    public void operation() {
        System.out.println(name);
        for (Component component : childList) {
            component.operation();
        }
    }
}
