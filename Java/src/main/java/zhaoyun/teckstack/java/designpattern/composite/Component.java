package zhaoyun.teckstack.java.designpattern.composite;

public interface Component {

    void add(Component component);

    void remove(Component component);

    Component getChildAt(int index);

    void operation();
}
