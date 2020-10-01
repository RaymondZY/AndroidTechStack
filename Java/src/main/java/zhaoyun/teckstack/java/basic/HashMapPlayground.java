package zhaoyun.teckstack.java.basic;

import java.util.HashMap;

public class HashMapPlayground {

    void test() {
        HashMap<Object, Object> map = new HashMap<>(99);
        map.put(1, 1);
    }

    public static void main(String[] args) {
        new HashMapPlayground().test();
    }
}
