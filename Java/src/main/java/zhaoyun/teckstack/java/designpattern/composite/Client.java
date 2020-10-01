package zhaoyun.teckstack.java.designpattern.composite;

public class Client {

    public static void main(String[] args) {
        Component company = new Composite("公司");
        Component department1 = new Composite("-部门1");
        Component department2 = new Composite("-部门2");
        company.add(department1);
        company.add(department2);
        Component groupA = new Leaf("--组A");
        Component groupB = new Leaf("--组B");
        Component groupC = new Leaf("--组C");
        department1.add(groupA);
        department1.add(groupB);
        department2.add(groupC);
        company.operation();
    }
}
