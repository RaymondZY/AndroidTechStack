package zhaoyun.teckstack.java.basic;

import java.util.ArrayList;
import java.util.List;

public class Generics {

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();

        System.out.println(stringList.getClass());  // ArrayList.class
        System.out.println(integerList.getClass()); // ArrayList.class
        System.out.println(stringList.getClass() == integerList.getClass()); // true
    }
}
