package zhaoyun.teckstack.java.designpattern.singleton;

public class Singleton {

    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    private Singleton() {
        System.out.println("constructor Singleton()");
    }

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
    }
}
