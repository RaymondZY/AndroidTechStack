package zhaoyun.teckstack.java.designpattern.composite;

public class Leaf implements Component {

    private String name;

    public Leaf(String name) {
        this.name = name;
    }

    @Override
    public void add(Component component) {
    }

    @Override
    public void remove(Component component) {
    }

    @Override
    public Component getChildAt(int index) {
        return null;
    }

    @Override
    public void operation() {
        System.out.println(name);
    }
}
